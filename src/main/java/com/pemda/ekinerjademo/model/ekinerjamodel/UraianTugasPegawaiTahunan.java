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

    @Column(name = "tahun_uraian_tugas")
    private Long tahunUraianTugas;

    @Column(name = "status_pengerjaan")
    private Integer statusPengerjaan;

    @ManyToOne
    @JoinColumn(
            name = "nip_pegawai",
            insertable = false,
            updatable = false,
            referencedColumnName = "nip_pegawai")
    private AkunPegawai akunPegawai;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(
//                    name = "kd_jenis_urtug",
//                    referencedColumnName = "kd_jenis_urtug",
//                    insertable = false,
//                    updatable = false),
//            @JoinColumn(
//                    name = "kd_urtug",
//                    referencedColumnName = "kd_urtug",
//                    insertable = false,
//                    updatable = false),
//            @JoinColumn(
//                    name = "kd_jabatan",
//                    referencedColumnName = "kd_jabatan",
//                    insertable = false,
//                    updatable = false)
//    })
//    private JenisUrtugUrtug jenisUrtugUrtug;

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

    public Long getTahunUraianTugas() {
        return tahunUraianTugas;
    }

    public void setTahunUraianTugas(Long tahunUraianTugas) {
        this.tahunUraianTugas = tahunUraianTugas;
    }

    public Integer getStatusPengerjaan() {
        return statusPengerjaan;
    }

    public void setStatusPengerjaan(Integer statusPengerjaan) {
        this.statusPengerjaan = statusPengerjaan;
    }
}
