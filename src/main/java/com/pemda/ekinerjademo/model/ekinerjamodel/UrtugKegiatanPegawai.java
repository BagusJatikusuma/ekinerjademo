package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 05/10/17.
 */
@Entity
@Table(name = "urtug_kegiatan_pegawai")
public class UrtugKegiatanPegawai {
    @EmbeddedId
    private UrtugKegiatanPegawaiId urtugKegiatanPegawaiId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "kd_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_urtug"),
            @JoinColumn(
                    name = "kd_jabatan",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_jabatan"),
            @JoinColumn(
                    name = "kd_jenis_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_jenis_urtug"),
            @JoinColumn(
                    name = "kd_urusan",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_urusan"),
            @JoinColumn(
                    name = "kd_bidang",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_bidang"),
            @JoinColumn(
                    name = "kd_unit",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_unit"),
            @JoinColumn(
                    name = "kd_sub",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_sub"),
            @JoinColumn(
                    name = "tahun",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "tahun"),
            @JoinColumn(
                    name = "kd_prog",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_prog"),
            @JoinColumn(
                    name = "id_prog",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "id_prog"),
            @JoinColumn(
                    name = "kd_keg",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_keg")
    })
    private UrtugKegiatan urtugKegiatan;

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

    public UrtugKegiatanPegawaiId getUrtugKegiatanPegawaiId() {
        return urtugKegiatanPegawaiId;
    }

    public void setUrtugKegiatanPegawaiId(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId) {
        this.urtugKegiatanPegawaiId = urtugKegiatanPegawaiId;
    }

    public UrtugKegiatan getUrtugKegiatan() {
        return urtugKegiatan;
    }

    public void setUrtugKegiatan(UrtugKegiatan urtugKegiatan) {
        this.urtugKegiatan = urtugKegiatan;
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
}
