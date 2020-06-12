package com.example.a2020_1_network;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownImage2 extends AppCompatActivity {
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
                String imageurl = "http://www.soen.kr/data/child3.jpg";
                int idx = imageurl.lastIndexOf('/');
                String localimage = imageurl.substring(idx + 1);
                String path = Environment.getDataDirectory().getAbsolutePath();
                path += "/data/com.example.a2020_1_network/files/" + localimage;

                if (new File(path).exists()) {
                    Toast.makeText(this, "bitmap is exist", Toast.LENGTH_SHORT).show();
                    mImage.setImageBitmap(BitmapFactory.decodeFile(path));
                }
                else {
                    Toast.makeText(this, "bitmap is not exist", Toast.LENGTH_SHORT).show();
                    (new DownThread(imageurl, localimage)).start();
                }
                break;
        }
    }

    class DownThread extends Thread {
        String mAddr;
        String mFile;

        DownThread(String addr, String filename) {
            mAddr = addr;
            mFile = filename;
        }

        public void run() {
           URL imageurl;
           int Read;

           try {
               imageurl = new URL(mAddr);
               HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
               int len = conn.getContentLength();
               byte[] raster = new byte[len];
               InputStream is = conn.getInputStream();
               FileOutputStream fos = openFileOutput(mFile, 0);

               for (;;) {
                   Read = is.read(raster);
                   if (Read <= 0) {
                       break;
                   }
                   fos.write(raster, 0, Read);
               }

               is.close();
               fos.close();
               conn.disconnect();
           } catch (Exception e) {
               mFile = null;
           }
           Message message = mAfterDown.obtainMessage();
           message.obj = mFile;
           mAfterDown.sendMessage(message);
        }
    }

    Handler mAfterDown = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                String path = Environment.getDataDirectory().getAbsolutePath();
                path += "/data/com.example.a2020_1_network/files/" + (String)msg.obj;
                mImage.setImageBitmap(BitmapFactory.decodeFile(path));
            }
            else {
                Toast.makeText(DownImage2.this, "File not found", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
