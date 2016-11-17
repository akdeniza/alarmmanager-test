package com.sensorberg.alarmmangertest.event;

/**
 * Created by Akdeniz on 17/11/2016.
 */

public class TimerStopEvent {

    private boolean running;

    public TimerStopEvent(boolean running){
        this.running = running;
    }
}
