package com.example.csaper6.finalp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.peak.salut.Callbacks.SalutCallback;
import com.peak.salut.Callbacks.SalutDataCallback;
import com.peak.salut.Salut;
import com.peak.salut.SalutDataReceiver;
import com.peak.salut.SalutServiceData;

public class salutTry extends AppCompatActivity implements SalutDataCallback{

    public static final String TAG = salutTry.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salut_try);

        SalutDataReceiver dataReceiver = new SalutDataReceiver(salutTry.this, salutTry.this);
        SalutServiceData serviceData = new SalutServiceData("sas", 50489, "hello");

        Salut network = new Salut(dataReceiver, serviceData, new SalutCallback() {
            @Override
            public void call() {
                Log.e(TAG, "Sorry, but this device does not support WiFi Direct.");
            }
        });





    }

    @Override
    public void onDataReceived(Object o) {

    }
}
