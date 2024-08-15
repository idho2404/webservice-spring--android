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
public class RiwayatDto {
    private Long id;
    private String reporterName;
    private String nim;
    private String reportDate;
    private Long reportedClass;
    private String status;
}
