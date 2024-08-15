package com.example.laporkelas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laporkelas.R;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.UserDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvEmail, tvName, tvNim, tvKelas;
    private ImageView imgBack;
    private Api api;
    private String token;
    ArrayList<String> roles;

    Long id;
    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        token = getIntent().getStringExtra("TOKEN");
        roles = getIntent().getStringArrayListExtra("ROLES");
        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);
        tvNim = findViewById(R.id.tvNim);
        tvKelas = findViewById(R.id.tvKelas);
        editProfile = findViewById(R.id.editProfile);
        imgBack = findViewById(R.id.imageViewProfile);
        loadMyProfile();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){ menu();}
        });
    }
    private void loadMyProfile() {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);
        Call<UserDto> call = api.myProfile(token); // Assuming myProfile endpoint exists

        call.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserDto userDto = response.body();
                    tvEmail.setText(userDto.getEmail());
                    tvName.setText(userDto.getName());
                    tvNim.setText(userDto.getNim());
                    tvKelas.setText(userDto.getKelas());
                    id =  userDto.getId().longValue();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to load profile: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editProfile() {
        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("ID", id);
        intent.putStringArrayListExtra("ROLES", roles);
        startActivity(intent);
    }

    private void menu(){
        Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("TOKEN", token);
        intent.putStringArrayListExtra("ROLES", roles);
        startActivity(intent);
    }

}
