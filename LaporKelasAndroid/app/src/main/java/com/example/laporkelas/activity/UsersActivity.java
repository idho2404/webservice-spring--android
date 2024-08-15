package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laporkelas.R;
import com.example.laporkelas.adapter.UserAdapter;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersActivity extends AppCompatActivity implements UserAdapter.OnUserListener {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private String token;

    private ImageView back;

    private EditText searchUserEditText;
    private Button searchUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        token = getIntent().getStringExtra("TOKEN");
        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchUserButton = findViewById(R.id.searchUserButton);
        searchUserEditText = findViewById(R.id.searchUserEditText);

        searchUserButton.setOnClickListener(v -> performSearch());
        loadUsers();

        back = findViewById(R.id.backImage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersActivity.this, DashboardActivity.class);
                intent.putExtra("TOKEN", token);
                startActivity(intent);
            }
        });
    }

    private void loadUsers() {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);
        Call<List<User>> call = api.getAllUsers(token);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new UserAdapter(response.body(), UsersActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(UsersActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UsersActivity.this, "Fatal Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSearch() {
        String searchText = searchUserEditText.getText().toString();
        if (!searchText.isEmpty()) {
            Retrofit retrofit = ApiClient.getRetrofitInstance(token);
            Api api = retrofit.create(Api.class);
            Call<List<User>> call = api.searchUsers(searchText);

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        adapter.updateData(response.body());
                    } else {
                        Toast.makeText(UsersActivity.this, "No users found or error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(UsersActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            loadUsers();
        }
    }

    @Override
    public void onUserClick(User user) {
        // Handle the user click event
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("USER", user); // User should implement Serializable or Parcelable
        intent.putExtra("TOKEN", token);
        startActivity(intent);
    }
}
