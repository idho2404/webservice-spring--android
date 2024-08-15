package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.laporkelas.R;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.UserDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditProfileActivity extends AppCompatActivity {
    private EditText etName, etEmail, etNim, etKelas, etPassword, etEmailValidation, etPasswordValidation;
    private Button btnUpdateProfile;
    private Api api;
    private Long userId;
    private String token;
    ArrayList<String> roles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize the EditTexts and Button
        etName = findViewById(R.id.editTextName);
        etEmail = findViewById(R.id.editTextEmail);
        etNim = findViewById(R.id.editTextNim);
        etKelas = findViewById(R.id.editTextKelas);
        etPassword = findViewById(R.id.editTextPassword);
        etEmailValidation = findViewById(R.id.editTextEmailValidation);
        etPasswordValidation = findViewById(R.id.editTextPasswordValidation);
        btnUpdateProfile = findViewById(R.id.buttonUpdateProfile);

        // Retrieve the userId and token (assuming they are passed via an Intent or saved in Shared Preferences)
        userId = getIntent().getLongExtra("ID", 0);
        token = getIntent().getStringExtra("TOKEN");
        roles = getIntent().getStringArrayListExtra("ROLES");

        // Initialize Retrofit and the API interface
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        api = retrofit.create(Api.class);

        btnUpdateProfile.setOnClickListener(v -> updateUserProfile());
    }

    private void updateUserProfile() {
        // Gather user input
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String nim = etNim.getText().toString();
        String kelas = etKelas.getText().toString();
        String password = etPassword.getText().toString();
        String emailValidation = etEmailValidation.getText().toString();
        String passwordValidation = etPasswordValidation.getText().toString();

        // Create a UserDto object with the user input
        UserDto userDto = new UserDto(name, email, nim, kelas, password);
        if (TextUtils.isEmpty(name)) {
            userDto.setName(null);
        }
        if (TextUtils.isEmpty(email)) {
            userDto.setEmail(null);
        }
        if (TextUtils.isEmpty(nim)) {
            userDto.setNim(null);
        }
        if (TextUtils.isEmpty(kelas)) {
            userDto.setKelas(null);
        }
        if (TextUtils.isEmpty(password)) {
            userDto.setPassword(null);
        }
        // Make the API call to update the user profile
        api.updateUser(userId, userDto, emailValidation, passwordValidation).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, DashboardActivity.class);
                    intent.putExtra("TOKEN", token);
                    intent.putStringArrayListExtra("ROLES", roles);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditProfileActivity.this, "Failed to update profile: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Update error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
