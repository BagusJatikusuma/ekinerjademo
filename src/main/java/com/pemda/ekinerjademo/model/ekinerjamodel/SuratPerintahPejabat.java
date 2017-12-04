package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 28/11/17.
 */
@Entity
@Table(name = "surat_perintah_pejabat")
public class SuratPerintahPejabat {
    @Id
    @Column(name = "kd_surat_perintah")
    private String kdSuratPerintah;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "kd_surat_perintah", referencedColumnName = "kd_surat_perintah")
    private SuratPerintah suratPerintah;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
    }

    public SuratPerintah getSuratPerintah() {
        return suratPerintah;
    }

    public void setSuratPerintah(SuratPerintah suratPerintah) {
        this.suratPerintah = suratPerintah;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }
}
