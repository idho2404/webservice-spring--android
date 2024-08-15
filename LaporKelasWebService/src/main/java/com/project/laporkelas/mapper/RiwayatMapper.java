/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.mapper;

import com.project.laporkelas.dto.RiwayatDto;
import com.project.laporkelas.entity.Riwayat;

/**
 *
 * @author GHOLIDHO
 */
public class RiwayatMapper {
     public static Riwayat mapToRiwayat(RiwayatDto riwayatDto){
        return Riwayat.builder()
                .id(riwayatDto.getId())
                .reporterName(riwayatDto.getReporterName())
                .nim(riwayatDto.getNim())
                .reportDate(riwayatDto.getReportDate())
                .reportedClass(riwayatDto.getReportedClass())
                .status(riwayatDto.getStatus())
                .build();
    }
    public static RiwayatDto mapToRiwayatDto(Riwayat riwayat){
        return RiwayatDto.builder()
                .id(riwayat.getId())
                .reporterName(riwayat.getReporterName())
                .nim(riwayat.getNim())
                .reportDate(riwayat.getReportDate())
                .reportedClass(riwayat.getReportedClass())
                .status(riwayat.getStatus())
                .build();
    }
}
