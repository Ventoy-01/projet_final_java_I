// package etudiant.pret.gestion;

import java.util.Scanner;
/**
* The main class for managing student "prets" and "rembousement".
*/
public class Main {

 /**
  * The main method that runs the program.
  * It displays a menu for managing "pret" and "Remboursements".
  * 
  * @param args the command line arguments
  */
 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix = 0; 
        Prets pret = new Prets();
        Remboursements remboursement = new Remboursements(pret);

        do {
            System.out.println("\n\nMenu:\n");
            System.out.println("1. Gérer les Préts");
            System.out.println("2. Gérer les Remboursements");
            System.out.println("3. Quitter");
            System.out.print("\nVotre choix: ");
            String choixx = scanner.nextLine();
            try {
                choix = Integer.parseInt(choixx);
                switch (choix) {
                    case 1:
                    pret.gererPrets();
                    break;
                    case 2:
                        remboursement.gererRemboursements();
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
                System.out.println("Veuillez entrer un nombre entre 1 et 3: ");
            }
        } 
        while (choix != 3);
        scanner.close();
    }
}
        


