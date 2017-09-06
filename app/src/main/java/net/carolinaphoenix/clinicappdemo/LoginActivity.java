package net.carolinaphoenix.clinicappdemo;

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
        // TODO figure out how the login system works, and store a token on successful login
    }
}
