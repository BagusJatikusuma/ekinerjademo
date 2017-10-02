package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ref_Urusan")
public class RefUrusan {
    @Id
    @Column(name = "Kd_Urusan")
    private Integer kdUrusan;
    @Column(name = "Nm_Urusan")
    private String nmUrusan;

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public String getNmUrusan() {
        return nmUrusan;
    }

    public void setNmUrusan(String nmUrusan) {
        this.nmUrusan = nmUrusan;
    }
}
