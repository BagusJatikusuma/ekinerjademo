package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

@Entity
@Table(name = "uraian_tugas_pegawai_bulanan_ajuan")
public class UraianTugasPegawaiBulananAjuan {
    @EmbeddedId
    private UraianTugasPegawaiBulananAjuanId uraianTugasPegawaiBulananAjuanId;

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
                    referencedColumnName = "tahun_urtug"),
            @JoinColumn(
                    name = "bulan_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "bulan_urtug"),
            @JoinColumn(
                    name = "nip_pegawai",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "nip_pegawai")
    })
    private UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan;

    @Column(name = "target_kuantitas_diajukan")
    private Integer targetKuantitasDiajukan;

    public UraianTugasPegawaiBulananAjuanId getUraianTugasPegawaiBulananAjuanId() {
        return uraianTugasPegawaiBulananAjuanId;
    }

    public void setUraianTugasPegawaiBulananAjuanId(UraianTugasPegawaiBulananAjuanId uraianTugasPegawaiBulananAjuanId) {
        this.uraianTugasPegawaiBulananAjuanId = uraianTugasPegawaiBulananAjuanId;
    }

    public Integer getTargetKuantitasDiajukan() {
        return targetKuantitasDiajukan;
    }

    public void setTargetKuantitasDiajukan(Integer targetKuantitasDiajukan) {
        this.targetKuantitasDiajukan = targetKuantitasDiajukan;
    }

    public UraianTugasPegawaiBulanan getUraianTugasPegawaiBulanan() {
        return uraianTugasPegawaiBulanan;
    }

    public void setUraianTugasPegawaiBulanan(UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan) {
        this.uraianTugasPegawaiBulanan = uraianTugasPegawaiBulanan;
    }
}
