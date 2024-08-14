//  package etudiant.pret.gestion;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

/**
* The class for "prets" (prets)
*/
public class Prets {    
    private String idPret;
    private String nomEtudiant;
    private String prenomEtudiant;
    private int niveauEtudiant;
    private double montantEmprunte;
    private double interet;
    private double versementPeriodique;
    private String datePret;
    private double montantDu;
    
    private boolean estPaye;
    private double montantEnAttente;
    private int numeroVersement = 1;
    private static int countPret = 1;

    Scanner scanner = new Scanner(System.in);
    /**
     * An ArrayList, it extends the {@link ArrayList} class
    */
    private ArrayList<Prets> prets ;

    /**
     * The constructor with parameters of class {@link #Prets}
    * 
    * @param idPret the identification of the "pret"
    * @param nomEtudiant the student's last name
    * @param prenomEtudiant the student's first name
    * @param niveauEtudiant the student's level
    * @param montantEmprunte the amount borrowed
    */
    Prets(String nomEtudiant, String prenomEtudiant, int niveauEtudiant, double montantEmprunte) {
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.niveauEtudiant = niveauEtudiant;
        this.montantEmprunte = montantEmprunte;
        this.interet = montantEmprunte * 0.055;
        this.montantDu = montantEmprunte + interet;
        this.versementPeriodique = montantDu / 4;
        this.estPaye = false;  // Initialisation par défaut à false
        this.datePret = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.idPret =  ""+countPret++;
    }
    

    /**
     * The constructor without any parameters of class {@link #Prets}
    */
    public Prets() {
        this.prets = new ArrayList<>();

    }
    // Constructeur et autres méthodes
    

    public boolean getEstPaye() {
        return estPaye;
    }

    public static int getCountPret() {
        return countPret;
    }


