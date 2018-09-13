package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uraian_tugas_pegawai_bulanan")
public class UraianTugasPegawaiBulanan {
    @EmbeddedId
    private UraianTugasPegawaiBulananId uraianTugasPegawaiBulananId;

    @Column(name = "status_approval")
    private Integer statusApproval;

    @Column(name = "target_kuantitas")
    private Integer targetKuantitas;

    @Column(name = "target_satuan_kuantitas")
    private String targetSatuanKuantitas;

    @Column(name = "target_kualitas")
    private Integer targetKualitas;

    @Column(name = "waktu")
    private Integer waktu;

    @Column(name = "biaya")
    private Integer biaya;

    @Column(name = "realisasi_kuantitas")
    private Integer realisasiKuantitas;

    @Column(name = "realisasi_kualitas")
    private Integer realisasiKualitas;

    @Column(name = "realisasi_waktu")
    private Integer realisasiWaktu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "kd_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_urtug"),
            @JoinColumn(
                    name = "kd_jabatan",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_jabatan"),
            @JoinColumn(
                    name = "kd_jenis_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_jenis_urtug"),
            @JoinColumn(
                    name = "tahun_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "tahun_urtug")
    })
    private UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "nip_pegawai",
            insertable = false,
            updatable = false,
            referencedColumnName = "nip_pegawai")
    private AkunPegawai akunPegawai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uraianTugasPegawaiBulanan")
    private List<UraianTugasPegawaiBulananAjuan> uraianTugasPegawaiBulananAjuanList;

    public UraianTugasPegawaiBulananId getUraianTugasPegawaiBulananId() {
        return uraianTugasPegawaiBulananId;
    }

    public void setUraianTugasPegawaiBulananId(UraianTugasPegawaiBulananId uraianTugasPegawaiBulananId) {
        this.uraianTugasPegawaiBulananId = uraianTugasPegawaiBulananId;
    }

    public Integer getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(Integer statusApproval) {
        this.statusApproval = statusApproval;
    }

    public Integer getTargetKuantitas() {
        return targetKuantitas;
    }

    public void setTargetKuantitas(Integer targetKuantitas) {
        this.targetKuantitas = targetKuantitas;
    }

    public String getTargetSatuanKuantitas() {
        return targetSatuanKuantitas;
    }

    public void setTargetSatuanKuantitas(String targetSatuanKuantitas) {
        this.targetSatuanKuantitas = targetSatuanKuantitas;
    }

    public Integer getTargetKualitas() {
        return targetKualitas;
    }

    public void setTargetKualitas(Integer targetKualitas) {
        this.targetKualitas = targetKualitas;
    }

    public Integer getWaktu() {
        return waktu;
    }

    public void setWaktu(Integer waktu) {
        this.waktu = waktu;
    }

    public Integer getBiaya() {
        return biaya;
    }

    public void setBiaya(Integer biaya) {
        this.biaya = biaya;
    }

    public Integer getRealisasiKuantitas() {
        return realisasiKuantitas;
    }

    public void setRealisasiKuantitas(Integer realisasiKuantitas) {
        this.realisasiKuantitas = realisasiKuantitas;
    }

    public Integer getRealisasiKualitas() {
        return realisasiKualitas;
    }

    public void setRealisasiKualitas(Integer realisasiKualitas) {
        this.realisasiKualitas = realisasiKualitas;
    }

    public Integer getRealisasiWaktu() {
        return realisasiWaktu;
    }

    public void setRealisasiWaktu(Integer realisasiWaktu) {
        this.realisasiWaktu = realisasiWaktu;
    }

    public UraianTugasJabatanJenisUrtug getUraianTugasJabatanJenisUrtug() {
        return uraianTugasJabatanJenisUrtug;
    }

    public void setUraianTugasJabatanJenisUrtug(UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug) {
        this.uraianTugasJabatanJenisUrtug = uraianTugasJabatanJenisUrtug;
    }

    public AkunPegawai getAkunPegawai() {
        return akunPegawai;
    }

    public void setAkunPegawai(AkunPegawai akunPegawai) {
        this.akunPegawai = akunPegawai;
    }

    public List<UraianTugasPegawaiBulananAjuan> getUraianTugasPegawaiBulananAjuanList() {
        return uraianTugasPegawaiBulananAjuanList;
    }

    public void setUraianTugasPegawaiBulananAjuanList(List<UraianTugasPegawaiBulananAjuan> uraianTugasPegawaiBulananAjuanList) {
        this.uraianTugasPegawaiBulananAjuanList = uraianTugasPegawaiBulananAjuanList;
    }
}
