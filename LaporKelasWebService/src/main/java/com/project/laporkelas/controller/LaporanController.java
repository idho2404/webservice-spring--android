/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.controller;

/**
 *
 * @author GHOLIDHO
 */


import com.project.laporkelas.dto.LaporanDto;
import com.project.laporkelas.service.LaporanService;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class LaporanController {

    private final LaporanService laporanService;

    public LaporanController(LaporanService laporanService) {
        this.laporanService = laporanService;
    }

    @GetMapping("/laporan/all")
    public ResponseEntity<List<LaporanDto>> getLaporanAll() {
        List<LaporanDto> laporanDtos = laporanService.getLaporanAll();
        return new ResponseEntity<>(laporanDtos, HttpStatus.OK);
    }

    @GetMapping("/laporan/kelas/{kelas}")
    public ResponseEntity<List<LaporanDto>> getLaporanByKelas(@PathVariable Long kelas) {
        List<LaporanDto> laporanDtos = laporanService.getLaporanByKelas(kelas);
        return new ResponseEntity<>(laporanDtos, HttpStatus.OK);
    }

    @PatchMapping("/laporan/lapor/{kelas}")
    public ResponseEntity<String> updateLaporan(@PathVariable Long kelas, @RequestBody LaporanDto laporanDto) {
        laporanService.updateLaporan(kelas, laporanDto);
        return ResponseEntity.ok("Permasalahan di kelas ["+kelas+"] berhasil di Laporkan");
    }

    @GetMapping("/laporan/attributes")
    public ResponseEntity<Map<String, List<String>>> getLaporanByAttributes(
        @RequestParam List<String> attributes
    ) {
        List<LaporanDto> allLaporans = laporanService.getLaporanAll();
        Map<String, List<String>> result = new HashMap<>();

        for (String attribute : attributes) {
            List<String> attributeValues = allLaporans.stream()
                .flatMap(laporan -> Stream.of(laporan.getKelas().toString() +"-"+ getAttributeByAttributeName(laporan, attribute)))
                .collect(Collectors.toList());

            result.put(attribute, attributeValues);
        }


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
    private String getAttributeByAttributeName(LaporanDto laporan, String attributeName) {
        switch (attributeName) {
            case "proyektor":
                return laporan.getProyektor();
            case "kursi":
                return laporan.getKursi();
            case "ac":
                return laporan.getAc();
            case "papan":
                return laporan.getPapan();
            case "spidol":
                return laporan.getSpidol();
            case "penghapus":
                return laporan.getPenghapus();
            case "jamDinding":
                return laporan.getJamDinding();
            case "lainnya":
                return laporan.getLainnya();
            default:
                return null;
        }
    }
    
    @PostMapping("/laporan/konfirmasi/{kelas}")
    public ResponseEntity<String> konfirmasiPerubahan(
        @PathVariable Long kelas,
        @RequestBody List<String> attributes
    ) {
        try {
            laporanService.konfirmasi(kelas, attributes);
            return ResponseEntity.ok("Konfirmasi perubahan atribut berhasil.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
