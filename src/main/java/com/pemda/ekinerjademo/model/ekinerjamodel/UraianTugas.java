package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "uraian_tugas")
public class UraianTugas {
    @Id
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "satuan")
    private String satuan;

    @Column(name = "volume_kerja")
    private Integer volumeKerja;

    @Column(name = "norma_waktu")
    private Integer normaWaktu;

    @Column(name = "beban_kerja")
    private Integer bebanKerja;

    @Column(name = "peralatan")
    private String peralatan;

    @Column(name = "keterangan")
    private  String keterangan;

    @Column(name = "created_by")
    private String createdBy;

    @OneToMany(mappedBy = "uraianTugas")
    private List<RincianEKinerja> rincianEKinerjaList;

    @OneToMany(mappedBy = "uraianTugas")
    private List<UraianTugasJabatan> uraianTugasJabatanList;

    public UraianTugas() {}
    public UraianTugas(String kdUrtug, String deskripsi) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<RincianEKinerja> getRincianEKinerjaList() {
        return rincianEKinerjaList;
    }

    public void setRincianEKinerjaList(List<RincianEKinerja> rincianEKinerjaList) {
        this.rincianEKinerjaList = rincianEKinerjaList;
    }

    public List<UraianTugasJabatan> getUraianTugasJabatanList() {
        return uraianTugasJabatanList;
    }

    public void setUraianTugasJabatanList(List<UraianTugasJabatan> uraianTugasJabatanList) {
        this.uraianTugasJabatanList = uraianTugasJabatanList;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getVolumeKerja() {
        return volumeKerja;
    }

    public void setVolumeKerja(Integer volumeKerja) {
        this.volumeKerja = volumeKerja;
    }

    public Integer getNormaWaktu() {
        return normaWaktu;
    }

    public void setNormaWaktu(Integer normaWaktu) {
        this.normaWaktu = normaWaktu;
    }

    public Integer getBebanKerja() {
        return bebanKerja;
    }

    public void setBebanKerja(Integer bebanKerja) {
        this.bebanKerja = bebanKerja;
    }

    public String getPeralatan() {
        return peralatan;
    }

    public void setPeralatan(String peralatan) {
        this.peralatan = peralatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
