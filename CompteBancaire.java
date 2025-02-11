public class CompteBancaire {

    private String titulaire;
    private double solde;


    public CompteBancaire(String titulaire, double montant) {
        this.titulaire = titulaire;
        this.solde = montant;
    }
        
      public void deposer(int montant){
        if(montant>0 ){
        this.solde += montant;
        System.out.println(this.titulaire+ " Vous avez déposé " + montant + " euros");
        System.out.println(this.titulaire+ " Votre nouveau solde est de " + this.solde+"euros" );

    }
}
    
 public void retirer (double montant){
    if(this.solde - montant > 0){
        this.solde -= montant;
        System.out.println( "Vous avez retiré " + montant + " euros");
        System.out.println(this.titulaire+ "votre nouveau solde est de "+this.solde);

    }
 } 

}
