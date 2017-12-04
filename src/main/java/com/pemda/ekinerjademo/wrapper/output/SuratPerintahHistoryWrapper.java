package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 15/11/17.
 */
public class SuratPerintahHistoryWrapper {
    private String kdSurat;
    private String createdDate;
    private boolean isSuratPejabat;

    public SuratPerintahHistoryWrapper() {
    }
    public SuratPerintahHistoryWrapper(String kdSurat, String createdDate, boolean isSuratPejabat) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
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
}
