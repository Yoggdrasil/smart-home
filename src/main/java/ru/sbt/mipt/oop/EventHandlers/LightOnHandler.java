package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;

public class LightOnHandler {
    public static void handle(Room room, Light light){
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }
}
