package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "surat_tugas_pejabat")
public class SuratTugasPejabat {
    @Id
    @Column(name = "kd_surat_tugas")
    private String kdSuratTugas;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_surat_tugas", referencedColumnName = "kd_surat_tugas")
    private SuratTugas suratTugas;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

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

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }
}
