package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 11/09/17.
 */
public class UraianTugasJabatanInputWrapper {
    private String kdJabatan;
    private List<KdUraianTugasWrapper> kdUraianTugasList;
    private String createdBy;

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public List<KdUraianTugasWrapper> getKdUraianTugasList() {
        return kdUraianTugasList;
    }

    public void setKdUraianTugasList(List<KdUraianTugasWrapper> kdUraianTugasList) {
        this.kdUraianTugasList = kdUraianTugasList;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
