package com.monprojet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
        String url = "jdbc:mysql://localhost:3306/mabase"; // Remplacer "maBase" par le nom de votre base
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;

        System.out.println( "Hello World!" );
        try {
            // Établir la connexion
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie !");

            try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement stmt = conn.createStatement()) {

            String sqlInsert = "INSERT INTO utilisateur (nom, email) VALUES ('Alice', 'alice@example.com')";
            int rowsAffected = stmt.executeUpdate(sqlInsert);
            System.out.println("Insertion réussie, nombre de lignes affectées : " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de l'INSERT : " + e.getMessage());
        }
    

            
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        } finally { // Toujours fermer la connexion pour éviter les fuites de ressources 
	        if (connexion != null) { 
		        try { 
			        connexion.close(); 
			        System.out.println("Connexion fermée avec succès."); 
			    } catch (SQLException e) { 
				    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage()); 
				} 
                
			} 
		}


        
    }
}

