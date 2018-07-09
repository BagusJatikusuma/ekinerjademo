package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "surat_tugas_non_pejabat")
public class SuratTugasNonPejabat {
    @Id
    @Column(name = "kd_surat_tugas")
    private String kdSuratTugas;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_surat_tugas", referencedColumnName = "kd_surat_tugas")
    private SuratTugas suratTugas;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public String getKdSuratTugas() {
        return kdSuratTugas;
    }

    public void setKdSuratTugas(String kdSuratTugas) {
        this.kdSuratTugas = kdSuratTugas;
    }

    public SuratTugas getSuratTugas() {
        return suratTugas;
    }

    public void setSuratTugas(SuratTugas suratTugas) {
        this.suratTugas = suratTugas;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }
}
