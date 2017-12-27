package smartHome.SmartHomeDir.Components;

import smartHome.SmartHomeDir.Action;
import smartHome.SmartHomeDir.Actionable;

import java.util.Collection;

public class Room implements Actionable{
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for(Light light : lights)
            light.executeAction(action);

        for(Door door : doors)
            door.executeAction(action);
    }
}
