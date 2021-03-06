package smartHome.EventProcessors;

import smartHome.Events.CommandType;
import smartHome.Events.SensorCommand;
import smartHome.SmartHomeDir.Components.Light;
import smartHome.SmartHomeDir.Components.Room;
import smartHome.SmartHomeDir.SmartHome;

public class ClosedDoorScenarioRunner {
    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending a command " + command);
    }

    public static void start(Room room, SmartHome smartHome){
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        if (room.getName().equals("hall")) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    sendCommand(command);
                }
            }
        }
    }
}
