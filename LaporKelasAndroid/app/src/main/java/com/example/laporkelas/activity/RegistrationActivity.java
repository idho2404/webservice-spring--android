package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laporkelas.R;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.UserRegistrationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEditText, nimEditText, kelasEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize fields
        nameEditText = findViewById(R.id.nameEditText);
        nimEditText = findViewById(R.id.nimEditText);
        kelasEditText = findViewById(R.id.kelasEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        tv = findViewById(R.id.loginText);

        // Set up the button click listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String nim = nimEditText.getText().toString().trim();
        String kelas = kelasEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Membuat instance dari model
        UserRegistrationModel newUser = new UserRegistrationModel(name, nim, kelas, email, password);

        // Implementasi Retrofit untuk mengirim data ke web service
        Retrofit retrofit = ApiClient.getRetrofitInstance();
        Api userApi = retrofit.create(Api.class);
        Call<UserRegistrationModel> call = userApi.register(newUser);

        // Make the API call
        call.enqueue(new Callback<UserRegistrationModel>() {
            @Override
            public void onResponse(Call<UserRegistrationModel> call, Response<UserRegistrationModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    // Handle the successful response, maybe navigate to login or main screen
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegistrationModel> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(){
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(RegistrationActivity.this, "Login", Toast.LENGTH_SHORT).show();
    }
}
