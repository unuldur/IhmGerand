package addCategorie;

import actionWindow.ActionWindow;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Categorie;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCategorie implements Initializable{
    public TextField nameText;
    public Button deleteButton;
    public Button addButton;
    private Categorie categorie;
    private ActionWindow action;

    public void initCategorie(Categorie cat){
        nameText.setText(cat.getName());
        deleteButton.setManaged(true);
        categorie = cat;
        addButton.setText("Modifier");
    }

    public void nameInit(String name){
        nameText.setText(name);
    }

    public void onCancel(ActionEvent actionEvent) {
        action = ActionWindow.CANCEL;
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez vous vraiment supprimer cette categorie.\n Elle sera supprimé de toutes les formules et de tous menus.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            return;
        }
        action = ActionWindow.DELETE;
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onAdd(ActionEvent actionEvent) {
        if(nameText.getText().isEmpty()) return;
        if(categorie == null) {
            categorie = new Categorie(nameText.getText());
        }else {
            categorie.setName(nameText.getText());
        }
        action = ActionWindow.ADD;
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public ActionWindow getAction() {
        return action;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteButton.setManaged(false);
    }
}
