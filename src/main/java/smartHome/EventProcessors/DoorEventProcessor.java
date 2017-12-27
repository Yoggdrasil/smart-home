package smartHome.EventProcessors;

import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;
import smartHome.SmartHomeDir.Components.Door;
import smartHome.SmartHomeDir.Components.Room;
import smartHome.SmartHomeDir.SmartHome;

public class DoorEventProcessor implements EventProcessor{
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome){
        // ищет источник сигнала и вызывает обработчик сигнала
        if (!isDoorEvent(event)) { return; }

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == SensorEventType.DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        ClosedDoorScenarioRunner.start(room, smartHome);
                    }
                }
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_OPEN || event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
