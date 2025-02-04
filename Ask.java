
    import java.util.Scanner;

    public class Ask {
    
        public static void main(String [] args) {
            Scanner scanner = new Scanner(System.in);
    
            System.out.print( "Entrez votre nom : ");
            String nom = scanner.nextLine();
    
            System.out.print( "Entrez votre age : ");
            int age = scanner.nextInt();
    
            System.out.println("Bonjour, " + nom + ", et vous avez " + age + " ans");
    
            scanner.close();

            System.out.println("bonjour" + nom + " ! vous avez " + age + "ans");

            if(age > 18)  {

                System.out.println("vous etes majeur");


            }
            else if (age < 18){

                System.out.println("vous etes mineur");
            }

            if(age >80 && age < 100)

            System.out.println("nezuuuzu");
        }
    }
    