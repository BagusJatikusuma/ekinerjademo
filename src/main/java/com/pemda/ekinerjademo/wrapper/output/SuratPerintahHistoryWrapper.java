package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 15/11/17.
 */
public class SuratPerintahHistoryWrapper {
    private String kdSurat;
    private String createdDate;
    private boolean isSuratPejabat;
    private Integer statusBaca;
    private String jenisSurat;
    private Integer kdJenisSurat;
    private Long tanggalDibuatMilis;

    public SuratPerintahHistoryWrapper() {
    }
    public SuratPerintahHistoryWrapper(String kdSurat, String createdDate, boolean isSuratPejabat) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
    }
    public SuratPerintahHistoryWrapper(
            String kdSurat,
            String createdDate,
            boolean isSuratPejabat,
            Integer statusBaca) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
        this.statusBaca = statusBaca;
    }
    public SuratPerintahHistoryWrapper(String kdSurat, String createdDate, boolean isSuratPejabat, Integer statusBaca, String jenisSurat, Integer kdJenisSurat) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
        this.statusBaca = statusBaca;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
    }
    public SuratPerintahHistoryWrapper(
            String kdSurat,
            String createdDate,
            boolean isSuratPejabat,
            Integer statusBaca,
            String jenisSurat,
            Integer kdJenisSurat,
            Long tanggalDibuatMilis) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
        this.statusBaca = statusBaca;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
    }

    public String getKdSurat() {
        return kdSurat;
    }

    public void setKdSurat(String kdSurat) {
        this.kdSurat = kdSurat;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
}
