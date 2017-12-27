package smartHome.TestEventProcessors;

import org.junit.Before;
import org.junit.Test;
import smartHome.EventProcessors.ClosedDoorScenarioRunner;
import smartHome.SmartHomeDir.Components.Door;
import smartHome.SmartHomeDir.Components.Light;
import smartHome.SmartHomeDir.Components.Room;
import smartHome.SmartHomeDir.SmartHome;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClosedDoorScenarioRunnerTest {
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
                Arrays.asList(new Door(true, "4")),
                "hall");

        smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }


    @Test
    public void testAllLightsOff_whenExitHall() {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        // по-дефолту дверь открыта

        Room tempRoom = null;
        for (Room room : smartHome.getRooms()){
            if(room.getName().equals("hall")){
                tempRoom = room;
                break;
            }
        }

        ClosedDoorScenarioRunner.start(tempRoom,smartHome);

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    @Test
    public void testAllLightsOff_whenExitNotHall() {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        // по-дефолту дверь открыта

        Room tempRoom = null;
        for (Room room : smartHome.getRooms()) {
            if (!room.getName().equals("hall")) {
                tempRoom = room;
                break;
            }
        }

        ClosedDoorScenarioRunner.start(tempRoom, smartHome);

        int onLightsCounter = 0;

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                if (light.isOn()) {
                    onLightsCounter += 1;
                }
            }
        }

        assertTrue((onLightsCounter != 0));
    }

}