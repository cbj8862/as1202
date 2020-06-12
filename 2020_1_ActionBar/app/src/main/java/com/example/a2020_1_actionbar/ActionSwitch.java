package com.example.a2020_1_actionbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActionSwitch extends AppCompatActivity {
    TextView mText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mText = new TextView(this);
        mText.setText("액션바에서 네트워크 옵션을 선택하세요.");
        setContentView(mText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionswitchmenu, menu);

        MenuItem network = menu.findItem(R.id.network);
        Switch sw = (Switch)network.getActionView();
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mText.setText("선택된 네트워크 : "+(isChecked?"WiFi":"LTE"));
            }
        });

        return true;
    }
}
