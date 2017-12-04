package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 04/12/17.
 */

@Embeddable
public class TaIndikatorId implements Serializable {
    @Column(name = "Tahun")
    private Integer tahun;
    @Column(name = "Kd_Urusan")
    private Integer kdUrusan;
    @Column(name = "Kd_Bidang")
    private Integer kdBIdang;
    @Column(name = "Kd_Unit")
    private Integer kdUnit;
    @Column(name = "Kd_Sub")
    private Integer kdSub;
    @Column(name = "Kd_Prog")
    private Integer kdProg;
    @Column(name = "ID_Prog")
    private Integer idProg;
    @Column(name = "Kd_Keg")
    private Integer kdKegiatan;

    @Column(name = "Kd_Indikator")
    private Integer kdIndikator;
    @Column(name = "No_ID")
    private Integer noId;

    public TaIndikatorId(){

    }

    public TaIndikatorId(
            Integer tahun,
            Integer kdUrusan,
            Integer kdBIdang,
            Integer kdUnit,
            Integer kdSub,
            Integer kdProg,
            Integer idProg,
            Integer kdKegiatan,
            Integer kdIndikator,
            Integer noId) {
        this.tahun = tahun;
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKegiatan = kdKegiatan;
        this.kdIndikator = kdIndikator;
        this.noId = noId;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBIdang() {
        return kdBIdang;
    }

    public void setKdBIdang(Integer kdBIdang) {
        this.kdBIdang = kdBIdang;
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

    public Integer getKdKegiatan() {
        return kdKegiatan;
    }

    public void setKdKegiatan(Integer kdKegiatan) {
        this.kdKegiatan = kdKegiatan;
    }

    public Integer getKdIndikator() {
        return kdIndikator;
    }

    public void setKdIndikator(Integer kdIndikator) {
        this.kdIndikator = kdIndikator;
    }

    public Integer getNoId() {
        return noId;
    }

    public void setNoId(Integer noId) {
        this.noId = noId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaIndikatorId that = (TaIndikatorId) o;

        if (!tahun.equals(that.tahun)) return false;
        if (!kdUrusan.equals(that.kdUrusan)) return false;
        if (!kdBIdang.equals(that.kdBIdang)) return false;
        if (!kdUnit.equals(that.kdUnit)) return false;
        if (!kdSub.equals(that.kdSub)) return false;
        if (!kdProg.equals(that.kdProg)) return false;
        if (!idProg.equals(that.idProg)) return false;
        if (!kdKegiatan.equals(that.kdKegiatan)) return false;
        if (!kdIndikator.equals(that.kdIndikator)) return false;
        return noId.equals(that.noId);
    }

    @Override
    public int hashCode() {
        int result = tahun.hashCode();
        result = 31 * result + kdUrusan.hashCode();
        result = 31 * result + kdBIdang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        result = 31 * result + kdSub.hashCode();
        result = 31 * result + kdProg.hashCode();
        result = 31 * result + idProg.hashCode();
        result = 31 * result + kdKegiatan.hashCode();
        result = 31 * result + kdIndikator.hashCode();
        result = 31 * result + noId.hashCode();
        return result;
    }
}
