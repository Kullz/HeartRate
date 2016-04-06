package com.android.apps.heartrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProcessingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        getFragmentManager().beginTransaction().replace(R.id.process_container, new DecisionFragment()).commit();
    }
}
