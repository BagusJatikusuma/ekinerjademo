package com.pemda.ekinerjademo.model.ekinerjamodel;

public class JabatanUrtugDTO {
    private String jabatan;
    private String unitKerja;

    public JabatanUrtugDTO() {
    }

    public JabatanUrtugDTO(String jabatan, String unitKerja) {
        this.jabatan = jabatan;
        this.unitKerja = unitKerja;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }
}
