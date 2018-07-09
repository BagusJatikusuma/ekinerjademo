package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 06/11/17.
 */
public class NodinHistoryWrapper {
    private String kdHistory;
    private String createdDate;

    public NodinHistoryWrapper() {
    }
    public NodinHistoryWrapper(String kdHistory, String createdDate) {
        this.kdHistory = kdHistory;
        this.createdDate = createdDate;
    }

    public String getKdHistory() {
        return kdHistory;
    }

    public void setKdHistory(String kdHistory) {
        this.kdHistory = kdHistory;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
