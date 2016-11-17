package com.sensorberg.alarmmangertest.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sensorberg.alarmmangertest.event.TimerStopEvent;
import com.sensorberg.alarmmangertest.R;
import com.sensorberg.alarmmangertest.receiver.AlarmReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    public Handler mHandler = new Handler();
    private long mStartTime = 0L;

    @BindView(R.id.timerTextView)
    TextView timerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.startAlarmButton)
    public void startAlarm(View v){
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 3, intent, 0);
        Toast.makeText(this, "Started...", Toast.LENGTH_SHORT).show();

        alarmManager.set(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis() + 3 * 1000 , pendingIntent);

        if (mStartTime == 0L) {
            mStartTime = System.currentTimeMillis();
            mHandler.removeCallbacks(mUpdateTimeTask);
            mHandler.postDelayed(mUpdateTimeTask, 100);
        }
    }

    @OnClick(R.id.stopAlarmButton)
    public void stopAlarm(View v){

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 3, intent, 0);

        alarmManager.cancel(pendingIntent);

    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            final long start = mStartTime;

            float millis = System.currentTimeMillis() - start;
            int middleStep = (int) (millis/100);
            float seconds = (float)middleStep / 10;

            timerTextView.setText(seconds + " s");

            mHandler.postDelayed(this, 100);
        }
    };

    @Subscribe
    public void stopTimer(TimerStopEvent event){
        mHandler.removeCallbacks(mUpdateTimeTask);
        mStartTime = 0L;
    }

}
