package com.pemda.ekinerjademo.model.bismamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 07/09/17.
 */
@Entity
@Table(name = "tkdjabatan")
public class TkdJabatan {
    @Id
    @Column(name = "kdJabatan")
    private String kdJabatan;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "eselon")
    private String eselon;

    @Column(name = "pada")
    private String pada;

    @Column(name = "unitKerja")
    private String unitKerja;

    @Column(name = "tnjJabatan")
    private Long tnjJabatan;

    @Column(name = "tnjJabatanLm")
    private Long tnjJabatanLm;

    @Column(name = "kdUnitKerja")
    private String kdUnitKerja;

    @Column(name = "ket")
    private String ket;

    public TkdJabatan() {}

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getEselon() {
        return eselon;
    }

    public void setEselon(String eselon) {
        this.eselon = eselon;
    }

    public String getPada() {
        return pada;
    }

    public void setPada(String pada) {
        this.pada = pada;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public Long getTnjJabatan() {
        return tnjJabatan;
    }

    public void setTnjJabatan(Long tnjJabatan) {
        this.tnjJabatan = tnjJabatan;
    }

    public Long getTnjJabatanLm() {
        return tnjJabatanLm;
    }

    public void setTnjJabatanLm(Long tnjJabatanLm) {
        this.tnjJabatanLm = tnjJabatanLm;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

}
