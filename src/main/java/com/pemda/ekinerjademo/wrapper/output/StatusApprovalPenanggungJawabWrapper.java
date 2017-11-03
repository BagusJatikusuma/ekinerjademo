package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 02/11/17.
 */
public class StatusApprovalPenanggungJawabWrapper {
    private String nipPegawai;
    private String kdStatusPenanggungJawab;
    private String statusPenanggungJawab;
    private Integer statusApproval;

    public StatusApprovalPenanggungJawabWrapper() {
    }
    public StatusApprovalPenanggungJawabWrapper(
            String nipPegawai,
            String kdStatusPenanggungJawab,
            String statusPenanggungJawab,
            Integer statusApproval) {
        this.nipPegawai = nipPegawai;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
        this.statusPenanggungJawab = statusPenanggungJawab;
        this.statusApproval = statusApproval;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
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
