package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 04/01/18.
 */
@Entity
@Table(name = "memorandum_non_pejabat")
public class MemorandumNonPejabat {
    @Id
    @Column(name = "kd_memorandum")
    private String kdMemorandum;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_memorandum", referencedColumnName = "kd_memorandum")
    private Memorandum memorandum;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public String getKdMemorandum() {
        return kdMemorandum;
    }

    public void setKdMemorandum(String kdMemorandum) {
        this.kdMemorandum = kdMemorandum;
    }

    public Memorandum getMemorandum() {
        return memorandum;
    }

    public void setMemorandum(Memorandum memorandum) {
        this.memorandum = memorandum;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }
}
