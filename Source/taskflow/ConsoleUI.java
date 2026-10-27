package taskflow;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class ConsoleUI {

    private final GestionnaireTaches gestionnaire;
    private final Scanner scanner;

    public ConsoleUI(GestionnaireTaches gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {

        int choix = -1;

        while (choix != 0) {

            afficherMenu();

            System.out.print("Votre choix : ");

            String saisie = scanner.nextLine();

            try {
                choix = Integer.parseInt(saisie);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : choix invalide.");
                continue;
            }

            switch (choix) {

                case 1:
                    ajouterTache();
                    break;

                case 2:
                    afficherTaches();
                    break;

                case 3:
                    terminerTache();
                    break;

                case 4:
                    modifierPriorite();
                    break;

                case 5:
                    gererEcheance();
                    break;

                case 6:
                    sauvegarder();
                    break;

                case 0:
                    System.out.println("\nFermeture de TaskFlow.");
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Erreur : choix invalide.");
            }
        }
    }

    private void afficherMenu() {

        System.out.println();
        System.out.println("TASKFLOW — Gestion des tâches");
        System.out.println();
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Modifier la priorité d'une tâche");
        System.out.println("5. Gérer l'échéance d'une tâche");
        System.out.println("6. Sauvegarder les données");
        System.out.println("0. Quitter");
        System.out.println();
    }

    // ==============================
    // AJOUTER UNE TÂCHE
    // ==============================

    

private void ajouterTache() {

    System.out.println("\nAJOUTER UNE TÂCHE");

    String titre;
    Priorite priorite;
    LocalDate echeance;

    // 1. Saisie et validation du titre
    while (true) {

        System.out.print("\nTitre : ");
        titre = scanner.nextLine().trim();

        if (!titre.isEmpty()) {
            break;
        }

        System.out.println(
                "Erreur : le titre est obligatoire. "
                + "Veuillez saisir un titre ou annuler l'ajout."
        );

        if (!demanderCorrectionOuAnnulation()) {
            System.out.println(
                    "Ajout annulé. Aucune tâche créée."
            );
            return;
        }
    }

    // 2. Saisie et validation de la priorité
    while (true) {

        System.out.println(
                "\nPriorité : Basse, Normale ou Élevée"
        );
        System.out.println(
                "Appuyez sur Entrée pour choisir Normale."
        );
        System.out.print("Votre priorité : ");

        String textePriorite = scanner.nextLine();

        try {
            priorite = Priorite.depuisTexte(textePriorite);
            break;

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erreur : la priorité doit être "
                    + "Basse, Normale ou Élevée."
            );

            if (!demanderCorrectionOuAnnulation()) {
                System.out.println(
                        "Ajout annulé. Aucune tâche créée."
                );
                return;
            }
        }
    }

    // 3. Saisie et validation de l'échéance
    while (true) {

        System.out.println(
                "\nÉchéance au format AAAA-MM-JJ"
        );
        System.out.println(
                "Appuyez sur Entrée pour ne pas préciser d'échéance."
        );
        System.out.print("Votre échéance : ");

        String texteDate = scanner.nextLine().trim();

        if (texteDate.isEmpty()) {
            echeance = null;
            break;
        }

        // Vérifier d'abord le format attendu
        if (!texteDate.matches("\\d{4}-\\d{2}-\\d{2}")) {

            System.out.println(
                    "Erreur : format de date invalide. "
                    + "Le format attendu est AAAA-MM-JJ."
            );

        } else {

            try {
                echeance = LocalDate.parse(texteDate);
                break;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Erreur : " + texteDate
                        + " n'est pas une date valide."
                );
            }
        }

        if (!demanderCorrectionOuAnnulation()) {
            System.out.println(
                    "Ajout annulé. Aucune tâche créée."
            );
            return;
        }
    }

    // 4. Création : uniquement après validation de toutes les saisies
    Tache tache = gestionnaire.ajouterTache(
            titre,
            priorite,
            echeance
    );

    System.out.println("\nTâche ajoutée avec succès.");
    afficherDetails(tache);
}


private boolean demanderCorrectionOuAnnulation() {

    while (true) {

        System.out.print(
                "Voulez-vous corriger la saisie "
                + "ou annuler l'ajout ? (C/A) : "
        );

        String choix = scanner.nextLine().trim();

        if (choix.equalsIgnoreCase("C")) {
            return true;
        }

        if (choix.equalsIgnoreCase("A")) {
            return false;
        }

        System.out.println(
                "Erreur : veuillez saisir C pour corriger "
                + "ou A pour annuler."
        );
    }
}

