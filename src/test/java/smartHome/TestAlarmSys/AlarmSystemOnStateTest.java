package smartHome.TestAlarmSys;

import org.junit.Before;
import org.junit.Test;
import smartHome.AlarmSys.AlarmSystem;
import smartHome.AlarmSys.AlarmSystemStateEnum;
import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;

import static org.junit.Assert.assertEquals;

public class AlarmSystemOnStateTest {
    private AlarmSystem alarmSystem;
    private SensorEvent createSensorEvent() {return new SensorEvent(SensorEventType.DOOR_OPEN, "1");}

    @Before
    public void generateStateOn() {
        alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        }

    @Test
    public void turnOn() {
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void onSensor() {
        alarmSystem.onSensor(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void turnOff() {
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void otherMethods() {
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
        alarmSystem.typeIncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

}
