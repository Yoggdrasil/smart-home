package smartHome.AlarmSys;

import smartHome.Events.SensorEvent;

public interface AlarmSystemState {
    void turnOn();
    void onSensor(SensorEvent sensorEvent);
    void turnOff();
    void typeCorrectPassword();
    void typeIncorrectPassword();
}
