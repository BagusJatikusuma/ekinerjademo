package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

/**
 * Created by bagus on 08/02/18.
 */
public class PenanggungJawabWrapper {
    private Integer kdUrusan;
    private Integer kdBidang;
    private Integer kdUnit;
    private Integer kdSub;
    private Integer tahun;
    private Integer kdProg;
    private Integer idProg;
    private Integer kdKeg;
    private String keteranganKegiatan;
    private CustomPegawaiCredential penanggungJawab;
    private String kdStatusPenanggungJawab;
    private String statusPenanggungJawab;
    private Integer statusApproval;

    public PenanggungJawabWrapper() {
    }

    public PenanggungJawabWrapper(
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKeg,
            String keteranganKegiatan,
            CustomPegawaiCredential penanggungJawab,
            String kdStatusPenanggungJawab,
            String statusPenanggungJawab,
            Integer statusApproval) {
        this.kdUrusan = kdUrusan;
        this.kdBidang = kdBidang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKeg = kdKeg;
        this.keteranganKegiatan = keteranganKegiatan;
        this.penanggungJawab = penanggungJawab;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
        this.statusPenanggungJawab = statusPenanggungJawab;
        this.statusApproval = statusApproval;
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

    public String getKeteranganKegiatan() {
        return keteranganKegiatan;
    }

    public void setKeteranganKegiatan(String keteranganKegiatan) {
        this.keteranganKegiatan = keteranganKegiatan;
    }

    public CustomPegawaiCredential getPenanggungJawab() {
        return penanggungJawab;
    }

    public void setPenanggungJawab(CustomPegawaiCredential penanggungJawab) {
        this.penanggungJawab = penanggungJawab;
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

    public Integer getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(Integer statusApproval) {
        this.statusApproval = statusApproval;
    }
}
