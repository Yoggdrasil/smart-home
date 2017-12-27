package smartHome.TestEventProcessors;

import org.junit.Before;
import org.junit.Test;
import smartHome.EventProcessors.DoorEventProcessor;
import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;
import smartHome.SmartHomeDir.Components.Door;
import smartHome.SmartHomeDir.Components.Light;
import smartHome.SmartHomeDir.Components.Room;
import smartHome.SmartHomeDir.SmartHome;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoorEventProcessorTest {
    SmartHome smartHome;

    @Before
    public void setUp() throws Exception {
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

        smartHome     = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }

    @Test
    public void testOpenRightDoor() {
        DoorEventProcessor processor = new DoorEventProcessor();
        String objId = "4"; // по-дефолту закрыта
        SensorEvent openDoor  = new SensorEvent(SensorEventType.DOOR_OPEN, objId);


        processor.processEvent(openDoor, smartHome);

        for (Room room : smartHome.getRooms()){
            for (Door door : room.getDoors()){
                if (door.getId().equals(objId)){
                    assertTrue(door.is_open());
                    return;
                }
            }
        }

    }

    @Test
    public  void testCloseRightDoor() {
        DoorEventProcessor processor = new DoorEventProcessor();
        String objId = "3"; // по-дефолту открыта
        SensorEvent openDoor  = new SensorEvent(SensorEventType.DOOR_CLOSED, objId);

        processor.processEvent(openDoor, smartHome);

        for (Room room : smartHome.getRooms()){
            for (Door door : room.getDoors()){
                if (door.getId().equals(objId)){
                    assertFalse(door.is_open());
                    return;
                }
            }
        }

    }
}