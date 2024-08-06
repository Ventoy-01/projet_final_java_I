// package pret.etudiant.gestion;

import java.util.Scanner;
/**
 * The main class for managing student "prets" and "rembousement".
 */
public class Main {

    /**
     * The main method that runs the program.
     * It displays a menu for managing "prets" and "remboursements".
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix = 0; 
        Prets prets = new Prets();
        Remboursements remboursements = new Remboursements(prets);

        do {
            System.out.println("\n\nMenu:\n");
            System.out.println("1. Gérer les Prêts");
            System.out.println("2. Gérer les Remboursements");
            System.out.println("3. Quitter");
            System.out.print("\nVotre choix: ");
            String choixx = scanner.nextLine();

            try {
                choix = Integer.parseInt(choixx);
                switch (choix) {
                    case 1:
                        prets.gererPrets();
                        break;
                    case 2:
                        remboursements.gererRemboursements();
                        break;
                    case 3:
                        System.out.println("Au revoir!");
                        break;
                    default:
                        System.out.println("!!! Oupps Choix invalide.");
                        break;
                }
            } 
            catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entre 1 à 3" + e.getMessage());
            }
        } 
        while (choix != 3);
        scanner.close();
    }
}
        

