package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class JabatanWrapper {
    private String kdJabatan;
    private String jabatan;
    private String eselon;

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
}
