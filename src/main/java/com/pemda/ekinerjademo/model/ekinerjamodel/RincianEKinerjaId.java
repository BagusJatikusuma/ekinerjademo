package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by bagus on 08/09/17.
 */
@Embeddable
public class RincianEKinerjaId implements Serializable {
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    @Column(name = "kd_urtug")
    private String kdUrtug;

    public RincianEKinerjaId() {}
    public RincianEKinerjaId(String nipPegawai, String kdUrtug) {
        this.nipPegawai = nipPegawai;
        this.kdUrtug = kdUrtug;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RincianEKinerjaId)) return false;
        RincianEKinerjaId that = (RincianEKinerjaId) o;
        return Objects.equals(getNipPegawai(), that.getNipPegawai()) &&
                Objects.equals(getKdUrtug(), that.getKdUrtug());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNipPegawai(), getKdUrtug());
    }

}
