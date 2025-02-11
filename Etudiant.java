class Etudiant {
    private String nom; // Attribut privé

    // Constructeur
    public Etudiant(String nom) {
        this.nom = nom;
    }

    // Getter pour récupérer le nom
    public String getNom() {
        return nom;
    }

    // Méthode d'affichage
    @Override
    public String toString() {
        return "Étudiant: " + nom;
    }
}