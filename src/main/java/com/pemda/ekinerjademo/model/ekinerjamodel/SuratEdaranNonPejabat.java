package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 10/01/18.
 */
@Entity
@Table(name = "surat_edaran_non_pejabat")
public class SuratEdaranNonPejabat {
    @Id
    @Column(name = "kd_surat_edaran")
    private String kdSuratEdaran;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_surat_edaran", referencedColumnName = "kd_surat_edaran")
    private SuratEdaran suratEdaran;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public String getKdSuratEdaran() {
        return kdSuratEdaran;
    }

    public void setKdSuratEdaran(String kdSuratEdaran) {
        this.kdSuratEdaran = kdSuratEdaran;
    }

    public SuratEdaran getSuratEdaran() {
        return suratEdaran;
    }

    public void setSuratEdaran(SuratEdaran suratEdaran) {
        this.suratEdaran = suratEdaran;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }
}
