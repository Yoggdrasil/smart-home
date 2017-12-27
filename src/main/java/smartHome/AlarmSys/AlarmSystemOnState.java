package smartHome.AlarmSys;

import smartHome.Events.SensorEvent;

public class AlarmSystemOnState implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemOnState(AlarmSystem alarmSystem) {this.alarmSystem = alarmSystem;}

    @Override
    public void turnOn() {}

    @Override
    public void onSensor(SensorEvent sensorEvent) {
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

    @Override
    public void turnOff() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.OFF);
    }

    @Override
    public void typeCorrectPassword() {}

    @Override
    public void typeIncorrectPassword() {}
}
