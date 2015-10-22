package com.neves.aboutme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class BasicDataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_data);
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
