package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 10/01/18.
 */
@Embeddable
public class SuratEdaranSubId implements Serializable {
    @Column(name = "kd_surat_edaran")
    private String kdSuratEdaran;
    @Column(name = "kd_surat_edaran_sub")
    private String kdSuratEdaranSub;

    public SuratEdaranSubId() {
    }

    public SuratEdaranSubId(String kdSuratEdaran, String kdSuratEdaranSub) {
        this.kdSuratEdaran = kdSuratEdaran;
        this.kdSuratEdaranSub = kdSuratEdaranSub;
    }

    public String getKdSuratEdaran() {
        return kdSuratEdaran;
    }

    public void setKdSuratEdaran(String kdSuratEdaran) {
        this.kdSuratEdaran = kdSuratEdaran;
    }

    public String getKdSuratEdaranSub() {
        return kdSuratEdaranSub;
    }

    public void setKdSuratEdaranSub(String kdSuratEdaranSub) {
        this.kdSuratEdaranSub = kdSuratEdaranSub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuratEdaranSubId that = (SuratEdaranSubId) o;

        if (!kdSuratEdaran.equals(that.kdSuratEdaran)) return false;
        return kdSuratEdaranSub.equals(that.kdSuratEdaranSub);
    }

    @Override
    public int hashCode() {
        int result = kdSuratEdaran.hashCode();
        result = 31 * result + kdSuratEdaranSub.hashCode();
        return result;
    }
}
