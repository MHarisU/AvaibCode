package com.umbrellamd.avaib.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.umbrellamd.avaib.R;
import com.umbrellamd.avaib.databinding.ActivityLoginBinding;
import com.umbrellamd.avaib.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    private UserViewModel userViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = new ViewModelProvider(this).get(UserViewModel .class);

        activityLoginBinding.registrationButton.setOnClickListener(view -> openRegister());

        activityLoginBinding.loginButton.setOnClickListener(view -> login());


    }

    private void login() {

        String username = activityLoginBinding.editTextEmail.getText().toString();
        String password = activityLoginBinding.editTextPassword.getText().toString();

        if (!username.equals("") && !password.equals("")){
            userViewModel.getUserWithAuth(username, password).observe(this, user -> {
                if (user != null && user.size() != 0) {
                    Log.d("UserCheck", String.valueOf(user.size()));
                    Toast.makeText(this, "Logged in Welcome "+user.get(0).getFirstName(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "User not registered, or wrong username/password", Toast.LENGTH_SHORT).show();
                    Log.d("UserCheck", "null");
                }
            });
        }else {
            Toast.makeText(this, "Please Enter Username and Passwrod", Toast.LENGTH_SHORT).show();
        }


    }

    private void openRegister() {
        startActivity(new Intent(this, SignupActivity.class));
    }
}