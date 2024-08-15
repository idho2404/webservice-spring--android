/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.laporkelas.service;

import com.project.laporkelas.entity.Riwayat;
import java.util.List;

/**
 *
 * @author GHOLIDHO
 */
public interface RiwayatService {
     public List<Riwayat> getAllHistoryReports();
     public List<Riwayat> findRiwayatByDateOrClass(String date, Long reportedClass);   
     public void deleteById(Long id);
}
