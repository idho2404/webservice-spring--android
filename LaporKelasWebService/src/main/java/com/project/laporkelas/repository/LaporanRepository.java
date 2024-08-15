package com.project.laporkelas.repository;

import com.project.laporkelas.entity.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaporanRepository extends JpaRepository<Laporan, Long> {
    List<Laporan> findByKelas(Long kelas);
}
