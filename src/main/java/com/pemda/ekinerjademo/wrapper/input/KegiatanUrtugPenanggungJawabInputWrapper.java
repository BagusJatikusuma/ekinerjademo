package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 12/03/18.
 */
public class KegiatanUrtugPenanggungJawabInputWrapper {
    private String kdUrtug;
    private String kdJabatan;
    private String kdJenisUrtug;
    private Integer tahunUrtug;
    private List<KegiatanWrapper> kegiatanList;

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

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public List<KegiatanWrapper> getKegiatanList() {
        return kegiatanList;
    }

    public void setKegiatanList(List<KegiatanWrapper> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }
}
