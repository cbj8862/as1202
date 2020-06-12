package com.example.a2020_1_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

public class CalcClient extends AppCompatActivity {
    CalcService mCalc;
    TextView mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_client);

        mResult = (TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnLCM:
                int LCM = 0;
                try {
                    LCM = mCalc.getLCM(6, 8);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mResult.setText("6과 8의 최소 공배수 = " + LCM);
                break;
            case R.id.btnPrime:
                boolean prime = false;
                try {
                    prime = mCalc.isPrime(7);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mResult.setText("7의 소수 여부 = " + prime);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, CalcService.class);
        bindService(intent, srvConn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(srvConn);
    }

    ServiceConnection srvConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mCalc = ((CalcService.CalcBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mCalc = null;
        }
    };
}
