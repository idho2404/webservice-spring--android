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
import com.example.laporkelas.adapter.LaporanAdapter;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import com.example.laporkelas.model.LaporanDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LaporanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LaporanAdapter adapter;
    private String token;

    private EditText kelasEditText;
    private Button searchButton, buatLaporan;

    private ImageView back;
    Long kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan); // You'll need to create this layout

        token = getIntent().getStringExtra("TOKEN");

        recyclerView = findViewById(R.id.recyclerViewLaporan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LaporanAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        kelasEditText = findViewById(R.id.kelasEditText);
        searchButton = findViewById(R.id.searchButton);
        buatLaporan = findViewById(R.id.buatLaporanButton);

        buatLaporan.setOnClickListener(v -> buatLaporan());
        searchButton.setOnClickListener(v -> performSearch());
        loadLaporan();

        back = findViewById(R.id.backImage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaporanActivity.this, DashboardActivity.class);
                intent.putExtra("TOKEN", token);
                startActivity(intent);
            }
        });
    }

    private void buatLaporan() {
        Intent intent = new Intent(LaporanActivity.this, BuatLaporanActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
    }

    private void performSearch() {
        String kelasString = kelasEditText.getText().toString();
        if (!kelasString.isEmpty()) {
            try {
                kelas = Long.parseLong(kelasString);
                searchLaporanByKelas(kelas);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid class number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a class number", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchLaporanByKelas(long kelas) {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);
        Call<List<LaporanDto>> call = api.getLaporanByKelas(token, kelas);

        call.enqueue(new Callback<List<LaporanDto>>() {
            @Override
            public void onResponse(Call<List<LaporanDto>> call, Response<List<LaporanDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.updateData(response.body());
                } else {
                    loadLaporan();
                }
            }

            @Override
            public void onFailure(Call<List<LaporanDto>> call, Throwable t) {
                Toast.makeText(LaporanActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadLaporan() {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);
        Call<List<LaporanDto>> call = api.getLaporanAll(token);

        call.enqueue(new Callback<List<LaporanDto>>() {
            @Override
            public void onResponse(Call<List<LaporanDto>> call, Response<List<LaporanDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new LaporanAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(LaporanActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LaporanDto>> call, Throwable t) {
                Toast.makeText(LaporanActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
