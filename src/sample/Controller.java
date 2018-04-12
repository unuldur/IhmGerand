package sample;

import data.Cartes;
import data.Categories;
import data.Plats;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Carte;
import model.Categorie;
import model.IElement;
import model.Plat;
import treeview.CarteTreeItem;
import treeview.ElementTreeCell;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    public ListView<Carte> menuList;
    public TextField titreMenu;
    public CheckBox useMenu;
    public TreeView<IElement> tree;
    public HBox image;

    private int numCarte = 1;
    private ObjectProperty<Carte> carteUse = new SimpleObjectProperty<>();
    private ObservableList<Carte> cartes = FXCollections.observableArrayList();

    public void ajouterCarteAction(ActionEvent actionEvent) {
        Carte c = new Carte("Carte " + numCarte);
        Cartes.getInstance().getCartes().add(c);
        cartes.add(c);
        menuList.getSelectionModel().selectLast();
        numCarte++;
    }

    public void useCarteAction(ActionEvent actionEvent) {
        for (Carte c :
                Cartes.getInstance().getCartes()) {
            if(c.equals(carteUse.get())) continue;
            c.setUse(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cartes = FXCollections.observableArrayList(Cartes.getInstance().getCartes());
        carteUse.setValue(new Carte(""));
        menuList.itemsProperty().bind(new SimpleListProperty<>(cartes));
        menuList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        carteUse.bind(menuList.getSelectionModel().selectedItemProperty());
        tree.setShowRoot(false);
        carteUse.addListener((observable, oldValue, newValue) -> {
            if(oldValue != null){
                titreMenu.textProperty().unbindBidirectional(oldValue.nameProperty());
                useMenu.selectedProperty().unbindBidirectional(oldValue.useProperty());
            }
            if(newValue != null){
                titreMenu.textProperty().bindBidirectional(newValue.nameProperty());
                useMenu.selectedProperty().bindBidirectional(newValue.useProperty());
                CarteTreeItem c= new CarteTreeItem(newValue);
                tree.setRoot(c);
            }
            tree.setCellFactory((final TreeView<IElement> treeView) -> {
                ElementTreeCell etc = new ElementTreeCell((observable1, oldValue1, newValue1) -> {
                    CarteTreeItem c = new CarteTreeItem(carteUse.getValue());
                    tree.setRoot(c);
                });
                etc.setOnElementChange(e -> {
                    CarteTreeItem c = new CarteTreeItem(carteUse.getValue());
                    tree.setRoot(c);
                });
                return etc;
            });
        });
        titreMenu.textProperty().addListener((c, o ,n)->{
            menuList.refresh();
        });

    }

    public void onDeleteCarte(ActionEvent actionEvent) {
        cartes.remove(menuList.getSelectionModel().getSelectedItem());
        Cartes.getInstance().getCartes().remove(menuList.getSelectionModel().getSelectedItem());
        menuList.getSelectionModel().selectFirst();
        if(cartes.isEmpty()){
            titreMenu.setText("");
            useMenu.setSelected(false);
            tree.setRoot(null);
        }
    }
}
