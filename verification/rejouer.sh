#!/usr/bin/env bash
set -uo pipefail
export LANG=C.UTF-8
export LC_ALL=C.UTF-8

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
SCENARIOS="$ROOT/verification/scenarios"
REFERENCES="$ROOT/verification/references"
TMP="$(mktemp -d)"
trap 'rm -rf "$TMP"' EXIT
BUILD="$TMP/classes"
mkdir -p "$BUILD"

if ! command -v javac >/dev/null 2>&1 || ! command -v java >/dev/null 2>&1; then
  echo "ERREUR : Java (JDK) doit être installé et accessible dans PATH."
  exit 1
fi

if ! find "$ROOT/Source" -name '*.java' -print0 | xargs -0 javac -encoding UTF-8 -d "$BUILD"; then
  echo "ÉCHEC : compilation impossible."
  exit 1
fi

failed=0
count=0
for scenario in "$SCENARIOS"/*.txt; do
  [ -e "$scenario" ] || continue
  name="$(basename "$scenario")"
  reference="$REFERENCES/$name"
  actual="$TMP/$name"
  work="$TMP/work-${name%.txt}"
  mkdir -p "$work"

  if [ ! -f "$reference" ]; then
    echo "[ABSENTE] Référence manquante : $name"
    failed=1
    continue
  fi

  if grep -q '^### RESTART ###$' "$scenario"; then
    sed '/^### RESTART ###$/,$d' "$scenario" > "$TMP/part1.txt"
    sed '1,/^### RESTART ###$/d' "$scenario" > "$TMP/part2.txt"
    if ! (cd "$work" && java -Dfile.encoding=UTF-8 -cp "$BUILD" taskflow.Main < "$TMP/part1.txt") > "$TMP/out1.txt"; then
      echo "[ÉCHEC] Exécution interrompue : $name (avant redémarrage)"; failed=1; continue
    fi
    if ! (cd "$work" && java -Dfile.encoding=UTF-8 -cp "$BUILD" taskflow.Main < "$TMP/part2.txt") > "$TMP/out2.txt"; then
      echo "[ÉCHEC] Exécution interrompue : $name (après redémarrage)"; failed=1; continue
    fi
    cat "$TMP/out1.txt" "$TMP/out2.txt" > "$actual"
  else
    if ! (cd "$work" && java -Dfile.encoding=UTF-8 -cp "$BUILD" taskflow.Main < "$scenario") > "$actual"; then
      echo "[ÉCHEC] Exécution interrompue : $name"; failed=1; continue
    fi
  fi

  count=$((count + 1))
  if diff -u "$reference" "$actual"; then
    echo "[RÉUSSI] $name"
  else
    echo "[ÉCHEC] $name — différence détectée"
    failed=1
  fi
done

echo
echo "$count scénario(s) exécuté(s)."
if [ "$failed" -eq 0 ]; then
  echo "Tous les scénarios correspondent aux références."
else
  echo "Au moins un scénario diffère des références."
  exit 1
fi
