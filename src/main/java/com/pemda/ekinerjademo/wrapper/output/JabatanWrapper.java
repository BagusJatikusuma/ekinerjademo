package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class JabatanWrapper {
    private String kdJabatan;
    private String jabatan;
    private String eselon;
    private String kdUnitKerja;
    private String unitKerja;

    public JabatanWrapper() {}
    public JabatanWrapper(String kdJabatan, String jabatan) {
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
    }
    public JabatanWrapper(String kdJabatan, String jabatan, String eselon) {
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
        this.eselon = eselon;
    }
    public JabatanWrapper(String kdJabatan, String jabatan, String eselon, String kdUnitKerja, String unitKerja) {
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
        this.eselon = eselon;
        this.kdUnitKerja = kdUnitKerja;
        this.unitKerja = unitKerja;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getEselon() {
        return eselon;
    }

    public void setEselon(String eselon) {
        this.eselon = eselon;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }
}
