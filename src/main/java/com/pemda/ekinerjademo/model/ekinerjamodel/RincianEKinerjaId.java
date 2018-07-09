package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
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

    @Column(name = "tgl_submit")
    @Temporal(TemporalType.DATE)
    private Date tglSubmit;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    public RincianEKinerjaId() {}
    public RincianEKinerjaId(String nipPegawai, String kdUrtug) {
        this.nipPegawai = nipPegawai;
        this.kdUrtug = kdUrtug;
        this.tglSubmit = new Date();
    }
    public RincianEKinerjaId(String nipPegawai, String kdUrtug, Date tglSubmit) {
        this.nipPegawai = nipPegawai;
        this.kdUrtug = kdUrtug;
        this.tglSubmit = tglSubmit;
    }
    public RincianEKinerjaId(String nipPegawai, String kdUrtug, Date tglSubmit, String kdJenisUrtug) {
        this.nipPegawai = nipPegawai;
        this.kdUrtug = kdUrtug;
        this.tglSubmit = tglSubmit;
        this.kdJenisUrtug = kdJenisUrtug;
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

    public Date getTglSubmit() {
        return tglSubmit;
    }

    public void setTglSubmit(Date tglSubmit) {
        this.tglSubmit = tglSubmit;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof RincianEKinerjaId)) return false;
//        RincianEKinerjaId that = (RincianEKinerjaId) o;
//        return Objects.equals(getNipPegawai(), that.getNipPegawai()) &&
//                Objects.equals(getKdUrtug(), that.getKdUrtug()) &&
//                Objects.equals(getTglSubmit(), that.getTglSubmit());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getNipPegawai(), getKdUrtug(), getTglSubmit());
//    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RincianEKinerjaId that = (RincianEKinerjaId) o;

        if (!nipPegawai.equals(that.nipPegawai)) return false;
        if (!kdUrtug.equals(that.kdUrtug)) return false;
        if (!tglSubmit.equals(that.tglSubmit)) return false;
        return kdJenisUrtug.equals(that.kdJenisUrtug);
    }

    @Override
    public int hashCode() {
        int result = nipPegawai.hashCode();
        result = 31 * result + kdUrtug.hashCode();
        result = 31 * result + tglSubmit.hashCode();
        result = 31 * result + kdJenisUrtug.hashCode();
        return result;
    }
}
