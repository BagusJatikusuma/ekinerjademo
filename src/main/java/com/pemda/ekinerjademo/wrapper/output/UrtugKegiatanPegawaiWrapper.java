package com.pemda.ekinerjademo.wrapper.output;

import java.math.BigDecimal;

/**
 * Created by bagus on 08/10/17.
 */
public class UrtugKegiatanPegawaiWrapper {
    private String kdUrtug;
    private String kdJabatan;
    private String kdJenisUrtug;
    private Integer kdUrusan;
    private Integer kdBidang;
    private Integer kdUnit;
    private Integer kdSub;
    private Integer tahun;
    private Integer kdProg;
    private Integer idProg;
    private Integer kdKeg;
    private String ketKeg;
    private BigDecimal paguAnggaran;
    private String nipPegawai;
    private String kdStatusPenanggungJawab;
    private String statusPenanggungJawab;

    public UrtugKegiatanPegawaiWrapper() {
    }
    public UrtugKegiatanPegawaiWrapper(
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKeg,
            String ketKeg,
            BigDecimal paguAnggaran,
            String nipPegawai,
            String kdStatusPenanggungJawab,
            String statusPenanggungJawab) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.kdUrusan = kdUrusan;
        this.kdBidang = kdBidang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKeg = kdKeg;
        this.ketKeg = ketKeg;
        this.paguAnggaran = paguAnggaran;
        this.nipPegawai = nipPegawai;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
        this.statusPenanggungJawab = statusPenanggungJawab;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBidang() {
        return kdBidang;
    }

    public void setKdBidang(Integer kdBidang) {
        this.kdBidang = kdBidang;
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

    public Integer getKdKeg() {
        return kdKeg;
    }

    public void setKdKeg(Integer kdKeg) {
        this.kdKeg = kdKeg;
    }

    public String getKetKeg() {
        return ketKeg;
    }

    public void setKetKeg(String ketKeg) {
        this.ketKeg = ketKeg;
    }

    public BigDecimal getPaguAnggaran() {
        return paguAnggaran;
    }

    public void setPaguAnggaran(BigDecimal paguAnggaran) {
        this.paguAnggaran = paguAnggaran;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdStatusPenanggungJawab() {
        return kdStatusPenanggungJawab;
    }

    public void setKdStatusPenanggungJawab(String kdStatusPenanggungJawab) {
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
    }

    public String getStatusPenanggungJawab() {
        return statusPenanggungJawab;
    }

    public void setStatusPenanggungJawab(String statusPenanggungJawab) {
        this.statusPenanggungJawab = statusPenanggungJawab;
    }
}
