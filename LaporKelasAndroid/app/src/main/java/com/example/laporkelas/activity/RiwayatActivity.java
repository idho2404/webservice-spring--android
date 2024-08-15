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
import com.example.laporkelas.adapter.RiwayatAdapter;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.RiwayatDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RiwayatActivity extends AppCompatActivity implements RiwayatAdapter.OnRiwayatListener {

    private RecyclerView recyclerView;
    private RiwayatAdapter adapter;
    private String token;
    private EditText searchEditText;
    private Button searchButton;
    ArrayList<String> roles;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        token = getIntent().getStringExtra("TOKEN");
        roles = getIntent().getStringArrayListExtra("ROLES");
        searchButton.setOnClickListener(v -> performSearch());
        loadRiwayats();

        back = findViewById(R.id.backImage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiwayatActivity.this, DashboardActivity.class);
                intent.putExtra("TOKEN", token);
                startActivity(intent);
            }
        });
    }

    private boolean isDate(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            dateFormat.parse(text);
            return true; // Parsing succeeded, the text is a valid date
        } catch (ParseException e) {
            return false; // Parsing failed, the text is not a valid date
        }
    }
    private void performSearch() {
        String searchText = searchEditText.getText().toString();
        if (isDate(searchText)) {
            searchRiwayats(searchText, null);
        } else {
            try {
                long classId = Long.parseLong(searchText);
                searchRiwayats(null, classId);
            } catch (NumberFormatException e) {
                loadRiwayats();
            }
        }
    }
    private void loadRiwayats() {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);
        Call<List<RiwayatDto>> call = api.getAllRiwayats(token);

        call.enqueue(new Callback<List<RiwayatDto>>() {
            @Override
            public void onResponse(Call<List<RiwayatDto>> call, Response<List<RiwayatDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new RiwayatAdapter(response.body(), RiwayatActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RiwayatActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatDto>> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchRiwayats(String date, Long classId) {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);

        Call<List<RiwayatDto>> call;

        if (date != null) {
            call = api.findRiwayatByDateOrClass(token, date, null);
        } else {
            call = api.findRiwayatByDateOrClass(token, null, classId);
        }

        call.enqueue(new Callback<List<RiwayatDto>>() {
            @Override
            public void onResponse(Call<List<RiwayatDto>> call, Response<List<RiwayatDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new RiwayatAdapter(response.body(), RiwayatActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RiwayatActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatDto>> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRiwayatClick(RiwayatDto riwayat) {
        if (userHasAdminRole()) {
            Intent intent = new Intent(this, RiwayatDetailsActivity.class);
            intent.putExtra("RiwayatDto", riwayat);
            intent.putExtra("TOKEN", token);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Access Denied: You must be an admin to view details.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean userHasAdminRole() {
        return roles != null && roles.contains("ROLE_ADMIN");
    }
}