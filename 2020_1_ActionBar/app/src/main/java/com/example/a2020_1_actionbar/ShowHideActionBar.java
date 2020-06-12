package com.example.a2020_1_actionbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class ShowHideActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hide_action_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);

        return true;
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btntoggle:
                ActionBar ab = getSupportActionBar();
                if(ab.isShowing()) {
                    ab.hide();
                    ((Button)v).setText("Show Action Bar");
                }
                else {
                    ab.show();
                    ((Button)v).setText("Hide Action Bar");
                }
                break;
        }
    }
}
