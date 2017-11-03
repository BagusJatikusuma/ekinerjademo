package com.pemda.ekinerjademo.wrapper.output;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bagus on 02/11/17.
 */
public class KegiatanApprovalWrapper {
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
    private List<StatusApprovalPenanggungJawabWrapper> statusPenanggungJawabList;

    public KegiatanApprovalWrapper() {
    }
    public KegiatanApprovalWrapper(
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
            List<StatusApprovalPenanggungJawabWrapper> statusPenanggungJawabList) {
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
        this.statusPenanggungJawabList = statusPenanggungJawabList;
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

    public List<StatusApprovalPenanggungJawabWrapper> getStatusPenanggungJawabList() {
        return statusPenanggungJawabList;
    }

    public void setStatusPenanggungJawabList(List<StatusApprovalPenanggungJawabWrapper> statusPenanggungJawabList) {
        this.statusPenanggungJawabList = statusPenanggungJawabList;
    }
}
