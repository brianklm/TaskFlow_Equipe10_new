# TaskFlow

> Application Java en console pour gérer une liste de tâches (priorité, échéance, statut) avec sauvegarde locale.
> Projet du cours INF1763 — Mini-projet 1 — Automne 2026.

## Équipe

**Équipe n° XX**

| Membre | Compte GitHub | Responsabilité de coordination |
|---|---|---|
| Kelome Mamondé Brian | @… | Dépôt et intégrations |
| Trabi Christ Emmanuel | @… |Vérifications |
| Kolié Odile Haoussa | @… | Documentation |
| Touré Karim | @… |Backlog et GitHub Projects   |
| Agoua Sopie Doryne Marie-Noelle | @… | Suivi des itérations et rétrospectives |

Organisation du travail, backlog, itérations et contributions : voir [`docs/organisation.md`](docs/organisation.md).
Scénarios de vérification et résultats : voir [`docs/verification.md`](docs/verification.md).

## Fonctionnalités disponibles

- Ajouter une tâche (titre obligatoire, priorité et échéance facultatives)
- Afficher la liste des tâches
- Marquer une tâche comme terminée
- Modifier la priorité d'une tâche (Basse, Normale, Élevée)
- Attribuer, modifier ou retirer l'échéance d'une tâche (format `AAAA-MM-JJ`)
- Sauvegarder les données dans un fichier local (commande explicite)
- Rechargement automatique des données au démarrage

Règles principales : l'identifiant est attribué automatiquement et ne change jamais; la priorité par défaut est *Normale*; une nouvelle tâche a le statut *À faire*; deux tâches peuvent avoir le même titre.

## Prérequis

- **JDK 17 ou plus récent** (développé et essayé avec JDK 21; le code utilise les `record`, donc JDK 17 minimum) — vérifier avec `java -version` et `javac -version`
- Git
- Aucune bibliothèque externe (JDK uniquement)

## Récupérer, compiler et exécuter

### 1. Cloner le dépôt

```bash
git clone https://github.com/ORGANISATION/taskflow.git
cd taskflow
```

### 2. Compiler

**Linux / macOS**

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
```

**Windows (PowerShell)**

```powershell
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

### 3. Exécuter

```bash
java -Dfile.encoding=UTF-8 -cp out taskflow.Main
```

Sous Windows, saisir d'abord `chcp 65001` dans la console pour afficher correctement les accents.

Sans argument, le fichier de données est `taskflow-donnees.txt` dans le dossier courant. Un autre chemin peut être donné en argument :

```bash
java -Dfile.encoding=UTF-8 -cp out taskflow.Main data/fixtures/valide.txt
```

## Exemple d'utilisation

```
TASKFLOW — Gestion des tâches

Aucun fichier de données trouvé.
L'application démarre avec une liste vide.

1. Ajouter une tâche
2. Afficher les tâches
3. Marquer une tâche comme terminée
4. Modifier la priorité d'une tâche
5. Gérer l'échéance d'une tâche
6. Sauvegarder les données
0. Quitter

Votre choix : 1
Titre : Vérifier la sauvegarde du serveur
Votre priorité : Élevée
Votre échéance : 2026-10-05

Tâche ajoutée avec succès.
Identifiant : 1
...
```

Un parcours complet de bout en bout (ajout, modification, sauvegarde, redémarrage, nouvel ajout) est décrit dans [`docs/verification.md`](docs/verification.md).

## Fichier de données

| Élément | Valeur |
|---|---|
| **Emplacement** | `taskflow-donnees.csv` dans le dossier d'où l'application est lancée (ou le chemin passé en argument) |
| **Format** | CSV (Comma-Separated Values), avec une ligne d'en-tête et une ligne par tâche |
| **Séparateur** | Virgule(,) |
| **Encodage** | UTF-8 |
| **Création** | Lors de la première sauvegarde explicite (menu 6) |

### Exemple de contenu

```id,titre,priorite,echeance,statut
1,"Vérifier le serveur",ELEVEE,2026-10-05,A_FAIRE
2,"Mettre à jour le guide utilisateur",NORMALE,,A_FAIRE
3,"Préparer le poste, bureau 3",BASSE,2026-11-15,TERMINEE
```

### Représentation des valeurs

