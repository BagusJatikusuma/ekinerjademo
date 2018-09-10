package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UraianTugasPegawaiBulananAjuanId implements Serializable {
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    @Column(name = "tahun_urtug")
    private Integer tahunUrtug;

    @Column(name = "bulan_urtug")
    private Integer bulanUrtug;

    @Column(name = "nip_pegawai")
    private String nipPegawai;

    @Column(name = "nip_pengaju")
    private String nipPengaju;

    public UraianTugasPegawaiBulananAjuanId() {
    }

    public UraianTugasPegawaiBulananAjuanId(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug, Integer bulanUrtug, String nipPegawai, String nipPengaju) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.tahunUrtug = tahunUrtug;
        this.bulanUrtug = bulanUrtug;
        this.nipPegawai = nipPegawai;
        this.nipPengaju = nipPengaju;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public Integer getBulanUrtug() {
        return bulanUrtug;
    }

    public void setBulanUrtug(Integer bulanUrtug) {
        this.bulanUrtug = bulanUrtug;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNipPengaju() {
        return nipPengaju;
    }

    public void setNipPengaju(String nipPengaju) {
        this.nipPengaju = nipPengaju;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UraianTugasPegawaiBulananAjuanId that = (UraianTugasPegawaiBulananAjuanId) o;
        return Objects.equals(kdUrtug, that.kdUrtug) &&
                Objects.equals(kdJabatan, that.kdJabatan) &&
                Objects.equals(kdJenisUrtug, that.kdJenisUrtug) &&
                Objects.equals(tahunUrtug, that.tahunUrtug) &&
                Objects.equals(bulanUrtug, that.bulanUrtug) &&
                Objects.equals(nipPegawai, that.nipPegawai) &&
                Objects.equals(nipPengaju, that.nipPengaju);
    }

    @Override
    public int hashCode() {

        return Objects.hash(kdUrtug, kdJabatan, kdJenisUrtug, tahunUrtug, bulanUrtug, nipPegawai, nipPengaju);
    }
}
