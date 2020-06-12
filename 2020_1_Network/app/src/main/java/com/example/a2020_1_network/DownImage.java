package com.example.a2020_1_network;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;


public class DownImage extends AppCompatActivity {
    ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_image);

        mImage = (ImageView)findViewById(R.id.result);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btndown:
                (new DownThread("http://www.soen.kr/data/child2.jpg")).start();
                break;
        }
    }

    class DownThread extends Thread {
        String mAddr;

        DownThread(String addr) {
            mAddr = addr;
        }

        public void run() {
            try {
                InputStream is = new URL(mAddr).openStream();
                Bitmap bit = BitmapFactory.decodeStream(is);
                is.close();
                Message message = mAfterDown.obtainMessage();
                message.obj = bit;
                mAfterDown.sendMessage(message);
            } catch (Exception e) {;}
        }


    }

    Handler mAfterDown = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bit = (Bitmap)msg.obj;
            if (bit == null) {
                Toast.makeText(DownImage.this, "bitmap is null", Toast.LENGTH_SHORT).show();
            }
            else {
                mImage.setImageBitmap(bit);
            }
        }
    };
}
