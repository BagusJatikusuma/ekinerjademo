package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 09/01/18.
 */
@Entity
@Table(name = "surat_pengantar_isi")
public class SuratPengantarIsi {
    @EmbeddedId
    private SuratPengantarIsiId suratPengantarIsiId;
    @Column(name = "naskah_dinas_yang_dikirim")
    private String naskahDinasYangDikirim;
    @Column(name = "banyak_naskah")
    private Integer banyakNaskah;
    @Column(name = "keterangan")
    private String keterangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_pengantar",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_pengantar")
    private SuratPengantar suratPengantar;

    public SuratPengantarIsiId getSuratPengantarIsiId() {
        return suratPengantarIsiId;
    }

    public void setSuratPengantarIsiId(SuratPengantarIsiId suratPengantarIsiId) {
        this.suratPengantarIsiId = suratPengantarIsiId;
    }

    public String getNaskahDinasYangDikirim() {
        return naskahDinasYangDikirim;
    }

    public void setNaskahDinasYangDikirim(String naskahDinasYangDikirim) {
        this.naskahDinasYangDikirim = naskahDinasYangDikirim;
    }

    public Integer getBanyakNaskah() {
        return banyakNaskah;
    }

    public void setBanyakNaskah(Integer banyakNaskah) {
        this.banyakNaskah = banyakNaskah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public SuratPengantar getSuratPengantar() {
        return suratPengantar;
    }

    public void setSuratPengantar(SuratPengantar suratPengantar) {
        this.suratPengantar = suratPengantar;
    }
}
