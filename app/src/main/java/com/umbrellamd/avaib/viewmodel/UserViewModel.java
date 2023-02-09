package com.umbrellamd.avaib.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.umbrellamd.avaib.database.User;
import com.umbrellamd.avaib.repository.UserRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();

    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<User>> getUserWithAuth(String username, String password) {
        return repository.getUser(username, password);
    }


}

