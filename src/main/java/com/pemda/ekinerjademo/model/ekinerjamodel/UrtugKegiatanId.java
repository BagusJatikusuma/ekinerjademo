package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 05/10/17.
 */
@Embeddable
public class UrtugKegiatanId implements Serializable {
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    @Column(name = "kd_urusan")
    private Integer kdUrusan;

    @Column(name = "kd_bidang")
    private Integer kdBidang;

    @Column(name = "kd_unit")
    private Integer kdUnit;

    @Column(name = "kd_sub")
    private Integer kdSub;

    @Column(name = "tahun")
    private Integer tahun;

    @Column(name = "kd_prog")
    private Integer kdProg;

    @Column(name = "id_prog")
    private Integer idProg;

    @Column(name = "kd_keg")
    private Integer kdKeg;

    public UrtugKegiatanId() {
    }
    public UrtugKegiatanId(
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKeg) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.kdUrusan = kdUrusan;
        this.kdBidang = kdBidang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKeg = kdKeg;
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

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBidang() {
        return kdBidang;
    }

    public void setKdBidang(Integer kdBidang) {
        this.kdBidang = kdBidang;
    }

    public Integer getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(Integer kdUnit) {
        this.kdUnit = kdUnit;
    }

    public Integer getKdSub() {
        return kdSub;
    }

    public void setKdSub(Integer kdSub) {
        this.kdSub = kdSub;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getKdProg() {
        return kdProg;
    }

    public void setKdProg(Integer kdProg) {
        this.kdProg = kdProg;
    }

    public Integer getIdProg() {
        return idProg;
    }

    public void setIdProg(Integer idProg) {
        this.idProg = idProg;
    }

    public Integer getKdKeg() {
        return kdKeg;
    }

    public void setKdKeg(Integer kdKeg) {
        this.kdKeg = kdKeg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrtugKegiatanId that = (UrtugKegiatanId) o;

        if (!kdUrtug.equals(that.kdUrtug)) return false;
        if (!kdJabatan.equals(that.kdJabatan)) return false;
        if (!kdJenisUrtug.equals(that.kdJenisUrtug)) return false;
        if (!kdUrusan.equals(that.kdUrusan)) return false;
        if (!kdBidang.equals(that.kdBidang)) return false;
        if (!kdUnit.equals(that.kdUnit)) return false;
        if (!kdSub.equals(that.kdSub)) return false;
        if (!tahun.equals(that.tahun)) return false;
        if (!kdProg.equals(that.kdProg)) return false;
        if (!idProg.equals(that.idProg)) return false;
        return kdKeg.equals(that.kdKeg);
    }

    @Override
    public int hashCode() {
        int result = kdUrtug.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        result = 31 * result + kdJenisUrtug.hashCode();
        result = 31 * result + kdUrusan.hashCode();
        result = 31 * result + kdBidang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        result = 31 * result + kdSub.hashCode();
        result = 31 * result + tahun.hashCode();
        result = 31 * result + kdProg.hashCode();
        result = 31 * result + idProg.hashCode();
        result = 31 * result + kdKeg.hashCode();
        return result;
    }
}
