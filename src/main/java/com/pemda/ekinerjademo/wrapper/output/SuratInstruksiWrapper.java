package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 23/11/17.
 */
public class SuratInstruksiWrapper {
    private String kdInstruksi;
    private String judulInstruksi;
    private String tanggalDibuat;
    private boolean suratPejabat;

    public SuratInstruksiWrapper() {
    }
    public SuratInstruksiWrapper(
            String kdInstruksi,
            String judulInstruksi,
            String tanggalDibuat,
            boolean suratPejabat) {
        this.kdInstruksi = kdInstruksi;
        this.judulInstruksi = judulInstruksi;
        this.tanggalDibuat = tanggalDibuat;
        this.suratPejabat = suratPejabat;
    }

    public String getKdInstruksi() {
        return kdInstruksi;
    }

    public void setKdInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }

    public String getJudulInstruksi() {
        return judulInstruksi;
    }

    public void setJudulInstruksi(String judulInstruksi) {
        this.judulInstruksi = judulInstruksi;
    }

    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public boolean isSuratPejabat() {
        return suratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        this.suratPejabat = suratPejabat;
    }
}
