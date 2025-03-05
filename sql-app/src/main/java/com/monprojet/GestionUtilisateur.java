package com.monprojet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionUtilisateur {
    ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    Connexion link = null;

    public GestionUtilisateur(Connexion plink) {
        this.link = plink;
    }

    public void listUtilisateurs() {
        try {
            Statement stmt = this.link.connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nom, email FROM utilisateur");
            System.out.println("Liste des utilisateurs:");
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
    } // <-- Fermeture correcte de addUtilisateurs

    public void deleteUtilisateurs(int id) {
        try {
            String sqlDelete = "DELETE FROM utilisateur WHERE id = ?";
            PreparedStatement pstmtDelete = this.link.connexion.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, id);
            int affectedRows = pstmtDelete.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Utilisateur supprimé avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void updateUtilisateur(int id, String newNom, String newEmail) {
        try {
            String sqlUpdate = "UPDATE utilisateur SET nom = ?, email = ? WHERE id = ?";
            PreparedStatement pstmtUpdate = this.link.connexion.prepareStatement(sqlUpdate);
            pstmtUpdate.setString(1, newNom);
            pstmtUpdate.setString(2, newEmail);
            pstmtUpdate.setInt(3, id);
            int affectedRows = pstmtUpdate.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Utilisateur mis à jour avec succès.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    
    
    public void searchUtilisateurByEmail(String email) {
        try {
            String sqlSearch = "SELECT id, nom, email FROM utilisateur WHERE email LIKE ?";
            PreparedStatement pstmtSearch = this.link.connexion.prepareStatement(sqlSearch);
            pstmtSearch.setString(1, "%" + email + "%");
            ResultSet rs = pstmtSearch.executeQuery();
            
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id") + ", Nom : " + rs.getString("nom") + ", Email : " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
 }
}

        
public void searchUtilisateurByName(String nom) {
    try {
        String sqlSearch = "SELECT id, nom, email FROM utilisateur WHERE nom LIKE ?";
        PreparedStatement pstmtSearch = this.link.connexion.prepareStatement(sqlSearch);
        pstmtSearch.setString(1, "%" + nom + "%");
        ResultSet rs = pstmtSearch.executeQuery();
        
        while (rs.next()) {
            System.out.println("ID : " + rs.getInt("id") + ", Nom : " + rs.getString("nom") + ", Email : " + rs.getString("email"));
        }
    } catch (SQLException e) {
        System.out.println("Erreur de connexion : " + e.getMessage());
    }
}
}











