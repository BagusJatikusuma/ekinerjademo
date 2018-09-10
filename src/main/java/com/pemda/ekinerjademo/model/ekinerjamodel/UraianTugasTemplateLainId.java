package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UraianTugasTemplateLainId
        implements Serializable {
    @Column(name = "kd_template_lain")
    private String kdTemplateLain;
    @Column(name = "kd_urtug")
    private String kdUrtug;
    @Column(name = "kd_jabatan")
    private String kdJabatan;
    @Column(name = "tahun_urtug")
    private Integer tahunUrtug;
    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;
    @Column(name = "bulan_urtug")
    private Integer bulanUrtug;
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public UraianTugasTemplateLainId() {
    }

    public UraianTugasTemplateLainId(String kdTemplateLain, String kdUrtug, String kdJabatan, Integer tahunUrtug, String kdJenisUrtug, Integer bulanUrtug, String nipPegawai) {
        this.kdTemplateLain = kdTemplateLain;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.tahunUrtug = tahunUrtug;
        this.kdJenisUrtug = kdJenisUrtug;
        this.bulanUrtug = bulanUrtug;
        this.nipPegawai = nipPegawai;
    }

    public String getKdTemplateLain() {
        return kdTemplateLain;
    }

    public void setKdTemplateLain(String kdTemplateLain) {
        this.kdTemplateLain = kdTemplateLain;
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

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getBulanUrtug() {
        return bulanUrtug;
    }

    public void setBulanUrtug(Integer bulanUrtug) {
        this.bulanUrtug = bulanUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UraianTugasTemplateLainId that = (UraianTugasTemplateLainId) o;
        return Objects.equals(kdTemplateLain, that.kdTemplateLain) &&
                Objects.equals(kdUrtug, that.kdUrtug) &&
                Objects.equals(kdJabatan, that.kdJabatan) &&
                Objects.equals(tahunUrtug, that.tahunUrtug) &&
                Objects.equals(kdJenisUrtug, that.kdJenisUrtug) &&
                Objects.equals(bulanUrtug, that.bulanUrtug);
    }

    @Override
    public int hashCode() {

        return Objects.hash(kdTemplateLain, kdUrtug, kdJabatan, tahunUrtug, kdJenisUrtug, bulanUrtug);
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }
}
