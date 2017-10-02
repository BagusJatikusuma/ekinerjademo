package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ref_Sub_Unit")
public class RefSubUnit {
    @EmbeddedId
    private RefSubUnitId refSubUnitId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "Kd_Urusan",
                    referencedColumnName = "Kd_Urusan",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Bidang",
                    referencedColumnName = "Kd_Bidang",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Unit",
                    referencedColumnName = "Kd_Unit",
                    insertable = false,
                    updatable = false)
    })
    private RefUnit refUnit;
    @Column(name = "Nm_Sub_Unit")
    private String nmSubUnit;

    public RefSubUnitId getRefSubUnitId() {
        return refSubUnitId;
    }

    public void setRefSubUnitId(RefSubUnitId refSubUnitId) {
        this.refSubUnitId = refSubUnitId;
    }

    public RefUnit getRefUnit() {
        return refUnit;
    }

    public void setRefUnit(RefUnit refUnit) {
        this.refUnit = refUnit;
    }

    public String getNmSubUnit() {
        return nmSubUnit;
    }

    public void setNmSubUnit(String nmSubUnit) {
        this.nmSubUnit = nmSubUnit;
    }

}
