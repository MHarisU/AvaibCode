package com.umbrellamd.avaib.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.umbrellamd.avaib.R;
import com.umbrellamd.avaib.database.User;
import com.umbrellamd.avaib.databinding.UsersRowBinding;
import com.umbrellamd.avaib.listeners.UserOnClickListener;
import com.umbrellamd.avaib.viewmodel.UserViewModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {


    private List<User> users;
    private LayoutInflater layoutInflater;
    UsersRowBinding userRowBinding;
    UserViewModel userViewModel;
    UserOnClickListener listener;


    public UserAdapter(List<User> users, UserViewModel userViewModel, UserOnClickListener listener) {
        this.users = users;
        this.userViewModel = userViewModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        userRowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.users_row, parent, false
        );
        return new UsersViewHolder(userRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.bindUsers(users.get(position));


    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class UsersViewHolder extends RecyclerView.ViewHolder {
        private UsersRowBinding userRowBinding;


        public UsersViewHolder(UsersRowBinding userRowBinding) {
            super(userRowBinding.getRoot());
            this.userRowBinding = userRowBinding;


        }

        public void bindUsers(final User users) {
            userRowBinding.setUser(users);
            userRowBinding.executePendingBindings();



            userRowBinding.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(users);
                }
            });

            userRowBinding.viewProfileRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userRowBinding.viewProfileLayout.setVisibility(View.VISIBLE);
                    userRowBinding.viewProfileRow.setVisibility(View.GONE);
                    userRowBinding.editProfileLayout.setVisibility(View.GONE);
                }
            });

            userRowBinding.closeViewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userRowBinding.viewProfileLayout.setVisibility(View.GONE);
                    userRowBinding.viewProfileRow.setVisibility(View.VISIBLE);
                    userRowBinding.editProfileLayout.setVisibility(View.GONE);

                }
            });

            userRowBinding.editViewLayoutCloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userRowBinding.viewProfileLayout.setVisibility(View.GONE);
                    userRowBinding.viewProfileRow.setVisibility(View.VISIBLE);
                    userRowBinding.editProfileLayout.setVisibility(View.GONE);

                }
            });

            userRowBinding.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userRowBinding.viewProfileLayout.setVisibility(View.GONE);
                    userRowBinding.viewProfileRow.setVisibility(View.GONE);
                    userRowBinding.editProfileLayout.setVisibility(View.VISIBLE);
                }
            });

            userRowBinding.updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    users.setFirstName(userRowBinding.firstNameEditText.getText().toString());
                    users.setLastName(userRowBinding.lastNameEditText.getText().toString());
                    users.setEmail(userRowBinding.emailEditText.getText().toString());
                    listener.onEditClick(users);
                    userRowBinding.viewProfileLayout.setVisibility(View.GONE);
                    userRowBinding.viewProfileRow.setVisibility(View.VISIBLE);
                    userRowBinding.editProfileLayout.setVisibility(View.GONE);
                }
            });


        }
    }


}
