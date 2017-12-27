package smartHome.EventProcessors;

import smartHome.Events.SensorEvent;
import smartHome.SmartHomeDir.EventGenerator;
import smartHome.SmartHomeDir.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

/**
 * распределяет SensorEvents по обработчикам "в потоковом режиме"
 */
public class EventObserver {
    private Collection<EventProcessor> eventProcessors;
    // список всех обработчиков = наблюдателей

    public EventObserver(Collection<EventProcessor> processorCollection){
        this.eventProcessors = processorCollection;
    }

    public EventObserver(){
        this.eventProcessors = new ArrayList<>();
    }

    public void addProcessor(EventProcessor eventProcessor){
        this.eventProcessors.add(eventProcessor);
    }

    public void onSensorEvent(SensorEvent event, SmartHome smartHome){
        for (EventProcessor processor: this.eventProcessors){
            processor.processEvent(event, smartHome);
        }
    }

    public void runEvents(EventGenerator generator, SmartHome smartHome) {
        SensorEvent event = generator.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            this.onSensorEvent(event, smartHome); // notify them all
            // в каждом обработчике (=наблюдателе) есть проверка типа сигнала
            event = generator.getNextSensorEvent();
        }
    }

}
