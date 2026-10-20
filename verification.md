# Vérification manuelle — TaskFlow

> Équipe : Kelome Mamondé Brian, Trabi Christ Emmanuel, Kolié Odile Haoussa, Touré Karim, Agoua Sopie Doryne Marie-Noelle.
> Modèle à compléter **en exécutant réellement** chaque scénario. Les colonnes « Résultat observé » et « Conclusion » ne se remplissent qu'après l'essai : ne rien y écrire par avance.
> Données **fictives uniquement**.

## 1. Contexte de la vérification

| Élément | Valeur |
|---|---|
| Version testée (tag / commit) | *(ex. v1.0-mp1, `abc1234`)* |
| JDK | *(ex. OpenJDK 17.0.x)* |
| Système d'exploitation | *(ex. Windows 11, Ubuntu 24.04)* |
| Testeur(s) | Voir la répartition des scénarios ci-dessous |
| Date(s) | *(AAAA-MM-JJ)* |
| Commandes de compilation et d'exécution | Voir [README.md](../README.md) |

### Légende des conclusions

- ✅ **Conforme** : résultat observé = résultat attendu
- ❌ **Non conforme** : écart, à consigner dans la section 5 (anomalies)
- ⚠️ **Conforme avec réserve** : fonctionne, mais comportement à préciser

### Répartition des scénarios

Principe : un scénario est exécuté par un membre qui **n'est pas l'auteur** des récits concernés.

| Scénario | Récits concernés (auteurs) | Exécuté par |
|---|---|---|
| 1. Liste vide | US-06 (Odile) | Karim |
| 2. Ajout valide | US-02 (Karim), US-03 (Marie-Noelle) | Brian |
| 3. Titre invalide | US-04 (Christ Emmanuel) | Marie-Noelle |
| 4. Liste de plusieurs tâches | US-05 (Odile) | Christ Emmanuel |
| 5. Tâche terminée | US-07 (Brian), US-08 (Christ Emmanuel) | Odile |
| 6. Identifiant inconnu | US-08 à US-11 (Christ Emmanuel, Karim, Odile) | Marie-Noelle |
| 7. Priorité | US-04 (Christ Emmanuel), US-09 (Karim) | Odile |
| 8. Échéance | US-04, US-10, US-11 (Christ Emmanuel, Karim, Odile) | Brian |
| 9. Sauvegarde et redémarrage | US-12 (Brian), US-13 (Marie-Noelle) | Karim |
| 10. Ajout après rechargement | US-13 (Marie-Noelle) | Christ Emmanuel |
| 11. Fichier absent ou mal formé | US-14 (Marie-Noelle), US-15 (Odile) | Brian |
| 12. Échec d'écriture | US-16 (Christ Emmanuel) | Karim |
| Parcours de bout en bout | Tous | Toute l'équipe, puis refait par un membre sur une machine propre |

## 2. Préparation de l'environnement

Avant chaque scénario, partir d'un état connu. Convention : le fichier de données est `taskflow-donnees.txt` dans le dossier d'où l'application est lancée (ou le chemin passé en argument).

| Situation de départ | Comment la préparer |
|---|---|
| **Aucun fichier** | Supprimer ou renommer le fichier de données |
| **Fichier valide** | Copier `data/fixtures/valide.*` à l'emplacement du fichier de données |
| **Fichier mal formé** | Copier `data/fixtures/corrompu.*` à l'emplacement du fichier de données |
| **Écriture impossible** | Rendre le fichier ou le dossier non modifiable *(voir scénario 12)* |

### Fichiers de données fictifs à fournir dans le dépôt

| Fichier | Contenu |
|---|---|
| `data/fixtures/valide.txt` | 3 tâches : une terminée avec échéance, une sans échéance, une avec titre contenant virgule et accent |
| `data/fixtures/corrompu-contenu.*` | Contenu aléatoire non conforme au format |
| `data/fixtures/corrompu-champ.*` | Fichier bien structuré mais avec un champ invalide (date `2026-02-30`, priorité inconnue) |
| `data/fixtures/vide.*` | Fichier de 0 octet |

Ces fichiers sont déjà dans `data/fixtures/` (`corrompu-champ.txt`, `corrompu-contenu.txt`, `corrompu-tronque.txt`, `vide.txt`, `valide.txt`).

## 3. Résumé des résultats

