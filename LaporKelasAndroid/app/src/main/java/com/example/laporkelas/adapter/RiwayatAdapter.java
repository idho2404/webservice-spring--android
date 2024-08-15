package com.example.laporkelas.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laporkelas.R;
import com.example.laporkelas.activity.RiwayatDetailsActivity;
import com.example.laporkelas.model.RiwayatDto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    private final List<RiwayatDto> riwayatList;
    private final OnRiwayatListener onRiwayatListener;

    public interface OnRiwayatListener {
        void onRiwayatClick(RiwayatDto riwayat);
    }

    public RiwayatAdapter(List<RiwayatDto> riwayatList, OnRiwayatListener onRiwayatListener) {
        this.riwayatList = riwayatList;
        this.onRiwayatListener = onRiwayatListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, parent, false);
        return new ViewHolder(view, onRiwayatListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatDto riwayat = riwayatList.get(position);
        holder.bind(riwayat);
    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reporterName, nim, reportDate, reportedClass, status;

        public ViewHolder(View itemView, OnRiwayatListener onRiwayatListener) {
            super(itemView);
            reporterName = itemView.findViewById(R.id.reporterNameTextView);
            nim = itemView.findViewById(R.id.nimTextView);
            reportDate = itemView.findViewById(R.id.reportDateTextView);
            reportedClass = itemView.findViewById(R.id.reportedClassTextView);
            status = itemView.findViewById(R.id.statusTextView);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onRiwayatListener.onRiwayatClick(riwayatList.get(position));
                }
            });
        }

        void bind(RiwayatDto riwayat) {
            reporterName.setText(riwayat.getReporterName());
            nim.setText(riwayat.getNim());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = dateFormat.format(riwayat.getReportDate());
            reportDate.setText(formattedDate);

            reportedClass.setText(String.valueOf(riwayat.getReportedClass()));
            status.setText(riwayat.getStatus());
        }
    }
}
