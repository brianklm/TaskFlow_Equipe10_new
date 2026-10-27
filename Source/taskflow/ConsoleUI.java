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

        System.out.print("\nTitre : ");
        String titre = scanner.nextLine();

        if (titre.trim().isEmpty()) {
            System.out.println(
                    "Erreur : le titre est obligatoire."
            );
            return;
        }

        System.out.println(
                "\nPriorité : Basse, Normale ou Élevée"
        );

        System.out.println(
                "Appuyez sur Entrée pour choisir Normale."
        );

        System.out.print("Votre priorité : ");
        String textePriorite = scanner.nextLine();

        Priorite priorite;

        try {

            priorite =
                    Priorite.depuisTexte(textePriorite);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erreur : " + e.getMessage()
            );

            return;
        }

        System.out.println(
                "\nÉchéance au format AAAA-MM-JJ"
        );

        System.out.println(
                "Appuyez sur Entrée pour ne pas préciser d'échéance."
        );

        System.out.print("Votre échéance : ");

        String texteDate = scanner.nextLine();

        LocalDate echeance = null;

        if (!texteDate.trim().isEmpty()) {

            try {

                echeance =
                        LocalDate.parse(texteDate.trim());

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Erreur : date ou format invalide."
                );

                System.out.println(
                        "Le format attendu est AAAA-MM-JJ."
                );

                return;
            }
        }

        Tache tache = gestionnaire.ajouterTache(
                titre,
                priorite,
                echeance
        );

        System.out.println(
                "\nTâche ajoutée avec succès."
        );

        afficherDetails(tache);
    }
}
    
