package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

public class PejabatBarjasPPUKWrapper {
    private String nip;
    private String nama;
    private String jabatan;
    private String jabatanBarjas;
    private List<PejabatBarjasPPKWrapper> pejabatBarjasPPKList;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getJabatanBarjas() {
        return jabatanBarjas;
    }

    public void setJabatanBarjas(String jabatanBarjas) {
        this.jabatanBarjas = jabatanBarjas;
    }

    public List<PejabatBarjasPPKWrapper> getPejabatBarjasPPKList() {
        return pejabatBarjasPPKList;
    }

    public void setPejabatBarjasPPKList(List<PejabatBarjasPPKWrapper> pejabatBarjasPPKList) {
        this.pejabatBarjasPPKList = pejabatBarjasPPKList;
    }
}
