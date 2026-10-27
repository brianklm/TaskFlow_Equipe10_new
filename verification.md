# Vérification — TaskFlow (Équipe 10)

**Cours :** INF1763 — Mini-projet 1 — Automne 2026  
**Dépôt :** https://github.com/brianklm/TaskFlow_Equipe10_new

## 1. Portée et statut

Ce document contient des **scénarios de test à exécuter**. Aucun résultat n'est présenté comme « réussi » sans preuve d'exécution. Remplir les colonnes *Résultat observé*, *Statut*, *Date* et *Testeur* au fur et à mesure.

## 2. Environnement et démarrage

Prérequis : JDK 17+, terminal UTF-8 et code Java à jour.

Pour l'archive dont les sources sont dans `src/taskflow/` :

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -Dfile.encoding=UTF-8 -cp out taskflow.Main
```

Pour le dépôt local utilisant `Source/taskflow/`, remplacer `src` par `Source` dans la compilation. **Attention :** un fichier `Main.java` vide peut laisser la compilation se terminer sans erreur tout en provoquant `ClassNotFoundException` à l'exécution.

**Persistance :** `GestionFichier.java` référence `data/taches.txt` relativement au dossier de lancement. L'archive contient aussi `src/data/taches.txt` ; vérifier le chemin réellement lu et écrit avant de tester la sauvegarde.

## 3. Scénarios fonctionnels

| Test | US | Étapes | Résultat attendu | Statut |
|---|---|---|---|---|
| V01 | US-01 | Démarrer le programme | Menu principal affiché | Non exécuté |
| V02 | US-01 | Saisir une option inconnue puis `0` | Message d'erreur, puis fermeture propre | Non exécuté |
| V03 | US-02 | Ajouter une tâche avec titre, sans priorité ni date | Tâche créée, priorité normale, échéance absente, statut à faire | Non exécuté |
| V04 | US-03 | Ajouter une tâche avec priorité élevée et date valide | Valeurs choisies conservées | Non exécuté |
| V05 | US-04 | Saisir un titre vide | Refus de l'ajout ; possibilité de corriger ou annuler | Non exécuté |
| V06 | US-04 | Saisir une date invalide | Refus de la date ; possibilité de corriger ou annuler | Non exécuté |
| V07 | US-05 | Ajouter deux tâches puis afficher la liste | ID, titre, priorité, échéance, statut et total `2` visibles | Non exécuté |
| V08 | US-06 | Afficher la liste sans tâche | Message explicite de liste vide | Non exécuté |
| V09 | US-07 | Terminer une tâche existante | Son statut devient « terminée » | Non exécuté |
| V10 | US-08 | Terminer à nouveau la même tâche | Message « déjà terminée », sans changement | Non exécuté |
| V11 | US-08 | Saisir un ID inexistant | Message d'identifiant inconnu | Non exécuté |
| V12 | US-09 | Modifier la priorité d'une tâche | Nouvelle priorité visible dans la liste | Non exécuté |
| V13 | US-10 | Attribuer une échéance puis la modifier | Nouvelle date visible | Non exécuté |
| V14 | US-11 | Retirer l'échéance d'une tâche | Aucune échéance affichée | Non exécuté |
| V15 | Persistance | Ajouter une tâche, sauvegarder, fermer, relancer | Tâche et ID conservés | Non exécuté |
| V16 | Persistance | Démarrer sans fichier de données | Démarrage sans blocage avec liste vide | Non exécuté |
| V17 | Régression | Répéter V01, V07, V09 après intégration | Fonctionnalités antérieures inchangées | Non exécuté |

**Attention :** V05 et V06 vérifient l'exigence de correction/annulation ; un simple retour au menu ne suffit pas nécessairement à satisfaire US-04.

## 4. Fiche de résultat à remplir

| Test | Date | Testeur | Résultat observé | Statut (Réussi / Échec / Bloqué) | Issue liée |
|---|---|---|---|---|---|
| V01 | — | — | — | Non exécuté | #1 |
| V02 | — | — | — | Non exécuté | #1 |
| V03 à V17 | — | — | — | Non exécuté | À compléter |

## 5. Vérification des fichiers de données

- **Fichier absent :** vérifier le démarrage avec liste vide.
- **Sauvegarde explicite :** utiliser l'option du menu prévue à cet effet, puis inspecter `data/taches.txt`.
- **Rechargement :** redémarrer et vérifier les ID, titres, priorités, échéances et statuts.
- **Date incorrecte :** vérifier que l'application ne conserve pas une échéance invalide.
- **Fichier mal formé :** tester sur une copie, sans écraser les données de l'équipe.
- **Titre contenant `;` :** vérifier le comportement ; le format texte séparé par `;` peut nécessiter une validation ou un échappement.

## 6. Journal des anomalies

| Référence | Description | Reproduction | Gravité | Responsable | État |
|---|---|---|---|---|---|
| A-01 (à confirmer) | Différence entre `src/data/taches.txt` dans l'archive et `data/taches.txt` dans le code | Démarrer depuis la racine puis sauvegarder | À évaluer | À attribuer | À vérifier |
| A-02 (à confirmer) | Versions locales de fichiers Java potentiellement vides | Vérifier le contenu et lancer `taskflow.Main` | À évaluer | À attribuer | À vérifier |

Ces points sont des **risques à examiner**, pas des anomalies validées du dernier code fusionné.

## 7. Validation finale

- [ ] Compilation sans erreur
- [ ] Démarrage du programme
- [ ] US-01 à US-11 vérifiées selon leurs critères officiels
- [ ] Sauvegarde et rechargement testés
- [ ] Anomalies bloquantes corrigées
- [ ] Revue par un autre membre
- [ ] Pull Requests fusionnées dans `main`
- [ ] README, backlog et organisation à jour
