package ru.sbt.mipt.oop.TestEventProcessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.Events.SensorEvent;
import ru.sbt.mipt.oop.Events.SensorEventType;
import ru.sbt.mipt.oop.SmartHomeDir.Components.Door;
import ru.sbt.mipt.oop.SmartHomeDir.Components.Light;
import ru.sbt.mipt.oop.SmartHomeDir.Components.Room;
import ru.sbt.mipt.oop.SmartHomeDir.SmartHome;

import java.util.Arrays;

import static org.junit.Assert.*;

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