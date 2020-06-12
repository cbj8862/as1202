package com.example.a2020_1_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FreeBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, DefaultBR.class);
        intent2.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }
}
