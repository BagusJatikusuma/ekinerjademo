package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 29/09/17.
 */
@Embeddable
public class TaKegiatanId implements Serializable {
    @Column(name = "Kd_Urusan")
    private Integer kdUrusan;
    @Column(name = "Kd_Bidang")
    private Integer kdBIdang;
    @Column(name = "Kd_Unit")
    private Integer kdUnit;
    @Column(name = "Kd_Sub")
    private Integer kdSub;
    @Column(name = "Tahun")
    private Integer tahun;
    @Column(name = "Kd_Prog")
    private Integer kdProg;
    @Column(name = "ID_Prog")
    private Integer idProg;
    @Column(name = "Kd_Keg")
    private Integer kdKegiatan;

    public TaKegiatanId() {
    }
    public TaKegiatanId(
            Integer kdUrusan,
            Integer kdBIdang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKegiatan) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKegiatan = kdKegiatan;
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

    public Integer getKdKegiatan() {
        return kdKegiatan;
    }

    public void setKdKegiatan(Integer kdKegiatan) {
        this.kdKegiatan = kdKegiatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaKegiatanId that = (TaKegiatanId) o;

        if (!kdUrusan.equals(that.kdUrusan)) return false;
        if (!kdBIdang.equals(that.kdBIdang)) return false;
        if (!kdUnit.equals(that.kdUnit)) return false;
        if (!kdSub.equals(that.kdSub)) return false;
        if (!tahun.equals(that.tahun)) return false;
        if (!kdProg.equals(that.kdProg)) return false;
        if (!idProg.equals(that.idProg)) return false;
        return kdKegiatan.equals(that.kdKegiatan);
    }

    @Override
    public int hashCode() {
        int result = kdUrusan.hashCode();
        result = 31 * result + kdBIdang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        result = 31 * result + kdSub.hashCode();
        result = 31 * result + tahun.hashCode();
        result = 31 * result + kdProg.hashCode();
        result = 31 * result + idProg.hashCode();
        result = 31 * result + kdKegiatan.hashCode();
        return result;
    }
}
