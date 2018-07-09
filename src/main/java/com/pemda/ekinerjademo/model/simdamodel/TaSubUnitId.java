package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 29/09/17.
 */
@Embeddable
public class TaSubUnitId implements Serializable {
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

    public TaSubUnitId() {
    }
    public TaSubUnitId(Integer kdUrusan, Integer kdBIdang, Integer kdUnit, Integer kdSub, Integer tahun) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
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

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaSubUnitId that = (TaSubUnitId) o;

        if (!kdUrusan.equals(that.kdUrusan)) return false;
        if (!kdBIdang.equals(that.kdBIdang)) return false;
        if (!kdUnit.equals(that.kdUnit)) return false;
        if (!kdSub.equals(that.kdSub)) return false;
        return tahun.equals(that.tahun);
    }

    @Override
    public int hashCode() {
        int result = kdUrusan.hashCode();
        result = 31 * result + kdBIdang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        result = 31 * result + kdSub.hashCode();
        result = 31 * result + tahun.hashCode();
        return result;
    }
}