    public static void setCountPret(int countPret) {
        Prets.countPret = countPret;
    }


    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }
    
    public int getNumeroVersement() {
        return numeroVersement;
    }

    public void setNumeroVersement(int numeroVersement) {
        this.numeroVersement = numeroVersement;
    }

    public String getIdPret() {
        return idPret;
    }

    public void setIdPret(String idPret) {
        this.idPret = idPret;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    public int getNiveauEtudiant() {
        return niveauEtudiant;
    }

    public void setNiveauEtudiant(int niveauEtudiant) {
        this.niveauEtudiant = niveauEtudiant;
    }

    public double getMontantEmprunte() {
        return montantEmprunte;
    }

    public void setMontantEmprunte(double montantEmprunte) {
        this.montantEmprunte = montantEmprunte;
    }

    public double getInteret() {
        return interet;
    }

    public void setInteret(double interet) {
        this.interet = interet;
    }

    public double getVersementPeriodique() {
        return versementPeriodique;
    }

    public void setVersementPeriodique(double versementPeriodique) {
        this.versementPeriodique = versementPeriodique;
    }

    public String getDatePret() {
        return datePret;
    }

    public void setDatePret(String datePret) {
        this.datePret = datePret;
    }
    

    public double getMontantDu() {
        return montantDu;
    }

    public void setMontantDu(double montantDu) {
        this.montantDu = montantDu;
    }

    public double getMontantEnAttente() {
        return montantEnAttente;
    }

    public void setMontantEnAttente(double montantEnAttente) {
        this.montantEnAttente = montantEnAttente;
    }

    /**
     * Checks if a given string starts with an alpha character and contains only alphanumeric characters.
    * The string length must be at least 3.
    * 
    * @param text the string to check
    * @return the string if it matches the specified pattern, otherwise {@code null}
    */
    public String valideInputText(String text){
        System.out.println(text);
        String textCatch = scanner.nextLine();
        String regex = "^[a-zA-Z][a-zA-Z0-9]{2,}$";
        if (Pattern.matches(regex, textCatch)){
            return textCatch;
        }
        return null;
    }

    /**
     * Validates the student's level
    * 
    * @return the valid level choice
    */
    public int valideNiveau(){
        int choix;
        while (true) {
            System.out.println("\nMenu pour le niveau:");
            System.out.println("2. Pour 2éme Année");
            System.out.println("3. Pour 3éme Année");
            System.out.println("4. Pour 4éme Année");
            System.out.print("Votre choix: ");
            String choixx = scanner.nextLine();
            try{
                choix = Integer.parseInt(choixx);

            if (choix >= 2 && choix <= 4) {
                break;
            } else {
                System.out.println("Votre choix est invalide, choisir (2, 3 ou 4)");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\nVeuillez entrer un chiffre " + e.getMessage());
        }
        }
        return choix;
    }

    /**
     * Validates if the given string can be parsed to a double
    * 
    * @param money the string to check
    * @return {@code true} if the string can be parsed to a double, otherwise {@code false}
    */
    public boolean valideMoney(String money){
        try {
            Double.parseDouble(money);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un nombre valide "+ e.getMessage());
        }
        return false;
    }

    public boolean peutFaireUnPret(String nom, String prenom) {
        for (Prets pret : prets) {
            if (pret.getNomEtudiant().equals(nom) && pret.getPrenomEtudiant().equals(prenom)) {
                // Si l'étudiant a un prêt non payé, il ne peut pas faire un autre prêt
                if (!pret.getEstPaye() && pret.getMontantDu() > 0) {
                    return false;
                }
            }
        }
        // L'étudiant peut faire un autre prêt si tous les prêts précédents sont payés
        return true;
    }    
    
    /**
     * Registers a "pret" by prompting the user for input and validating it
    */
    public void enregistrerPret() {

        System.out.println();
        // for the student's last name
        while (true){
            String textNom = "Entrer le nom de l'étudiant: ";
            String catchNom = valideInputText(textNom).toLowerCase();
            if (catchNom != null){
                nomEtudiant = catchNom;
                break;
            } else {
                System.out.println("Nom invalide (seulement lettres (au commencement) et chiffres, min 3)");
            }
        }
        // for the student's first name
        while (true){
            String textPrenom = "Entrer le prénom de l'étudiant: ";
            String catchPrenom = valideInputText(textPrenom).toLowerCase();
            if (catchPrenom != null ){
                prenomEtudiant = catchPrenom;
                break;
            } else {
                System.out.println("Prénom invalide (seulement lettres (au commencement) et chiffres, min 3)");
            }
        }

        boolean peutFaireUnPret = peutFaireUnPret(nomEtudiant, prenomEtudiant);
        if(!peutFaireUnPret){
            System.out.println("L'étudiant a un prêt en cours avec un montant dû. Il ne peut pas faire un autre versement.");
            return;
        }
        else{
            // for the student's level
            int niveau = valideNiveau();
            niveauEtudiant = niveau;
            // for the amount borrowed
            while (true){
                scanner.nextLine();
                System.out.println("Entrer le montant emprunté");
                String montant = scanner.nextLine();
                if (valideMoney(montant)){
                    montantEmprunte = Double.parseDouble(montant);
                    if(montantEmprunte > 0){
                        break;
                    }
                    else{
                        System.out.println("Le montant doit etre positif.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre valide");
                }
            }
            Prets pret = new Prets(nomEtudiant, prenomEtudiant, niveauEtudiant, montantEmprunte);
            prets.add(pret);
            System.out.println("Prêt enregistré avec succès.\n");
            }
    }

    /**
     * Displays the details of a "pret"
    */
    /**
     * Displays all "prets" in the ArrayList {@code prets}
    */
    public void afficherPrets() {
        int i = 1;
        for (Prets pret : prets) {
            System.out.println("=========================== PRET "+ i+ " =============================");
            System.out.println("L'ID du prét : " + pret.getIdPret());
            System.out.println("Nom de l'étudiant : " + pret.getNomEtudiant());
            System.out.println("Prénom de l'étudiant : " + pret.getPrenomEtudiant());
            System.out.println("Niveau de l'étudiant : " + pret.getNiveauEtudiant());
            System.out.println("Montant emprunté : " + pret.getMontantEmprunte());
            System.out.println("Intérét : " +pret.getInteret());
            System.out.println("Versement périodique : " + pret.getVersementPeriodique());
            System.out.println("Date du prét : " + pret.getDatePret());
            i++;
            System.out.println();
        }
    }


    /**
     * Manages the "prets" by displaying a menu for the user to choose from
    */
        public void gererPrets() {
            int choix =0;
            do{
                System.out.println("\nGestion des préts:");
                System.out.println("1. Enregistrer un prét");
                System.out.println("2. Afficher tous les préts");
                System.out.println("3. Retour au menu principale");
                System.out.print("Votre choix: ");
                String choixx = scanner.nextLine();
                try{
                    choix = Integer.parseInt(choixx);
                    switch (choix) {
                        case 1:
                            enregistrerPret();
                            break;
                        case 2:
                        if (prets.size() == 0){
                            System.out.println("\nPas de pret enregistrer!");
                        }
                        else{
                            afficherPrets();
                        }
                        break;
                        case 3:
                            break;
                        default:
                            System.out.println("\nChoix invalide.");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("\nVeuillez entrer un nombre entre 1 et 2 " + e.getMessage());
                }
        }
        while(choix != 3);
    }
 

    public Prets rechercherPret(String idPret){
        for (Prets pret : prets) {  
            if (pret.getIdPret().equals(idPret)) {
                return pret;
            }
        }
        return null;
    }
}

