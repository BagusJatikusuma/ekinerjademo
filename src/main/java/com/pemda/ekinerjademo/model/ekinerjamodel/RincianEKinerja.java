package com.pemda.ekinerjademo.model.ekinerjamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "rincian_e_kinerja")
public class RincianEKinerja {

    @EmbeddedId
    private RincianEKinerjaId rincianEKinerjaId;

    @Column(name = "capaian_menit")
    private Integer capaianMenit;

    @ManyToOne
    @JoinColumn(name = "kd_urtug", insertable = false, updatable = false)
    private UraianTugas uraianTugas;

    @ManyToOne
    @JoinColumn(name = "nip_pegawai", insertable = false, updatable = false)
    private AkunPegawai akunPegawai;

    public RincianEKinerja() {}

    public RincianEKinerjaId getRincianEKinerjaId() {
        return rincianEKinerjaId;
    }

    public void setRincianEKinerjaId(RincianEKinerjaId rincianEKinerjaId) {
        this.rincianEKinerjaId = rincianEKinerjaId;
    }

    @JsonIgnore
    public UraianTugas getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(UraianTugas uraianTugas) {
        this.uraianTugas = uraianTugas;
    }

    @JsonIgnore
    public AkunPegawai getAkunPegawai() {
        return akunPegawai;
    }

    public void setAkunPegawai(AkunPegawai akunPegawai) {
        this.akunPegawai = akunPegawai;
    }

    public Integer getCapaianMenit() {
        return capaianMenit;
    }

    public void setCapaianMenit(Integer capaianMenit) {
        this.capaianMenit = capaianMenit;
    }
}
