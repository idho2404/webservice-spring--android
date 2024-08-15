/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.service;

import com.project.laporkelas.entity.Riwayat;
import com.project.laporkelas.repository.RiwayatRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GHOLIDHO
 */
@Service
public class RiwayatServiceImpl implements RiwayatService {
    @Autowired
    private RiwayatRepository riwayatRepository;

    @Override
    public List<Riwayat> getAllHistoryReports() {
        return riwayatRepository.findAll();
    }

    // Tambahkan metode lain sesuai kebutuhan

    @Override
    public List<Riwayat> findRiwayatByDateOrClass(String date, Long reportedClass) {
        if (date != null && reportedClass == null) {
            // Jika tanggal tidak null dan id null, cari berdasarkan tanggal
            return riwayatRepository.findByReportDateContaining(date);
        } else if (date == null && reportedClass != null) {
            // Jika tanggal null dan id tidak null, cari berdasarkan id
            return riwayatRepository.findByReportedClass(reportedClass);
        } else if (date != null && reportedClass != null) {
            // Jika keduanya tidak null, cari berdasarkan keduanya
            return riwayatRepository.findByReportDateAndReportedClass(date, reportedClass);
        } else {
            // Jika keduanya null, kembalikan daftar kosong
            return Collections.emptyList();
        }
    }
    
    @Override
    public void deleteById(Long id) {
        riwayatRepository.deleteById(id);
    }
}
