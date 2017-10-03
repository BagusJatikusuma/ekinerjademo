package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 11/09/17.
 */
public class UraianTugasJabatanInputWrapper {
    private String kdUrtug;
    private String kdJabatan;
    private List<JenisUrtugUrtugInputWrapper> jenisUrtugList;
    private String createdBy;

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<JenisUrtugUrtugInputWrapper> getJenisUrtugList() {
        return jenisUrtugList;
    }

    public void setJenisUrtugList(List<JenisUrtugUrtugInputWrapper> jenisUrtugList) {
        this.jenisUrtugList = jenisUrtugList;
    }
}
