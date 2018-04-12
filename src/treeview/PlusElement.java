package treeview;

import model.IElement;

import java.util.List;

public class PlusElement implements IElement {

    private String name;

    public PlusElement(String name) {
        this.name = name;
    }

    public PlusElement() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
