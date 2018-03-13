package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 10/10/17.
 */
@Entity
@Table(name = "uraian_tugas_jabatan_jenis_urtug")
public class UraianTugasJabatanJenisUrtug {
    @EmbeddedId
    private UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId;

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

    @ManyToOne(optional = false)
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
                    referencedColumnName = "kd_jabatan")
    })
    private UraianTugasJabatan uraianTugasJabatan;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "kd_jenis_urtug",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_jenis_urtug")
    private JenisUrtug jenisUrtug;

    @OneToMany(mappedBy = "uraianTugasJabatanJenisUrtug")
    private List<UrtugKegiatan> urtugKegiatanList;

    public UraianTugasJabatanJenisUrtugId getUraianTugasJabatanJenisUrtugId() {
        return uraianTugasJabatanJenisUrtugId;
    }

    public void setUraianTugasJabatanJenisUrtugId(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId) {
        this.uraianTugasJabatanJenisUrtugId = uraianTugasJabatanJenisUrtugId;
    }

    public UraianTugasJabatan getUraianTugasJabatan() {
        return uraianTugasJabatan;
    }

    public void setUraianTugasJabatan(UraianTugasJabatan uraianTugasJabatan) {
        this.uraianTugasJabatan = uraianTugasJabatan;
    }

    public JenisUrtug getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(JenisUrtug jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UraianTugasJabatanJenisUrtug that = (UraianTugasJabatanJenisUrtug) o;

        if (!uraianTugasJabatanJenisUrtugId.equals(that.uraianTugasJabatanJenisUrtugId)) return false;
        if (uraianTugasJabatan != null ? !uraianTugasJabatan.equals(that.uraianTugasJabatan) : that.uraianTugasJabatan != null)
            return false;
        return jenisUrtug != null ? jenisUrtug.equals(that.jenisUrtug) : that.jenisUrtug == null;
    }

    @Override
    public int hashCode() {
        int result = uraianTugasJabatanJenisUrtugId.hashCode();
        result = 31 * result + (uraianTugasJabatan != null ? uraianTugasJabatan.hashCode() : 0);
        result = 31 * result + (jenisUrtug != null ? jenisUrtug.hashCode() : 0);
        return result;
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

    public List<UrtugKegiatan> getUrtugKegiatanList() {
        return urtugKegiatanList;
    }

    public void setUrtugKegiatanList(List<UrtugKegiatan> urtugKegiatanList) {
        this.urtugKegiatanList = urtugKegiatanList;
    }
}
