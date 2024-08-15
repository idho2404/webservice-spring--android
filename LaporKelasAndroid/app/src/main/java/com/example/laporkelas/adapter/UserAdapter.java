package com.example.laporkelas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laporkelas.R;
import com.example.laporkelas.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final List<User> userList;
    private OnUserListener onUserListener;

    public interface OnUserListener {
        void onUserClick(User user);
    }

    public UserAdapter(List<User> userList, OnUserListener onUserListener) {
        this.userList = userList;
        this.onUserListener = onUserListener;
    }

    public void updateData(List<User> newUserList) {
        userList.clear();
        userList.addAll(newUserList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view, onUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.itemView.setTag(user); // Set the tag for itemView with the user object
        holder.userNameTextView.setText(user.getName());
        holder.userEmailTextView.setText(user.getEmail());
        holder.userNimTextView.setText(user.getNim() + " -");
        holder.userKelasTextView.setText("- " + user.getKelas());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, userEmailTextView, userNimTextView, userKelasTextView;

        ViewHolder(View itemView, OnUserListener onUserListener) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userEmailTextView = itemView.findViewById(R.id.userEmailTextView);
            userNimTextView = itemView.findViewById(R.id.userNimTextView);
            userKelasTextView = itemView.findViewById(R.id.userKelasTextView);

            itemView.setOnClickListener(view -> {
                if (onUserListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onUserListener.onUserClick((User) itemView.getTag());
                }
            });
        }
    }
}
