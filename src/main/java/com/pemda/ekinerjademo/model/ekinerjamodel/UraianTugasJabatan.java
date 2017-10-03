package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "uraian_tugas_jabatan")
public class UraianTugasJabatan implements Serializable {
    @EmbeddedId
    private UraianTugasJabatanId uraianTugasJabatanId;

//    @Column(name = "satuan")
//    private String satuan;
//
//    @Column(name = "volume_kerja")
//    private Integer volumeKerja;
//
//    @Column(name = "norma_waktu")
//    private Integer normaWaktu;
//
//    @Column(name = "beban_kerja")
//    private Integer bebanKerja;
//
//    @Column(name = "peralatan")
//    private String peralatan;
//
//    @Column(name = "keterangan")
//    private  String keterangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AkunPegawai createdBy;

    @ManyToOne
    @JoinColumn(name = "kd_urtug", insertable = false, updatable = false, referencedColumnName = "kd_urtug")
    private UraianTugas uraianTugas;


    public UraianTugasJabatanId getUraianTugasJabatanId() {
        return uraianTugasJabatanId;
    }

    public void setUraianTugasJabatanId(UraianTugasJabatanId uraianTugasJabatanId) {
        this.uraianTugasJabatanId = uraianTugasJabatanId;
    }

    public UraianTugas getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(UraianTugas uraianTugas) {
        this.uraianTugas = uraianTugas;
    }

//    public String getSatuan() {
//        return satuan;
//    }
//
//    public void setSatuan(String satuan) {
//        this.satuan = satuan;
//    }
//
//    public Integer getVolumeKerja() {
//        return volumeKerja;
//    }
//
//    public void setVolumeKerja(Integer volumeKerja) {
//        this.volumeKerja = volumeKerja;
//    }
//
//    public Integer getNormaWaktu() {
//        return normaWaktu;
//    }
//
//    public void setNormaWaktu(Integer normaWaktu) {
//        this.normaWaktu = normaWaktu;
//    }
//
//    public Integer getBebanKerja() {
//        return bebanKerja;
//    }
//
//    public void setBebanKerja(Integer bebanKerja) {
//        this.bebanKerja = bebanKerja;
//    }
//
//    public String getPeralatan() {
//        return peralatan;
//    }
//
//    public void setPeralatan(String peralatan) {
//        this.peralatan = peralatan;
//    }
//
//    public String getKeterangan() {
//        return keterangan;
//    }
//
//    public void setKeterangan(String keterangan) {
//        this.keterangan = keterangan;
//    }

    public AkunPegawai getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AkunPegawai createdBy) {
        this.createdBy = createdBy;
    }
}
