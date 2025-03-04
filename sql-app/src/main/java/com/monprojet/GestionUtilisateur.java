package com.monprojet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionUtilisateur {
    ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    Connexion link = null;

    public GestionUtilisateur(Connexion plink) {
        this.link = plink;
    }

    public void listUtilisateurs () {
        try {
            Statement stmt = this.link.connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nom, email FROM utilisateur");
            System.out.println("Listes des utilisateurs:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");

                System.out.println("ID : " + id + ", Nom : " + nom + ", Email : " + email);
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void addUtilisateurs(Utilisateur utilisateur) {
        try {

            if (utilisateur.isValidNom()) {
                String sqlInsert = "INSERT INTO utilisateur (nom, email) VALUES (?, ?)";
                PreparedStatement pstmtInsert = this.link.connexion.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, utilisateur.getNom());
                pstmtInsert.setString(2, utilisateur.getEmail());
                pstmtInsert.executeUpdate();

                this.utilisateurs.add(utilisateur);
                
                System.out.println("Insertion réussie.");
            } else {
                System.out.println("Nom non valide !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }



        public void deleteUtilisateurs(int id) {
            try {
                String sqlDelete = "DELETE FROM utilisateur WHERE id = ?";
                PreparedStatement pstmtDelete = this.link.connexion.prepareStatement(sqlDelete);
                pstmtDelete.setInt(1, id);
                int affectedRows = pstmtDelete.executeUpdate();
                
                if (affectedRows > 0) {
                    System.out.println("Utilisateur supprimé avec succès.");
                } else {
                    System.out.println("Aucun utilisateur trouvé avec cet ID.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur de connexion : " + e.getMessage());
    }
}
    

}   




    }
}