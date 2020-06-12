package com.example.a2020_1_network;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class ConMgr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_mgr);

        TextView result = (TextView)findViewById(R.id.result);
        String sResult = "";
        ConnectivityManager mgr = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo[] ani = mgr.getAllNetworkInfo();
        for (NetworkInfo n : ani) {
            sResult += (n.toString() + "\n\n");
        }

        NetworkInfo ni = mgr.getActiveNetworkInfo();
        if (ni != null) {
            sResult += ("Active : \n" + ni.toString() + "\n");
            result.setText(sResult);
        }
    }
}
