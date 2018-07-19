package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RekapitulasiPerilakuKerjaPegawaiId implements Serializable {
    @Column(name = "bulan_tahun_rekapitulasi")
    private long bulanTahunRekapulasi;

    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public RekapitulasiPerilakuKerjaPegawaiId() {
    }

    public RekapitulasiPerilakuKerjaPegawaiId(long bulanTahunRekapulasi, String nipPegawai) {
        this.bulanTahunRekapulasi = bulanTahunRekapulasi;
        this.nipPegawai = nipPegawai;
    }

    public long getBulanTahunRekapulasi() {
        return bulanTahunRekapulasi;
    }

    public void setBulanTahunRekapulasi(long bulanTahunRekapulasi) {
        this.bulanTahunRekapulasi = bulanTahunRekapulasi;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RekapitulasiPerilakuKerjaPegawaiId that = (RekapitulasiPerilakuKerjaPegawaiId) o;
        return bulanTahunRekapulasi == that.bulanTahunRekapulasi &&
                Objects.equals(nipPegawai, that.nipPegawai);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bulanTahunRekapulasi, nipPegawai);
    }
}
