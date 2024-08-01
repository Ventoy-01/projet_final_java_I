// package pret.etudiant.gestion;

import java.util.Scanner;

/**
 * The main class for managing student "prets" and "rembousement".
 */
public class Main {

    /**
     * The main method that runs the program.
     * It displays a menu for managing "prets" and "rembousements".
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        Prets prets = new Prets();
        Remboursements remboursements = new Remboursements();

        do {
            System.out.println("\n\n Menu:\n");
            System.out.println("\n 1. Gérer les Prêts");
            System.out.println("\n 2. Gérer les Remboursements");
            System.out.println("\n 3. Quitter");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();

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
                    System.out.println("Choix invalide.");
            }
        } while (choix != 3);

        scanner.close();
    }
}
