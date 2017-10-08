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

    @Column(name = "kuantitas")
    private Integer kuantitas;

    @Column(name = "satuan_kuantitas")
    private String satuanKuantitas;

    @Column(name = "kualitas")
    private Integer kualitas;

    @Column(name = "waktu")
    private Integer waktu;

    @Column(name = "satuan_waktu")
    private String satuanWaktu;

    @Column(name = "biaya")
    private Long biaya;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AkunPegawai createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_jenis_urtug",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_jenis_urtug")
    private JenisUrtug jenisUrtug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_urtug",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_urtug")
    private UraianTugas uraianTugas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uraianTugasJabatan")
    private List<UrtugKegiatan> urtugKegiatanList;

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

    public JenisUrtug getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(JenisUrtug jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }

    public List<UrtugKegiatan> getUrtugKegiatanList() {
        return urtugKegiatanList;
    }

    public void setUrtugKegiatanList(List<UrtugKegiatan> urtugKegiatanList) {
        this.urtugKegiatanList = urtugKegiatanList;
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

    public String getSatuanWaktu() {
        return satuanWaktu;
    }

    public void setSatuanWaktu(String satuanWaktu) {
        this.satuanWaktu = satuanWaktu;
    }

    public Long getBiaya() {
        return biaya;
    }

    public void setBiaya(Long biaya) {
        this.biaya = biaya;
    }
}
