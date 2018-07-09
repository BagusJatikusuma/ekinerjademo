package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 07/01/18.
 */
@Entity
@Table(name = "surat_dinas_non_pejabat")
public class SuratDinasNonPejabat {
    @Id
    @Column(name = "kd_surat_dinas")
    private String kdSuratDinas;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_surat_dinas", referencedColumnName = "kd_surat_dinas")
    private SuratDinas suratDinas;

    public String getKdSuratDinas() {
        return kdSuratDinas;
    }

    public void setKdSuratDinas(String kdSuratDinas) {
        this.kdSuratDinas = kdSuratDinas;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public SuratDinas getSuratDinas() {
        return suratDinas;
    }

    public void setSuratDinas(SuratDinas suratDinas) {
        this.suratDinas = suratDinas;
    }
}
