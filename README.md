# TaskFlow — Flux de tâches

Application Java en console pour gérer une liste de tâches (priorité, échéance et statut), avec sauvegarde locale.

**Cours :** INF1763 — Mini-projet 1 — Automne 2026  
**Équipe :** n° 10 — Université du Québec en Outaouais

## Équipe

| Membre | Responsabilité de coordination |
|---|---|
| Kelome Mamondé Brian | Dépôt et intégrations |
| Trabant Christ Emmanuel | Vérifications |
| Kolié Odile Haoussa | Documentation |
| Touré Karim | Backlog et projets GitHub |
| Agoua Sopie Doryne Marie-Noëlle | Suivi des itérations et rétrospectives |

**Dépôt :** https://github.com/brianklm/TaskFlow_Equipe10_new

## Fonctionnalités prévues (User Stories)

| US | Description |
|---|---|
| US-01 | Afficher le menu et quitter proprement |
| US-02 | Ajouter une tâche avec valeurs par défaut |
| US-03 | Ajouter une tâche avec priorité et échéance |
| US-04 | Refuser un ajout invalide et permettre de corriger ou d'annuler |
| US-05 | Afficher la liste des tâches |
| US-06 | Afficher un message pour une liste vide |
| US-07 | Marquer une tâche comme terminée |
| US-08 | Signaler une tâche déjà terminée ou un identifiant inconnu |
| US-09 | Modifier la priorité d'une tâche |
| US-10 | Attribuer ou modifier l'échéance d'une tâche |
| US-11 | Retirer l'échéance d'une tâche |

Le statut de chaque US est suivi dans les issues GitHub. Une fonctionnalité n'est considérée comme terminée qu'après vérification de ses critères d'acceptation.

## Prérequis

- JDK 17 ou plus récent
- Git (pour cloner et contribuer)
- Aucun composant externe : bibliothèque standard Java uniquement

Vérifier Java :

```bash
java -version
javac -version
```

## Installation et exécution

```bash
git clone https://github.com/brianklm/TaskFlow_Equipe10_new.git
cd TaskFlow_Equipe10_new
```

**Sur macOS / Linux**, pour la structure `src/` de l'archive fournie :

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out taskflow.Main
```

**Sur Windows (PowerShell) :**

```powershell
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -Dfile.encoding=UTF-8 -cp out taskflow.Main
```

> **Important :** dans certaines versions du dépôt, les sources se trouvent dans `Source/taskflow/` et non `src/taskflow/`. Dans ce cas, remplacer `src` par `Source` dans la commande de compilation.

## Menu principal

```text
TASKFLOW — Gestion des tâches

1. Ajouter une tâche
2. Afficher les tâches
3. Marquer une tâche comme terminée
4. Modifier la priorité d'une tâche
5. Gérer l'échéance d'une tâche
6. Sauvegarder les données
0. Quitter
```

L'utilisateur choisit une option en saisissant son numéro. L'option `0` permet de quitter le programme.

## Modèle de tâche

Chaque tâche comprend :

| Champ | Description |
|---|---|
| ID | Identifiant unique |
| Titre | Nom ou description de la tâche |
| Priorité | BASSE, NORMALE ou ELEVEE |
| Échéance | Date facultative au format `AAAA-MM-JJ` |
| Statut | À faire ou terminée |

La priorité par défaut est **Normale** et une nouvelle tâche est **à faire**.

## Structure des fichiers Java

```text
TaskFlow_Equipe10/
├── src/
│   └── taskflow/
│       ├── Main.java
│       ├── ConsoleUI.java
│       ├── Tache.java
│       ├── Priorite.java
│       ├── GestionnaireTaches.java
│       └── GestionFichier.java
├── .gitignore
└── README.md
```

| Classe | Rôle |
|---|---|
| `Main.java` | Démarrage de l'application |
| `ConsoleUI.java` | Menu et saisies utilisateur |
| `Tache.java` | Modèle d'une tâche |
| `Priorite.java` | Valeurs de priorité |
| `GestionnaireTaches.java` | Ajout, consultation et modifications des tâches |
| `GestionFichier.java` | Lecture et écriture des données |

## Sauvegarde locale

Le code de `GestionFichier.java` utilise le chemin relatif **`data/taches.txt`** à partir du répertoire où l'application est lancée. Le dossier est créé si nécessaire lors de la sauvegarde.

Chaque ligne du fichier contient cinq champs séparés par des points-virgules :

```text
ID;Titre;Priorite;Echeance;Terminee
```

Exemple de **lignes de données** (sans en-tête) :

```text
1;Préparer le rapport Java;ELEVEE;2026-11-15;false
2;Réviser le cours;NORMALE;;false
3;Terminer le devoir;BASSE;2026-10-30;true
```

- Une échéance absente correspond à un champ vide.
- Le statut est représenté par `false` (à faire) ou `true` (terminée).
- Les données sont chargées au démarrage lorsque le fichier existe.
- La sauvegarde est déclenchée depuis le menu.
- Le format actuel n'est pas un CSV complet avec échappement : éviter les points-virgules dans les titres.

## Exemple d'utilisation

```text
Votre choix : 1
Titre : Préparer le rapport Java
Priorité : Élevée
Échéance : 2026-11-15

Votre choix : 2
```

Les libellés précis affichés peuvent varier selon la version du code et les fonctionnalités intégrées.

## Travail en équipe et Git

Le travail est organisé avec les **issues**, les **branches** et les **pull requests** du dépôt GitHub.

Processus recommandé :

1. Choisir une User Story et lire ses critères d'acceptation.
2. Créer une branche dédiée, par exemple `feature/US-01`.
3. Modifier uniquement les parties nécessaires du code.
4. Compiler et tester la fonctionnalité.
5. Créer un commit explicite.
6. Envoyer la branche et ouvrir une pull request.
7. Faire relire, puis fusionner dans `main`.

Exemple :

```bash
git switch -c feature/US-01
git add src/taskflow/Main.java src/taskflow/ConsoleUI.java
git commit -m "US-01 : afficher le menu et quitter proprement"
git push -u origin feature/US-01
```

Adapter les chemins si le dépôt utilise `Source/`.

## Vérifications recommandées

- Compilation sans erreur.
- Affichage du menu et sortie avec `0`.
- Gestion d'une saisie invalide.
- Ajout d'une tâche avec et sans priorité/échéance.
- Affichage d'une liste vide et d'une liste remplie.
- Modification du statut, de la priorité et de l'échéance.
- Sauvegarde et rechargement des données.
- Gestion d'un identifiant inexistant.

## Limites et évolutions possibles

La suppression d'une tâche, la modification de son titre, le tri, les filtres et une interface graphique ne font pas partie des onze User Stories indiquées. Ces fonctionnalités pourraient être envisagées ultérieurement.

## Confidentialité

Utiliser des données fictives pour les démonstrations. Ne jamais publier de mots de passe, de secrets ou de renseignements personnels sensibles dans le dépôt.
