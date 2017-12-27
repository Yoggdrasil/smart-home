package smartHome.TestEventProcessors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.runners.*;

import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class EventObserverTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addProcessor() {
    }

    @Test
    public void onSensorEvent() {
    }

    @Test
    public void runEvents() {
    }
/*
    @RunWith(MockitoJUnitRunner.class)
    public class EventsObservableTest {
        @Mock
        EventProcessorDecorator doorProcessor;
        @Mock
        EventProcessorDecorator lightProcessor;
        @Mock
        EventProcessorDecorator scenarioProcessor;
        @Mock
        SmartHome smartHome;
        @Mock
        SensorEvent sensorEvent;

        @Test
        public void testOnSensorEvent() throws Exception {
            TimeMeasuringObservable timeMeasuringObserver = new TimeMeasuringObservable(asList(doorProcessor, lightProcessor, scenarioProcessor));
            timeMeasuringObserver.onSensorEvent(smartHome, sensorEvent);
            verify(doorProcessor).processEvent(smartHome, sensorEvent);
            verify(lightProcessor).processEvent(smartHome, sensorEvent);
            verify(scenarioProcessor).processEvent(smartHome, sensorEvent);
        }*/
}