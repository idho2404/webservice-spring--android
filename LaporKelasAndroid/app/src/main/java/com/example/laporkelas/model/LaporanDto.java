package com.example.laporkelas.model;

public class LaporanDto {
    private Long kelas;
    private String proyektor;
    private String kursi;
    private String papan;
    private String spidol;
    private String penghapus;
    private String ac;
    private String jamDinding;
    private String lainnya;

    public LaporanDto(){

    }

    public Long getKelas() {
        return kelas;
    }

    public void setKelas(Long kelas) {
        this.kelas = kelas;
    }

    public String getProyektor() {
        return proyektor;
    }

    public void setProyektor(String proyektor) {
        this.proyektor = proyektor;
    }

    public String getKursi() {
        return kursi;
    }

    public void setKursi(String kursi) {
        this.kursi = kursi;
    }

    public String getPapan() {
        return papan;
    }

    public void setPapan(String papan) {
        this.papan = papan;
    }

    public String getSpidol() {
        return spidol;
    }

    public void setSpidol(String spidol) {
        this.spidol = spidol;
    }

    public String getPenghapus() {
        return penghapus;
    }

    public void setPenghapus(String penghapus) {
        this.penghapus = penghapus;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getJamDinding() {
        return jamDinding;
    }

    public void setJamDinding(String jamDinding) {
        this.jamDinding = jamDinding;
    }

    public String getLainnya() {
        return lainnya;
    }

    public void setLainnya(String lainnya) {
        this.lainnya = lainnya;
    }
}
