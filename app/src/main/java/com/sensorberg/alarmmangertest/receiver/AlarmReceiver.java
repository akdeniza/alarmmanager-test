package com.sensorberg.alarmmangertest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sensorberg.alarmmangertest.event.TimerStopEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Akdeniz on 14/11/2016.
 */

public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Delayed notification", Toast.LENGTH_LONG).show();

        EventBus.getDefault().post(new TimerStopEvent());


    }
}
