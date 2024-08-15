/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.laporkelas.service;


import com.project.laporkelas.dto.LaporanDto;
import com.project.laporkelas.dto.UserDto;
import com.project.laporkelas.entity.Laporan;
import com.project.laporkelas.entity.Riwayat;
import com.project.laporkelas.mapper.LaporanMapper;
import com.project.laporkelas.repository.LaporanRepository;
import com.project.laporkelas.repository.RiwayatRepository;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author GHOLIDHO
 */

@Service
public class LaporanServiceImpl implements LaporanService {

    private final LaporanRepository laporanRepository;
    private final RiwayatRepository riwayatRepository;

    public LaporanServiceImpl(LaporanRepository laporanRepository, RiwayatRepository riwayatRepository) {
        this.laporanRepository = laporanRepository;
        this.riwayatRepository = riwayatRepository;
    }
    
    @Override
    @Transactional
    public void updateLaporan(Long kelas, LaporanDto laporanDto) {
        Laporan existingLaporan = laporanRepository.findById(kelas)
                .orElseThrow(() -> new RuntimeException("Laporan with ID " + kelas + " not found."));
        
        String laporanSebelumnya = existingLaporan.toString();
        
        if (laporanDto.getProyektor() != null) {
            String proyektor = laporanDto.getProyektor().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(proyektor) || !"aman".equalsIgnoreCase(existingLaporan.getProyektor())) {
                throw new RuntimeException("Proyektor sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setProyektor(proyektor);
        }
        
        if (laporanDto.getKursi() != null) {
            String kursi = laporanDto.getKursi().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(kursi) || !"aman".equalsIgnoreCase(existingLaporan.getKursi())) {
                throw new RuntimeException("Kursi sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setKursi(kursi);
        }
        
        if (laporanDto.getAc() != null) {
            String ac = laporanDto.getAc().trim();
            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(ac) || !"aman".equalsIgnoreCase(existingLaporan.getAc())) {
                throw new RuntimeException("Ac sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setAc(ac);
        }
        
        if (laporanDto.getPapan() != null) {
            String papan = laporanDto.getPapan().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(papan) || !"aman".equalsIgnoreCase(existingLaporan.getPapan())) {
                throw new RuntimeException("Ac sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setPapan(papan);
        }
        
        if (laporanDto.getSpidol() != null) {
            String spidol = laporanDto.getSpidol().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(spidol) || !"aman".equalsIgnoreCase(existingLaporan.getSpidol())) {
                throw new RuntimeException("Spidol sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setSpidol(spidol);
        }

        if (laporanDto.getPenghapus() != null) {
            String penghapus = laporanDto.getPenghapus().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(penghapus) || !"aman".equalsIgnoreCase(existingLaporan.getPenghapus())) {
                throw new RuntimeException("Penghapus sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setPenghapus(penghapus);
        }

        if (laporanDto.getJamDinding() != null) {
            String jamDinding = laporanDto.getJamDinding().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(jamDinding) || !"aman".equalsIgnoreCase(existingLaporan.getJamDinding())) {
                throw new RuntimeException("Jam Dinding sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setJamDinding(jamDinding);
        }

        if (laporanDto.getLainnya() != null) {
            String lainnya = laporanDto.getLainnya().trim();

            // Jika nilai diinput adalah "aman" atau nilai di database sudah tidak "aman," lempar exception.
            if ("aman".equalsIgnoreCase(lainnya) || !"aman".equalsIgnoreCase(existingLaporan.getLainnya())) {
                throw new RuntimeException("Lainnya sudah dilaporkan atau tidak boleh menggunakan kata [aman]");
            }       
            existingLaporan.setLainnya(lainnya);
        }

        laporanRepository.save(existingLaporan);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        String reporterName = userDto.getName(); // Gantilah dengan metode yang sesuai untuk mengambil nama pengguna         String laporanSesudah = existingLaporan.toString();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reportDateStr = dateFormat.format(new Date());

        Riwayat riwayat = new Riwayat();
        riwayat.setReporterName(reporterName);
        riwayat.setNim(userDto.getNim());
        riwayat.setReportDate(reportDateStr);
        riwayat.setReportedClass(kelas);
        riwayat.setStatus("melaporkan");
                
        riwayatRepository.save(riwayat);
    }

    @Override
    public List<LaporanDto> getLaporanAll() {
        List<Laporan> laporans = this.laporanRepository.findAll();
        List<LaporanDto> laporanDtos = laporans.stream()
                .map((laporan) -> (LaporanMapper.mapToLaporanDto(laporan)))
                .collect(Collectors.toList());        
        return laporanDtos;
    }

    @Override
    public List<LaporanDto> getLaporanByKelas(Long kelas) {
        List<Laporan> laporanList = laporanRepository.findByKelas(kelas);
        if (!laporanList.isEmpty()) {
            return laporanList.stream()
                .map(laporan -> LaporanMapper.mapToLaporanDto(laporan))
                .collect(Collectors.toList());
        } else {
            // Atau throw exception jika tidak ada yang ditemukan.
            throw new RuntimeException("Laporan with class " + kelas + " not found.");
        }
    }
    
    @Override
    public LaporanDto filterLaporanByAttributes(LaporanDto laporanDto, List<String> attributes) {
        LaporanDto filteredLaporan = new LaporanDto();

        for (String attribute : attributes) {
            switch (attribute) {
                case "proyektor":
                    filteredLaporan.setProyektor(laporanDto.getProyektor());
                    break;
                case "kursi":
                    filteredLaporan.setKursi(laporanDto.getKursi());
                    break;
                case "papan":
                    filteredLaporan.setPapan(laporanDto.getPapan());
                    break;
                case "spidol":
                    filteredLaporan.setSpidol(laporanDto.getSpidol());
                    break;
                case "penghapus":
                    filteredLaporan.setPenghapus(laporanDto.getPenghapus());
                    break;
                case "ac":
                    filteredLaporan.setAc(laporanDto.getAc());
                    break;
                case "jamDinding":
                    filteredLaporan.setJamDinding(laporanDto.getJamDinding());
                    break;
                case "lainnya":
                    filteredLaporan.setLainnya(laporanDto.getLainnya());
                    break;
                // Tambahkan atribut lain jika diperlukan.
            }
        }

        return filteredLaporan;
        }
    
    @Override
    public void konfirmasi(Long kelas, List<String> attributes) {
        Laporan existingLaporan = laporanRepository.findById(kelas)
                .orElseThrow(() -> new RuntimeException("Laporan with ID " + kelas + " not found."));

        // Loop melalui atribut yang dipilih
        for (String attribute : attributes) {
            updateAttribute(existingLaporan, attribute);
        }

        laporanRepository.save(existingLaporan);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        String reporterName = userDto.getName(); // Gantilah dengan metode yang sesuai untuk mengambil nama pengguna         String laporanSesudah = existingLaporan.toString();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reportDateStr = dateFormat.format(new Date());

        Riwayat riwayat = new Riwayat();
        riwayat.setReporterName(reporterName);
        riwayat.setNim(userDto.getNim());
        riwayat.setReportDate(reportDateStr);
        riwayat.setReportedClass(kelas);
        riwayat.setStatus("konfirmasi");
                
        riwayatRepository.save(riwayat);
    }

    private void updateAttribute(Laporan existingLaporan, String attributeName) {
        // Mengubah atribut menjadi "aman"
        if ("ac".equalsIgnoreCase(attributeName)) {
            existingLaporan.setAc("aman");
        }else if ("proyektor".equalsIgnoreCase(attributeName)) {
            existingLaporan.setProyektor("aman");
        }else if ("kursi".equalsIgnoreCase(attributeName)) {
            existingLaporan.setKursi("aman");
        } else if ("papan".equalsIgnoreCase(attributeName)) {
            existingLaporan.setPapan("aman");
        } else if ("spidol".equalsIgnoreCase(attributeName)) {
            existingLaporan.setSpidol("aman");
        } else if ("penghapus".equalsIgnoreCase(attributeName)) {
            existingLaporan.setPenghapus("aman");
        } else if ("jamDinding".equalsIgnoreCase(attributeName)) {
            existingLaporan.setJamDinding("aman");
        } else if ("lainnya".equalsIgnoreCase(attributeName)) {
            existingLaporan.setLainnya("aman");
        }
    }
}
