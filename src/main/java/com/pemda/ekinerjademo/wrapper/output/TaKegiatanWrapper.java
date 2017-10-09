package com.pemda.ekinerjademo.wrapper.output;

import java.math.BigDecimal;

/**
 * Created by bagus on 08/10/17.
 */
public class TaKegiatanWrapper {
    private Integer kdUrusan;
    private Integer kdBIdang;
    private Integer kdUnit;
    private Integer kdSub;
    private Integer tahun;
    private Integer kdProg;
    private Integer idProg;
    private Integer kdKegiatan;
    private String ketKegiatan;
    private String lokasi;
    private String kelompokSasaran;
    private String statusKegiatan;
    private BigDecimal paguAnggaran;
    private String waktuPelaksanaan;
    private Integer kdSumber;

    public TaKegiatanWrapper() {
    }
    public TaKegiatanWrapper(
            Integer kdUrusan,
            Integer kdBIdang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKegiatan,
            String ketKegiatan,
            String lokasi,
            String kelompokSasaran,
            String statusKegiatan,
            BigDecimal paguAnggaran,
            String waktuPelaksanaan,
            Integer kdSumber) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKegiatan = kdKegiatan;
        this.ketKegiatan = ketKegiatan;
        this.lokasi = lokasi;
        this.kelompokSasaran = kelompokSasaran;
        this.statusKegiatan = statusKegiatan;
        this.paguAnggaran = paguAnggaran;
        this.waktuPelaksanaan = waktuPelaksanaan;
        this.kdSumber = kdSumber;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBIdang() {
        return kdBIdang;
    }

    public void setKdBIdang(Integer kdBIdang) {
        this.kdBIdang = kdBIdang;
    }

    public Integer getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(Integer kdUnit) {
        this.kdUnit = kdUnit;
    }

    public Integer getKdSub() {
        return kdSub;
    }

    public void setKdSub(Integer kdSub) {
        this.kdSub = kdSub;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getKdProg() {
        return kdProg;
    }

    public void setKdProg(Integer kdProg) {
        this.kdProg = kdProg;
    }

    public Integer getIdProg() {
        return idProg;
    }

    public void setIdProg(Integer idProg) {
        this.idProg = idProg;
    }

    public Integer getKdKegiatan() {
        return kdKegiatan;
    }

    public void setKdKegiatan(Integer kdKegiatan) {
        this.kdKegiatan = kdKegiatan;
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
}
