package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "uraian_tugas")
public class UraianTugas {
    @Id
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "satuan_volume")
    private String SatuanVolume;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "unit_kerja")
    private String unitKerja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AkunPegawai createdBy;

    @OneToMany(mappedBy = "uraianTugas")
    private List<RincianEKinerja> rincianEKinerjaList;

    @OneToMany(mappedBy = "uraianTugas")
    private List<UraianTugasJabatan> uraianTugasJabatanList;


    public UraianTugas() {}
    public UraianTugas(String kdUrtug, String deskripsi) {
        this.kdUrtug = kdUrtug;
        this.deskripsi = deskripsi;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<RincianEKinerja> getRincianEKinerjaList() {
        return rincianEKinerjaList;
    }

    public void setRincianEKinerjaList(List<RincianEKinerja> rincianEKinerjaList) {
        this.rincianEKinerjaList = rincianEKinerjaList;
    }

    public List<UraianTugasJabatan> getUraianTugasJabatanList() {
        return uraianTugasJabatanList;
    }

    public void setUraianTugasJabatanList(List<UraianTugasJabatan> uraianTugasJabatanList) {
        this.uraianTugasJabatanList = uraianTugasJabatanList;
    }

    public AkunPegawai getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AkunPegawai createdBy) {
        this.createdBy = createdBy;
    }

    public String getSatuanVolume() {
        return SatuanVolume;
    }

    public void setSatuanVolume(String satuanVolume) {
        SatuanVolume = satuanVolume;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }
}
