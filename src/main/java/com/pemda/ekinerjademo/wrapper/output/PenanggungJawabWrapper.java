package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

/**
 * Created by bagus on 08/02/18.
 */
public class PenanggungJawabWrapper {
    private CustomPegawaiCredential penanggungJawab;
    private String kdStatusPenanggungJawab;
    private String statusPenanggungJawab;
    private Integer statusApproval;

    public PenanggungJawabWrapper() {
    }

    public PenanggungJawabWrapper(CustomPegawaiCredential penanggungJawab, String kdStatusPenanggungJawab, String statusPenanggungJawab, Integer statusApproval) {
        this.penanggungJawab = penanggungJawab;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
        this.statusPenanggungJawab = statusPenanggungJawab;
        this.statusApproval = statusApproval;
    }

    public CustomPegawaiCredential getPenanggungJawab() {
        return penanggungJawab;
    }

    public void setPenanggungJawab(CustomPegawaiCredential penanggungJawab) {
        this.penanggungJawab = penanggungJawab;
    }

    public String getKdStatusPenanggungJawab() {
        return kdStatusPenanggungJawab;
    }

    public void setKdStatusPenanggungJawab(String kdStatusPenanggungJawab) {
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
    }

    public String getStatusPenanggungJawab() {
        return statusPenanggungJawab;
    }

    public void setStatusPenanggungJawab(String statusPenanggungJawab) {
        this.statusPenanggungJawab = statusPenanggungJawab;
    }

    public Integer getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(Integer statusApproval) {
        this.statusApproval = statusApproval;
    }
}
