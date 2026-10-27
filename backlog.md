# Backlog — TaskFlow (Équipe 10)

**Cours :** INF1763 — Mini-projet 1 — Automne 2026  
**Dépôt :** https://github.com/brianklm/TaskFlow_Equipe10_new  
**Suivi officiel :** [Issues GitHub](https://github.com/brianklm/TaskFlow_Equipe10_new/issues)

Ce document récapitule les 11 User Stories connues. **Les états et affectations doivent être mis à jour à partir des issues GitHub** ; la présence d'une méthode Java ne prouve pas qu'une US est validée.

| US | Besoin utilisateur | Dépendance fonctionnelle | Classes principalement concernées | Vérification minimale | État à confirmer |
|---|---|---|---|---|---|
| [US-01](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/1) | Afficher le menu et quitter proprement | Aucune | `Main`, `ConsoleUI` | Menu visible, saisie invalide gérée, option 0 ferme l'application | À valider |
| [US-02](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/2) | Ajouter une tâche avec valeurs par défaut | Modèle `Tache` disponible | `ConsoleUI`, `GestionnaireTaches`, `Tache` | Titre valide, priorité normale, sans échéance, statut à faire | À valider |
| [US-03](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/3) | Ajouter avec priorité et échéance | US-02 | `ConsoleUI`, `GestionnaireTaches`, `Tache`, `Priorite` | Priorité et date choisies conservées | À valider |
| [US-04](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/4) | Refuser un ajout invalide ; corriger ou annuler | US-02 / US-03 | `ConsoleUI` | Titre vide et saisies incorrectes refusés ; correction/annulation possibles | À valider |
| [US-05](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/5) | Afficher la liste des tâches | US-02 (confirmée dans l'issue) | `ConsoleUI`, `GestionnaireTaches`, `Tache` | ID, titre, priorité, échéance, statut, total | À valider |
| [US-06](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/6) | Afficher un message si la liste est vide | Affichage de liste | `ConsoleUI`, `GestionnaireTaches` | Message explicite quand il n'y a aucune tâche | À valider |
| [US-07](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/7) | Marquer une tâche comme terminée | Tâche existante | `ConsoleUI`, `GestionnaireTaches`, `Tache` | Le statut change pour l'ID choisi | À valider |
| [US-08](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/8) | Signaler une tâche déjà terminée ou un ID inconnu | US-07 | `ConsoleUI`, `GestionnaireTaches` | Deux messages distincts, aucun changement indésirable | À valider |
| [US-09](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/9) | Modifier la priorité | Tâche existante | `ConsoleUI`, `GestionnaireTaches`, `Tache` | Nouvelle priorité visible | À valider |
| [US-10](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/10) | Attribuer ou modifier une échéance | Tâche existante | `ConsoleUI`, `GestionnaireTaches`, `Tache` | Date valide ajoutée ou remplacée | À valider |
| [US-11](https://github.com/brianklm/TaskFlow_Equipe10_new/issues/11) | Retirer une échéance | Gestion d'échéance | `ConsoleUI`, `GestionnaireTaches`, `Tache` | Échéance absente après retrait | À valider |

## Informations connues sur l'attribution

- **US-01 :** Brian (indiqué par le membre responsable dans les échanges de l'équipe).
- **US-05 :** auteur Odile, relecteur Christ Emmanuel ; priorité 3 ; estimation 3 points ; état « à faire » lors du partage de sa description. **Auteur et responsable de développement ne sont pas nécessairement la même personne.**
- Pour les autres US : consulter le champ *Assignees* de chaque issue ; ne pas déduire le responsable du seul auteur.

## Priorisation et dépendances

Il n'est pas nécessaire de développer toutes les US dans l'ordre numérique. Le socle (`Main`, `Tache`, `GestionnaireTaches`, `ConsoleUI`) doit toutefois être disponible pour intégrer les fonctionnalités. US-02 facilite les essais de US-03, US-05, US-07 et US-09 ; US-08 s'appuie sur US-07 ; US-11 s'appuie sur la gestion d'échéance.

## Définition de « terminé »

1. Critères d'acceptation de l'issue respectés.
2. Compilation réussie et tests manuels consignés dans `verification.md`.
3. Modifications limitées à la fonctionnalité, avec un commit explicite.
4. Pull Request relue par un autre membre et fusionnée dans `main`.
5. Issue fermée après intégration.

> Les dépendances non explicitement indiquées comme « confirmées » ci-dessus sont des recommandations techniques, à ajuster selon les issues officielles.
