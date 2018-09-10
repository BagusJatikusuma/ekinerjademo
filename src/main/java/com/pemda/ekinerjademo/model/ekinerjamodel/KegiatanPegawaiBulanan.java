package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

@Entity
@Table(name = "kegiatan_pegawai_bulanan")
public class KegiatanPegawaiBulanan {

    @EmbeddedId
    private KegiatanPegawaiBulananId kegiatanPegawaiBulananId;

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

    @Column(name = "realisasi_biaya")
    private Integer realisasiBiaya;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "kd_urusan",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_urusan"),
            @JoinColumn(
                    name = "kd_bidang",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_bidang"),
            @JoinColumn(
                    name = "kd_unit",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_unit"),
            @JoinColumn(
                    name = "kd_sub",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_sub"),
            @JoinColumn(
                    name = "tahun",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "tahun"),
            @JoinColumn(
                    name = "kd_prog",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_prog"),
            @JoinColumn(
                    name = "id_prog",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "id_prog"),
            @JoinColumn(
                    name = "kd_keg",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_keg"),
            @JoinColumn(
                    name = "nip_pegawai",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "nip_pegawai"),
            @JoinColumn(
                    name = "kd_status_penanggung_jawab",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_status_penanggung_jawab")
    })
    private PenanggungJawabKegiatan penanggungJawabKegiatan;

    public KegiatanPegawaiBulananId getKegiatanPegawaiBulananId() {
        return kegiatanPegawaiBulananId;
    }

    public void setKegiatanPegawaiBulananId(KegiatanPegawaiBulananId kegiatanPegawaiBulananId) {
        this.kegiatanPegawaiBulananId = kegiatanPegawaiBulananId;
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

    public Integer getRealisasiBiaya() {
        return realisasiBiaya;
    }

    public void setRealisasiBiaya(Integer realisasiBiaya) {
        this.realisasiBiaya = realisasiBiaya;
    }

    public PenanggungJawabKegiatan getPenanggungJawabKegiatan() {
        return penanggungJawabKegiatan;
    }

    public void setPenanggungJawabKegiatan(PenanggungJawabKegiatan penanggungJawabKegiatan) {
        this.penanggungJawabKegiatan = penanggungJawabKegiatan;
    }
}
