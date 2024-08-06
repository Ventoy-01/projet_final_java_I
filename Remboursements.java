import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Remboursements {

    private ArrayList<Remboursements> remboursements = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private String idRemboursement;
    private String idPret;
    private int nomVersement;
    private String dateRemboursement;

    private Prets pret;  // Référence à l'objet Prets

    public Remboursements(Prets pret) {
        this.pret = pret;
        if (this.pret.prets == null) {
            this.pret.prets = new ArrayList<>();  // Initialiser la liste si elle est null
        }
    }

    public Remboursements(String idRemboursement, String idPret, int nomVersement) {
        this.idRemboursement = idRemboursement;
        this.idPret = idPret;
        this.nomVersement = nomVersement;
        this.dateRemboursement = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public void enregistrerRemboursement() {
        boolean verifyPret = false;

        while (true) {
            System.out.print("Entrer l'ID du Pret: ");
            idPret = scanner.nextLine();
            
            for (Prets pret : this.pret.prets) {  // Utilisation de this.pret.prets
                if (pret.getIdPret().equals(idPret)) {
                    verifyPret = true;
                    break;
                }
            }

            if (verifyPret) {
                break;
            } else {
                System.out.println("ID du prêt introuvable");
                System.out.println();
                System.out.println("Voulez-vous réessayer? (tapez 'fin' pour arrêter)");
                String choix = scanner.nextLine();
                if (choix.equalsIgnoreCase("fin")) {
                    return; // Arrêter la méthode si l'utilisateur choisit "fin"
                }
            }
        }
        
        nomVersement = valideVersement();
        boolean verifyVersement = nomVersement != 0;

        if (verifyVersement && verifyPret) {
            idRemboursement = "R-" + idPret + "-" + nomVersement;
            Remboursements remboursement = new Remboursements(idRemboursement, idPret, nomVersement);
            remboursements.add(remboursement);

            double montant = 0;
            for (Prets pret : this.pret.prets) {  // Utilisation de this.pret.prets
                if (pret.getIdPret().equals(idPret)) {
                    montant = pret.trouverMontantPeriodique(idPret);
                    pret.mettreAJourVersements(idPret, nomVersement, montant);
                    break;
                }
            }

            System.out.println("Remboursement enregistré avec succès.");
        } else {
            System.out.println("Veuillez remplir les champs correctement.");
        }

        System.out.println(this.pret.prets.size());
    }

    public int valideVersement() {
        int choix;
        while (true) {
            System.out.println("\nMenu pour le Versement:");
            System.out.println("1. Pour 1er versement");
            System.out.println("2. Pour 2ème versement");
            System.out.println("3. Pour 3ème versement");
            System.out.println("4. Pour 4ème versement");
            System.out.print("Votre choix: ");
            String choixx = scanner.nextLine();
            try{
                choix = Integer.parseInt(choixx);

            if (choix >= 1 && choix <= 4) {
                return choix;
            } else if (choix == 5) {
                return 0;
            } else {
                System.out.println("Choix invalide.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\nVeuillez entrer un chiffre" + e.getMessage());
        }
        }
    }

    public void gererRemboursements() {
        int choix = 0;
        do {
            System.out.println("\nGestion des Remboursements:");
            System.out.println("1. Enregistrer un Remboursement");
            System.out.println("2. Afficher tous les Remboursements");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix: ");
            String choixx = scanner.nextLine();

            try {
                choix = Integer.parseInt(choixx);
                switch (choix) {
                    case 1:
                        enregistrerRemboursement();
                        break;
                    case 2:
                        if (remboursements.size() == 0) {
                            System.out.println("\nPas de remboursement enregistré!");
                        } else {
                            afficherRemboursements();
                        }
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nVeuillez entrer un nombre entre 1 et 3 " + e.getMessage());
            }
        } while (choix != 3);
    }

    public void afficherRemboursements() {
        for (Remboursements remboursement : remboursements) {
            remboursement.afficher();
            System.out.println();
        }
    }

    void afficher() {
        System.out.println("ID: " + idRemboursement);
        System.out.println("ID Pret: " + idPret);
        System.out.println("Nom Versement: " + nomVersement);
        System.out.println("Date Remboursement: " + dateRemboursement);
    }
}
