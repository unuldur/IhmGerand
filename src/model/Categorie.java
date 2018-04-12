package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Categorie implements IElement, Comparable{
    private StringProperty name = new SimpleStringProperty();

    public Categorie(String name) {
        this.name.set(name);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return name.get();
    }


    @Override
    public int compareTo(Object o) {
        return getName().compareTo(((Categorie)o).getName());
    }
}
