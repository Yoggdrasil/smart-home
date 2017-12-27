package smartHome.TestAlarmSys;

import org.junit.Test;
import smartHome.AlarmSys.AlarmSystem;
import smartHome.AlarmSys.AlarmSystemStateEnum;
import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;

import static org.junit.Assert.assertEquals;

public class AlarmSystemOffStateTest {
    @Test
    public void turnOn() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void onSensor() {
        AlarmSystem alarmSystem = new AlarmSystem();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void otherMethods() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
        alarmSystem.typeIncorrectPassword();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    private SensorEvent createSensorEvent() {return new SensorEvent(SensorEventType.DOOR_OPEN, "1");}
}
