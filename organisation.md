# Organisation du travail — TaskFlow (Équipe 10)

**Cours :** INF1763 — Mini-projet 1 — Automne 2026  
**Dépôt :** https://github.com/brianklm/TaskFlow_Equipe10_new

## Équipe et coordination

| Membre | Responsabilité de coordination |
|---|---|
| Kelome Mamondé Brian | Dépôt et intégrations |
| Trabant Christ Emmanuel | Vérifications |
| Kolié Odile Haoussa | Documentation |
| Touré Karim | Backlog et projets GitHub |
| Agoua Sopie Doryne Marie-Noëlle | Suivi des itérations et rétrospectives |

Ces rôles portent sur la coordination ; les responsables des User Stories sont désignés dans les **Issues**. Les identifiants GitHub des membres ne sont pas inventés.

## Outils et livrables

- **GitHub Issues :** suivi des 11 User Stories et de leurs critères d'acceptation.
- **Branches Git :** une branche par fonctionnalité, par exemple `feature/US-01`.
- **Pull Requests :** revue avant fusion dans `main`.
- **`backlog.md` :** périmètre, dépendances et suivi.
- **`verification.md` :** scénarios, résultats et anomalies.
- **`README.md` :** installation, exécution et présentation du projet.

## Processus de contribution

1. Consulter l'issue et confirmer son responsable.
2. Récupérer `main` et vérifier que le répertoire de travail est propre.
3. Créer une branche dédiée : `git switch -c feature/US-01`.
4. Modifier uniquement les portions de code nécessaires à l'US.
5. Compiler et exécuter les scénarios de `verification.md`.
6. Ajouter les fichiers concernés, puis créer un commit descriptif.
7. Envoyer la branche : `git push -u origin feature/US-01`.
8. Ouvrir une Pull Request vers `main` ; demander une revue à un autre membre.
9. Corriger les observations, fusionner et mettre à jour l'issue.

**Ne pas utiliser `git push --force` sur `main`.** Avant un `pull --rebase`, sauvegarder les modifications locales non commitées (commit ou stash). Après une intégration, revérifier les fonctions déjà livrées.

## Coordination des fichiers Java

| Fichier | Utilisation principale | Risque de conflit |
|---|---|---|
| `Main.java` | Démarrage, US-01 | Faible |
| `ConsoleUI.java` | Menu et interactions, US-01 à US-11 | **Élevé** |
| `Tache.java` | Modèle et propriétés des tâches | Moyen |
| `Priorite.java` | Valeurs de priorité | Faible |
| `GestionnaireTaches.java` | Ajout, consultation, statut, priorité, échéance | **Élevé** |
| `GestionFichier.java` | Sauvegarde et chargement | Moyen |

**Règle d'équipe :** annoncer dans l'issue ou la Pull Request les méthodes modifiées dans `ConsoleUI.java` et `GestionnaireTaches.java`. Éviter de remplacer intégralement les fichiers d'un coéquipier. Préférer de petites Pull Requests intégrées régulièrement.

## Itérations proposées (à confirmer avec l'équipe)

| Itération | Objectif | User Stories indicatives |
|---|---|---|
| 1 — Socle | Lancement et création de tâches | US-01, US-02, US-03, US-04 |
| 2 — Consultation | Liste et gestion des statuts | US-05, US-06, US-07, US-08 |
| 3 — Mise à jour | Priorité, échéance, stabilisation | US-09, US-10, US-11 |

Les itérations ci-dessus sont une **proposition**, pas un historique réel. Les dates, participants, décisions et rétrospectives doivent être ajoutés à mesure que le travail avance.

## Modèle de suivi d'itération

| Date | Itération | Travail accompli | Difficultés | Décisions | Prochaine action |
|---|---|---|---|---|---|
| À compléter | À compléter | À compléter | À compléter | À compléter | À compléter |

## Règles de revue

- Vérifier la cohérence avec les critères de l'issue.
- Confirmer qu'aucune autre US n'a été cassée.
- Contrôler la compilation et les scénarios de vérification.
- Ne pas fermer une issue avant validation et intégration.
- Mettre à jour les documents si les commandes, chemins ou comportements changent.
