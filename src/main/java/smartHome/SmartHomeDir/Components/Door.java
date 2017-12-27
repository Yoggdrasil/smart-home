package smartHome.SmartHomeDir.Components;

import smartHome.SmartHomeDir.Action;
import smartHome.SmartHomeDir.Actionable;

public class Door implements Actionable{
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public boolean is_open() {
        return isOpen;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}
