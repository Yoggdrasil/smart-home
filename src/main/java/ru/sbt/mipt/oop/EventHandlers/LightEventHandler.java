package ru.sbt.mipt.oop.EventHandlers;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler {
    public static void processEvent(SensorEvent event, SmartHome smartHome){
        // ищет источник сигнала и вызывает обработчик сигнала
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        LightOnHandler.handle(room, light);
                    } else {
                        LightOffHandler.handle(room, light);
                    }
                }
            }
        }
    }
}
