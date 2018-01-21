package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 10/01/18.
 */
@Entity
@Table(name = "surat_edaran_sub")
public class SuratEdaranSub {
    @EmbeddedId
    private SuratEdaranSubId suratEdaranSubId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_edaran",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_edaran")
    private SuratEdaran suratEdaran;

    @Column(name = "nama_sub")
    private String namaSub;

    @Column(name = "isi_sub")
    private String isiSub;

    public SuratEdaranSubId getSuratEdaranSubId() {
        return suratEdaranSubId;
    }

    public void setSuratEdaranSubId(SuratEdaranSubId suratEdaranSubId) {
        this.suratEdaranSubId = suratEdaranSubId;
    }

    public SuratEdaran getSuratEdaran() {
        return suratEdaran;
    }

    public void setSuratEdaran(SuratEdaran suratEdaran) {
        this.suratEdaran = suratEdaran;
    }

    public String getNamaSub() {
        return namaSub;
    }

    public void setNamaSub(String namaSub) {
        this.namaSub = namaSub;
    }

    public String getIsiSub() {
        return isiSub;
    }

    public void setIsiSub(String isiSub) {
        this.isiSub = isiSub;
    }
}
