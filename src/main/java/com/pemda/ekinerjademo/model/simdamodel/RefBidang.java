package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ref_Bidang")
public class RefBidang {
    @EmbeddedId
    private RefBidangId refBidangId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Kd_Urusan", insertable = false, updatable = false)
    private RefUrusan refUrusan;
    @Column(name = "Nm_Bidang")
    private String nmBidang;

    public RefBidangId getRefBidangId() {
        return refBidangId;
    }

    public void setRefBidangId(RefBidangId refBidangId) {
        this.refBidangId = refBidangId;
    }

    public RefUrusan getRefUrusan() {
        return refUrusan;
    }

    public void setRefUrusan(RefUrusan refUrusan) {
        this.refUrusan = refUrusan;
    }

    public String getNmBidang() {
        return nmBidang;
    }

    public void setNmBidang(String nmBidang) {
        this.nmBidang = nmBidang;
    }

}
