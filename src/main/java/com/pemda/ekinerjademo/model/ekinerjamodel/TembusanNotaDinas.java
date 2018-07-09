package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bayu on 07/01/18.
 */
@Entity
@Table(name = "tembusan_nota_dinas")
public class TembusanNotaDinas {
    @EmbeddedId
    private TembusanNotaDinasId tembusanNotaDinasId;

    @Column(name = "status_diterima")
    private Integer statusDiterima;

    @Column(name = "status_baca")
    private Integer statusBaca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_nota_dinas",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_nota_dinas")
    private NotaDinas notaDinas;

    public TembusanNotaDinasId getTembusanNotaDinasId() {
        return tembusanNotaDinasId;
    }

    public void setTembusanNotaDinasId(TembusanNotaDinasId tembusanNotaDinasId) {
        this.tembusanNotaDinasId = tembusanNotaDinasId;
    }

    public Integer getStatusDiterima() {
        return statusDiterima;
    }

    public void setStatusDiterima(Integer statusDiterima) {
        this.statusDiterima = statusDiterima;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public NotaDinas getNotaDinas() {
        return notaDinas;
    }

    public void setNotaDinas(NotaDinas notaDinas) {
        this.notaDinas = notaDinas;
    }
}
