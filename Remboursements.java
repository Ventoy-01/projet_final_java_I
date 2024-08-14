import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Remboursements {

    private Scanner scanner = new Scanner(System.in);

    private String idRemboursement;
    private String idPret;
    private String nomVersement;
    private String dateRemboursement;
    private double versementPeriodique;
    private double montantReste;

    private ArrayList<Remboursements> remboursements;
    private ArrayList<Double> versements;  // Liste pour stocker les versements effectués

    private Prets pret;
    

    public String getIdRemboursement() {
        return idRemboursement;
    }

    public void setIdRemboursement(String idRemboursement) {
        this.idRemboursement = idRemboursement;
    }

    public String getIdPret() {
        return idPret;
    }

    public void setIdPret(String idPret) {
        this.idPret = idPret;
    }

    public String getNomVersement() {
        return nomVersement;
    }

    public void setNomVersement(String nomVersement) {
        this.nomVersement = nomVersement;
    }

    public String getDateRemboursement() {
        return dateRemboursement;
    }

    public void setDateRemboursement(String dateRemboursement) {
        this.dateRemboursement = dateRemboursement;
    }

    public double getVersementPeriodique() {
        return versementPeriodique;
    }

    public void setVersementPeriodique(double versementPeriodique) {
        this.versementPeriodique = versementPeriodique;
    }

    public double getMontantReste() {
        return montantReste;
    }

    public void setMontantReste(double montantReste) {
        this.montantReste = montantReste;
    }

    public Prets getPret() {
        return pret;
    }

    public void setPret(Prets pret) {
        this.pret = pret;
    }

    public Remboursements(String idRemboursement, String idPret, String nomVersement, double versementPeriodique, Prets pret) {
        this.idRemboursement = idRemboursement;
        this.idPret = idPret;
        this.nomVersement = nomVersement;
        this.versementPeriodique = versementPeriodique;
        this.dateRemboursement = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.pret = pret;
        this.versements = new ArrayList<>();  // Initialiser la liste des versements
    }

    public Remboursements(Prets pret) {
        this.remboursements = new ArrayList<>();
        this.pret = pret;
        this.versements = new ArrayList<>();
    }

    public void enregistrerRemboursement() {
        boolean verifyPret = false;
        Prets catchPret;
        int nbreEnregistrement = 0;
        String montantString;

        while (true) {
            System.out.print("Entrer l'ID du Pret: ");
            idPret = scanner.nextLine();

            catchPret = pret.rechercherPret(idPret);

            if (catchPret != null) {
                verifyPret = true;
                break;
            } else {
                System.out.println("ID du prêt introuvable");
                System.out.println("Voulez-vous réessayer? (tapez 'fin' pour arrêter)");
                String choix = scanner.nextLine();
                if (choix.equalsIgnoreCase("fin")) {
                    return; // Arrêter la méthode si l'utilisateur choisit "fin"
                }
            }
        }

        if (verifyPret) {
            double montDu = catchPret.getMontantDu();
            double montantEnAttente = catchPret.getMontantEnAttente();
            double versementPeriodique = catchPret.getVersementPeriodique();
            int numeroVersement = catchPret.getNumeroVersement();

            if (montDu <= 0) {
                System.out.println("========================================");
                System.out.println("Cet étudiant n'a pas de dette.");
                System.out.println("========================================");
                return;
            }
            while (true) {
                System.out.println("\n==================== INFO ========================\n");
                System.out.println("montant en attente : " + catchPret.getMontantEnAttente());
                System.out.println("montant en periodique : " + versementPeriodique);
                System.out.println("montant dû : " + montDu);
                System.out.println("--------------------------------------------------\n");
                System.out.print("Entrer le montant  : ");
                montantString = scanner.nextLine();
                System.out.println("===============================================");
                try {
                    double convert = Double.parseDouble(montantString);
                    if(convert > 0){    
                        break;
                    }
                    else{
                        System.out.println("Le montant doit etre positif.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Essayez des nombre reels!");
                }
            }
            double montant = Double.parseDouble(montantString);

            montantEnAttente += montant;
            // a veriifye
            if(montantEnAttente > versementPeriodique * 4){
                System.out.println("Le montant entre + le montant en attente est superieur au somme dû " + montDu);
                System.out.println("Verifiez si vous n'avez pas une somme en attente.");
                return;
            }
            montantReste = montantEnAttente;

            while (montantReste >= versementPeriodique && numeroVersement < 5) {
                idRemboursement = "R-" + idPret + "-" + numeroVersement;
                String nomVersement = "Versement " + numeroVersement;

                Remboursements remboursement = new Remboursements(idRemboursement, idPret, nomVersement, versementPeriodique, pret);
                remboursement.versements.add(versementPeriodique); // Ajouter le versement à la liste
                this.remboursements.add(remboursement); 

                numeroVersement++;
                catchPret.setNumeroVersement(numeroVersement);
                montantReste -= versementPeriodique;
                nbreEnregistrement++;
            }

            montDu -= montant;
            catchPret.setMontantDu(montDu);
            catchPret.setMontantEnAttente(montantReste);

            if (nbreEnregistrement > 0) {
                System.out.println("==================================================================================================");
                System.out.println("Le montant restant + le montant donné vous permet de faire " + nbreEnregistrement + " versement(s).");
                System.out.println("Le montant restant après versement: " + montantReste);
                System.out.println("==================================================================================================");
            } else {
                System.out.println("==================================================================================");
                System.out.println("Le montant restant + le montant donné n'est pas suffisant pour faire un versement.");
                System.out.println("Le montant en cours: " + montantReste);
                System.out.println("==================================================================================");
            }
            if (pret.getMontantDu() <= 0) {
                pret.setEstPaye(true);  // Le prêt est marqué comme payé
            }
            
        }
    }

    public void afficherRemboursements() {
        int i = 1;
        for (Remboursements remboursement : remboursements) {
            System.out.println("================== REMBOURSEMENT "+ i + " ========================");
            System.out.println("\nID: " + remboursement.getIdRemboursement());
            System.out.println("ID Pret: " + remboursement.getIdPret());
            System.out.println("Nom Versement: " + remboursement.getNomVersement());
            System.out.println("Date Remboursement: " + remboursement.getDateRemboursement());
            System.out.println();
            i++;
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
                 System.out.println("\nVeuillez entrer un nombre entre 1 et 3: ");
            }
        } while (choix != 3);
    }
}
