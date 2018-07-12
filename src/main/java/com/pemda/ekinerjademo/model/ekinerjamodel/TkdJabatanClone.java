package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

@Entity
@Table(name = "tkd_jabatan_clone")
public class TkdJabatanClone {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kdUnitKerja")
    private TkdUnkClone kdUnitKerja;

    @Column(name = "ket")
    private String ket;

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

    public TkdUnkClone getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(TkdUnkClone kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}
