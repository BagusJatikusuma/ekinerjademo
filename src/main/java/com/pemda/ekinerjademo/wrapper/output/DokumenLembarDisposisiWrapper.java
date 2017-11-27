package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 27/11/17.
 */
public class DokumenLembarDisposisiWrapper {
    private String kdLembarDisposisi;
    private String path;
    private String tanggalPenerimaan;
    private Integer tktKeamanan;
    private String tglPenyelesaian;
    private String noSuratDisposisi;
    private String tanggalSuratDisposisi;
    private String dari;
    private String ringkasanIsi;
    private String lampiran;
    private List<QutPegawaiWrapper> targetPegawaiLembarDisposisi;

    public DokumenLembarDisposisiWrapper() {
    }
    public DokumenLembarDisposisiWrapper(
            String kdLembarDisposisi,
            String path,
            String tanggalPenerimaan,
            Integer tktKeamanan,
            String tglPenyelesaian,
            String noSuratDisposisi,
            String tanggalSuratDisposisi,
            String dari,
            String ringkasanIsi,
            String lampiran,
            List<QutPegawaiWrapper> targetPegawaiLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.noSuratDisposisi = noSuratDisposisi;
        this.tanggalSuratDisposisi = tanggalSuratDisposisi;
        this.dari = dari;
        this.ringkasanIsi = ringkasanIsi;
        this.lampiran = lampiran;
        this.targetPegawaiLembarDisposisi = targetPegawaiLembarDisposisi;
    }

    public String getKdLembarDisposisi() {
        return kdLembarDisposisi;
    }

    public void setKdLembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTanggalPenerimaan() {
        return tanggalPenerimaan;
    }

    public void setTanggalPenerimaan(String tanggalPenerimaan) {
        this.tanggalPenerimaan = tanggalPenerimaan;
    }

    public Integer getTktKeamanan() {
        return tktKeamanan;
    }

    public void setTktKeamanan(Integer tktKeamanan) {
        this.tktKeamanan = tktKeamanan;
    }

    public String getTglPenyelesaian() {
        return tglPenyelesaian;
    }

    public void setTglPenyelesaian(String tglPenyelesaian) {
        this.tglPenyelesaian = tglPenyelesaian;
    }

    public String getNoSuratDisposisi() {
        return noSuratDisposisi;
    }

    public void setNoSuratDisposisi(String noSuratDisposisi) {
        this.noSuratDisposisi = noSuratDisposisi;
    }

    public String getTanggalSuratDisposisi() {
        return tanggalSuratDisposisi;
    }

    public void setTanggalSuratDisposisi(String tanggalSuratDisposisi) {
        this.tanggalSuratDisposisi = tanggalSuratDisposisi;
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

    public List<QutPegawaiWrapper> getTargetPegawaiLembarDisposisi() {
        return targetPegawaiLembarDisposisi;
    }

    public void setTargetPegawaiLembarDisposisi(List<QutPegawaiWrapper> targetPegawaiLembarDisposisi) {
        this.targetPegawaiLembarDisposisi = targetPegawaiLembarDisposisi;
    }
}
