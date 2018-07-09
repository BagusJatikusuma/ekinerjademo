package com.pemda.ekinerjademo.wrapper.input;

/**
 * Created by bagus on 05/12/17.
 */
public class TargetSuratDiterimaInputWrapper {
    private String kdTarget;
    private Integer jenisTarget; //0 = targetPegawai, 1 = targetPejabat, 2 = tembusan

    public String getKdTarget() {
        return kdTarget;
    }

    public void setKdTarget(String kdTarget) {
        this.kdTarget = kdTarget;
    }

    public Integer getJenisTarget() {
        return jenisTarget;
    }

    public void setJenisTarget(Integer jenisTarget) {
        this.jenisTarget = jenisTarget;
    }
}
