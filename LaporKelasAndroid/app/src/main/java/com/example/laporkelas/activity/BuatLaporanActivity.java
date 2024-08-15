package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.laporkelas.R;
import com.example.laporkelas.model.LaporanDto;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// BuatLaporanActivity.java
public class BuatLaporanActivity extends AppCompatActivity {
    private EditText etKelas, etProyektor, etKursi, etAc, etPapan, etSpidol, etPenghapus, etJamDinding, etLainnya;
    private Button btnSubmit;

    private String token;
    private Long kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_laporan);

        token = getIntent().getStringExtra("TOKEN");
        etKelas = findViewById(R.id.etKelas);
        etProyektor = findViewById(R.id.etProyektor);
        etKursi = findViewById(R.id.etKursi);
        etAc = findViewById(R.id.etAc);
        etPapan = findViewById(R.id.etPapan);
        etSpidol = findViewById(R.id.etSpidol);
        etPenghapus = findViewById(R.id.etPenghapus);
        etJamDinding = findViewById(R.id.etJamDinding);
        etLainnya = findViewById(R.id.etLainnya);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLaporan();
            }
        });
    }

    private void submitLaporan() {
        LaporanDto laporanDto = new LaporanDto();

        if (!etProyektor.getText().toString().trim().isEmpty()) {
            laporanDto.setProyektor(etProyektor.getText().toString().trim());
        }
        if (!etKursi.getText().toString().trim().isEmpty()) {
            laporanDto.setKursi(etKursi.getText().toString().trim());
        }
        if (!etAc.getText().toString().trim().isEmpty()) {
            laporanDto.setAc(etAc.getText().toString().trim());
        }
        if (!etPapan.getText().toString().trim().isEmpty()) {
            laporanDto.setPapan(etPapan.getText().toString().trim());
        }
        if (!etSpidol.getText().toString().trim().isEmpty()) {
            laporanDto.setSpidol(etSpidol.getText().toString().trim());
        }
        if (!etPenghapus.getText().toString().trim().isEmpty()) {
            laporanDto.setPenghapus(etPenghapus.getText().toString().trim());
        }
        if (!etJamDinding.getText().toString().trim().isEmpty()) {
            laporanDto.setJamDinding(etJamDinding.getText().toString().trim());
        }
        if (!etLainnya.getText().toString().trim().isEmpty()) {
            laporanDto.setLainnya(etLainnya.getText().toString().trim());
        }


        kelas = Long.parseLong(etKelas.getText().toString());

        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api apiService = retrofit.create(Api.class);

        apiService.updateLaporan(token, kelas, laporanDto).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(BuatLaporanActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuatLaporanActivity.this, LaporanActivity.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                } else {
                    if(kelas == null){
                        Toast.makeText(BuatLaporanActivity.this, "Kelas Harus Diisi", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BuatLaporanActivity.this, "Attribute Sudah Dilaporkan / Minimal Ada 1 Attribute Dilaporkan", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(BuatLaporanActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

