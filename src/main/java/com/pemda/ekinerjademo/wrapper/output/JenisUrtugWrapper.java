package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 28/09/17.
 */
public class JenisUrtugWrapper {
    private String kdJenisUrtug;
    private String jenisUrtug;

    public JenisUrtugWrapper(String kdJenisUrtug, String jenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
        this.jenisUrtug = jenisUrtug;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public String getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(String jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }
}
