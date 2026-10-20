# Organisation du travail — TaskFlow

> Document de synthèse du travail d'équipe (INF1763 — Mini-projet 1). Équipe : Kelome Mamondé Brian, Trabi Christ Emmanuel, Kolié Odile Haoussa, Touré Karim, Agoua Sopie Doryne Marie-Noelle.
> Principe : rester **concis** et **renvoyer vers les traces existantes** (Issues, PR, Project, commits) plutôt que les recopier.

## 1. Objectif du produit

TaskFlow est un outil interne en ligne de commande permettant à une équipe de soutien informatique de conserver une liste de tâches, de leur attribuer une priorité et une échéance, de les marquer comme terminées et de retrouver ses données d'une utilisation à l'autre.

**Public visé :** *(ex. équipe de soutien informatique d'une entreprise fictive)*
**Version remise :** *(ex. v1.0-mp1, commit `abc1234`)*

## 2. Organisation de l'équipe

### Membres et responsabilités de coordination

| Membre | Compte GitHub | Coordination | Récits (auteur principal) | Tâches techniques |
|---|---|---|---|---|
| Kelome Mamondé Brian | @… | Dépôt et intégrations | US-01, US-07, US-12 | T-01, T-02 |
| Trabi Christ Emmanuel | @… | Backlog et GitHub Projects | US-04, US-08, US-16 | — |
| Kolié Odile Haoussa | @… | Documentation (README, ce document) | US-05, US-06, US-11, US-15 | T-04 |
| Touré Karim | @… | Vérifications (`verification.md`) | US-02, US-09, US-10 | T-05 |
| Agoua Sopie Doryne Marie-Noelle | @… | Suivi des itérations et rétrospectives | US-03, US-13, US-14 | T-03 |

*(Rôles de coordination proposés : à confirmer par l'équipe et à consigner dans le journal des décisions.)*

### Rotation des revues

| Auteur de la PR | Relecteur principal |
|---|---|
| Brian | Marie-Noelle |
| Christ Emmanuel | Brian |
| Odile | Christ Emmanuel |
| Karim | Odile |
| Marie-Noelle | Karim |

Remplaçant si le relecteur principal est indisponible plus de 24 h : n'importe quel autre membre qui n'est pas l'auteur.

> Une responsabilité de coordination ne remplace pas la contribution au code et aux vérifications.

### Règles de fonctionnement

- **Communication :** *(canal, fréquence, délai de réponse attendu)*
- **Prise de décision :** *(consensus, vote, qui tranche en cas de désaccord)*
- **Signalement des blocages :** *(étiquette `bloqué` sur l'Issue + message dans le canal d'équipe)*
- **Vérification et intégration :** une branche par Issue, pull request, relecture par un autre membre, fusion dans `main`. Aucune poussée directe sur `main` après l'initialisation.
- **Réunions :** *(planification d'itération, point rapide, rétrospective : dates et durée)*

## 3. Backlog

- Source de vérité : **Issues GitHub** — *(lien)*
- Tableau de suivi : **GitHub Projects** — *(lien)*
- Les cartes représentent des : *(récits utilisateurs / tâches techniques)*
- Étiquettes utilisées : `récit`, `technique`, `anomalie`, `bloqué`, `itération-1`, `itération-2`

### Règles de passage entre états

| De → vers | Condition |
|---|---|
| À faire → En cours | Issue assignée, branche créée |
| En cours → À vérifier | Pull request ouverte, relecteur désigné |
| À vérifier → Terminé | PR approuvée et fusionnée, définition de terminé respectée |
| Tout état → bloqué | Étiquette `bloqué` et commentaire expliquant le blocage |

## 4. Priorités et estimations

### Échelle d'estimation

*(ex. points relatifs 1, 2, 3, 5, 8 : 1 = changement trivial, 3 = fonction avec validation, 5 = fonction avec plusieurs cas d'erreur ou accès fichier)*

Méthode : discussion d'équipe, chaque personne propose une valeur, on discute des écarts puis on s'entend. *(Pas de planning poker exigé.)*

### Tableau des récits

Détail des critères d'acceptation : [`backlog.md`](backlog.md). Les numéros d'Issues sont à compléter à leur création.

| ID | Titre | Rang | Justification du rang | Estim. | Hypothèses | Itér. | Auteur principal | Issue | État |
|---|---|---|---|---|---|---|---|---|---|
| US-01 | Afficher le menu et quitter proprement | 1 | Sans menu, rien n'est démontrable | 2 | Un seul lecteur de saisie partagé | 1 | Brian | #… | À faire |
| US-02 | Ajouter une tâche avec valeurs par défaut | 2 | Première fonctionnalité utile, base des suivantes | 3 | Identifiant généré par un compteur dans `TaskManager` | 1 | Karim | #… | À faire |
| US-05 | Afficher la liste des tâches | 3 | Permet de vérifier visuellement tous les ajouts et modifications | 3 | Colonnes alignées avec `String.format` | 1 | Odile | #… | À faire |
| US-06 | Afficher un message pour une liste vide | 4 | Petit récit, très visible pour un nouvel utilisateur | 1 | — | 1 | Odile | #… | À faire |
| US-03 | Ajouter une tâche avec priorité et échéance | 5 | Complète l'ajout une fois la version minimale en place | 3 | Échéance de type `LocalDate` nullable | 1 | Marie-Noelle | #… | À faire |
| US-04 | Refuser un ajout invalide, corriger ou annuler | 6 | Exigé, mais s'appuie sur un ajout qui fonctionne | 5 | Validation dans le service, messages dans l'interface | 1 | Christ Emmanuel | #… | À faire |
| US-07 | Marquer une tâche comme terminée | 7 | Première modification d'une tâche existante | 2 | `trouver` par identifiant déjà disponible | 2 | Brian | #… | À faire |
| US-08 | Tâche déjà terminée ou identifiant inconnu | 8 | Fiabilise US-07, réutilisé par les récits suivants | 2 | Lecture de l'identifiant centralisée | 2 | Christ Emmanuel | #… | À faire |
| US-09 | Modifier la priorité d'une tâche | 9 | Modification simple, peu de dépendances | 3 | Réutilise la validation de priorité | 2 | Karim | #… | À faire |
| US-10 | Attribuer ou modifier l'échéance | 10 | Complète la gestion des échéances | 3 | Validation de date stricte réutilisée | 2 | Karim | #… | À faire |
| US-11 | Retirer l'échéance d'une tâche | 11 | Dépend de US-10 (même sous-menu) | 2 | — | 2 | Odile | #… | À faire |
| US-12 | Sauvegarder les données dans un fichier | 12 | Nécessite que toutes les données existent | 5 | Format choisi dans T-03 avant le début | 2 | Brian | #… | À faire |
| US-13 | Recharger les données au démarrage | 13 | Suit directement la sauvegarde | 5 | Prochain identifiant lu dans le fichier | 2 | Marie-Noelle | #… | À faire |
| US-14 | Démarrer avec un fichier absent | 14 | Cas simple mais à vérifier explicitement | 2 | — | 2 | Marie-Noelle | #… | À faire |
| US-15 | Interrompre le démarrage si fichier mal formé | 15 | Protège les données, exigé par l'énoncé | 3 | Exception dédiée interceptée dans `Main` | 2 | Odile | #… | À faire |
| US-16 | Être averti d'un échec de sauvegarde | 16 | Suppose sauvegarde et chargement fonctionnels | 3 | Capture de `IOException` dans le dépôt | 2 | Christ Emmanuel | #… | À faire |

### Tâches techniques

| ID | Titre | Estim. | Responsable | Issue | État |
|---|---|---|---|---|---|
| T-01 | Initialiser le projet Java et la structure de packages | 1 | Brian | #… | À faire |
| T-02 | Créer le modèle `Task` et les enums | 2 | Brian | #… | À faire |
| T-03 | Choisir et documenter le format du fichier | 2 | Marie-Noelle | #… | À faire |
| T-04 | Rédiger le README | 2 | Odile | #… | À faire |
| T-05 | Rédiger `verification.md` et les données fictives | 3 | Karim | #… | À faire |

Charge totale (récits et tâches techniques) : Brian 12, Christ Emmanuel 10, Odile 11, Karim 12, Marie-Noelle 12 (sur 57 points).

## 5. Définition de terminé

Un élément est **terminé** lorsque :

1. les critères d'acceptation sont satisfaits ;
2. les vérifications pertinentes ont été exécutées et consignées dans `docs/verification.md` ;
3. le code est intégré dans `main` par une pull request fusionnée ;
4. un autre membre a relu le changement, l'a essayé et a laissé un commentaire précis ;
5. la documentation utile (README, ce document) est à jour ;
6. l'Issue est fermée et la carte est dans l'état *Terminé*.

## 6. Itérations

### Itération 1 — *(titre, ex. « Ajouter et afficher »)*

| Élément | Contenu |
|---|---|
| **Période** | du … au … |
| **Objectif** | *(ex. pouvoir démarrer l'application, ajouter des tâches valides et les afficher)* |
| **Récits sélectionnés** | US-01, US-02, … |
| **Principales tâches de réalisation** | *(modèle, console, validation du titre, …)* |
| **Version obtenue** | *(tag `v0.1`, commit …)* |
| **Vérifications effectuées** | *(scénarios 1 à 4, voir `docs/verification.md`)* |
| **Observations** | *(ce qui a bien ou mal fonctionné)* |
| **Ajustements décidés** | *(au moins une amélioration concrète pour la suite)* |

### Itération 2 — *(titre)*

*(même tableau)*

## 7. Journal des décisions

Décisions qui modifient le produit, le backlog ou la façon de travailler.

| Date | Décision | Raison | Personnes concernées |
|---|---|---|---|
| AAAA-MM-JJ | *(ex. format du fichier : JSON)* | *(ex. lisible, titres avec virgules sans problème)* | Toute l'équipe |
| AAAA-MM-JJ | *(ex. PR de 200 lignes maximum)* | *(constat de rétrospective de l'itération 1)* | Toute l'équipe |

## 8. Contribution de chaque membre

Pour chaque membre : récits dont il est l'auteur principal (au moins deux), pull requests contenant du code (au moins une), revues effectuées (au moins une), participation aux vérifications. Les liens sont à compléter au fil du projet.

### Kelome Mamondé Brian (@…)

- **Récits réalisés :** US-01, US-07, US-12 *(liens vers les Issues)*
- **Tâches techniques :** T-01, T-02
- **Pull requests :** [#…](lien), [#…](lien)
- **Revues effectuées :** pull requests de Christ Emmanuel : [#…](lien)
- **Vérifications :** scénarios 2, 8 et 11 (voir `verification.md`)
- **Autres contributions :** *(backlog, résolution de conflit de fusion, décisions, …)*

### Trabi Christ Emmanuel (@…)

- **Récits réalisés :** US-04, US-08, US-16 *(liens vers les Issues)*
- **Tâches techniques :** —
- **Pull requests :** [#…](lien), [#…](lien)
- **Revues effectuées :** pull requests de Odile : [#…](lien)
- **Vérifications :** scénarios 4 et 10
- **Autres contributions :** *(backlog, résolution de conflit de fusion, décisions, …)*

### Kolié Odile Haoussa (@…)

- **Récits réalisés :** US-05, US-06, US-11, US-15 *(liens vers les Issues)*
- **Tâches techniques :** T-04
- **Pull requests :** [#…](lien), [#…](lien)
- **Revues effectuées :** pull requests de Karim : [#…](lien)
- **Vérifications :** scénarios 5 et 7
- **Autres contributions :** *(backlog, résolution de conflit de fusion, décisions, …)*

### Touré Karim (@…)

- **Récits réalisés :** US-02, US-09, US-10 *(liens vers les Issues)*
- **Tâches techniques :** T-05
- **Pull requests :** [#…](lien), [#…](lien)
- **Revues effectuées :** pull requests de Marie-Noelle : [#…](lien)
- **Vérifications :** scénarios 1, 9 et 12
- **Autres contributions :** *(backlog, résolution de conflit de fusion, décisions, …)*

### Agoua Sopie Doryne Marie-Noelle (@…)

- **Récits réalisés :** US-03, US-13, US-14 *(liens vers les Issues)*
- **Tâches techniques :** T-03
- **Pull requests :** [#…](lien), [#…](lien)
- **Revues effectuées :** pull requests de Brian : [#…](lien)
- **Vérifications :** scénarios 3 et 6
- **Autres contributions :** *(backlog, résolution de conflit de fusion, décisions, …)*

## 9. Bilan

- **Ce qui a bien fonctionné :** …
- **Difficultés rencontrées :** …
- **Anomalies connues :** voir `docs/verification.md` et le README.
- **Améliorations envisagées :** …
