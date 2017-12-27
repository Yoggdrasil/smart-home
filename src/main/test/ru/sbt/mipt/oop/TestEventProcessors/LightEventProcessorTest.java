package ru.sbt.mipt.oop.TestEventProcessors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessors.LightEventProcessor;
import ru.sbt.mipt.oop.Events.SensorEvent;
import ru.sbt.mipt.oop.Events.SensorEventType;
import ru.sbt.mipt.oop.SmartHomeDir.Components.Door;
import ru.sbt.mipt.oop.SmartHomeDir.Components.Light;
import ru.sbt.mipt.oop.SmartHomeDir.Components.Room;
import ru.sbt.mipt.oop.SmartHomeDir.SmartHome;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LightEventProcessorTest {
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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLightOn() {
        LightEventProcessor processor = new LightEventProcessor();
        String ObjId = "4"; // по-дефолту выключен
        SensorEvent lightEvent  = new SensorEvent(SensorEventType.LIGHT_ON, ObjId);

        processor.processEvent(lightEvent, smartHome);

        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (light.getId().equals( ObjId )){
                    assertTrue(light.isOn());
                    return;
                }
            }
        }

    }

    @Test
    public void testLightOff() {
        LightEventProcessor processor = new LightEventProcessor();
        String objId = "3"; // по-дефолту включен
        SensorEvent lightEvent  = new SensorEvent(SensorEventType.LIGHT_OFF, objId);

        processor.processEvent(lightEvent, smartHome);

        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (light.getId().equals( objId )){
                    assertFalse(light.isOn());
                    return;
                }
            }
        }

    }
}