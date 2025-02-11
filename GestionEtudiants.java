import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GestionEtudiants {
    private static ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("\n1. Ajouter un étudiant");
            System.out.println("2. Afficher la liste des étudiants");
            System.out.println("3. Supprimer un étudiant par nom");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne

            switch (choix) {
                case 1 -> ajouterEtudiant();
                case 2 -> afficherEtudiants();
                case 3 -> supprimerEtudiant();
                case 4 -> System.out.println("Programme terminé.");
                default -> System.out.println("Choix invalide, essayez encore.");
            }
        } while (choix != 4);
    }

    private static void ajouterEtudiant() {
        System.out.print("Entrez le nom de l'étudiant : ");
        String nom = scanner.nextLine();
        listeEtudiants.add(new Etudiant(nom)); // Création d'un objet Étudiant
        System.out.println("Étudiant ajouté !");
    }

    private static void afficherEtudiants() {
        if (listeEtudiants.isEmpty()) {
            System.out.println("Aucun étudiant enregistré.");
        } else {
            System.out.println("\nListe des étudiants :");
            for (Etudiant e : listeEtudiants) {
                System.out.println(e);
            }
        }
    }

    private static void supprimerEtudiant() {
        System.out.print("Entrez le nom de l'étudiant à supprimer : ");
        String nom = scanner.nextLine();
        Iterator<Etudiant> iterator = listeEtudiants.iterator();
        boolean trouvé = false;

        while (iterator.hasNext()) {
            Etudiant e = iterator.next();
            if (e.getNom().equalsIgnoreCase(nom)) {
                iterator.remove();
                trouvé = true;
                System.out.println("Étudiant supp.");
                break;
            }
        }
        if (!trouvé) {
            System.out.println("Étudiant non trouvé.");
        }
    }
}