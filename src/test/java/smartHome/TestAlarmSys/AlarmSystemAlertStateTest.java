package smartHome.TestAlarmSys;

import org.junit.Before;
import org.junit.Test;
import smartHome.AlarmSys.AlarmSystem;
import smartHome.AlarmSys.AlarmSystemStateEnum;
import smartHome.Events.SensorEvent;
import smartHome.Events.SensorEventType;

import static org.junit.Assert.assertEquals;

public class AlarmSystemAlertStateTest {
    private AlarmSystem alarmSystem;
    private SensorEvent createSensorEvent() {return new SensorEvent(SensorEventType.DOOR_OPEN, "1");}

    @Before
    public void generateStateAlert() {
        alarmSystem = new AlarmSystem();

        alarmSystem.turnOn();
        alarmSystem.onSensor(createSensorEvent());
        alarmSystem.typeIncorrectPassword();
    }

    @Test
    public void onSensor() {
        alarmSystem.onSensor(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void turnOff() {
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void typeCorrectPassword() {
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void otherMethods() {
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
        alarmSystem.typeIncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
    }
}
