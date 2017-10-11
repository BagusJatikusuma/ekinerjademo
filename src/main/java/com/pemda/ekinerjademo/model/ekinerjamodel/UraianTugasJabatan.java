package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "uraian_tugas_jabatan")
public class UraianTugasJabatan implements Serializable {
    @EmbeddedId
    private UraianTugasJabatanId uraianTugasJabatanId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AkunPegawai createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_urtug",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_urtug")
    private UraianTugas uraianTugas;

    @OneToMany(
            mappedBy = "uraianTugasJabatan",
            orphanRemoval = true,
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE},
            targetEntity = UraianTugasJabatanJenisUrtug.class)
    private List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uraianTugasJabatan")
    private List<SopUraianTugasJabatan> sopUraianTugasJabatanList;

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

    public AkunPegawai getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AkunPegawai createdBy) {
        this.createdBy = createdBy;
    }

    public List<SopUraianTugasJabatan> getSopUraianTugasJabatanList() {
        return sopUraianTugasJabatanList;
    }

    public void setSopUraianTugasJabatanList(List<SopUraianTugasJabatan> sopUraianTugasJabatanList) {
        this.sopUraianTugasJabatanList = sopUraianTugasJabatanList;
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

    public List<UraianTugasJabatanJenisUrtug> getUraianTugasJabatanJenisUrtugList() {
        return uraianTugasJabatanJenisUrtugList;
    }

    public void setUraianTugasJabatanJenisUrtugList(List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList) {
        this.uraianTugasJabatanJenisUrtugList = uraianTugasJabatanJenisUrtugList;
    }
}
