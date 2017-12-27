package smartHome.EventProcessors;

import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;
import smartHome.SmartHomeDir.Components.Light;
import smartHome.SmartHomeDir.Components.Room;
import smartHome.SmartHomeDir.SmartHome;

import static smartHome.Events.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome){
        // ищет источник сигнала и вызывает обработчик сигнала
        if (!isLightEvent(event)) { return; }

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == SensorEventType.LIGHT_ON || event.getType() == SensorEventType.LIGHT_OFF;
    }
}
