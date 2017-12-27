package smartHome.AlarmSys;

import smartHome.Events.SensorEvent;

public class AlarmSystemWaitForPasswordState implements AlarmSystemState {
    private AlarmSystem alarmSystem;

    public AlarmSystemWaitForPasswordState(AlarmSystem alarmSystem) {this.alarmSystem = alarmSystem;}

    @Override
    public void turnOn() {}

    @Override
    public void turnOff() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.OFF);
    }

    @Override
    public void onSensor(SensorEvent sensorEvent) {}

    @Override
    public void typeCorrectPassword() {
        alarmSystem.setStateStrategy(new AlarmSystemOnState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.ON);
    }

    @Override
    public void typeIncorrectPassword() {
        alarmSystem.setStateStrategy(new AlarmSystemAlertState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.ALERT);
    }
}