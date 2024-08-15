package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laporkelas.R;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.User;
import com.example.laporkelas.adapter.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView tvUserName, tvUserEmail, tvUserNim, tvUserKelas;
    private Button btnDeleteUser;
    private User user;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Initialize views
        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserNim = findViewById(R.id.tvUserNim);
        tvUserKelas = findViewById(R.id.tvUserKelas);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);

        // Retrieve user from intent
        user = (User) getIntent().getSerializableExtra("USER");
        token = getIntent().getStringExtra("TOKEN");
        if (user != null) {
            displayUserDetails(user);
        }

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    deleteUser(user.getId());
                }
            }
        });
    }

    private void displayUserDetails(User user) {
        tvUserName.setText(user.getName());
        tvUserEmail.setText(user.getEmail());
        tvUserNim.setText(user.getNim());
        tvUserKelas.setText(user.getKelas());
    }

    private void deleteUser(Long userId) {
        // Retrofit instance
        Api api = ApiClient.getRetrofitInstance(token).create(Api.class);
        Call<Void> call = api.deleteUser(userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserDetailsActivity.this, "User "+user.getName()+" Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserDetailsActivity.this, UserAdapter.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent); // Close this activity
                } else {
                    Toast.makeText(UserDetailsActivity.this, "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UserDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
