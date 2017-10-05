package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 05/10/17.
 */
@Entity
@Table(name = "status_penanggung_jawab_kegiatan")
public class StatusPenanggungJawabKegiatan {
    @Id
    @Column(name = "kd_status")
    private String kdStatus;

    @Column(name = "status")
    private String status;

    public String getKdStatus() {
        return kdStatus;
    }

    public void setKdStatus(String kdStatus) {
        this.kdStatus = kdStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
