package smartHome;

import org.junit.Before;
import org.junit.Test;
import smartHome.SmartHomeDir.Components.Door;
import smartHome.SmartHomeDir.Components.Light;
import smartHome.SmartHomeDir.Components.Room;
import smartHome.SmartHomeDir.SmartHome;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestComposIteration {
    private SmartHome smartHome;
    private Collection<Room> rooms;
    private Collection<Door> doors;
    private Collection<Light> lights;


    @Before
    public void setUp() {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");

        smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));

        rooms  = new HashSet<>();
        lights = new HashSet<>();
        doors  = new HashSet<>();

        for (Room room : smartHome.getRooms()){
            rooms.add( room );
            for (Light light : room.getLights())
                lights.add(light);
            for (Door door : room.getDoors())
                doors.add(door);
        }
    }

    @Test
    public void testDelAll() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) rooms.remove(object);
            if (object instanceof Light) lights.remove(object);
            if (object instanceof Door) doors.remove(object);
        });
        assertEquals(0, rooms.size());
        assertEquals(0, lights.size());
        assertEquals(0, doors.size());
    }

    @Test
    public void testDelOnlyRooms() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) rooms.remove(object);
        });
        assertEquals(0, rooms.size());
    }

    @Test
    public void testDelDoorsLightsNotRooms() {
        int countRooms = smartHome.getRooms().size();
        smartHome.executeAction(object -> {
            if (object instanceof Light) lights.remove(object);
            if (object instanceof Door) doors.remove(object);
        });
        assertEquals(0, lights.size());
        assertEquals(0, doors.size());
        assertEquals(countRooms, rooms.size());
    }
}
