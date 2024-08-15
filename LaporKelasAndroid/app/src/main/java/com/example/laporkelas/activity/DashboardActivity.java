package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.laporkelas.R;
import com.example.laporkelas.model.*;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private TextView profileText, riwayatText, laporanText, usersText, konfirmasiText, exitText;
    private ImageView profileImage, riwayatImage, laporanImage, usersImage, konfirmasiImage, exitImage;

    private CardView penggunaCard, konfirmasiCard;
    private String token;

    ArrayList<String> roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        profileText = findViewById(R.id.profileText);
        profileImage = findViewById(R.id.profileImage);

        laporanText = findViewById(R.id.laporanText);
        laporanImage = findViewById(R.id.laporanImage);
        riwayatText = findViewById(R.id.riwayatText);
        riwayatImage = findViewById(R.id.riwayatImage);
        penggunaCard = findViewById(R.id.penggunaCard);
        konfirmasiCard = findViewById(R.id.konfirmasiCard);

        exitText = findViewById(R.id.exittext);
        exitImage = findViewById(R.id.exitImage);
        token = getIntent().getStringExtra("TOKEN");
        roles = getIntent().getStringArrayListExtra("ROLES");

        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Profile Icon click
                openProfile();
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Profile Icon click
                openProfile();
            }
        });

        // Setup click listener for Create Report Button
        laporanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReport();
            }
        });
        laporanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReport();
            }
        });

        exitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
        exitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        // Setup click listener for View History Button
        riwayatText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHistory();
            }
        });
        riwayatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHistory();
            }
        });

        usersText = findViewById(R.id.usersText);
        usersImage = findViewById(R.id.usersImage);
        if (userHasAdminRole()) {
            usersText.setVisibility(View.VISIBLE);
            penggunaCard.setVisibility(View.VISIBLE);
            konfirmasiCard.setVisibility(View.VISIBLE);
            usersImage.setVisibility(View.VISIBLE);
        }else {
            usersText.setVisibility(View.GONE);
            penggunaCard.setVisibility(View.GONE);
            konfirmasiCard.setVisibility(View.GONE);
            usersImage.setVisibility(View.GONE);
        }
            usersText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewAllUsers();
                }
            });
            usersImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewAllUsers();
                }
            });


        konfirmasiText = findViewById(R.id.konfirmasiText);
        konfirmasiImage = findViewById(R.id.konfirmasiImage);

            konfirmasiText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    konfirmasi();
                }
            });
            konfirmasiImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    konfirmasi();
                }
            });
        }

    private boolean userHasAdminRole() {
            for (String role : roles) {
                if ("ROLE_ADMIN".equals(role)) {
                    return true;
                }
            }
        return false;
    }

    private void viewAllUsers() {
        // Intent to open an activity that shows all users
        Intent intent = new Intent(DashboardActivity.this, UsersActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "View All Users", Toast.LENGTH_SHORT).show();
    }
    private void openProfile() {
        // Intent to open profile activity (example)
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
    }

    private void createReport() {
        Intent intent = new Intent(DashboardActivity.this, LaporanActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "Create Report", Toast.LENGTH_SHORT).show();
    }

    private void viewHistory() {
        Intent intent = new Intent(DashboardActivity.this, RiwayatActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putStringArrayListExtra("ROLES", roles);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "View History", Toast.LENGTH_SHORT).show();
    }

    private void konfirmasi() {
        Intent intent = new Intent(DashboardActivity.this, KonfirmasiActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "Konfirmasi", Toast.LENGTH_SHORT).show();
    }

    private void exit() {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "Login", Toast.LENGTH_SHORT).show();
    }
}

