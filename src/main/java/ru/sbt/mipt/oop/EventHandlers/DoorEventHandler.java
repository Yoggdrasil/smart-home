package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {
    public static void processEvent(SensorEvent event, SmartHome smartHome){
        // ищет источник сигнала и вызывает обработчик сигнала
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        OpenDoorHandler.handle(door, room);
                    } else {
                        ClosedDoorHandler.handle(door, room, smartHome);
                    }
                }
            }
        }
    }
}
