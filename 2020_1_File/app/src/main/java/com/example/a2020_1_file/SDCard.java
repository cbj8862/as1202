package com.example.a2020_1_file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SDCard extends AppCompatActivity {
    EditText mEdit;
    String mSDPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_card);

        mEdit = (EditText)findViewById(R.id.edittext);

        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            mSDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            mSDPath = Environment.MEDIA_UNMOUNTED;
        }
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.test:
                String rootdir = Environment.getRootDirectory().getAbsolutePath();
                String datadir = Environment.getDataDirectory().getAbsolutePath();
                String cachedir = Environment.getDownloadCacheDirectory().getAbsolutePath();
                mEdit.setText(String.format("ext = %s\nroot = %s\ndata = %s\ncache = %s", mSDPath, rootdir, datadir, cachedir));
                break;
            case R.id.save:
                File dir = new File(mSDPath + "/dir");
                dir.mkdir();
                File file = new File(mSDPath + "/dir/file.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    String str = "This is exists in in SDcard";
                    fos.write(str.getBytes());
                    fos.close();
                    mEdit.setText("write success");
                } catch (FileNotFoundException e) {
                    mEdit.setText("File Not Found." + e.getMessage());
                } catch (SecurityException e) {
                    mEdit.setText("Security Exception");
                } catch (Exception e) {
                    mEdit.setText(e.getMessage());
                }
                break;
            case R.id.load:
                try {
                    FileInputStream fis = new FileInputStream(mSDPath + "/dir/file.txt");
                    byte[] data = new byte[fis.available()];
                    while (fis.read(data) != -1) { ; }
                    fis.close();
                    mEdit.setText(new String(data));
                } catch (FileNotFoundException e) {
                    mEdit.setText("File Not Found");
                } catch (Exception e) { ; }
                break;
        }
    }
}