Champs d'une tâche : identifiant, titre, priorité, échéance et statut, dans cet ordre.

Priorité : BASSE, NORMALE ou ELEVEE.

Statut : A_FAIRE ou TERMINEE.

Échéance absente : le champ reste vide entre deux virgules.

Titre : texte de la tâche. Si le titre contient une virgule, un guillemet ou un retour à la ligne, le champ doit être entouré de guillemets doubles. Un guillemet présent dans le titre est représenté par deux guillemets doubles.

Identifiant : entier positif, unique et conservé entre les exécutions.
- **Identifiants** : la ligne `#PROCHAIN_ID=n` conserve le prochain identifiant à attribuer; il doit être supérieur au plus grand identifiant du fichier.
- **Ligne `#FIN`** : obligatoire en dernière ligne, elle permet de détecter un fichier tronqué.

### Sauvegarde et rechargement

**Sauvegarde** : uniquement sur commande (menu 6. Sauvegarder les données). Quitter sans sauvegarder ne conserve pas les modifications ; l'application demande alors une confirmation. L'écriture est effectuée dans un fichier temporaire avant le remplacement du fichier existant, afin de protéger les données en cas d'échec.

**Rechargement** : automatique au démarrage si le fichier CSV existe. Toutes les informations des tâches sont conservées, notamment les identifiants. Une nouvelle tâche reçoit un identifiant distinct.

**Fichier absent** : l'application démarre avec une liste vide. Le fichier CSV n'est pas créé automatiquement au démarrage ; il est créé lors de la première sauvegarde explicite.

**Fichier illisible ou mal formé** : un message d'erreur est affiché et le démarrage est interrompu. Le fichier d'origine n'est pas modifié. Cette règle s'applique notamment aux fichiers vides ou tronqués.

**Échec d'écriture** : un message d'erreur clair est affiché et aucun message de réussite n'est présenté.

**Format et encodage** : le fichier utilise le format CSV et l'encodage UTF-8. Les règles d'échappement CSV sont appliquées à la lecture et à l'écriture afin de préserver les titres contenant des virgules, des guillemets ou des retours à la ligne.

**Identifiants** : après le rechargement, le prochain identifiant est déterminé à partir des identifiants existants afin d'éviter les doublons.

## Structure du projet

```text
taskflow/
├── src/taskflow/
│   ├── Main.java
│   ├── modele/          # Task, Priorite, Statut, Echeances, DateInvalideException
│   ├── service/         # TaskManager, IdentifiantInconnuException
│   ├── persistance/     # TaskRepository, EtatSauvegarde, FichierIllisibleException
│   └── ui/              # ConsoleUI
├── data/fixtures/       # données fictives (fichier valide, fichiers mal formés)
├── docs/
│   ├── backlog.md
│   ├── organisation.md
│   └── verification.md
├── .gitignore
└── README.md
```

## Limites et anomalies connues

| # | Description | Impact | État |
|---|---|---|---|
| 1 | Pas de suppression ni de modification du titre d'une tâche | Fonctionnalité hors périmètre | Non prévue |
| 2 | Les titres de plus de 40 caractères sont tronqués à l'affichage (le titre complet est conservé) | Lisibilité de la liste | Choix de conception |
| 3 | Les accents peuvent s'afficher incorrectement si la console n'est pas en UTF-8 | Affichage seulement | Voir la section d'exécution |
| 4 | Un fichier de données vide est traité comme mal formé | Démarrage interrompu | Choix de conception |
| 5 | Les titres sont enregistrés sans les espaces de début et de fin | Données | Choix de conception |



## Fonctionnalités envisagées (hors exigences)

- suppression d'une tâche, modification du titre, tri par priorité ou échéance, filtre par statut, signalement des tâches en retard

## Travail d'équipe

- Tableau de suivi : [GitHub Projects](https://github.com/ORGANISATION/taskflow/projects) *(lien réel)*
- Issues : [liste des Issues](https://github.com/ORGANISATION/taskflow/issues)
- Processus d'intégration : une branche par Issue, pull request, revue par un autre membre, fusion dans `main`.

## Données et confidentialité

Toutes les données de démonstration sont **fictives**. Aucune donnée réelle, aucun mot de passe ni secret ne doit se trouver dans ce dépôt.
