package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;

public class OpenDoorHandler {
    public static void handle(Door door, Room room){
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }
}