| # | Scénario | Résultat | Récits liés |
|---|---|---|---|
| 1 | Liste vide | ⬜ | US-06 |
| 2 | Ajout valide | ⬜ | US-02, US-03 |
| 3 | Titre invalide | ⬜ | US-04 |
| 4 | Liste de plusieurs tâches | ⬜ | US-05 |
| 5 | Tâche terminée | ⬜ | US-07, US-08 |
| 6 | Identifiant inconnu | ⬜ | US-08 à US-11 |
| 7 | Priorité | ⬜ | US-04, US-09 |
| 8 | Échéance | ⬜ | US-04, US-10, US-11 |
| 9 | Sauvegarde et redémarrage | ⬜ | US-12, US-13 |
| 10 | Ajout après rechargement | ⬜ | US-13 |
| 11 | Fichier absent ou mal formé | ⬜ | US-14, US-15 |
| 12 | Échec d'écriture | ⬜ | US-16 |
| — | Parcours de bout en bout | ⬜ | Tous |

*(Remplacer ⬜ par ✅, ❌ ou ⚠️.)*

## 4. Scénarios détaillés

Chaque ligne est un cas à exécuter. Les résultats attendus reprennent les règles de l'énoncé ; la formulation exacte des messages peut varier.

### Scénario 1 — Liste vide

**Situation de départ :** aucun fichier de données.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 1.1 | Démarrer, choisir 2 (Afficher) | Message explicite « Aucune tâche à afficher. », pas de tableau vide | | |
| 1.2 | Démarrer sans fichier | Message indiquant qu'aucun fichier n'a été trouvé, démarrage normal | | |

### Scénario 2 — Ajout valide

**Situation de départ :** liste vide.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 2.1 | Titre « Mettre à jour le guide utilisateur », Entrée, Entrée | Tâche créée : ID 1, priorité Normale, échéance Aucune, statut À faire ; message de confirmation avec l'ID | | |
| 2.2 | Titre « Vérifier la sauvegarde du serveur », priorité Élevée, échéance 2026-10-05 | Tâche créée avec ces valeurs, ID 2, statut À faire | | |
| 2.3 | Ajouter deux tâches avec le même titre | Deux tâches acceptées, identifiants distincts | | |
| 2.4 | Titre avec accents et virgule : « Préparer le poste, bureau 3 » | Accepté et affiché correctement | | |
| 2.5 | Titre précédé et suivi d'espaces : « ␣␣Test␣␣ » | Accepté *(préciser si les espaces sont retirés)* | | |

### Scénario 3 — Titre invalide

**Situation de départ :** liste vide.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 3.1 | Titre vide (Entrée) | Message « le titre est obligatoire », possibilité de corriger ou d'annuler | | |
| 3.2 | Titre « ␣␣␣␣ » (espaces seulement) | Même refus qu'en 3.1 | | |
| 3.3 | Après 3.1, annuler l'ajout, puis afficher la liste | Aucune tâche ajoutée | | |
| 3.4 | Après 3.1, corriger avec un titre valide | Tâche créée avec l'ID 1 *(l'ID n'a pas été consommé par l'échec)* | | |

### Scénario 4 — Liste de plusieurs tâches

**Situation de départ :** fichier valide (3 tâches) ou 3 tâches ajoutées à la main.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 4.1 | Afficher la liste | Pour chaque tâche : ID, titre, priorité, échéance (ou « Aucune »), statut ; « Nombre de tâches : 3 » | | |
| 4.2 | Ajouter 10 tâches, afficher | Les 10 tâches sont affichées, colonnes lisibles | | |
| 4.3 | Titre de 80 caractères ou plus | Affichage lisible *(préciser le comportement : troncature ou retour à la ligne)* | | |

### Scénario 5 — Tâche terminée

**Situation de départ :** tâches 1 et 2 à « À faire ».

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 5.1 | Marquer la tâche 1 terminée | Message de confirmation ; statut de la tâche 1 = Terminée | | |
| 5.2 | Afficher la liste après 5.1 | Titre, priorité, échéance de la tâche 1 **inchangés** ; tâche 2 **inchangée** | | |
| 5.3 | Marquer à nouveau la tâche 1 | « La tâche 1 est déjà terminée. Aucune modification n'est nécessaire. » ; aucun doublon, aucune modification | | |

### Scénario 6 — Identifiant inconnu

**Situation de départ :** tâches 1 et 2 présentes.

| Cas | Fonction | Données saisies | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|---|
| 6.1 | Terminer | `99` | « aucune tâche ne correspond à l'identifiant 99 », données inchangées | | |
| 6.2 | Priorité | `99` | Même message, données inchangées | | |
| 6.3 | Échéance | `99` | Même message, données inchangées | | |
| 6.4 | Terminer | `0` | Refus avec message, pas de plantage | | |
| 6.5 | Terminer | `-1` | Refus avec message, pas de plantage | | |
| 6.6 | Terminer | `abc` | Refus avec message, pas de plantage | | |
| 6.7 | Terminer | *(Entrée vide)* | Refus avec message, pas de plantage | | |
| 6.8 | Afficher la liste après 6.1 à 6.7 | | Toutes les données existantes sont intactes | | |

