package com.pemda.ekinerjademo.wrapper.output;

import java.math.BigDecimal;

/**
 * Created by bagus on 11/12/17.
 */
public class TaProgramWrapper {
    private Integer kdUrusan;
    private Integer kdBIdang;
    private Integer kdUnit;
    private Integer kdSub;
    private Integer tahun;
    private Integer kdProg;
    private Integer idProg;
    private String ketProgram;
    private String tolakUkur;
    private BigDecimal targetAngka;
    private String targetUraian;
    private String kdUrusan1;
    private String kdBidang1;

    public TaProgramWrapper() {
    }
    public TaProgramWrapper(
            Integer kdUrusan,
            Integer kdBIdang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            String ketProgram,
            String tolakUkur,
            BigDecimal targetAngka,
            String targetUraian,
            String kdUrusan1,
            String kdBidang1) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.ketProgram = ketProgram;
        this.tolakUkur = tolakUkur;
        this.targetAngka = targetAngka;
        this.targetUraian = targetUraian;
        this.kdUrusan1 = kdUrusan1;
        this.kdBidang1 = kdBidang1;
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

    public String getKetProgram() {
        return ketProgram;
    }

    public void setKetProgram(String ketProgram) {
        this.ketProgram = ketProgram;
    }

    public String getTolakUkur() {
        return tolakUkur;
    }

    public void setTolakUkur(String tolakUkur) {
        this.tolakUkur = tolakUkur;
    }

    public BigDecimal getTargetAngka() {
        return targetAngka;
    }

    public void setTargetAngka(BigDecimal targetAngka) {
        this.targetAngka = targetAngka;
    }

    public String getTargetUraian() {
        return targetUraian;
    }

    public void setTargetUraian(String targetUraian) {
        this.targetUraian = targetUraian;
    }

    public String getKdUrusan1() {
        return kdUrusan1;
    }

    public void setKdUrusan1(String kdUrusan1) {
        this.kdUrusan1 = kdUrusan1;
    }

    public String getKdBidang1() {
        return kdBidang1;
    }

    public void setKdBidang1(String kdBidang1) {
        this.kdBidang1 = kdBidang1;
    }

}
