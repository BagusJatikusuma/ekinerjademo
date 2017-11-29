package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 18/11/17.
 */
@Entity
@Table(name = "surat_disposisi")
public class SuratDisposisi {
    @Id
    @Column(name = "no_surat")
    private String noSurat;
    @Column(name = "tgl_surat_milis")
    private Long tanggalSuratMilis;
    @Column(name = "dari")
    private String dari;
    @Column(name = "ringkasan_isi")
    private String ringkasanIsi;
    @Column(name = "lampiran")
    private String lampiran;

    public SuratDisposisi() {
    }
    public SuratDisposisi(String noSurat) {
        this.noSurat = noSurat;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public Long getTanggalSuratMilis() {
        return tanggalSuratMilis;
    }

    public void setTanggalSuratMilis(Long tanggalSuratMilis) {
        this.tanggalSuratMilis = tanggalSuratMilis;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getRingkasanIsi() {
        return ringkasanIsi;
    }

    public void setRingkasanIsi(String ringkasanIsi) {
        this.ringkasanIsi = ringkasanIsi;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }
}
