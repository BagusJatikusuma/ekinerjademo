package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tugas_tambahan")
public class TugasTambahan {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nip_pegawai")
    private String nipPegawai;

    @Column(name = "bulan")
    private Short bulan;

    @Column(name = "tahun")
    private Short tahun;

    @Column(name = "deskripsi")
    private String deskripsi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public Short getBulan() {
        return bulan;
    }

    public void setBulan(Short bulan) {
        this.bulan = bulan;
    }

    public Short getTahun() {
        return tahun;
    }

    public void setTahun(Short tahun) {
        this.tahun = tahun;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
