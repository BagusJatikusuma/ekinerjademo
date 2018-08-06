package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ta_Kegiatan")
public class TaKegiatan {
    @EmbeddedId
    private TaKegiatanId taKegiatanId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "Kd_Urusan",
                    referencedColumnName = "Kd_Urusan",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Bidang",
                    referencedColumnName = "Kd_Bidang",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Unit",
                    referencedColumnName = "Kd_Unit",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Sub",
                    referencedColumnName = "Kd_Sub",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Tahun",
                    referencedColumnName = "Tahun",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Prog",
                    referencedColumnName = "Kd_Prog",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "ID_Prog",
                    referencedColumnName = "ID_Prog",
                    insertable = false,
                    updatable = false)
    })
    private TaProgram taProgram;
    @Column(name = "ket_Kegiatan")
    private String ketKegiatan;
    @Column(name = "Lokasi")
    private String lokasi;
    @Column(name = "Kelompok_Sasaran")
    private String kelompokSasaran;
    @Column(name = "Status_Kegiatan")
    private String statusKegiatan;
    @Column(name = "Pagu_Anggaran")
    private BigDecimal paguAnggaran;
    @Column(name = "Waktu_Pelaksanaan")
    private String waktuPelaksanaan;
    @Column(name = "Kd_Sumber")
    private Integer kdSumber;

    @OneToMany(mappedBy = "taKegiatan")
    private List<TaIndikator> taIndikatorList;

    public TaKegiatanId getTaKegiatanId() {
        return taKegiatanId;
    }

    public void setTaKegiatanId(TaKegiatanId taKegiatanId) {
        this.taKegiatanId = taKegiatanId;
    }

    public TaProgram getTaProgram() {
        return taProgram;
    }

    public void setTaProgram(TaProgram taProgram) {
        this.taProgram = taProgram;
    }

    public String getKetKegiatan() {
        return ketKegiatan;
    }

    public void setKetKegiatan(String ketKegiatan) {
        this.ketKegiatan = ketKegiatan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKelompokSasaran() {
        return kelompokSasaran;
    }

    public void setKelompokSasaran(String kelompokSasaran) {
        this.kelompokSasaran = kelompokSasaran;
    }

    public String getStatusKegiatan() {
        return statusKegiatan;
    }

    public void setStatusKegiatan(String statusKegiatan) {
        this.statusKegiatan = statusKegiatan;
    }

    public BigDecimal getPaguAnggaran() {
        return paguAnggaran;
    }

    public void setPaguAnggaran(BigDecimal paguAnggaran) {
        this.paguAnggaran = paguAnggaran;
    }

    public String getWaktuPelaksanaan() {
        return waktuPelaksanaan;
    }

    public void setWaktuPelaksanaan(String waktuPelaksanaan) {
        this.waktuPelaksanaan = waktuPelaksanaan;
    }

    public Integer getKdSumber() {
        return kdSumber;
    }

    public void setKdSumber(Integer kdSumber) {
        this.kdSumber = kdSumber;
    }

    public List<TaIndikator> getTaIndikatorList() {
        return taIndikatorList;
    }

    public void setTaIndikatorList(List<TaIndikator> taIndikatorList) {
        this.taIndikatorList = taIndikatorList;
    }
}
