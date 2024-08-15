/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author GHOLIDHO
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "laporan")
    public class Laporan {
    @Id
    private Long kelas;
    @Column(nullable = false)
    @Builder.Default
    private String proyektor = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String kursi = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String papan = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String spidol = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String penghapus = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String ac = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String jamDinding = "aman";
    
    @Column(nullable = false)
    @Builder.Default
    private String lainnya = "aman";
}
