package com.example.elinsakurai.myfirstapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DisplayAccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    TextView heading;
    TextView values;
    private SensorManager mSensorManager;
    private Sensor aSensor;
    private float[] mAcc = new float[3];
    static final float alpha = 0.97f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        heading = (TextView) findViewById(R.id.textView);
        values = (TextView) findViewById(R.id.values);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == aSensor) {
            mAcc[0] = alpha * mAcc[0] + (1 - alpha)
                    * event.values[0];
            mAcc[1] = alpha * mAcc[1] + (1 - alpha)
                    * event.values[1];
            mAcc[2] = alpha * mAcc[2] + (1 - alpha)
                    * event.values[2];
        }
        Float x = (float) mAcc[0];
        Float y = (float) mAcc[1];
        Float z = (float) mAcc[2];

        values.setText("x = " + Float.toString(Math.round(x)) + "    y = " + Float.toString(Math.round(y)) + "    z = " + Float.toString(Math.round(z)));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
