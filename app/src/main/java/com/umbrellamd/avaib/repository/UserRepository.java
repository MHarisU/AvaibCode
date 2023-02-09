package com.umbrellamd.avaib.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.umbrellamd.avaib.database.AppDatabase;
import com.umbrellamd.avaib.database.UserDao;
import com.umbrellamd.avaib.database.User;

import java.util.List;

import io.reactivex.Single;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<User>> authenticatedUser;


    public UserRepository(Application application) {
        AppDatabase database = Room.databaseBuilder(application, AppDatabase.class, "user_database")
                .build();
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getUser(String username, String password){
        return userDao.getUserAuth(username, password);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }


}


