package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 29/09/17.
 */
@Embeddable
public class RefSubUnitId implements Serializable {
    @Column(name = "Kd_Urusan")
    private Integer kdUrusan;
    @Column(name = "Kd_Bidang")
    private Integer kdBIdang;
    @Column(name = "Kd_Unit")
    private Integer kdUnit;
    @Column(name = "Kd_Sub")
    private Integer kdSub;

    public RefSubUnitId() {
    }
    public RefSubUnitId(Integer kdUrusan, Integer kdBIdang, Integer kdUnit, Integer kdSub) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefSubUnitId that = (RefSubUnitId) o;

        if (!kdUrusan.equals(that.kdUrusan)) return false;
        if (!kdBIdang.equals(that.kdBIdang)) return false;
        if (!kdUnit.equals(that.kdUnit)) return false;
        return kdSub.equals(that.kdSub);
    }

    @Override
    public int hashCode() {
        int result = kdUrusan.hashCode();
        result = 31 * result + kdBIdang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        result = 31 * result + kdSub.hashCode();
        return result;
    }

}
