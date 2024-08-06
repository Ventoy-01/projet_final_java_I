// package pret.etudiant.gestion;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The class for "prets" (prets)
 */
class Prets {    
    private String idPret;
    private String nomEtudiant;
    private String prenomEtudiant;
    private int niveauEtudiant;
    private double montantEmprunte;
    private double interet;
    private double versementPeriodique;
    private String datePret;
    private double versements[];

    Scanner scanner = new Scanner(System.in);
    /**
     * An ArrayList, it extends the {@link ArrayList} class
     */
    ArrayList<Prets> prets = new ArrayList<>();

    /**
     * The constructor with parameters of class {@link #Prets}
     * 
     * @param idPret the identification of the "pret"
     * @param nomEtudiant the student's last name
     * @param prenomEtudiant the student's first name
     * @param niveauEtudiant the student's level
     * @param montantEmprunte the amount borrowed
     */
    Prets(String idPret, String nomEtudiant, String prenomEtudiant, int niveauEtudiant, double montantEmprunte) {
        this.idPret = idPret;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.niveauEtudiant = niveauEtudiant;
        this.montantEmprunte = montantEmprunte;
        this.interet = montantEmprunte * 0.055;
        this.versementPeriodique = (montantEmprunte + interet) / 4;
        this.datePret = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.versements = new double[4];
    }

    /**
     * The constructor without any parameters of class {@link #Prets}
     */
    public Prets() {
        this.prets = new ArrayList<>();
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

    public double[] getVersements() {
        return versements;
    }

    public void setVersements(double[] versements) {
        this.versements = versements;
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
            System.out.println("2. Pour 2ème Année");
            System.out.println("3. Pour 3ème Année");
            System.out.println("4. Pour 4ème Année");
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

    // public boolean valideMoney()

    /**
     * Registers a "pret" by prompting the user for input and validating it
     */
    public void enregistrerPret() {
        System.out.println('\n');
        // for the student's last name
        while (true){
            String textNom = "Entrer le nom de l'étudiant: ";
            String catchNom = valideInputText(textNom);
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
            String catchPrenom = valideInputText(textPrenom);
            if (catchPrenom != null ){
                prenomEtudiant = catchPrenom;
                break;
            } else {
                System.out.println("Prénom invalide (seulement lettres (au commencement) et chiffres, min 3)");
            }
        }
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
                break;
            } else {
                System.out.println("Veuillez entrer un nombre valide");
            }
        }
        idPret = "Pret-"+nomEtudiant.substring(0, 3)+prenomEtudiant.substring(0, 3)+'-'+ niveauEtudiant;
        Prets pret = new Prets(idPret, nomEtudiant, prenomEtudiant, niveauEtudiant, montantEmprunte);
        prets.add(pret);
        System.out.println("Prêt enregistré avec succès.\n");
    }

    /**
     * Displays the details of a "pret"
     */
    void afficher() {
        System.out.println("\n");
        System.out.println("L'ID du prêt : " + idPret);
        System.out.println("Nom de l'étudiant : " + nomEtudiant);
        System.out.println("Prénom de l'étudiant : " + prenomEtudiant);
        System.out.println("Niveau de l'étudiant : " + niveauEtudiant);
        System.out.println("Montant emprunté : " + montantEmprunte);
        System.out.println("Intérêt : " + interet);
        System.out.println("Versement périodique : " + versementPeriodique);
        System.out.println("Date du prêt : " + datePret);
        System.out.print("Versements : ");
        for (double v : versements) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    /**
     * Displays all "prets" in the ArrayList {@code prets}
     */
    public void afficherPrets() {
        for (Prets pret : prets) {
            pret.afficher();
            System.out.println();     
    }
    }

    /**
     * Manages the "prets" by displaying a menu for the user to choose from
     */
    public void gererPrets() {
        int choix =0;
        do{
        System.out.println("\nGestion des prêts:");
        System.out.println("1. Enregistrer un prêt");
        System.out.println("2. Afficher tous les prêts");
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
    
    
    /**
     * Updates the payment array for a specified "pret"
     * 
     * @param idPret the identification of the "pret"
     * @param nomVersement the number of the payment
     * @param montant the amount to update
     */
    public void mettreAJourVersements(String idPret, int nomVersement, double montant) {
        for (Prets pret : prets) {
            if (pret.getIdPret().equals(idPret)) {
                pret.versements[nomVersement - 1] += montant;
                break;
            }
        }
    }

    /**
     * Finds the periodic payment amount for a specified "pret"
     * 
     * @param idPret the identification of the "pret"
     * @return the periodic payment amount
     */
    public double trouverMontantPeriodique(String idPret) {
        for (Prets pret : prets) {
            if (pret.getIdPret().equals(idPret)) {
                return pret.getVersementPeriodique();
            }
        }
        return 0;
    }
   
}
