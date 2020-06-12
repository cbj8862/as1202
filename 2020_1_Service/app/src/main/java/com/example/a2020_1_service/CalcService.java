package com.example.a2020_1_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class CalcService extends Service {
    public class CalcBinder extends Binder {
        CalcService getService() { return CalcService.this; }
    }

    CalcBinder mBinder = new CalcBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getLCM(int a, int b) throws RemoteException {
        int i;
        for (i=1; ; i++) {
            if (i % a == 0 && i % b == 0) break;
        }
        return i;
    }

    public boolean isPrime(int n) throws RemoteException {
        int i;
        for (i=2; i<n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
