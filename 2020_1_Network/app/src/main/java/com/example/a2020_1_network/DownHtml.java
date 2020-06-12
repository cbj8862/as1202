package com.example.a2020_1_network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownHtml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_html);

        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(pol);

        Button btn = (Button)findViewById(R.id.down);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String html;
                html = DownloadHtml("http://www.google.com");
                TextView result = (TextView)findViewById(R.id.result);
                result.setText(html);
            }
        });
    }

    String DownloadHtml(String addr) {
        StringBuilder html = new StringBuilder();
        try {
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    for (;;) {
                        String line = br.readLine();
                        if (line == null) break;
                        html.append(line + '\n');
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch (NetworkOnMainThreadException e) {
            return "Error : 메인 스레드 네트워크 작업 에러 - " + e.getMessage();
        } catch (Exception e) {
            return "Error : " + e.getMessage();
        }
        return html.toString();
    }
}
