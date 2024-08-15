/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.controller;


import com.project.laporkelas.dto.RiwayatDto;
import com.project.laporkelas.entity.Riwayat;
import com.project.laporkelas.mapper.RiwayatMapper;
import com.project.laporkelas.repository.RiwayatRepository;
import com.project.laporkelas.service.RiwayatService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author GHOLIDHO
 */
@RestController
public class RiwayatController {
    @Autowired
    private RiwayatService riwayatService;
    private RiwayatRepository riwayatRepository;

    @GetMapping("/riwayat/all")
    public ResponseEntity<List<RiwayatDto>> getAllRiwayats() {
        
        List<Riwayat> reports = riwayatService.getAllHistoryReports();
        List<RiwayatDto> dtos = reports.stream()
                .map(RiwayatMapper::mapToRiwayatDto) // Ubah ke metode yang benar
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/riwayat/search")
    public List<Riwayat> findRiwayatByDateOrClass(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "class", required = false) Long reportedClass) {
        return riwayatService.findRiwayatByDateOrClass(date, reportedClass);
    }
    
    @DeleteMapping("/riwayat/delete/{id}")
    public ResponseEntity<String> deleteRiwayatById(@PathVariable Long id) {
        riwayatService.deleteById(id);
        return ResponseEntity.ok("Riwayat dengan Id " + id + " Sudah Dihapus");
    }

}
