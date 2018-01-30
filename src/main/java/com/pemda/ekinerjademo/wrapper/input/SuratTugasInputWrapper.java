package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 30/01/18.
 */
public class SuratTugasInputWrapper {
    private String nipPembuat;
    private String nipPenandatangan;

    private String nomorUrusan;
    private String nomorUnit;
    private String nomorPasanganUrut;

    private List<String> menimbang;
    private List<String> dasar;
    private List<String> untuk;
    private String tempat;

    private Integer durasiPengerjaan;

    private String kdUnitKerja;

    private Set<String> targetSuratTugasPegawaiSet;
    private Set<String> targetSuratTugasPejabatSet;
    private Set<String> tembusanSuratTugasSet;

    private boolean isSuratPejabat;
    private String kdJabatanSuratPejabat;

    private String kdSuratTugasBawahan;

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
    }

    public String getNomorUnit() {
        return nomorUnit;
    }

    public void setNomorUnit(String nomorUnit) {
        this.nomorUnit = nomorUnit;
    }

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public List<String> getMenimbang() {
        return menimbang;
    }

    public void setMenimbang(List<String> menimbang) {
        this.menimbang = menimbang;
    }

    public List<String> getDasar() {
        return dasar;
    }

    public void setDasar(List<String> dasar) {
        this.dasar = dasar;
    }

    public List<String> getUntuk() {
        return untuk;
    }

    public void setUntuk(List<String> untuk) {
        this.untuk = untuk;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Set<String> getTargetSuratTugasPegawaiSet() {
        return targetSuratTugasPegawaiSet;
    }

    public void setTargetSuratTugasPegawaiSet(Set<String> targetSuratTugasPegawaiSet) {
        this.targetSuratTugasPegawaiSet = targetSuratTugasPegawaiSet;
    }

    public Set<String> getTargetSuratTugasPejabatSet() {
        return targetSuratTugasPejabatSet;
    }

    public void setTargetSuratTugasPejabatSet(Set<String> targetSuratTugasPejabatSet) {
        this.targetSuratTugasPejabatSet = targetSuratTugasPejabatSet;
    }

    public Set<String> getTembusanSuratTugasSet() {
        return tembusanSuratTugasSet;
    }

    public void setTembusanSuratTugasSet(Set<String> tembusanSuratTugasSet) {
        this.tembusanSuratTugasSet = tembusanSuratTugasSet;
    }

    public boolean isSuratPejabat() {
        return isSuratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        isSuratPejabat = suratPejabat;
    }

    public String getKdJabatanSuratPejabat() {
        return kdJabatanSuratPejabat;
    }

    public void setKdJabatanSuratPejabat(String kdJabatanSuratPejabat) {
        this.kdJabatanSuratPejabat = kdJabatanSuratPejabat;
    }

    public String getKdSuratTugasBawahan() {
        return kdSuratTugasBawahan;
    }

    public void setKdSuratTugasBawahan(String kdSuratTugasBawahan) {
        this.kdSuratTugasBawahan = kdSuratTugasBawahan;
    }
}
