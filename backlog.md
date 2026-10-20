# Backlog de produit — TaskFlow

> Backlog de l'équipe : Kelome Mamondé Brian, Trabi Christ Emmanuel, Kolié Odile Haoussa, Touré Karim, Agoua Sopie Doryne Marie-Noelle. Modèle de départ à adapter. Les **Issues GitHub** restent la source de vérité : une Issue par récit, avec ce contenu (ou un lien vers ce fichier). Éviter les copies contradictoires.

## Conventions

- **Utilisateur** : membre de l'équipe de soutien informatique qui utilise TaskFlow.
- **Estimation** : points relatifs (1, 2, 3, 5, 8) — 1 = changement trivial, 3 = fonction avec validation, 5 = fonction avec plusieurs cas d'erreur ou accès fichier.
- **Priorité** : rang de réalisation (1 = d'abord), avec justification.
- **État** : À faire / En cours / À vérifier / Terminé (cohérent avec le tableau GitHub Projects). Tous les récits démarrent à **À faire**.
- **Scénario de vérif.** : numéro du scénario de `docs/verification.md` (section 8.2 de l'énoncé).

## Vue d'ensemble

| Rang | ID | Titre | Fonct. | Estim. | Itér. | Auteur principal | Relecteur principal | Scénarios |
|---|---|---|---|---|---|---|---|---|
| 1 | US-01 | Afficher le menu et quitter proprement | Base | 2 | 1 | Brian | Marie-Noelle | — |
| 2 | US-02 | Ajouter une tâche avec valeurs par défaut | 4.1 | 3 | 1 | Karim | Odile | 2 |
| 3 | US-05 | Afficher la liste des tâches | 4.2 | 3 | 1 | Odile | Christ Emmanuel | 4 |
| 4 | US-06 | Afficher un message pour une liste vide | 4.2 | 1 | 1 | Odile | Christ Emmanuel | 1 |
| 5 | US-03 | Ajouter une tâche avec priorité et échéance | 4.1 | 3 | 1 | Marie-Noelle | Karim | 2, 7, 8 |
| 6 | US-04 | Refuser un ajout invalide, corriger ou annuler | 4.1 | 5 | 1 | Christ Emmanuel | Brian | 3, 7, 8 |
| 7 | US-07 | Marquer une tâche comme terminée | 4.3 | 2 | 2 | Brian | Marie-Noelle | 5 |
| 8 | US-08 | Être informé d'une tâche déjà terminée ou d'un identifiant inconnu | 4.3 | 2 | 2 | Christ Emmanuel | Brian | 5, 6 |
| 9 | US-09 | Modifier la priorité d'une tâche | 4.4 | 3 | 2 | Karim | Odile | 6, 7 |
| 10 | US-10 | Attribuer ou modifier l'échéance d'une tâche | 4.5 | 3 | 2 | Karim | Odile | 6, 8 |
| 11 | US-11 | Retirer l'échéance d'une tâche | 4.5 | 2 | 2 | Odile | Christ Emmanuel | 6, 8 |
| 12 | US-12 | Sauvegarder les données dans un fichier | 4.6 | 5 | 2 | Brian | Marie-Noelle | 9 |
| 13 | US-13 | Recharger les données au démarrage | 4.6 | 5 | 2 | Marie-Noelle | Karim | 9, 10 |
| 14 | US-14 | Démarrer avec un fichier de données absent | 4.6 | 2 | 2 | Marie-Noelle | Karim | 11 |
| 15 | US-15 | Interrompre le démarrage si le fichier est mal formé | 4.6 | 3 | 2 | Odile | Christ Emmanuel | 11 |
| 16 | US-16 | Être averti d'un échec de sauvegarde | 4.6 | 3 | 2 | Christ Emmanuel | Brian | 12 |

> **Note sur le découpage.** Le plan initial prévoyait 14 récits avec un seul récit « fichier absent / corrompu / échec d'écriture ». L'énoncé (section 6.2) compte ces trois scénarios comme **trois récits distincts** : ils sont donc séparés ici en US-14, US-15 et US-16, pour un total de **16 récits** (minimum exigé : 12). Si l'équipe préfère rester à 14, fusionner US-14 à US-16 en un seul récit et US-05 avec US-06.

## Tâches techniques (distinctes des récits)

| ID | Titre | Estim. | Itération | Responsable | Dépendance |
|---|---|---|---|---|---|
| T-01 | Initialiser le projet Java et la structure de packages | 1 | 1 | Brian | — |
| T-02 | Créer le modèle `Task` et les enums `Priorite` et `Statut` | 2 | 1 | Brian | T-01 |
| T-03 | Choisir et documenter le format du fichier de données | 2 | 2 | Marie-Noelle | — |
| T-04 | Rédiger le README (au fil de l'eau) | 2 | 1-2 | Odile | — |
| T-05 | Rédiger `docs/verification.md` et les données fictives | 3 | 1-2 | Karim | — |

---

## Itération 1 — Ajouter et afficher

### US-01 — Afficher le menu et quitter proprement

**Récit :** En tant que membre de l'équipe de soutien, je veux voir un menu clair et pouvoir quitter l'application à tout moment depuis ce menu, afin de savoir quelles actions sont possibles et de fermer le programme sans erreur.

**Critères d'acceptation**
1. Au démarrage, le menu affiche les options 1 à 6 et 0 (Quitter), puis la ligne « Votre choix : ».
2. Quand je saisis `0`, le message « Fermeture de TaskFlow. Au revoir ! » s'affiche et le programme se termine sans exception.
3. Quand je saisis une valeur qui n'est pas une option (`9`, `abc`, ligne vide), un message d'erreur explique le problème et le menu est réaffiché, sans fermeture du programme.

**Priorité :** 1 — sans menu, aucune autre fonctionnalité n'est démontrable.
**Estimation :** 2 — boucle principale et lecture de saisie ; hypothèse : un seul `Scanner` partagé par toute l'interface.
**Dépend de :** T-01
**Auteur principal :** Kelome Mamondé Brian
**Relecteur principal :** Agoua Sopie Doryne Marie-Noelle
**État :** À faire

### US-02 — Ajouter une tâche avec valeurs par défaut

**Récit :** En tant que membre de l'équipe de soutien, je veux ajouter une tâche en saisissant seulement un titre, afin de noter rapidement une demande sans remplir tous les champs.

**Critères d'acceptation**
1. Quand j'ajoute « Mettre à jour le guide utilisateur » et que j'appuie sur Entrée pour la priorité et l'échéance, la tâche est créée avec la priorité **Normale**, **aucune** échéance et le statut **À faire**.
2. Un identifiant entier positif est attribué automatiquement et un message de confirmation l'indique (« Tâche ajoutée avec succès. Identifiant : 1 »).
3. Deux ajouts successifs reçoivent deux identifiants différents (1 puis 2).
4. Deux tâches avec le même titre sont acceptées et ont des identifiants distincts.

**Priorité :** 2 — première fonctionnalité utile, base des suivantes.
**Estimation :** 3 — hypothèse : l'identifiant est généré par un compteur dans `TaskManager`.
**Dépend de :** US-01, T-02
**Auteur principal :** Touré Karim
**Relecteur principal :** Kolié Odile Haoussa
**État :** À faire

### US-05 — Afficher la liste des tâches

**Récit :** En tant que membre de l'équipe de soutien, je veux afficher toutes les tâches avec leurs informations, afin de savoir ce qui reste à faire et ce qui est prioritaire.

**Critères d'acceptation**
1. Pour chaque tâche, la liste affiche l'identifiant, le titre, la priorité, l'échéance et le statut, sous forme de colonnes lisibles.
2. Une tâche sans échéance affiche « Aucune » dans la colonne Échéance.
3. La liste se termine par « Nombre de tâches : N ».
4. Un titre long ou contenant des accents (é, è, à) ne casse pas la lisibilité du tableau *(décider d'une largeur maximale ou d'une troncature)*.

**Priorité :** 3 — permet de vérifier visuellement les ajouts et toutes les modifications ultérieures.
**Estimation :** 3 — hypothèse : formatage avec `String.format` et largeurs de colonnes fixes.
**Dépend de :** US-02
**Auteur principal :** Kolié Odile Haoussa
**Relecteur principal :** Trabi Christ Emmanuel
**État :** À faire

### US-06 — Afficher un message pour une liste vide

**Récit :** En tant que membre de l'équipe de soutien, je veux un message explicite quand il n'y a aucune tâche, afin de ne pas croire à une erreur de l'application.

**Critères d'acceptation**
1. Au premier démarrage, le choix « Afficher les tâches » affiche « Aucune tâche à afficher. » et aucun en-tête de tableau vide.
2. Après ajout d'une première tâche, la même commande affiche la liste normale.

**Priorité :** 4 — petit récit, mais exigé explicitement et très visible pour un nouvel utilisateur.
**Estimation :** 1
**Dépend de :** US-05
**Auteur principal :** Kolié Odile Haoussa
**Relecteur principal :** Trabi Christ Emmanuel
**État :** À faire

### US-03 — Ajouter une tâche avec priorité et échéance

**Récit :** En tant que membre de l'équipe de soutien, je veux préciser une priorité et une échéance en ajoutant une tâche, afin de distinguer d'emblée ce qui est urgent.

**Critères d'acceptation**
1. Quand je saisis la priorité « Élevée » et l'échéance « 2026-10-05 », la tâche créée affiche ces deux valeurs et le statut À faire.
2. Les trois priorités Basse, Normale et Élevée sont acceptées *(décider si la casse et les accents sont tolérés : « elevee », « ÉLEVÉE »)*.
3. Une échéance dans le passé est acceptée.
4. Une date bissextile valide (`2024-02-29`) est acceptée.

**Priorité :** 5 — complète l'ajout une fois la version minimale en place.
**Estimation :** 3 — hypothèse : l'échéance est un `LocalDate` nullable.
**Dépend de :** US-02
**Auteur principal :** Agoua Sopie Doryne Marie-Noelle
**Relecteur principal :** Touré Karim
**État :** À faire

### US-04 — Refuser un ajout invalide et permettre de corriger ou d'annuler

**Récit :** En tant que membre de l'équipe de soutien, je veux être averti d'une saisie incorrecte lors de l'ajout et pouvoir corriger ou annuler, afin de ne jamais enregistrer une tâche invalide.

**Critères d'acceptation**
1. Un titre vide ou composé uniquement d'espaces est refusé avec « Erreur : le titre est obligatoire. Veuillez saisir un titre ou annuler l'ajout. » et **aucune tâche** n'est ajoutée.
2. Une priorité invalide (ex. « Urgente ») est refusée avec « Erreur : la priorité doit être Basse, Normale ou Élevée. »
3. Une échéance impossible (`2026-02-30`) est refusée avec « Erreur : 2026-02-30 n'est pas une date valide. »
4. Une échéance au mauvais format (`10/08/2026`) est refusée avec un message indiquant le format attendu AAAA-MM-JJ.
5. Après chaque erreur, je peux corriger la saisie **ou** annuler l'ajout ; en cas d'annulation, aucune tâche n'est créée et le compteur d'identifiants n'est pas consommé.

**Priorité :** 6 — exigé, mais s'appuie sur un ajout qui fonctionne déjà.
**Estimation :** 5 — plusieurs validations et un flux « corriger ou annuler » ; hypothèse : validation centralisée dans le service, messages dans l'interface.
**Dépend de :** US-03
**Auteur principal :** Trabi Christ Emmanuel
**Relecteur principal :** Kelome Mamondé Brian
**État :** À faire

---

## Itération 2 — Modifier, sauvegarder, robustesse

### US-07 — Marquer une tâche comme terminée

**Récit :** En tant que membre de l'équipe de soutien, je veux marquer une tâche terminée à partir de son identifiant, afin de suivre ce qui est fait.

**Critères d'acceptation**
1. Avec les tâches 1 et 2 à « À faire », quand je termine la tâche 1, son statut devient Terminée et le message « La tâche « … » est maintenant terminée. » s'affiche.
2. Le titre, la priorité et l'échéance de la tâche 1 sont **inchangés**.
3. La tâche 2 reste **inchangée** (statut À faire).

**Priorité :** 7 — première modification d'une tâche existante.
**Estimation :** 2 — hypothèse : une méthode `trouverParId` existe déjà dans `TaskManager`.
**Dépend de :** US-05
**Auteur principal :** Kelome Mamondé Brian
**Relecteur principal :** Agoua Sopie Doryne Marie-Noelle
**État :** À faire

### US-08 — Être informé d'une tâche déjà terminée ou d'un identifiant inconnu

**Récit :** En tant que membre de l'équipe de soutien, je veux un message clair quand je cible une tâche déjà terminée ou un identifiant inexistant, afin de comprendre qu'aucune modification n'a eu lieu.

**Critères d'acceptation**
1. Si la tâche 1 est déjà terminée, « La tâche 1 est déjà terminée. Aucune modification n'est nécessaire. » s'affiche et aucune donnée ne change.
2. Avec l'identifiant `99` : « Erreur : aucune tâche ne correspond à l'identifiant 99. Aucune donnée n'a été modifiée. »
3. Un identifiant non numérique (`abc`), vide, `0` ou négatif est refusé avec un message, sans exception ni fermeture du programme.
4. Le comportement des cas 2 et 3 est identique pour les fonctions priorité et échéance *(vérifié dans US-09 à US-11)*.

**Priorité :** 8 — fiabilise US-07, réutilisé par les récits suivants.
**Estimation :** 2 — hypothèse : lecture de l'identifiant centralisée dans une méthode réutilisable.
**Dépend de :** US-07
**Auteur principal :** Trabi Christ Emmanuel
**Relecteur principal :** Kelome Mamondé Brian
**État :** À faire

### US-09 — Modifier la priorité d'une tâche

**Récit :** En tant que membre de l'équipe de soutien, je veux changer la priorité d'une tâche, afin de refléter l'évolution de l'urgence.

**Critères d'acceptation**
1. Pour la tâche 2 (Normale), choisir « Élevée » affiche « La priorité de la tâche 2 a été modifiée : Normale → Élevée. »
2. Les trois valeurs Basse, Normale et Élevée sont acceptées.
3. Une valeur invalide est refusée avec « Erreur : la priorité doit être Basse, Normale ou Élevée. La priorité actuelle est conservée. » et la priorité reste inchangée.
4. Un identifiant inconnu est signalé et aucune donnée n'est modifiée.
5. Seule la priorité de la tâche ciblée change.

**Priorité :** 9 — modification simple, peu de dépendances.
**Estimation :** 3 — hypothèse : réutilisation de la validation de priorité de US-04.
**Dépend de :** US-08
**Auteur principal :** Touré Karim
**Relecteur principal :** Kolié Odile Haoussa
**État :** À faire

### US-10 — Attribuer ou modifier l'échéance d'une tâche

**Récit :** En tant que membre de l'équipe de soutien, je veux définir ou changer l'échéance d'une tâche existante, afin de garder les dates à jour.

**Critères d'acceptation**
1. Pour la tâche 2 sans échéance, saisir `2026-10-08` affiche « L'échéance de la tâche 2 est maintenant : 2026-10-08. »
2. Pour une tâche ayant déjà une échéance, saisir une nouvelle date la remplace.
3. Une date impossible (`2026-02-30`) est refusée et l'échéance actuelle est conservée.
4. Un format incorrect (`10/08/2026`) est refusé avec le format attendu, et l'échéance actuelle est conservée.
5. Je peux annuler l'action (option 0) sans modification.
6. Un identifiant inconnu est signalé.

**Priorité :** 10 — complète la gestion des échéances.
**Estimation :** 3 — hypothèse : réutilisation de la validation de date de US-04, mode strict.
**Dépend de :** US-08
**Auteur principal :** Touré Karim
**Relecteur principal :** Kolié Odile Haoussa
**État :** À faire

### US-11 — Retirer l'échéance d'une tâche

**Récit :** En tant que membre de l'équipe de soutien, je veux retirer l'échéance d'une tâche, afin de corriger une date devenue sans objet.

**Critères d'acceptation**
1. Pour la tâche 2 avec l'échéance `2026-10-08`, l'option « Retirer l'échéance » affiche « L'échéance de la tâche 2 a été retirée. » et la liste affiche « Aucune ».
2. Retirer l'échéance d'une tâche qui n'en a pas affiche un message clair, sans erreur ni modification.
3. Les autres informations de la tâche sont inchangées.

**Priorité :** 11 — dépend de US-10 (même sous-menu).
**Estimation :** 2
**Dépend de :** US-10
**Auteur principal :** Kolié Odile Haoussa
**Relecteur principal :** Trabi Christ Emmanuel
**État :** À faire

### US-12 — Sauvegarder les données dans un fichier

**Récit :** En tant que membre de l'équipe de soutien, je veux sauvegarder mes tâches dans un fichier local sur commande, afin de les retrouver à la prochaine utilisation.

**Critères d'acceptation**
1. Le choix 6 écrit toutes les tâches dans le fichier et affiche « Sauvegarde réussie : N tâches enregistrées. »
2. Toutes les informations sont écrites : identifiant, titre, priorité, échéance (ou absence), statut.
3. Un titre contenant une virgule, un guillemet ou un accent est sauvegardé sans corruption *(selon le format choisi)*.
4. Sauvegarder une liste vide produit un fichier valide, relisible au démarrage suivant.
5. L'emplacement et le format du fichier sont documentés dans le README.

**Priorité :** 12 — nécessite que toutes les données existent.
**Estimation :** 5 — hypothèse : le format est choisi dans T-03 avant le début du récit ; écriture isolée dans `TaskRepository`.
**Dépend de :** T-03, US-07, US-09, US-10
**Auteur principal :** Kelome Mamondé Brian
**Relecteur principal :** Agoua Sopie Doryne Marie-Noelle
**État :** À faire

### US-13 — Recharger les données au démarrage

**Récit :** En tant que membre de l'équipe de soutien, je veux que mes tâches soient rechargées automatiquement, afin de reprendre mon travail là où je l'ai laissé.

**Critères d'acceptation**
1. Au démarrage avec un fichier valide, « Chargement du fichier de données... 2 tâches chargées avec succès. » s'affiche.
2. Après redémarrage, les identifiants, titres, priorités, échéances (y compris absentes) et statuts sont identiques à ceux sauvegardés.
3. Une tâche ajoutée après rechargement reçoit un identifiant **distinct** de tous les identifiants chargés (ex. 3 après 1 et 2).
4. Le parcours de bout en bout de la section 5 de l'énoncé se déroule sans écart.

**Priorité :** 13 — suit directement la sauvegarde.
**Estimation :** 5 — hypothèse : le prochain identifiant est recalculé au chargement (`max + 1`) ou lu depuis le fichier.
**Dépend de :** US-12
**Auteur principal :** Agoua Sopie Doryne Marie-Noelle
**Relecteur principal :** Touré Karim
**État :** À faire

### US-14 — Démarrer avec un fichier de données absent

**Récit :** En tant que nouvel utilisateur, je veux que l'application démarre normalement sans fichier existant, afin de pouvoir l'utiliser dès la première fois.

**Critères d'acceptation**
1. Sans fichier, « Aucun fichier de données trouvé. L'application démarre avec une liste vide. » s'affiche et le menu apparaît.
2. Le premier ajout reçoit l'identifiant 1.
3. Le démarrage ne crée pas de fichier de façon implicite *(il n'est créé qu'à la première sauvegarde)*.

**Priorité :** 14 — cas simple, mais à vérifier explicitement.
**Estimation :** 2
**Dépend de :** US-13
**Auteur principal :** Agoua Sopie Doryne Marie-Noelle
**Relecteur principal :** Touré Karim
**État :** À faire

### US-15 — Interrompre le démarrage si le fichier est mal formé

**Récit :** En tant que membre de l'équipe de soutien, je veux être prévenu si le fichier de données est illisible et que mes données ne soient pas écrasées, afin de pouvoir le réparer ou le restaurer.

**Critères d'acceptation**
1. Avec un fichier mal formé (contenu aléatoire, ligne tronquée, champ manquant, date invalide, priorité inconnue), « Erreur : le fichier de données est illisible ou mal formé. Démarrage interrompu. Le fichier n'a pas été modifié. » s'affiche.
2. Le programme se termine sans afficher le menu.
3. Le contenu du fichier est **strictement identique** après l'échec (comparaison avant/après).
4. Un fichier vide est traité selon une règle documentée *(vide = mal formé, ou vide = liste vide)*.
5. Aucune exception non gérée ni trace de pile n'est affichée.

**Priorité :** 15 — protège les données ; exigé par l'énoncé.
**Estimation :** 3 — hypothèse : exception dédiée lancée par `TaskRepository` et interceptée dans `Main`.
**Dépend de :** US-13
**Auteur principal :** Kolié Odile Haoussa
**Relecteur principal :** Trabi Christ Emmanuel
**État :** À faire

### US-16 — Être averti d'un échec de sauvegarde

**Récit :** En tant que membre de l'équipe de soutien, je veux savoir clairement quand la sauvegarde échoue, afin de ne pas croire à tort que mes données sont enregistrées.

**Critères d'acceptation**
1. Si l'écriture est impossible (fichier en lecture seule, dossier inexistant), « Erreur : impossible d'écrire dans le fichier de données. Les modifications n'ont pas été sauvegardées. » s'affiche.
2. Le message « Sauvegarde réussie » **n'apparaît jamais** dans ce cas.
3. L'application reste utilisable après l'échec : les tâches en mémoire sont conservées et je peux réessayer.
4. Si un fichier valide existait, il n'est pas détruit par l'échec *(écrire dans un fichier temporaire puis le renommer, ou documenter la limite)*.

**Priorité :** 16 — dernier récit, car il suppose la sauvegarde et le chargement fonctionnels.
**Estimation :** 3 — hypothèse : capture de `IOException` dans `TaskRepository`, résultat de succès ou d'échec remonté à l'interface.
**Dépend de :** US-12
**Auteur principal :** Trabi Christ Emmanuel
**Relecteur principal :** Kelome Mamondé Brian
**État :** À faire

---

## Fonctionnalités supplémentaires envisagées (hors exigences)

À garder séparées des exigences obligatoires :

- Suppression d'une tâche *(attention : impact sur la règle d'attribution des identifiants)*
- Modification du titre
- Tri ou filtre (par priorité, échéance, statut)
- Signalement des tâches en retard

## Répartition des récits entre les membres

Chaque membre est auteur principal d'au moins deux récits, dans **les deux itérations**, et la charge est équilibrée (écart de 2 points au maximum, tâches techniques comprises).

| Membre | Itération 1 | Itération 2 | Points (récits) | Tâches techniques | Total |
|---|---|---|---|---|---|
| Kelome Mamondé Brian | US-01 | US-07, US-12 | 9 | T-01, T-02 (3) | 12 |
| Trabi Christ Emmanuel | US-04 | US-08, US-16 | 10 | — | 10 |
| Kolié Odile Haoussa | US-05, US-06 | US-11, US-15 | 9 | T-04 (2) | 11 |
| Touré Karim | US-02 | US-09, US-10 | 9 | T-05 (3) | 12 |
| Agoua Sopie Doryne Marie-Noelle | US-03 | US-13, US-14 | 10 | T-03 (2) | 12 |
| **Total** | | | **47** | **10** | **57** |

### Rotation des revues

| Auteur de la pull request | Relecteur principal |
|---|---|
| Brian | Marie-Noelle |
| Christ Emmanuel | Brian |
| Odile | Christ Emmanuel |
| Karim | Odile |
| Marie-Noelle | Karim |

Si le relecteur principal est indisponible plus de 24 h, un autre membre le remplace. Chacun relit ainsi au moins une pull request d'un autre membre.

### Ordre de démarrage conseillé

1. **Brian** : T-01 puis T-02, fusionnés rapidement (les autres en dépendent).
2. **Brian (US-01)** puis **Karim (US-02)** : menu et ajout minimal.
3. En parallèle ensuite : **Marie-Noelle (US-03)**, **Christ Emmanuel (US-04)**, **Odile (US-05, US-06)**.
4. Des conflits de fusion sont probables sur `ConsoleUI.java` : c'est normal, résolvez-les ensemble puis recompilez et retestez.
