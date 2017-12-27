package smartHome;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import smartHome.EventProcessors.DoorEventProcessor;
import smartHome.EventProcessors.EventObserver;
import smartHome.EventProcessors.LightEventProcessor;
import smartHome.SmartHomeDir.EventGenerator;
import smartHome.SmartHomeDir.HomeReader;
import smartHome.SmartHomeDir.SmartHome;

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
