package com.example.a2020_1_actionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

public class ActionView extends AppCompatActivity {
    MenuItem mSerach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionviewmenu, menu);
        mSerach = menu.findItem(R.id.search);

        mSerach.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                TextView text = (TextView)findViewById(R.id.textstatus);
                text.setText("현재상태 : 확장됨");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                TextView text = (TextView)findViewById(R.id.textstatus);
                text.setText("현재상태 : 축소됨");
                return true;
            }
        });

        SearchView sv = (SearchView)mSerach.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView text = (TextView)findViewById(R.id.textresult);
                text.setText(query+"를 검색합니다.");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                TextView text = (TextView)findViewById(R.id.textsearch);
                text.setText("검색식 : "+newText);
                return true;
            }
        });

        return true;
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnexpand:
                mSerach.expandActionView();
                break;
            case R.id.btncollapse:
                mSerach.collapseActionView();
                break;
        }
    }
}
