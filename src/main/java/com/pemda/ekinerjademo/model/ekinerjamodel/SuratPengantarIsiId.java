package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 09/01/18.
 */
@Embeddable
public class SuratPengantarIsiId implements Serializable {
    @Column(name = "kd_surat_pengantar")
    private String kdSuratPengantar;
    @Column(name = "kd_surat_pengantar_isi")
    private String kdSuratPengantarIsi;

    public SuratPengantarIsiId() {
    }

    public SuratPengantarIsiId(String kdSuratPengantar, String kdSuratPengantarIsi) {
        this.kdSuratPengantar = kdSuratPengantar;
        this.kdSuratPengantarIsi = kdSuratPengantarIsi;
    }

    public String getKdSuratPengantar() {
        return kdSuratPengantar;
    }

    public void setKdSuratPengantar(String kdSuratPengantar) {
        this.kdSuratPengantar = kdSuratPengantar;
    }

    public String getKdSuratPengantarIsi() {
        return kdSuratPengantarIsi;
    }

    public void setKdSuratPengantarIsi(String kdSuratPengantarIsi) {
        this.kdSuratPengantarIsi = kdSuratPengantarIsi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuratPengantarIsiId that = (SuratPengantarIsiId) o;

        if (!kdSuratPengantar.equals(that.kdSuratPengantar)) return false;
        return kdSuratPengantarIsi.equals(that.kdSuratPengantarIsi);
    }

    @Override
    public int hashCode() {
        int result = kdSuratPengantar.hashCode();
        result = 31 * result + kdSuratPengantarIsi.hashCode();
        return result;
    }
}
