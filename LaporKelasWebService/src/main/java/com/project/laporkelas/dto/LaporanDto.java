/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author GHOLIDHO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

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
}
