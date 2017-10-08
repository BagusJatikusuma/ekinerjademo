package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@Entity
@Table(name = "sop")
public class Sop {
    @Id
    @Column(name = "kd_sop")
    private String kdSop;

    @Column(name = "sop")
    private String sop;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sop")
    private List<SopTemplate> sopTemplates;

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

    public String getSop() {
        return sop;
    }

    public void setSop(String sop) {
        this.sop = sop;
    }
}
