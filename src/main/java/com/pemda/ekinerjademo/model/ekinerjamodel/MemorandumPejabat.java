package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 04/01/18.
 */
@Entity
@Table(name = "memorandum_pejabat")
public class MemorandumPejabat {
    @Id
    @Column(name = "kd_memorandum")
    private String kdMemorandum;



    @Column(name = "kd_jabatan")
    private String kdJabatan;

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
}
