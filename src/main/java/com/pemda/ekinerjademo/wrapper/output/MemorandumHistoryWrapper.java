package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bayu on 06/01/18.
 */
public class MemorandumHistoryWrapper {
    private String kdMemorandum;
    private String tanggalDibuat;
    private boolean isSuratPejabat;
    private Integer statusBaca;
    private String jenisSurat;
    private Integer kdJenisSurat;
    private Long tanggalDibuatMilis;
    private Integer statusPenilaian;

    public MemorandumHistoryWrapper() {
    }

    public MemorandumHistoryWrapper(String kdMemorandum, String tanggalDibuat, boolean isSuratPejabat, Integer statusBaca, String jenisSurat, Integer kdJenisSurat, Long tanggalDibuatMilis, Integer statusPenilaian) {
        this.kdMemorandum = kdMemorandum;
        this.tanggalDibuat = tanggalDibuat;
        this.isSuratPejabat = isSuratPejabat;
        this.statusBaca = statusBaca;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.statusPenilaian = statusPenilaian;
    }

    public String getKdMemorandum() {
        return kdMemorandum;
    }

    public void setKdMemorandum(String kdMemorandum) {
        this.kdMemorandum = kdMemorandum;
    }

    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public boolean isSuratPejabat() {
        return isSuratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        isSuratPejabat = suratPejabat;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }

    public Long getTanggalDibuatMilis() {
        return tanggalDibuatMilis;
    }

    public void setTanggalDibuatMilis(Long tanggalDibuatMilis) {
        this.tanggalDibuatMilis = tanggalDibuatMilis;
    }

    public Integer getStatusPenilaian() {
        return statusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        this.statusPenilaian = statusPenilaian;
    }
}
