package com.example.laporkelas.model;

import java.io.Serializable;
import java.util.Date;

public class RiwayatDto implements Serializable {
    private Long id;
    private String reporterName;
    private String nim;
    private Date reportDate;
    private Long reportedClass;
    private String status;

    public RiwayatDto(String reporterName, String nim, Date reportDate, Long reportedClass, String status) {
        this.reporterName = reporterName;
        this.nim = nim;
        this.reportDate = reportDate;
        this.reportedClass = reportedClass;
        this.status = status;
    }

    // Getter and Setter for reporterName
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    // Getter and Setter for nim
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    // Getter and Setter for reportDate
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    // Getter and Setter for reportedClass
    public Long getReportedClass() {
        return reportedClass;
    }

    public void setReportedClass(Long reportedClass) {
        this.reportedClass = reportedClass;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
