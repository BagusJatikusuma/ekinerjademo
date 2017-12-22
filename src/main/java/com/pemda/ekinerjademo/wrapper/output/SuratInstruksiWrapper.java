package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 23/11/17.
 */
public class SuratInstruksiWrapper {
    private String kdInstruksi;
    private String judulInstruksi;
    private String tanggalDibuat;
    private Long tanggalDibuatMilis;
    private boolean suratPejabat;
    private Integer statusbaca;
    private String nipPengirim;
    private String namaPengirim;
    private Integer statusBaca;
    private String path;

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
    public SuratInstruksiWrapper(
            String kdInstruksi,
            String judulInstruksi,
            String tanggalDibuat,
            boolean suratPejabat,
            Integer statusbaca) {
        this.kdInstruksi = kdInstruksi;
        this.judulInstruksi = judulInstruksi;
        this.tanggalDibuat = tanggalDibuat;
        this.suratPejabat = suratPejabat;
        this.statusbaca = statusbaca;
    }
    public SuratInstruksiWrapper(
            String kdInstruksi,
            String judulInstruksi,
            String tanggalDibuat,
            Long tanggalDibuatMilis,
            boolean suratPejabat,
            Integer statusbaca,
            String nipPengirim,
            String namaPengirim) {
        this.kdInstruksi = kdInstruksi;
        this.judulInstruksi = judulInstruksi;
        this.tanggalDibuat = tanggalDibuat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.suratPejabat = suratPejabat;
        this.statusbaca = statusbaca;
        this.nipPengirim = nipPengirim;
        this.namaPengirim = namaPengirim;
    }
    public SuratInstruksiWrapper(
            String kdInstruksi,
            String judulInstruksi,
            String tanggalDibuat,
            Long tanggalDibuatMilis,
            boolean suratPejabat,
            Integer statusbaca,
            String nipPengirim,
            String namaPengirim,
            Integer statusBaca) {
        this.kdInstruksi = kdInstruksi;
        this.judulInstruksi = judulInstruksi;
        this.tanggalDibuat = tanggalDibuat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.suratPejabat = suratPejabat;
        this.statusbaca = statusbaca;
        this.nipPengirim = nipPengirim;
        this.namaPengirim = namaPengirim;
        this.statusBaca = statusBaca;
    }
    public SuratInstruksiWrapper(
            String kdInstruksi,
            String judulInstruksi,
            String tanggalDibuat,
            Long tanggalDibuatMilis,
            boolean suratPejabat,
            Integer statusbaca,
            String nipPengirim,
            String namaPengirim,
            Integer statusBaca,
            String path) {
        this.kdInstruksi = kdInstruksi;
        this.judulInstruksi = judulInstruksi;
        this.tanggalDibuat = tanggalDibuat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.suratPejabat = suratPejabat;
        this.statusbaca = statusbaca;
        this.nipPengirim = nipPengirim;
        this.namaPengirim = namaPengirim;
        this.statusBaca = statusBaca;
        this.path = path;
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

    public Integer getStatusbaca() {
        return statusbaca;
    }

    public void setStatusbaca(Integer statusbaca) {
        this.statusbaca = statusbaca;
    }

    public Long getTanggalDibuatMilis() {
        return tanggalDibuatMilis;
    }

    public void setTanggalDibuatMilis(Long tanggalDibuatMilis) {
        this.tanggalDibuatMilis = tanggalDibuatMilis;
    }

    public String getNipPengirim() {
        return nipPengirim;
    }

    public void setNipPengirim(String nipPengirim) {
        this.nipPengirim = nipPengirim;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
