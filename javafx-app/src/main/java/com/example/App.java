package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/App.fxml"));
        VBox root = loader.load();

        // Récupérer les éléments de l'interface
        Label messageLabel = (Label) root.lookup("#messageLabel");
        Button clickButton = (Button) root.lookup("#clickButton");

        // Ajouter un événement au bouton
        clickButton.setOnAction(event -> {
            messageLabel.setText("Vous avez cliqué !");
        });

        // Créer une scène avec le label
        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        // Configurer la fenêtre principale
        primaryStage.setTitle("Ma Première Application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
