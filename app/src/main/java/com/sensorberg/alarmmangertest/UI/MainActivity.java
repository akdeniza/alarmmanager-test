package com.sensorberg.alarmmangertest.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sensorberg.alarmmangertest.R;
import com.sensorberg.alarmmangertest.receiver.AlarmReceiver;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    AlarmManager alarmManager;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.startAlarmButton)
    public void startAlarm(View v){

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 3, intent, 0);
        Toast.makeText(this, "Started...", Toast.LENGTH_SHORT).show();

        alarmManager.set(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis() + 3 * 1000 , pendingIntent);

    }

    @OnClick(R.id.stopAlarmButton)
    public void stopAlarm(View v){

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 3, intent, 0);
        Log.d("asd","asda");

        alarmManager.cancel(pendingIntent);

    }

}
