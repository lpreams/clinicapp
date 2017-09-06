package net.carolinaphoenix.clinicappdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This Activity will the the main page for Standard (non-Administrator) users
 */
public class StandardMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_main);
    }
}
