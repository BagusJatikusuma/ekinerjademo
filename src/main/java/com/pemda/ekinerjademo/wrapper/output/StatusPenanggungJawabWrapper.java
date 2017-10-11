package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/10/17.
 */
public class StatusPenanggungJawabWrapper {
    private String kdStatus;
    private String status;

    public StatusPenanggungJawabWrapper() {
    }
    public StatusPenanggungJawabWrapper(String kdStatus, String status) {
        this.kdStatus = kdStatus;
        this.status = status;
    }

    public String getKdStatus() {
        return kdStatus;
    }

    public void setKdStatus(String kdStatus) {
        this.kdStatus = kdStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
