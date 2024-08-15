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
import com.example.laporkelas.model.AuthRequest;
import com.example.laporkelas.model.AuthResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        tv = findViewById(R.id.signupText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Profile Icon click
                register();
            }
        });
    }

    private void performLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Retrofit retrofit = ApiClient.getRetrofitInstance();
        Api api = retrofit.create(Api.class);
        Call<AuthResponse> call = api.login(new AuthRequest(email, password));

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    AuthResponse authResponse = response.body();
                    String token = authResponse.getAccessToken();

                    ArrayList<String> roles = new ArrayList<>(authResponse.getRoles());

                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("TOKEN", token);
                    intent.putStringArrayListExtra("ROLES", roles);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Unexpected Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(){
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        Toast.makeText(LoginActivity.this, "Register", Toast.LENGTH_SHORT).show();
    }
}