package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 04/12/17.
 */

@Entity
@Table(name = "Ref_Indikator")
public class RefIndikator {
    @Id
    @Column(name = "Kd_Indikator")
    private Integer kdIndikator;
    @Column(name = "Nama_Indikator")
    private String namaIndikator;

    public Integer getKdIndikator() {
        return kdIndikator;
    }

    public void setKdIndikator(Integer kdIndikator) {
        this.kdIndikator = kdIndikator;
    }

    public String getNamaIndikator() {
        return namaIndikator;
    }

    public void setNamaIndikator(String namaIndikator) {
        this.namaIndikator = namaIndikator;
    }
}
