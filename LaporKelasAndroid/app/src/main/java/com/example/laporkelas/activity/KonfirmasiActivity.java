package com.example.laporkelas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laporkelas.R;
import com.example.laporkelas.api.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KonfirmasiActivity extends AppCompatActivity {

    private EditText kelasEditText;
    private Button konfirmasiButton;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi); // You'll need to create this layout

        token = getIntent().getStringExtra("TOKEN");

        kelasEditText = findViewById(R.id.kelasEditText);
        konfirmasiButton = findViewById(R.id.konfirmasiButton);

        konfirmasiButton.setOnClickListener(v -> performKonfirmasi());
    }

    private void performKonfirmasi() {
        String kelasStr = kelasEditText.getText().toString();
        if (!kelasStr.isEmpty()) {
            try {
                long kelas = Long.parseLong(kelasStr);
                List<String> attributes = getAttributesToKonfirmasi(); // Implement this method based on your UI
                sendKonfirmasiRequest(kelas, attributes);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid class number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a class number", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendKonfirmasiRequest(long kelas, List<String> attributes) {
        Retrofit retrofit = ApiClient.getRetrofitInstance(token);
        Api api = retrofit.create(Api.class);
        Call<String> call = api.konfirmasiPerubahan(kelas, attributes);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(KonfirmasiActivity.this, "Success: " + response.body(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(KonfirmasiActivity.this, LaporanActivity.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                } else {
                    Toast.makeText(KonfirmasiActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(KonfirmasiActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getAttributesToKonfirmasi() {
        List<String> attributes = new ArrayList<>();
        if (((CheckBox) findViewById(R.id.checkBoxJamDinding)).isChecked()) {
            attributes.add("jamDinding");
        }
        if (((CheckBox) findViewById(R.id.checkBoxAc)).isChecked()) {
            attributes.add("ac");
        }
        if (((CheckBox) findViewById(R.id.checkBoxKursi)).isChecked()) {
            attributes.add("kursi");
        }
        if (((CheckBox) findViewById(R.id.checkBoxProyektor)).isChecked()) {
            attributes.add("proyektor");
        }
        if (((CheckBox) findViewById(R.id.checkBoxPapan)).isChecked()) {
            attributes.add("papan");
        }
        if (((CheckBox) findViewById(R.id.checkBoxSpidol)).isChecked()) {
            attributes.add("spidol");
        }
        if (((CheckBox) findViewById(R.id.checkBoxPenghapus)).isChecked()) {
            attributes.add("penghapus");
        }
        if (((CheckBox) findViewById(R.id.checkBoxLainnya)).isChecked()) {
            attributes.add("lainnya");
        }

        return attributes;
    }
}