### Scénario 7 — Priorité

**Situation de départ :** tâche 2 avec la priorité Normale.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 7.1 | Nouvelle priorité « Élevée » | « La priorité de la tâche 2 a été modifiée : Normale → Élevée. » | | |
| 7.2 | Nouvelle priorité « Basse » | Acceptée | | |
| 7.3 | Nouvelle priorité « Normale » | Acceptée | | |
| 7.4 | Nouvelle priorité « Urgente » | « la priorité doit être Basse, Normale ou Élevée. La priorité actuelle est conservée. » ; priorité inchangée | | |
| 7.5 | Nouvelle priorité vide (Entrée) | Comportement défini *(refus, ou conservation de la valeur actuelle)* ; priorité non corrompue | | |
| 7.6 | Variations de casse : « élevée », « ELEVEE » | Comportement conforme à la décision d'équipe, documenté | | |

### Scénario 8 — Échéance

**Situation de départ :** tâche 2 sans échéance.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 8.1 | Définir `2026-10-08` | « L'échéance de la tâche 2 est maintenant : 2026-10-08. » | | |
| 8.2 | Modifier pour `2026-11-15` | Échéance remplacée | | |
| 8.3 | Définir `2020-01-01` (date passée) | Acceptée | | |
| 8.4 | Définir `2024-02-29` (bissextile valide) | Acceptée | | |
| 8.5 | Définir `2026-02-30` | « 2026-02-30 n'est pas une date valide. L'échéance actuelle est conservée. » | | |
| 8.6 | Définir `2026-02-29` (année non bissextile) | Refusée, échéance conservée | | |
| 8.7 | Définir `2026-13-01` | Refusée, échéance conservée | | |
| 8.8 | Définir `10/08/2026` | Refusée avec rappel du format AAAA-MM-JJ, échéance conservée | | |
| 8.9 | Définir `2026-1-5` ou `abc` | Refusée, échéance conservée | | |
| 8.10 | Annuler (option 0) | Aucune modification | | |
| 8.11 | Retirer l'échéance | « L'échéance de la tâche 2 a été retirée. » ; liste affiche « Aucune » | | |
| 8.12 | Retirer l'échéance d'une tâche qui n'en a pas | Message clair, aucune erreur ni modification | | |
| 8.13 | À l'ajout (option 1), échéance `2026-02-30` | Refusée, possibilité de corriger ou d'annuler, aucune tâche invalide créée | | |

### Scénario 9 — Sauvegarde et redémarrage

**Situation de départ :** aucun fichier, puis création des deux tâches du parcours de l'énoncé.

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 9.1 | Ajouter la tâche 1 (Élevée, échéance), la tâche 2 (défaut), terminer la tâche 1, changer la priorité de la tâche 2 à Élevée, sauvegarder | « Sauvegarde réussie : 2 tâches enregistrées. » ; fichier créé à l'emplacement documenté | | |
| 9.2 | Quitter (0), redémarrer, afficher | 2 tâches chargées ; mêmes ID (1 et 2), titres, priorités, échéances, statuts | | |
| 9.3 | Vérifier la tâche sans échéance | Toujours « Aucune » (pas de date par défaut ni de « null » affiché) | | |
| 9.4 | Vérifier la tâche terminée | Statut Terminée conservé | | |
| 9.5 | Ouvrir le fichier dans un éditeur de texte | Contenu cohérent avec le format documenté dans le README | | |
| 9.6 | Modifier une tâche **sans sauvegarder**, quitter, redémarrer | Modification absente *(comportement documenté dans le README)* | | |
| 9.7 | Sauvegarder une liste vide, redémarrer | Démarrage normal avec liste vide | | |
| 9.8 | Sauvegarder deux fois de suite | Le fichier reste valide, sans doublon | | |

### Scénario 10 — Ajout après rechargement

**Situation de départ :** fichier avec les tâches 1 et 2 (suite du scénario 9).

| Cas | Données / actions | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 10.1 | Redémarrer, ajouter « Préparer le poste du nouvel employé » | ID **3**, distinct des ID 1 et 2 | | |
| 10.2 | Afficher la liste | Les 3 tâches, aucune écrasée | | |
| 10.3 | Sauvegarder, redémarrer, ajouter une autre tâche | ID 4 | | |
| 10.4 | Fichier dont l'ID le plus élevé est 7 (avec des trous : 1, 4, 7) | La prochaine tâche reçoit un ID distinct (ex. 8) | | |

### Scénario 11 — Fichier absent ou mal formé

