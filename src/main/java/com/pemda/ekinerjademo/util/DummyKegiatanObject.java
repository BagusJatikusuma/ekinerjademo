package com.pemda.ekinerjademo.util;

public class DummyKegiatanObject {
    private Integer kdProg;
    private Integer idProg;
    private Integer kdKeg;

    public DummyKegiatanObject() {
    }

    public DummyKegiatanObject(Integer kdProg, Integer idProg, Integer kdKeg) {
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKeg = kdKeg;
    }

    public Integer getKdProg() {
        return kdProg;
    }

    public void setKdProg(Integer kdProg) {
        this.kdProg = kdProg;
    }

    public Integer getIdProg() {
        return idProg;
    }

    public void setIdProg(Integer idProg) {
        this.idProg = idProg;
    }

    public Integer getKdKeg() {
        return kdKeg;
    }

    public void setKdKeg(Integer kdKeg) {
        this.kdKeg = kdKeg;
    }
}
