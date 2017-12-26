package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.*;

public class ClosedDoorHandler {
    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending a command " + command);
    }

    public static void handle(Door door, Room room, SmartHome smartHome){
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
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
