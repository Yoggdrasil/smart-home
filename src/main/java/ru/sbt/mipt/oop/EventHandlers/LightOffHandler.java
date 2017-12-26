package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;

public class LightOffHandler {
    public static void handle(Room room, Light light){
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }
}
