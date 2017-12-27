package smartHome.EventProcessors;

import smartHome.Events.SensorEvent;
import smartHome.SmartHomeDir.SmartHome;

@FunctionalInterface // гарантия, что метод в интерфейсе только один будет
public interface EventProcessor {
    void processEvent(SensorEvent event, SmartHome smartHome);
}