| Cas | Situation de départ | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 11.1 | Fichier absent | « Aucun fichier de données trouvé. » ; démarrage avec liste vide ; le fichier n'est pas créé à ce stade | | |
| 11.2 | `corrompu-contenu.*` (contenu aléatoire) | « le fichier de données est illisible ou mal formé. Démarrage interrompu. Le fichier n'a pas été modifié. » ; le menu n'apparaît pas | | |
| 11.3 | `corrompu-champ.*` (date impossible, priorité inconnue) | Même message ; démarrage interrompu | | |
| 11.4 | Fichier tronqué (moitié du contenu valide) | Même message ; démarrage interrompu | | |
| 11.5 | `vide.*` (0 octet) | Comportement défini et documenté *(mal formé, ou liste vide)* | | |
| 11.6 | Comparer le fichier avant/après 11.2 à 11.4 | **Contenu strictement identique** *(ex. `sha256sum` ou `diff` avant et après)* | | |
| 11.7 | Aucune trace de pile (exception) visible | Message clair uniquement | | |

### Scénario 12 — Échec d'écriture

**Situation de départ :** application démarrée avec au moins une tâche en mémoire.

| Cas | Préparation | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 12.1 | Fichier de données en lecture seule | « impossible d'écrire dans le fichier de données. Les modifications n'ont pas été sauvegardées. » ; **aucun** message de réussite | | |
| 12.2 | Dossier du fichier inexistant ou en lecture seule | Même comportement | | |
| 12.3 | Après l'échec, continuer à utiliser l'application | Les tâches en mémoire sont intactes ; l'application ne se ferme pas | | |
| 12.4 | Rétablir les droits, sauvegarder à nouveau | « Sauvegarde réussie » ; fichier valide | | |
| 12.5 | Si un fichier valide existait avant l'échec | Il n'est pas détruit *(ou la limite est documentée)* | | |

**Comment provoquer l'échec**

| Système | Commande |
|---|---|
| Linux / macOS | `chmod 444 data/taskflow.json` (rétablir : `chmod 644 data/taskflow.json`) |
| Windows | Clic droit sur le fichier → Propriétés → cocher « Lecture seule » |
| Tous | Configurer l'application avec un chemin dans un dossier qui n'existe pas *(si le chemin est configurable)* |

### Parcours de bout en bout (section 5 de l'énoncé)

**Situation de départ :** aucun fichier de données.

| Étape | Action | Résultat attendu | Observé | Conclusion |
|---|---|---|---|---|
| 1 | Démarrer avec une liste vide | Menu, message « aucun fichier » | | |
| 2 | Ajouter « Vérifier la sauvegarde du serveur », Élevée, échéance valide | ID 1 | | |
| 3 | Ajouter « Mettre à jour le guide utilisateur », Normale, sans échéance | ID 2 | | |
| 4 | Afficher la liste | Les 2 tâches avec leurs ID | | |
| 5 | Modifier la priorité de la tâche 2 | Priorité modifiée | | |
| 6 | Marquer la tâche 1 terminée | Statut Terminée | | |
| 7 | Sauvegarder | « Sauvegarde réussie : 2 tâches enregistrées. » | | |
| 8 | Quitter puis redémarrer | « 2 tâches chargées avec succès. » | | |
| 9 | Afficher la liste | Mêmes ID et toutes les informations | | |
| 10 | Ajouter une autre tâche | ID 3, sans conflit | | |

## 5. Anomalies connues

Consigner **honnêtement** chaque écart constaté, corrigé ou non. Une anomalie documentée est préférable à une anomalie cachée.

| # | Scénario / cas | Description | Étapes pour reproduire | Gravité | État | Lien (Issue / PR) |
|---|---|---|---|---|---|---|
| A-01 | *(ex. 4.3)* | *(ex. un titre de plus de 80 caractères décale les colonnes)* | *(ajouter un titre de 100 caractères, afficher)* | Mineure | Non corrigée | #NN |

*(Si aucune anomalie n'a été trouvée, l'écrire explicitement : « Aucune anomalie constatée lors des vérifications du AAAA-MM-JJ ».)*

## 6. Reproduire les vérifications

1. Cloner le dépôt et se placer sur le commit indiqué en section 1.
2. Compiler et exécuter l'application selon le [README](../README.md).
3. Préparer la situation de départ de chaque scénario (section 2).
4. Exécuter les cas dans l'ordre et noter le résultat observé.
5. Reporter les écarts dans la section 5.

## 7. Historique des vérifications

| Date | Version / commit | Portée | Testeur(s) | Résumé |
|---|---|---|---|---|
| AAAA-MM-JJ | v0.1 | Scénarios 1 à 4 | *(noms)* | *(ex. 4/4 conformes)* |
| AAAA-MM-JJ | v1.0-mp1 | Scénarios 1 à 12 + parcours | *(noms)* | *(ex. 11/12 conformes, 1 anomalie mineure)* |
