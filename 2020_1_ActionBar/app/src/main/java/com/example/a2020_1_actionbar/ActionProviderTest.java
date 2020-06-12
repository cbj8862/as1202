package com.example.a2020_1_actionbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ActionProvider;

public class ActionProviderTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = new TextView(this);
        text.setText("액션 프로바이더를 테스트합니다.");
        setContentView(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionprovidermenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Counter Menu Item selected - onOptionsItemSelected", Toast.LENGTH_SHORT).show();

        return true;
    }

    public static class CounterProvider extends ActionProvider {
        Context mContext;
        TextView mCountText;

        public CounterProvider(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public View onCreateActionView() {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View linear = inflater.inflate(R.layout.counterprovider, null);
            mCountText = (TextView)linear.findViewById(R.id.count);

            Button btnInc = (Button)linear.findViewById(R.id.btnincrease);
            Button btnDec = (Button)linear.findViewById(R.id.btndecrease);
            btnInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(mCountText.getText().toString());
                    mCountText.setText(Integer.toString(count + 1));
                }
            });
            btnDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(mCountText.getText().toString());
                    mCountText.setText(Integer.toString(count - 1));
                }
            });
            return linear;
        }

        @Override
        public boolean onPerformDefaultAction() {
            Toast.makeText(mContext, "Counter Menu Item selected - onPerformDefaultAction" , Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
