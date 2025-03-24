

package com.monprojet;

import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controleur {

    @FXML
    private TextField nameField, emailField, searchField, idField;

    @FXML
    private TableView<Utilisateur> userTable;
    @FXML
    private TableColumn<Utilisateur, Integer> idColumn;
    @FXML
    private TableColumn<Utilisateur, String> nameColumn;
    @FXML
    private TableColumn<Utilisateur, String> emailColumn;
    @FXML
    private TableColumn<Utilisateur, Void> actionsColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        ajouterBoutonsActions();
        chargerUtilisateurs();
    }



    @FXML
private void modifierUtilisateur() {
    if (userTable.getSelectionModel().getSelectedItem() == null) {
        afficherAlerte("Erreur", "Veuillez sélectionner un utilisateur à modifier.");
        return;
    }

    Utilisateur utilisateur = userTable.getSelectionModel().getSelectedItem();
    afficherPopupModification(utilisateur);
}



private void afficherPopupModification(Utilisateur utilisateur) {
    Stage popupStage = new Stage();
    popupStage.setTitle("Modifier Utilisateur");
    popupStage.initModality(Modality.APPLICATION_MODAL);

    VBox popupVBox = new VBox(10);
    popupVBox.setPadding(new Insets(15));

    Label nameLabel = new Label("Nom:");
    TextField nameInput = new TextField(utilisateur.getName());

    Label emailLabel = new Label("Email:");
    TextField emailInput = new TextField(utilisateur.getEmail());

    Button btnModifier = new Button("Modifier");
    btnModifier.setOnAction(e -> {
        String newName = nameInput.getText();
        String newEmail = emailInput.getText();

        if (newName.isEmpty() || newEmail.isEmpty()) {
            afficherAlerte("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        GestionUtilisateur.modifierUtilisateur(utilisateur.getId(), newName, newEmail);
        chargerUtilisateurs();
        popupStage.close();
    });

    popupVBox.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput, btnModifier);
    Scene popupScene = new Scene(popupVBox, 300, 200);
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
}


    private void ajouterBoutonsActions() {
        actionsColumn.setCellFactory(param -> new TableCell<Utilisateur, Void>() {
            private final Button btnModifier = new Button("Modifier");
            private final Button btnSupprimer = new Button("Supprimer");
            private final HBox buttonsBox = new HBox(10, btnModifier, btnSupprimer);

            {
                btnModifier.setOnAction(event -> {
                    Utilisateur utilisateur = getTableView().getItems().get(getIndex());
                    afficherPopupModification(utilisateur);
                });

                btnSupprimer.setOnAction(event -> {
                    Utilisateur utilisateur = getTableView().getItems().get(getIndex());
                    supprimerUtilisateurDepuisTable(utilisateur);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsBox);
                }
            }
        });
    }




    @FXML
private void afficherCreationUtilisateur() {
    ajouterUtilisateur(); // Appelle la méthode pour afficher le pop-up
}

@FXML
private void afficherRechercheUtilisateur() {
    if (searchField == null) {
        afficherAlerte("Erreur", "Champ de recherche non initialisé.");
        return;
    }

    String keyword = searchField.getText();
    if (keyword.isEmpty()) {
        afficherAlerte("Erreur", "Veuillez entrer un nom ou un email pour la recherche.");
        return;
    }

    ObservableList<Utilisateur> users = FXCollections.observableArrayList(GestionUtilisateur.rechercherUtilisateur(keyword));
    userTable.setItems(users);
}

    @FXML
    private void ajouterUtilisateur() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Ajouter un utilisateur");
        popupStage.initModality(Modality.APPLICATION_MODAL);

        VBox popupVBox = new VBox(10);
        popupVBox.setPadding(new Insets(15));

        Label nameLabel = new Label("Nom:");
        TextField nameInput = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();

        Button btnAjouter = new Button("Ajouter");
        btnAjouter.setOnAction(e -> {
            String name = nameInput.getText();
            String email = emailInput.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
       


            if (name.isEmpty() || email.isEmpty()) {
                afficherAlerte("Erreur", "Veuillez remplir tous les champs.");
                return;
            }

            GestionUtilisateur.ajouterUtilisateur(name, email);
            chargerUtilisateurs();
            popupStage.close();
            
        });

        popupVBox.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput, btnAjouter);
        Scene popupScene = new Scene(popupVBox, 300, 200);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();


    }
    
    

    private void chargerUtilisateurs() {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList(GestionUtilisateur.listerUtilisateurs());
        userTable.setItems(users);
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void supprimerUtilisateurDepuisTable(Utilisateur utilisateur) {
        // Création de l'alerte de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer l'utilisateur");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer " + utilisateur.getName() + " ?");
    
        // Obtenir la réponse de l'utilisateur
        ButtonType boutonOui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType boutonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(boutonOui, boutonNon);
    
        // Afficher l'alerte et attendre la réponse
        alert.showAndWait().ifPresent(response -> {
            if (response == boutonOui) {
                GestionUtilisateur.supprimerUtilisateur(utilisateur.getId());
                chargerUtilisateurs();
                afficherAlerteInfo("Suppression réussie", "L'utilisateur a été supprimé avec succès.");
            }
        });
    }
    
    // Méthode pour afficher une alerte d'information après la suppression
    private void afficherAlerteInfo(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void afficherListeUtilisateurs() {
        chargerUtilisateurs();
    }

    @FXML
    private void rechercherUtilisateur() {
        if (searchField == null) {
            afficherAlerte("Erreur", "Champ de recherche non initialisé.");
            return;
        }

        String keyword = searchField.getText();
        if (keyword.isEmpty()) {
            afficherAlerte("Erreur", "Veuillez entrer un nom ou un email pour la recherche.");
            return;
        }

        ObservableList<Utilisateur> users = FXCollections.observableArrayList(GestionUtilisateur.rechercherUtilisateur(keyword));
        userTable.setItems(users);
    }

    @FXML
    private void fermerApplication() {
        Stage stage = (Stage) userTable.getScene().getWindow();
        stage.close();
    }
}

    

    
