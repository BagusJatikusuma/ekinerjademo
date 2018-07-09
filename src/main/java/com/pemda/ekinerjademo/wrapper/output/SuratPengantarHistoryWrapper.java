package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 22/01/18.
 */
public class SuratPengantarHistoryWrapper {
    private String kdSurat;
    private String createdDate;
    private boolean isSuratPejabat;
    private Integer statusBaca;
    private String jenisSurat;
    private Integer kdJenisSurat;
    private Long tanggalDibuatMilis;
    private Integer statusPenilaian;

    public SuratPengantarHistoryWrapper() {
    }

    public SuratPengantarHistoryWrapper(String kdSurat, String createdDate, boolean isSuratPejabat, Integer statusBaca, String jenisSurat, Integer kdJenisSurat, Long tanggalDibuatMilis, Integer statusPenilaian) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
        this.statusBaca = statusBaca;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.statusPenilaian = statusPenilaian;
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

    public Integer getStatusPenilaian() {
        return statusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        this.statusPenilaian = statusPenilaian;
    }
}
