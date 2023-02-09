package com.umbrellamd.avaib.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umbrellamd.avaib.R;
import com.umbrellamd.avaib.database.User;
import com.umbrellamd.avaib.databinding.ActivitySignupBinding;
import com.umbrellamd.avaib.viewmodel.UserViewModel;

import java.util.List;

public class SignupActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    ActivitySignupBinding activitySignupBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        activitySignupBinding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = activitySignupBinding.usernameEditText.getText().toString();
                String firstName = activitySignupBinding.firstNameEditText.getText().toString();
                String lastName = activitySignupBinding.lastNameEditText.getText().toString();
                String password = activitySignupBinding.passwordEditText.getText().toString();
                String email = activitySignupBinding.emailEditText.getText().toString();

                User user = new User(username, firstName, lastName, password, email);
                userViewModel.insert(user);
                activitySignupBinding.usernameEditText.setText("");
                activitySignupBinding.firstNameEditText.setText("");
                activitySignupBinding.lastNameEditText.setText("");
                activitySignupBinding.passwordEditText.setText("");
                activitySignupBinding.emailEditText.setText("");
                Toast.makeText(SignupActivity.this, "User Added", Toast.LENGTH_SHORT).show();


            }
        });


        activitySignupBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}
