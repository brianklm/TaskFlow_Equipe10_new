# Scénarios rejouables — TaskFlow v1.0.0

Les fichiers de `scenarios/` contiennent les saisies envoyées à l'application. Les fichiers qui portent le même nom dans `references/` contiennent la sortie attendue produite avec le code du tag v1.0.0.

Depuis la racine du dépôt, exécuter :

```bash
bash verification/rejouer.sh
```

Le script compile les sources de `Source/`, exécute chaque scénario dans un dossier de données temporaire isolé, puis compare la sortie à sa référence avec `diff`. Il ne modifie jamais les références. Il faut disposer d'un JDK (Java et javac) et de Bash.

Le scénario `09_sauvegarde_rechargement.txt` contient une ligne spéciale `### RESTART ###` : elle demande au script de fermer puis relancer l'application en conservant le même dossier de données. Cela permet de vérifier que la sauvegarde est bien rechargée.

Les dates utilisées sont fixes afin que les sorties restent reproductibles.
