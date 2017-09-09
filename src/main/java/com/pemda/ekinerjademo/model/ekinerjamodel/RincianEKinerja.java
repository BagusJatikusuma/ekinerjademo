package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "rincian_e_kinerja")
public class RincianEKinerja {

    @EmbeddedId
    private RincianEKinerjaId rincianEKinerjaId;

    @ManyToOne
    @JoinColumn(name = "kd_urtug", insertable = false, updatable = false)
    private UraianTugas uraianTugas;

    @ManyToOne
    @JoinColumn(name = "nip_pegawai", insertable = false, updatable = false)
    private AkunPegawai akunPegawai;

    @Column(name = "satuan")
    private String satuan;

    @Column(name = "volume_kerja")
    private Integer volumeKerja;

    @Column(name = "norma_waktu")
    private Integer normaWaktu;

    @Column(name = "beban_kerja")
    private Integer bebanKerja;

    @Column(name = "peralatan")
    private String peralatan;

    @Column(name = "keterangan")
    private  String keterangan;

    public RincianEKinerja() {}

    public RincianEKinerjaId getRincianEKinerjaId() {
        return rincianEKinerjaId;
    }

    public void setRincianEKinerjaId(RincianEKinerjaId rincianEKinerjaId) {
        this.rincianEKinerjaId = rincianEKinerjaId;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getVolumeKerja() {
        return volumeKerja;
    }

    public void setVolumeKerja(Integer volumeKerja) {
        this.volumeKerja = volumeKerja;
    }

    public Integer getNormaWaktu() {
        return normaWaktu;
    }

    public void setNormaWaktu(Integer normaWaktu) {
        this.normaWaktu = normaWaktu;
    }

    public Integer getBebanKerja() {
        return bebanKerja;
    }

    public void setBebanKerja(Integer bebanKerja) {
        this.bebanKerja = bebanKerja;
    }

    public String getPeralatan() {
        return peralatan;
    }

    public void setPeralatan(String peralatan) {
        this.peralatan = peralatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public UraianTugas getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(UraianTugas uraianTugas) {
        this.uraianTugas = uraianTugas;
    }

    public AkunPegawai getAkunPegawai() {
        return akunPegawai;
    }

    public void setAkunPegawai(AkunPegawai akunPegawai) {
        this.akunPegawai = akunPegawai;
    }
}
