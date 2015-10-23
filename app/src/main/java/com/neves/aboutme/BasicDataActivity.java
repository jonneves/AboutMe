package com.neves.aboutme;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class BasicDataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_data);
        try {
            String destPath = "/data/data/" + getPackageName() + "/databases/MyDB";
            File f = new File(destPath);
            if (!f.exists()) {
                CopyDB( getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBAdapter db = new DBAdapter(this);

        db.open();
        long jonId = db.insertContact("Jonatan Neves (db)", "jonneves@gmail.com");
        db.close();


        db.open();
        Cursor c = db.getContact(jonId);
        if (c.moveToFirst())
            setData(c);
        db.close();
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void setData(Cursor c)
    {
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(c.getString(1));

        TextView email = (TextView)findViewById(R.id.email);
        email.setText(c.getString(2));
    }

    public void gotoEducation(View view){
        Intent intent = new Intent(BasicDataActivity.this, EducationActivity.class);
        startActivity(intent);
    }

    public void gotoJobs(View view){
        Intent intent = new Intent(BasicDataActivity.this, JobsActivity.class);
        startActivity(intent);
    }
}
