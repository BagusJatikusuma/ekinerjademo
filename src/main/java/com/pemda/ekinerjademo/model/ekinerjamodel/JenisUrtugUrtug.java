package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bagus on 26/09/17.
 */
@Entity
@Table(name = "jenis_urtug_urtug")
public class JenisUrtugUrtug implements Serializable {
    @EmbeddedId
    private JenisUrtugUrtugId jenisUrtugUrtugId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kd_jenis_urtug", insertable = false, updatable = false)
    private JenisUrtug jenisUrtug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "kd_urtug",
                    referencedColumnName = "kd_urtug",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "kd_jabatan",
                    referencedColumnName = "kd_jabatan",
                    insertable = false,
                    updatable = false)
    })
    private UraianTugasJabatan uraianTugasJabatan;


    public JenisUrtugUrtugId getJenisUrtugUrtugId() {
        return jenisUrtugUrtugId;
    }

    public void setJenisUrtugUrtugId(JenisUrtugUrtugId jenisUrtugUrtugId) {
        this.jenisUrtugUrtugId = jenisUrtugUrtugId;
    }

    public JenisUrtug getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(JenisUrtug jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }

    public UraianTugasJabatan getUraianTugasJabatan() {
        return uraianTugasJabatan;
    }

    public void setUraianTugasJabatan(UraianTugasJabatan uraianTugasJabatan) {
        this.uraianTugasJabatan = uraianTugasJabatan;
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
}
