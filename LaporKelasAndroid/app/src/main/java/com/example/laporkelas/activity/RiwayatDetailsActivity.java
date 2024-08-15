package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.laporkelas.R;
import com.example.laporkelas.model.RiwayatDto;
import com.example.laporkelas.api.Api;
import com.example.laporkelas.api.ApiClient;

import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatDetailsActivity extends AppCompatActivity {

    private TextView tvReporterName, tvNim, tvReportDate, tvReportedClass, tvStatus;
    private Button btnDelete;
    private RiwayatDto riwayat;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_details);

        tvReporterName = findViewById(R.id.tvReporterName);
        tvNim = findViewById(R.id.tvNim);
        tvReportDate = findViewById(R.id.tvReportDate);
        tvReportedClass = findViewById(R.id.tvReportedClass);
        tvStatus = findViewById(R.id.tvStatus);

        riwayat = (RiwayatDto) getIntent().getSerializableExtra("RiwayatDto");
        token  = getIntent().getStringExtra("TOKEN");

        if (riwayat != null) {
            displayRiwayatDetails(riwayat);
        }

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> deleteRiwayat(riwayat.getId()));
    }

    private void displayRiwayatDetails(RiwayatDto riwayat) {
        tvReporterName.setText(riwayat.getReporterName());
        tvNim.setText(riwayat.getNim());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(riwayat.getReportDate());
        tvReportDate.setText(formattedDate);

        tvReportedClass.setText(String.valueOf(riwayat.getReportedClass()));
        tvStatus.setText(riwayat.getStatus());
    }

    private void deleteRiwayat(Long id) {
        Api api = ApiClient.getRetrofitInstance(token).create(Api.class);
        Call<String> call = api.deleteRiwayatById(id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RiwayatDetailsActivity.this, "Riwayat deleted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RiwayatDetailsActivity.this, DashboardActivity.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                } else {
                    Toast.makeText(RiwayatDetailsActivity.this, "Gagal Hapus Riwayat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RiwayatDetailsActivity.this, "Gagal Hapus Riwayat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
