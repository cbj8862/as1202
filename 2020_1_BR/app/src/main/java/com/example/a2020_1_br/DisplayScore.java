package com.example.a2020_1_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DisplayScore extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Kia : Samsung = 2 : 3", Toast.LENGTH_SHORT).show();
    }
}
