package com.example.a2020_1_file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileIO extends AppCompatActivity {
    EditText mEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);

        mEdit = (EditText)findViewById(R.id.edittext);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                try {
                    FileOutputStream fos = openFileOutput("test.txt", Context.MODE_PRIVATE);
                    String str = "Android File IO Test";
                    fos.write(str.getBytes());
                    fos.close();
                    mEdit.setText("write success");
                } catch (Exception e) { ; }
                break;
            case R.id.load:
                try {
                    FileInputStream fis = openFileInput("test.txt");
                    byte[] data = new byte[fis.available()];
                    while (fis.read(data) != -1) { ; }
                    fis.close();
                    mEdit.setText(new String(data));
                } catch (Exception e) {
                    mEdit.setText("File Not Found");
                }
                break;
            case R.id.loadres:
                try {
                    InputStream fres = getResources().openRawResource(R.raw.restext);
                    byte[] data = new byte[fres.available()];
                    while (fres.read(data) != -1) { ; }
                    fres.close();
                    mEdit.setText(new String(data));
                } catch (Exception e) { ; }
                break;
            case R.id.delete:
                if (deleteFile("test.txt")) {
                    mEdit.setText("Delete success");
                } else {
                    mEdit.setText("Delete failed");
                }
                break;
        }
    }
}
