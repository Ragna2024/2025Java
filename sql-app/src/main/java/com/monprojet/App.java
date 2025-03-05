package com.monprojet;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        /* On clear la console */
        System.out.print("\033[H\033[2J");   
        System.out.flush();

        System.out.println( "Hello World!" );
        Connexion link = new Connexion();
        GestionUtilisateur gu = new GestionUtilisateur(link);

        /* On demande à l'utilisateur ce qu'il veut faire */
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do { 
            System.out.println("Que voulez vosu faire ?");
            System.out.println("1 - Lister les utilisateurs");
            System.out.println("2 - Ajouter un utilisateur");
            System.out.println("3 - Supprimer un utilisateur");
            System.out.println("4 - Modifier un utilisateur");
            System.out.println("5 - Rechercher un utilisateur par 1(son NOM) ou 2 (son Email)");

            System.out.println("0 - Quitter");
            choice = sc.nextInt();
            
            System.out.print("\033[H\033[2J");   
            System.out.flush(); 
            
            switch (choice) {
                case 1:
                    gu.listUtilisateurs();
                    System.out.println("---------------------");
                    break;

                case 2:
                    System.out.print("Nom de l'utilisateur: ");
                    sc.nextLine();
                    String nom = sc.nextLine();

                    System.out.print("Email de l'utilisateur: ");
                    String email = sc.nextLine();

                    Utilisateur utilisateur = new Utilisateur(nom, email);

                    gu.addUtilisateurs(utilisateur);
                    System.out.println("---------------------");
                    break;

                    case 3:
                    System.out.print("ID de l'utilisateur à supprimer: ");
                    int id = sc.nextInt();
                    gu.deleteUtilisateurs(id);
                    System.out.println("---------------------");
                    break;

                    case 4:
                    System.out.print("ID de l'utilisateur à modifier: ");
                    int editId = sc.nextInt();
                    sc.nextLine(); // Consommer la ligne restante
                    
                    System.out.print("Nouveau nom: ");
                    String newNom = sc.nextLine();
                    
                    System.out.print("Nouvel email: ");
                    String newEmail = sc.nextLine();
                    
                    gu.updateUtilisateur(editId, newNom, newEmail);
                    System.out.println("---------------------");
                    break;

                    case 5:
                    System.out.print("Rechercher par (1: Nom, 2: Email): ");
                    int searchType = sc.nextInt();
                    sc.nextLine(); // Consommer la ligne restante
                    
                    if (searchType == 1) {
                        System.out.print("Entrez le nom: ");
                        String searchNom = sc.nextLine();
                        gu.searchUtilisateurByName(searchNom);
                    } else if (searchType == 2) {
                        System.out.print("Entrez l'email: ");
                        String searchEmail = sc.nextLine();
                        gu.searchUtilisateurByEmail(searchEmail);
                    } else {
                        System.out.println("Choix invalide.");
                    }
                    System.out.println("---------------------");
                    break;

        
  

            
                default:
                    System.out.println("Pas d'action pour ce choix !");
                    break;
            }
        } while(choice != 0);

        link.close();
        sc.close();
    }
}