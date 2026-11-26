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


## Mini-projet 2 – TaskFlow 1.1.0

### Récits utilisateurs – Export Excel

#### US-E1 – Exporter la liste des tâches dans Excel
**En tant que** gestionnaire,
**je veux** exporter la liste des tâches dans un classeur Excel (.xlsx)
**afin de** l'utiliser dans mes rapports.

Priorité : Haute · Volet : C (Apache POI `org.apache.poi:poi-ooxml`) · Responsable : Karim

**Critères d'acceptation**
- [ ] Une commande « Exporter vers Excel » est disponible dans le menu principal.
- [ ] Le fichier produit est au format `.xlsx` et contient une feuille nommée « Tâches ».
- [ ] La ligne 1 est une ligne d'en-tête : `ID`, `Titre`, `Priorité`, `Échéance`, `Statut`.
- [ ] Chaque tâche occupe une ligne, avec une colonne par information, dans l'ordre de l'en-tête.
- [ ] Les valeurs exportées sont identiques à celles affichées par la commande de liste.
- [ ] Une tâche sans échéance produit une cellule vide dans la colonne `Échéance`.
- [ ] Le classeur s'ouvre sans erreur dans Microsoft Excel et dans LibreOffice Calc.
- [ ] Après un export réussi, l'application affiche « Export réussi : <chemin> (<n> tâche(s)) ».
- [ ] Un export réussi est journalisé au niveau INFO dans `logs/taskflow.log` (nombre de tâches et chemin, sans titre de tâche).
- [ ] Les fonctionnalités existantes ne changent pas : les scénarios de `verification/` rejoués donnent 0 différence.

#### US-E2 – Choisir le nom du fichier exporté
**En tant que** gestionnaire,
**je veux** choisir le nom du fichier exporté
**afin de** classer mes rapports.

Priorité : Haute · Volet : C · Responsable : Karim

**Critères d'acceptation**
- [ ] Après le choix de la commande d'export, l'application demande le nom du fichier.
- [ ] Si l'utilisateur appuie sur Entrée sans rien saisir, le fichier par défaut `exports/taches.xlsx` est utilisé.
- [ ] Le dossier `exports/` est créé automatiquement s'il n'existe pas, uniquement pour le fichier par défaut.
- [ ] Si le nom saisi ne se termine pas par `.xlsx`, l'extension est ajoutée automatiquement.
- [ ] Le chemin réellement utilisé est affiché à l'utilisateur.
- [ ] Le nom par défaut et son emplacement sont documentés dans le README.

#### US-E3 – Être averti quand l'export est impossible
**En tant que** membre de l'équipe de soutien,
**je veux** un message clair quand l'export est impossible
**afin de** savoir quoi corriger.

Priorité : Haute · Volet : C · Responsable : Karim

**Critères d'acceptation**
- [ ] Si le dossier saisi n'existe pas, l'application affiche un message clair indiquant que le dossier est introuvable.
- [ ] Si le fichier est déjà ouvert dans Excel (Windows), l'application affiche un message clair demandant de fermer le fichier.
- [ ] Aucune trace d'exception (stack trace) n'est affichée dans la console.
- [ ] L'échec est journalisé au niveau ERROR dans `logs/taskflow.log`, avec le chemin visé et l'exception.
- [ ] Aucun message de succès n'est affiché et aucun journal INFO « export réussi » n'est écrit.
- [ ] L'application reste utilisable après l'échec : le menu principal s'affiche de nouveau et les tâches ne sont pas modifiées.

#### US-E4 – Exporter quand il n'y a aucune tâche
**En tant que** gestionnaire,
**je veux** savoir ce qui se passe quand j'exporte une liste vide
**afin de** ne pas être surpris par le résultat.

Priorité : Moyenne · Volet : C · Responsable : Karim

**Critères d'acceptation**
- [ ] Avec une liste vide, le classeur est créé et contient uniquement la ligne d'en-tête.
- [ ] L'application affiche « Export réussi : <chemin> (0 tâche(s)) ».
- [ ] L'export est journalisé au niveau INFO avec 0 tâche.
- [ ] Ce comportement est décrit dans le README (section Export).

### Vérifications associées (docs/verification.md)
| Récit | Scénario de vérification de l'énoncé |
|---|---|
| US-E1, US-E2 | 5. Export nominal |
| US-E4 | 6. Export d'une liste vide |
| US-E3 | 7. Échec d'export |