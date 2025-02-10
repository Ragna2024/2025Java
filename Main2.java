public class Main2 {
    public static void main(String[] args) {
        CompteBancaire compte1 =
        new CompteBancaire("Jean", 1000.0);
        CompteBancaire compte2 = new CompteBancaire ("Laissad", 10000.0);

        
        compte1.retirer(100);
        compte2.deposer(1000);
        
        
        
    }
}
