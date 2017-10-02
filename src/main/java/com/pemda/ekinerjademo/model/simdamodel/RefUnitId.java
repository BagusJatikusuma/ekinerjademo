package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 29/09/17.
 */
@Embeddable
public class RefUnitId implements Serializable {
    @Column(name = "Kd_Urusan")
    private Integer kdUrusan;
    @Column(name = "Kd_Bidang")
    private Integer kdBIdang;
    @Column(name = "Kd_Unit")
    private Integer kdUnit;

    public RefUnitId() {}
    public RefUnitId(Integer kdUrusan, Integer kdBIdang, Integer kdUnit) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefUnitId refUnitId = (RefUnitId) o;

        if (!kdUrusan.equals(refUnitId.kdUrusan)) return false;
        if (!kdBIdang.equals(refUnitId.kdBIdang)) return false;
        return kdUnit.equals(refUnitId.kdUnit);
    }

    @Override
    public int hashCode() {
        int result = kdUrusan.hashCode();
        result = 31 * result + kdBIdang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        return result;
    }

}
