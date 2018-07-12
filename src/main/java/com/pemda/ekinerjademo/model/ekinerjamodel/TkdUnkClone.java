package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tkd_unk_clone")
public class TkdUnkClone {
    @Id
    @Column(name = "kd_unk")
    private String kdUnK;

    @Column(name = "unit_kerja")
    private String unitKerja;

    @Column(name = "alamat_unit_kerja")
    private String alamatUnitKerja;

    @Column(name = "telepon_unk")
    private String teleponUnK;

    @Column(name = "fax_unk")
    private String faxUnk;

    @Column(name = "type_SKPD")
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
