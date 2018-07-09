package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 07/10/17.
 */
@Entity
@Table(name = "unit_kerja_kegiatan")
public class UnitKerjaKegiatan {
    @Id
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "kd_urusan")
    private Integer kdUrusan;
    @Column(name = "kd_bidang")
    private Integer kdBidang;
    @Column(name = "kd_unit")
    private Integer kdUnit;

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBidang() {
        return kdBidang;
    }

    public void setKdBidang(Integer kdBidang) {
        this.kdBidang = kdBidang;
    }

    public Integer getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(Integer kdUnit) {
        this.kdUnit = kdUnit;
    }
}
