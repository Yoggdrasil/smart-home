package smartHome.AlarmSys;

import smartHome.Events.SensorEvent;

public class AlarmSystemAlertState implements AlarmSystemState {
    private AlarmSystem alarmSystem;

    public AlarmSystemAlertState(AlarmSystem alarmSystem) {this.alarmSystem = alarmSystem;}

    @Override
    public void turnOn() {}

    @Override
    public void onSensor(SensorEvent sensorEvent) {
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

    @Override
    public void turnOff() {
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

    @Override
    public void typeCorrectPassword() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
        alarmSystem.setState(AlarmSystemStateEnum.ON);
    }

    @Override
    public void typeIncorrectPassword() {}
}
