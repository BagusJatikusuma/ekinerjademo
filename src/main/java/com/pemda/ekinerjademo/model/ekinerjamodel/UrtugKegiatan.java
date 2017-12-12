package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by bagus on 05/10/17.
 */
@Entity
@Table(name = "urtug_kegiatan")
public class UrtugKegiatan {
    @EmbeddedId
    private UrtugKegiatanId urtugKegiatanId;

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

    @Column(name = "kuantitas")
    private Integer kuantitas;

    @Column(name = "satuan_kuantitas")
    private String satuanKuantitas;

    @Column(name = "kualitas")
    private Integer kualitas;

    @Column(name = "waktu")
    private Integer waktu;

    @Column(name = "biaya")
    private BigDecimal biaya;

    @Column(name = "alasan")
    private String alasan;

    public UrtugKegiatanId getUrtugKegiatanId() {
        return urtugKegiatanId;
    }

    public void setUrtugKegiatanId(UrtugKegiatanId urtugKegiatanId) {
        this.urtugKegiatanId = urtugKegiatanId;
    }

    public UraianTugasJabatanJenisUrtug getUraianTugasJabatanJenisUrtug() {
        return uraianTugasJabatanJenisUrtug;
    }

    public void setUraianTugasJabatanJenisUrtug(UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug) {
        this.uraianTugasJabatanJenisUrtug = uraianTugasJabatanJenisUrtug;
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

    public BigDecimal getBiaya() {
        return biaya;
    }

    public void setBiaya(BigDecimal biaya) {
        this.biaya = biaya;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

}
