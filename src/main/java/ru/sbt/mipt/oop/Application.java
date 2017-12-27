package ru.sbt.mipt.oop;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.EventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessors.EventObserver;
import ru.sbt.mipt.oop.SmartHomeDir.EventGenerator;
import ru.sbt.mipt.oop.EventProcessors.LightEventProcessor;
import ru.sbt.mipt.oop.SmartHomeDir.HomeReader;
import ru.sbt.mipt.oop.SmartHomeDir.SmartHome;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = HomeReader.readFromJSON("smart-home-1.js");

       // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");

        // собираем обобщенный обработчик событий (за обрабатываемыми им событиями "наблюдают" - и реагируют на них - нормальные обработчики)
        EventObserver myObserver = new EventObserver();
        myObserver.addProcessor(new DoorEventProcessor());
        myObserver.addProcessor(new LightEventProcessor());

        myObserver.runEvents(new EventGenerator(), smartHome);
    }
}
