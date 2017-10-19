package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 08/10/17.
 */
public class SopUraianTugasJabatanWrapper {
    private String kdSop;
    private String kdUrtug;
    private String kdJabatan;

    public SopUraianTugasJabatanWrapper() {
    }
    public SopUraianTugasJabatanWrapper(
            String kdSop,
            String kdUrtug,
            String kdJabatan) {
        this.kdSop = kdSop;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

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
}
