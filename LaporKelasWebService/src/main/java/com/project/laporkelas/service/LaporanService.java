/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.service;


import com.project.laporkelas.dto.LaporanDto;
import java.util.List;

/**
 *
 * @author GHOLIDHO
 */
public interface LaporanService {
    List<LaporanDto> getLaporanAll();
    List<LaporanDto> getLaporanByKelas(Long kelas);
    public void updateLaporan(Long kelas, LaporanDto laporanDto);
    LaporanDto filterLaporanByAttributes(LaporanDto laporanDto, List<String> attributes);
    public void konfirmasi(Long kelas, List<String> attributes);
    
}
