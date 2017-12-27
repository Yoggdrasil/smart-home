package smartHome.TestAlarmSys;

import org.junit.Before;
import org.junit.Test;
import smartHome.AlarmSys.AlarmSystem;
import smartHome.AlarmSys.AlarmSystemStateEnum;
import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;

import static org.junit.Assert.assertEquals;

public class AlarmSystemWaitForPasswordStateTest {
    private AlarmSystem alarmSystem;
    private SensorEvent createSensorEvent() {return new SensorEvent(SensorEventType.DOOR_OPEN, "1");}

    @Before
    public void generateStateWait() {
        alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        alarmSystem.onSensor(createSensorEvent());
    }

    @Test
    public void turnOff() {
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void typeCorrectPassword() {
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void typeIncorrectPassword() {
        alarmSystem.typeIncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
    }

    @Test
    public void otherMethods() {
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
        alarmSystem.onSensor(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }
}
