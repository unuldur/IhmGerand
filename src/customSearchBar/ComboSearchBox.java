package customSearchBar;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;


public class ComboSearchBox<T> extends ComboBox<T> {
    private ObservableList<T> initialList;
    private ObservableList<T> bufferList = FXCollections.observableArrayList();
    private String previousValue = "";
    private ChangeListener<String> cl = (observable, oldValue, newValue) -> {
        previousValue = oldValue;
        final TextField editor = this.getEditor();
        final T selected = this.getSelectionModel().getSelectedItem();
        if (selected == null || !selected.toString().equals(editor.getText())) {
            filterItems(newValue, this);

            this.show();
        }
    };
    public ComboSearchBox() {
        super(FXCollections.observableArrayList());
        super.setEditable(true);
        initialList = FXCollections.observableArrayList();
        this.configAutoFilterListener();
    }


    public ComboSearchBox(ObservableList<T> items) {
        super(items);
        super.setEditable(true);

        this.initialList = items;

        this.configAutoFilterListener();
    }

    public void configAutoFilterListener() {
        this.getEditor().textProperty().addListener(cl);
    }

    public void removeListeners(){
        getEditor().textProperty().removeListener(cl);
    }

    private void filterItems(String filter, ComboBox<T> comboBox) {
        if (filter.startsWith(previousValue) && !previousValue.isEmpty()) {
            ObservableList<T> filteredList = this.readFromList(filter, bufferList);
            bufferList.clear();
            bufferList = filteredList;
        } else {
            bufferList = this.readFromList(filter, initialList);
        }
        System.out.println(bufferList);
        comboBox.setItems(bufferList);
    }

    private ObservableList<T> readFromList(String filter, ObservableList<T> originalList) {
        ObservableList<T> filteredList = FXCollections.observableArrayList();
        for (T item : originalList) {
            if (item.toString().toLowerCase().startsWith(filter.toLowerCase())) {
                filteredList.add(item);
            }
        }

        return filteredList;
    }

    public ObservableList<T> getAllItems() {
        return initialList;
    }
}
