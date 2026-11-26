# Diagnostic des mauvaises odeurs – TaskFlow v1.0.0



| # | Catégorie | Fichier / méthode | Preuve | Conséquence | Refactoring proposé |
|---|-----------|-------------------|--------|-------------|---------------------|
| 1 | Duplication | ConsoleUI : terminerTache, modifierPriorite, gererEcheance | Bloc `trouverTache(id)` + `if (tache == null)` + message « aucune tâche ne correspond  à l'identifiant » répété 3 fois | Une correction du message oblige à modifier 3 endroits | Extract Method (`Tache_AfficherErreur`) |
| 2 | Duplication | ConsoleUI.ajouterTache, GestionnaireTaches.ajouterTache, Tache (constructeur) | `titre.trim().isEmpty()` contrôlé 3 fois ; priorité nulle traitée différemment (erreur ou NORMALE) | Règles incohérentes, risque de divergence | Centraliser la validation dans Tache |
| 3 | Méthode longue | ConsoleUI.ajouterTache, gererEcheance, demarrer | 70 lignes mêlant saisie, validation, parsing et affichage | Difficile à lire et à tester | Extract Method (saisie priorité, saisie date…) |
| 4 | Valeurs magiques | GestionFichier ; ConsoleUI ; Priorite | `";"`, `parties.length != 5`, `parties[0..4]` ; `case 1..6`, `"1"`, `"2"`, `"0"` ; `"elevee"` | Changer le format ou le menu casse le code en silence | Constantes / enum d'actions de menu |
| 5 | Code inutilisé | Tache.toString ; GestionnaireTaches (4 méthodes boolean) | toString jamais appelé ; booléens retournés ignorés par ConsoleUI qui refait trouverTache | Code mort, double recherche | Supprimer toString ou l'utiliser ; exploiter les retours |
| 6 | Incohérence / mise en forme | GestionnaireTaches.ajouterTache ; ConsoleUI.definirEcheance, modifierPriorite | Bloc mal indenté ; `trim()` sur la date absent dans definirEcheance ; message d'erreur dupliqué | Comportement différent selon l'écran | Reformater, uniformiser |


## Observations de l'équipe

| Membre | Catégorie | Fichier / méthode | Preuve | Conséquence | Refactoring proposé |
|--------|-----------|-------------------|--------|-------------|---------------------|
| Brian | Défaut fonctionnel (à signaler)  | GestionFichier.sauvegarder / charger | Titre contenant `;` → fichier illisible au rechargement | Perte d'accès aux données| Échappement ou autre format|
| Emmanuel | Duplication|ConsoleUI.gererEcheance, afficherDetails ; Tache.toString | `getEcheance() == null ? "Aucune" : ...` | Même logique à 3 endroits | Méthode `getEcheance_Affiche()` |
| Karim |  Responsabilités mélangées | ConsoleUI.sauvegarder ; GestionFichier.charger ; Main ; Tache.toString | L'UI appelle GestionFichier ; charger() construit un GestionnaireTaches ; toString formate de l'affichage | Couplage fort, classes difficiles à tester | Séparer UI / logique / persistance |
| Odile |  Duplication | ConsoleUI : terminerTache, modifierPriorite, gererEcheance | Bloc `trouverTache(id)` + `if (tache == null)` + message « aucune tâche ne correspond  à l'identifiant » répété 3 fois | Une correction du message oblige à modifier 3 endroits | Extract Method (`Tache_AfficherErreur`) |
