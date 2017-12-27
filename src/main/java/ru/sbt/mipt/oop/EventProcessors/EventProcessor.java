package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.Events.SensorEvent;
import ru.sbt.mipt.oop.SmartHomeDir.SmartHome;

@FunctionalInterface // гарантия, что метод в интерфейсе только один будет
public interface EventProcessor {
    void processEvent(SensorEvent event, SmartHome smartHome);
}
