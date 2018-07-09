package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ref_Unit")
public class RefUnit {
    @EmbeddedId
    private RefUnitId refUnitId;
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
                    updatable = false)
    })
    private RefBidang refBidang;
    @Column(name = "Nm_Unit")
    private String nmUnit;

    public RefUnitId getRefUnitId() {
        return refUnitId;
    }

    public void setRefUnitId(RefUnitId refUnitId) {
        this.refUnitId = refUnitId;
    }

    public RefBidang getRefBidang() {
        return refBidang;
    }

    public void setRefBidang(RefBidang refBidang) {
        this.refBidang = refBidang;
    }

    public String getNmUnit() {
        return nmUnit;
    }

    public void setNmUnit(String nmUnit) {
        this.nmUnit = nmUnit;
    }

}
