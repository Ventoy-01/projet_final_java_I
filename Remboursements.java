// package pret.etudiant.gestion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * The class for remboursement (repayment)
 */
class Remboursements {
    /**
     * An ArrayList, it extends the {@link ArrayList} class
     */
    ArrayList<Remboursements> remboursements = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    private String idRemboursement;
    private String idPret;
    private int nomVersement;
    private String dateRemboursement;

    /**
     * An instance of class {@link Prets}
     */
    Prets pret = new Prets();
    /**
     * The constructor with parameters of class {@link #Remboursements}
     * @param idRemboursement the identification of the remboursement
     * @param idPret the identification of the pret
     * @param nomVersement the name of the versement (1 for 1st versement, 2 for 2nd versement, etc.)
     */
    Remboursements(String idRemboursement, String idPret, int nomVersement) {
        this.idRemboursement = idRemboursement;
        this.idPret = idPret;
        this.nomVersement = nomVersement;
        this.dateRemboursement = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
    /**
     * The constructor without any parameters of class {@code Remboursements}
     */
    public Remboursements() {
    }
    /**
     * To validate the versement
     * @return
     * the versement if it is between 1 and 4, otherwise
     * 0 if the versement is 5
     */
    public int valideVersement() {
        int choix;
        while (true) {
            System.out.println("Menu pour le Versement:");
            System.out.println("1. Pour 1er versement");
            System.out.println("2. Pour 2ème versement");
            System.out.println("3. Pour 3ème versement");
            System.out.println("4. Pour 4ème versement");
            System.out.println("5. Quitter");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choix >= 1 && choix <= 4) {
                return choix;
            } else if (choix == 5) {
                return 0;
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }
    /**
     * The specific method for registering a remboursement
     * 
     * @see <p>The remboursement will be registered if the {@code idPret} and the {@code versement} are provided.</p>
     * @see <p>If the {@code idPret} already has four (4) versements, you will receive a message like this:
     *      <i>This student has already paid all their versements.</i>
     * </p>
     */
    public void enregistrerRemboursement() {
        boolean verifyPret = false;
        while (true) {
            System.out.print("Entrer l'ID du Pret: ");
            idPret = scanner.nextLine();
            verifyPret = pret.prets.stream().anyMatch(p -> p.getIdPret().equals(idPret));
            if (verifyPret) {
                break;
            } else {
                System.out.println("ID du pret introuvable");
                System.out.println();
                System.out.println("Voulez-vous réessayer? (tapez 'fin' pour arrêter)");
                String choix = scanner.nextLine();
                if (choix.equalsIgnoreCase("fin")) {
                    return; // Stop the method if the user chooses "fin"
                }
            }
        }

        int versement = valideVersement();
        boolean verifyVersement = versement != 0;

        if (verifyVersement && verifyPret) {
            Remboursements remboursement = new Remboursements(idRemboursement, idPret, nomVersement);
            remboursements.add(remboursement);
            
            double montant = pret.trouverMontantPeriodique(idPret);
            pret.mettreAJourVersements(idPret, nomVersement, montant);

            System.out.println("Remboursement enregistré avec succès.");
        } else {
            System.out.println("Veuillez remplir les champs correctement.");
        }
    }
    /**
     * The specific method for showing all details about remboursement.
     */
    void afficher() {
        System.out.println("ID: " + idRemboursement);
        System.out.println("ID Pret: " + idPret);
        System.out.println("Nom Versement: " + nomVersement);
        System.out.println("Date Remboursement: " + dateRemboursement);
    }
    /**
     * Manages the "remboursements" by displaying a menu for the user to choose from
     */
    public void gererRemboursements() {
        System.out.println("Gestion des Remboursements:");
        System.out.println("1. Enregistrer un Remboursement");
        System.out.println("2. Afficher tous les Remboursements");
        System.out.print("Votre choix: ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choix) {
            case 1:
                enregistrerRemboursement();
                break;
            case 2:
                afficherRemboursements();
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    /**
     * Function to show all remboursements in the ArrayList {@code remboursements}.
     */
    public void afficherRemboursements() {
        for (Remboursements remboursement : remboursements) {
            remboursement.afficher();
            System.out.println();
        }
    }
}
