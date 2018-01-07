package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 07/01/18.
 */
@Embeddable
public class TembusanNotaDinasId implements Serializable {
    @Column(name = "kd_nota_dinas")
    private String kdNotaDinas;
    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public TembusanNotaDinasId() {
    }
    public TembusanNotaDinasId(String kdNotaDinas, String kdJabatan) {
        this.kdNotaDinas = kdNotaDinas;
        this.kdJabatan = kdJabatan;
    }

    public String getKdNotaDinas() {
        return kdNotaDinas;
    }

    public void setKdNotaDinas(String kdNotaDinas) {
        this.kdNotaDinas = kdNotaDinas;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TembusanNotaDinasId that = (TembusanNotaDinasId) o;

        if (!kdNotaDinas.equals(that.kdNotaDinas)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdNotaDinas.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }
}
