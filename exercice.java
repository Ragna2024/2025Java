import java.util.Arrays;

public class exercice {
    public static void main(String[] args) {
        int[] nombre= {12, 7, 22, 5, 17, 9, 14, 30, 25, 8};

        System.out.println("Tableau initial: " + Arrays.toString(nombre));

        // Trier le tableau
        Arrays.sort(nombre);
        System.out.println("Tableau trié: " + Arrays.toString(nombre));

        // Trouver min et max
        int min = nombre[0];
        int max = nombre[nombre.length - 1];
        System.out.println("Valeur minimale: " + min);
        System.out.println("Valeur maximale: " + max);

        // Calcul de la moyenne
        double moyenne = calculerMoyenne(nombre);
        System.out.println("Moyenne: " + moyenne);

        // Calcul de la médiane
        double mediane = calculerMediane(nombre);
        System.out.println("Médiane: " + mediane);

        // Calcul de l'écart-type
        double ecartType = calculerEcartType(nombre, moyenne);
        System.out.println("Écart-type: " + ecartType);
    }

    public static double calculerMoyenne(int[] tableau) {
        double somme = 0;
        for (int num : tableau) {
            somme += num;
        }
        return somme / tableau.length;
    }

    public static double calculerMediane(int[] tableau) {
        int n = tableau.length;
        if (n % 2 == 0) {
            return (tableau[n / 2 - 1] + tableau[n / 2]) / 2.0;
        } else {
            return tableau[n / 2];
        }
    }

    public static double calculerEcartType(int[] tableau, double moyenne) {
        double somme = 0;
        for (int num : tableau) {
            somme += Math.pow(num - moyenne, 2);
        }
        return Math.sqrt(somme / tableau.length);
    }
}
