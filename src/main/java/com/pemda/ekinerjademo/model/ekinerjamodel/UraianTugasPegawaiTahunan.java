package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/09/17.
 */
@Entity
@Table(name = "uraian_tugas_pegawai_tahunan")
public class UraianTugasPegawaiTahunan {
    @EmbeddedId
    private UraianTugasPegawaiTahunanId uraianTugasPegawaiTahunanId;

    @Column(name = "status_approval")
    private Boolean statusApproval;

    @Column(name = "status_pengerjaan")
    private Integer statusPengerjaan;

    @Column(name = "kuantitas")
    private Integer kuantitas;

    @Column(name = "satuan_kuantitas")
    private String satuanKuantitas;

    @Column(name = "kualitas")
    private Integer kualitas;

    @Column(name = "waktu")
    private Integer waktu;

    @Column(name = "biaya")
    private Integer biaya;

    @Column(name = "alasan")
    private Integer alasan;

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

    public UraianTugasPegawaiTahunanId getUraianTugasPegawaiTahunanId() {
        return uraianTugasPegawaiTahunanId;
    }

    public void setUraianTugasPegawaiTahunanId(UraianTugasPegawaiTahunanId uraianTugasPegawaiTahunanId) {
        this.uraianTugasPegawaiTahunanId = uraianTugasPegawaiTahunanId;
    }

    public AkunPegawai getAkunPegawai() {
        return akunPegawai;
    }

    public void setAkunPegawai(AkunPegawai akunPegawai) {
        this.akunPegawai = akunPegawai;
    }

    public Integer getStatusPengerjaan() {
        return statusPengerjaan;
    }

    public void setStatusPengerjaan(Integer statusPengerjaan) {
        this.statusPengerjaan = statusPengerjaan;
    }

    public UraianTugasJabatanJenisUrtug getUraianTugasJabatanJenisUrtug() {
        return uraianTugasJabatanJenisUrtug;
    }

    public void setUraianTugasJabatanJenisUrtug(UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug) {
        this.uraianTugasJabatanJenisUrtug = uraianTugasJabatanJenisUrtug;
    }

    public Boolean getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(Boolean statusApproval) {
        this.statusApproval = statusApproval;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }

    public String getSatuanKuantitas() {
        return satuanKuantitas;
    }

    public void setSatuanKuantitas(String satuanKuantitas) {
        this.satuanKuantitas = satuanKuantitas;
    }

    public Integer getKualitas() {
        return kualitas;
    }

    public void setKualitas(Integer kualitas) {
        this.kualitas = kualitas;
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

    public Integer getAlasan() {
        return alasan;
    }

    public void setAlasan(Integer alasan) {
        this.alasan = alasan;
    }
}
