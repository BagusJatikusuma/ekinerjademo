package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 26/09/17.
 */
@Entity
@Table(name = "jenis_urtug")
public class JenisUrtug {
    @Id
    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    @Column(name = "jenis_urtug")
    private String jenisUrtug;

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
