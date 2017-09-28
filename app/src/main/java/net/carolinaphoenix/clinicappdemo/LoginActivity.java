package net.carolinaphoenix.clinicappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * This Activity is shown first if the app is not already signed in, and allows the user to sign in
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginButton(View view) {
        startActivity(new Intent(this, DrawerActivity.class));
        finish();
    }
}
