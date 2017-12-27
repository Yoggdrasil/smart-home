package smartHome.AlarmSys;

import smartHome.Events.SensorEvent;

public class AlarmSystemOffState implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemOffState(AlarmSystem alarmSystem) {this.alarmSystem = alarmSystem;}

    @Override
    public void turnOn() {
        alarmSystem.setStateStrategy(new AlarmSystemOnState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.ON);
    }

    @Override
    public void onSensor(SensorEvent sensorEvent) {
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

    @Override
    public void turnOff() {}

    @Override
    public void typeCorrectPassword() {}

    @Override
    public void typeIncorrectPassword() {}
}
