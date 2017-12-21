package com.pemda.ekinerjademo.model.bismamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 21/12/17.
 */
@Entity
@Table(name = "tkdunk")
public class TkdUnk {
    @Id
    @Column(name = "KdUnK")
    private String kdUnK;

    @Column(name = "UnitKerja")
    private String unitKerja;

    @Column(name = "AlamatUnitKerja")
    private String alamatUnitKerja;

    @Column(name = "TeleponUnk")
    private String teleponUnK;

    @Column(name = "FaxUnk")
    private String faxUnk;

    @Column(name = "TypeSKPD")
    private String typeSKPD;

    public String getKdUnK() {
        return kdUnK;
    }

    public void setKdUnK(String kdUnK) {
        this.kdUnK = kdUnK;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public String getAlamatUnitKerja() {
        return alamatUnitKerja;
    }

    public void setAlamatUnitKerja(String alamatUnitKerja) {
        this.alamatUnitKerja = alamatUnitKerja;
    }

    public String getTeleponUnK() {
        return teleponUnK;
    }

    public void setTeleponUnK(String teleponUnK) {
        this.teleponUnK = teleponUnK;
    }

    public String getFaxUnk() {
        return faxUnk;
    }

    public void setFaxUnk(String faxUnk) {
        this.faxUnk = faxUnk;
    }

    public String getTypeSKPD() {
        return typeSKPD;
    }

    public void setTypeSKPD(String typeSKPD) {
        this.typeSKPD = typeSKPD;
    }
}
