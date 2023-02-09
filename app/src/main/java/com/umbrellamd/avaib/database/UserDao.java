package com.umbrellamd.avaib.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users ORDER BY username ASC")
    LiveData<List<User>> getAllUsers();


    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    LiveData<List<User>> getUserAuth(String username, String password);

}
