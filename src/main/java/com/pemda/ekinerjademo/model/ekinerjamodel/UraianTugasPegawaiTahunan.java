package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/09/17.
 */
@Entity
@Table(name = "uraian_tugas_pegawai_tahunan")
public class UraianTugasPegawaiTahunan {
    @EmbeddedId
    private UraianTugasPegawaiTahunanId uraianTugasPegawaiTahunanId;

    @ManyToOne
    @JoinColumn(name = "nip_pegawai", insertable = false, updatable = false)
    private AkunPegawai akunPegawai;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "kd_jenis_urtug",
                    referencedColumnName = "kd_jenis_urtug",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "kd_urtug",
                    referencedColumnName = "kd_urtug",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "kd_jabatan",
                    referencedColumnName = "kd_jabatan",
                    insertable = false,
                    updatable = false)
    })
    private JenisUrtugUrtug jenisUrtugUrtug;

    public UraianTugasPegawaiTahunanId getUraianTugasPegawaiTahunanId() {
        return uraianTugasPegawaiTahunanId;
    }

    public void setUraianTugasPegawaiTahunanId(UraianTugasPegawaiTahunanId uraianTugasPegawaiTahunanId) {
        this.uraianTugasPegawaiTahunanId = uraianTugasPegawaiTahunanId;
    }

    public AkunPegawai getAkunPegawai() {
        return akunPegawai;
    }

    public void setAkunPegawai(AkunPegawai akunPegawai) {
        this.akunPegawai = akunPegawai;
    }

    public JenisUrtugUrtug getJenisUrtugUrtug() {
        return jenisUrtugUrtug;
    }

    public void setJenisUrtugUrtug(JenisUrtugUrtug jenisUrtugUrtug) {
        this.jenisUrtugUrtug = jenisUrtugUrtug;
    }

}
