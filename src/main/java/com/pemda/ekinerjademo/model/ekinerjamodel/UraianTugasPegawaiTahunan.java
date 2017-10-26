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
}
