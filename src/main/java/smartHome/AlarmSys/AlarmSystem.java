package smartHome.AlarmSys;

import smartHome.Events.SensorEvent;

public class AlarmSystem {
    //основной класс,у которого может быть несколько реализация методов (состояний)
    private AlarmSystemState stateStrategy;
    private AlarmSystemStateEnum state;

    public AlarmSystem() {
        this.stateStrategy = new AlarmSystemOffState(this);
        this.state = AlarmSystemStateEnum.OFF;
    }

    public void setState(AlarmSystemStateEnum state)             {this.state = state;}
    public void setStateStrategy(AlarmSystemState stateStrategy) {this.stateStrategy = stateStrategy;}
    public void turnOff()                                        {stateStrategy.turnOff();}
    public void onSensor(SensorEvent sensorEvent)                {stateStrategy.onSensor(sensorEvent);}
    public void typeCorrectPassword()                            {stateStrategy.typeCorrectPassword();}
    public void typeIncorrectPassword()                          {stateStrategy.typeIncorrectPassword();}
    public void turnOn()                                         {stateStrategy.turnOn();}
    public AlarmSystemStateEnum getState()                       {return state;}
}
