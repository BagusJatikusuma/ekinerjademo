package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

@Entity
@Table(name = "anjab_uraian_tugas")
public class AnjabUraianTugas {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_instansi")
    private Integer idInstansi;
    @Column(name = "urutan")
    private Integer urutan;
    @Column(name = "uraian_tugas")
    private String uraianTugas;
    @Column(name = "bahan_kerja")
    private String bahanKerja;
    @Column(name = "perangkat_kerja")
    private String perangkatKerja;
    @Column(name = "hasil_kerja")
    private String hasilKerja;
    @Column(name = "satuan_hasil")
    private String satuanHasil;
    @Column(name = "prestasi_kerja")
    private String prestasiKerja;
    @Column(name = "jumlah_hasil")
    private String jumlahHasil;
    @Column(name = "waktu_penyelesaian")
    private String waktuPenyelesaian;
    @Column(name = "volume")
    private Integer volume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anjab_jabatan",
            insertable = false,
            updatable = false,
            referencedColumnName = "id")
    private AnjabJabatan anjabJabatan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdInstansi() {
        return idInstansi;
    }

    public void setIdInstansi(Integer idInstansi) {
        this.idInstansi = idInstansi;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public String getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(String uraianTugas) {
        this.uraianTugas = uraianTugas;
    }

    public String getBahanKerja() {
        return bahanKerja;
    }

    public void setBahanKerja(String bahanKerja) {
        this.bahanKerja = bahanKerja;
    }

    public String getPerangkatKerja() {
        return perangkatKerja;
    }

    public void setPerangkatKerja(String perangkatKerja) {
        this.perangkatKerja = perangkatKerja;
    }

    public String getHasilKerja() {
        return hasilKerja;
    }

    public void setHasilKerja(String hasilKerja) {
        this.hasilKerja = hasilKerja;
    }

    public String getSatuanHasil() {
        return satuanHasil;
    }

    public void setSatuanHasil(String satuanHasil) {
        this.satuanHasil = satuanHasil;
    }

    public String getPrestasiKerja() {
        return prestasiKerja;
    }

    public void setPrestasiKerja(String prestasiKerja) {
        this.prestasiKerja = prestasiKerja;
    }

    public String getJumlahHasil() {
        return jumlahHasil;
    }

    public void setJumlahHasil(String jumlahHasil) {
        this.jumlahHasil = jumlahHasil;
    }

    public String getWaktuPenyelesaian() {
        return waktuPenyelesaian;
    }

    public void setWaktuPenyelesaian(String waktuPenyelesaian) {
        this.waktuPenyelesaian = waktuPenyelesaian;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public AnjabJabatan getAnjabJabatan() {
        return anjabJabatan;
    }

    public void setAnjabJabatan(AnjabJabatan anjabJabatan) {
        this.anjabJabatan = anjabJabatan;
    }
}
