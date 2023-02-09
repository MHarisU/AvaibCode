package com.umbrellamd.avaib.listeners;

import com.umbrellamd.avaib.database.User;

public interface UserOnClickListener {
    void onDeleteClick(User user);
    void onEditClick(User user);
}
