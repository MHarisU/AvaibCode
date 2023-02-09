package com.umbrellamd.avaib.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umbrellamd.avaib.R;
import com.umbrellamd.avaib.adapters.UserAdapter;
import com.umbrellamd.avaib.database.User;
import com.umbrellamd.avaib.databinding.ActivityMainBinding;
import com.umbrellamd.avaib.listeners.UserOnClickListener;
import com.umbrellamd.avaib.viewmodel.UserViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserOnClickListener {

    private UserViewModel userViewModel;
    ActivityMainBinding activityMainBinding;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = new ViewModelProvider(this).get(UserViewModel .class);

        loadUsers();

        activityMainBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void loadUsers() {
        userViewModel.getAllUsers().observe(this, user -> {
            if (user != null) {

                activityMainBinding.usersRecyclerView.setHasFixedSize(true);
                activityMainBinding.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                userAdapter = new UserAdapter(user, userViewModel, this);
                activityMainBinding.usersRecyclerView.setAdapter(userAdapter);

            } else {
                Log.d("UserCheck", "null");
            }
        });
    }


    @Override
    public void onDeleteClick(User user) {
        userViewModel.delete(user);
        Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onEditClick(User user) {

        userViewModel.update(user);
        Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();

    }


}