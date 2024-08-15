package com.example.laporkelas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laporkelas.R;
import com.example.laporkelas.model.LaporanDto;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.ViewHolder> {

    private final List<LaporanDto> laporanList;

    public LaporanAdapter(List<LaporanDto> laporanList) {
        this.laporanList = laporanList;
    }

    public void updateData(List<LaporanDto> newLaporanList) {
        laporanList.clear();
        laporanList.addAll(newLaporanList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LaporanDto laporan = laporanList.get(position);
        holder.kelasTextView.setText(""+laporan.getKelas());
        holder.proyektorTextView.setText(laporan.getProyektor());
        holder.kursiTextView.setText(laporan.getKursi());
        holder.papanTextView.setText(laporan.getPapan());
        holder.spidolTextView.setText(laporan.getSpidol());
        holder.penghapusTextView.setText(laporan.getPenghapus());
        holder.acTextView.setText(laporan.getAc());
        holder.jamDindingTextView.setText(laporan.getJamDinding());
        holder.lainnyaTextView.setText(laporan.getLainnya());
    }

    @Override
    public int getItemCount() {
        return laporanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView kelasTextView, proyektorTextView, kursiTextView, papanTextView,
                spidolTextView, penghapusTextView, acTextView, jamDindingTextView, lainnyaTextView;


        ViewHolder(View itemView) {
            super(itemView);
            kelasTextView = itemView.findViewById(R.id.kelasTextView);
            proyektorTextView = itemView.findViewById(R.id.proyektorTextView);
            kursiTextView = itemView.findViewById(R.id.kursiTextView);
            papanTextView = itemView.findViewById(R.id.papanTextView);
            spidolTextView = itemView.findViewById(R.id.spidolTextView);
            penghapusTextView = itemView.findViewById(R.id.penghapusTextView);
            acTextView = itemView.findViewById(R.id.acTextView);
            jamDindingTextView = itemView.findViewById(R.id.jamDindingTextView);
            lainnyaTextView = itemView.findViewById(R.id.lainnyaTextView);
        }
    }
}
