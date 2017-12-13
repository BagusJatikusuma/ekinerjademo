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
    @Column(name = "path_file")
    private String pathFile;
    @Column(name = "jenis_surat_penugasan")
    private Integer jenisSuratPenugasan;
    @Column(name = "kd_surat_penugasan")
    private String kdSuratPenugasan;
    @Column(name = "no_surat_disposisi")
    private String noSuratDisposisi;

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

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Integer getJenisSuratPenugasan() {
        return jenisSuratPenugasan;
    }

    public void setJenisSuratPenugasan(Integer jenisSuratPenugasan) {
        this.jenisSuratPenugasan = jenisSuratPenugasan;
    }

    public String getKdSuratPenugasan() {
        return kdSuratPenugasan;
    }

    public void setKdSuratPenugasan(String kdSuratPenugasan) {
        this.kdSuratPenugasan = kdSuratPenugasan;
    }

    public String getNoSuratDisposisi() {
        return noSuratDisposisi;
    }

    public void setNoSuratDisposisi(String noSuratDisposisi) {
        this.noSuratDisposisi = noSuratDisposisi;
    }
}
