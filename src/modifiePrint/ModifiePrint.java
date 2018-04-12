package modifiePrint;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ModifiePrint extends HBox {
    private Button modifie;
    private Button delete;
    public enum ButtonType{
        BUTTON_MOD,
        BUTTON_DEL
    }

    public ModifiePrint(Node n, List<ButtonType> buttons) {
        getChildren().add(n);
        Button b = new Button("mod");
        Button b2 = new Button("del");
        b.setVisible(false);
        b2.setVisible(false);
        b.setManaged(false);
        b2.setManaged(false);
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(1,1,1,1));
        setOnMouseEntered(event -> {
            if(buttons.contains(ButtonType.BUTTON_MOD)){
                b.setVisible(true);
                b.setManaged(true);
            }
            if(buttons.contains(ButtonType.BUTTON_DEL)){
                b2.setVisible(true);
                b2.setManaged(true);
            }
        });

        setOnMouseExited(e ->{
            b.setVisible(false);
            b2.setVisible(false);
            b.setManaged(false);
            b2.setManaged(false);
        });
        modifie = b;
        delete = b2;
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        getChildren().addAll(r, b, b2);
    }

    public ModifiePrint(Node n) {
        this(n, Arrays.asList(ButtonType.BUTTON_DEL, ButtonType.BUTTON_MOD));
    }

    public Button getModifie() {
        return modifie;
    }

    public Button getDelete() {
        return delete;
    }
}
