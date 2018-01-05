package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 04/01/18.
 */
@Embeddable
public class TembusanMemorandumId implements Serializable {
    @Column(name = "kd_memorandum")
    private String kdMemorandum;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public TembusanMemorandumId() {
    }
    public TembusanMemorandumId(String kdMemorandum, String kdJabatan) {
        this.kdMemorandum = kdMemorandum;
        this.kdJabatan = kdJabatan;
    }

    public String getKdMemorandum() {
        return kdMemorandum;
    }

    public void setKdMemorandum(String kdMemorandum) {
        this.kdMemorandum = kdMemorandum;
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

        TembusanMemorandumId that = (TembusanMemorandumId) o;

        if (!kdMemorandum.equals(that.kdMemorandum)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdMemorandum.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }
}
