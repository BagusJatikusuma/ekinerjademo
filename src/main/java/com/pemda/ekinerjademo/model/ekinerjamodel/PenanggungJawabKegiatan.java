package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
@Entity
@Table(name = "penanggung_jawab_kegiatan")
public class PenanggungJawabKegiatan {
    @EmbeddedId
    private PenanggungJawabKegiatanId penanggungJawabKegiatanId;

    @Column(name = "status_approval")
    private Integer statusApproval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "nip_pegawai",
            insertable = false,
            updatable = false,
            referencedColumnName = "nip_pegawai")
    private AkunPegawai akunPegawai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_status_penanggung_jawab",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_status")
    private StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan;

    @OneToMany(mappedBy = "penanggungJawabKegiatan")
    private List<UrtugKegiatan> urtugKegiatanList;

    public PenanggungJawabKegiatanId getPenanggungJawabKegiatanId() {
        return penanggungJawabKegiatanId;
    }

    public void setPenanggungJawabKegiatanId(PenanggungJawabKegiatanId penanggungJawabKegiatanId) {
        this.penanggungJawabKegiatanId = penanggungJawabKegiatanId;
    }

    public Integer getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(Integer statusApproval) {
        this.statusApproval = statusApproval;
    }

    public AkunPegawai getAkunPegawai() {
        return akunPegawai;
    }

    public void setAkunPegawai(AkunPegawai akunPegawai) {
        this.akunPegawai = akunPegawai;
    }

    public StatusPenanggungJawabKegiatan getStatusPenanggungJawabKegiatan() {
        return statusPenanggungJawabKegiatan;
    }

    public void setStatusPenanggungJawabKegiatan(StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan) {
        this.statusPenanggungJawabKegiatan = statusPenanggungJawabKegiatan;
    }

    public List<UrtugKegiatan> getUrtugKegiatanList() {
        return urtugKegiatanList;
    }

    public void setUrtugKegiatanList(List<UrtugKegiatan> urtugKegiatanList) {
        this.urtugKegiatanList = urtugKegiatanList;
    }
}
