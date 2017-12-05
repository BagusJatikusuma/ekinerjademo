package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 15/11/17.
 */
public class SuratPerintahInputWrapper {
    private String kdSuratPerintah;

    private String nipPembuat;

    private String nipPenandatangan;

    private Integer nomorSurat1;

    private String nomorSurat2;

    private String nomorSurat3;

    private Integer nomorTahun;

    private List<String> menimbangList;

    private List<String> dasarList;

    private List<String> untukList;

    private String tempat;

    private Long tanggalPerintahMilis;

    private String ttdPath;

    private Set<String> kdTargetPegawaiList;

    private Set<String> kdTargetPejabatList;

    private Set<String> kdTembusanList;

    private boolean suratPejabat;

    private String kdJabatanSuratPejabat;

    private String kdUnitKerja;

    private String kdSuratPerintahBawahan;

    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public Integer getNomorSurat1() {
        return nomorSurat1;
    }

    public void setNomorSurat1(Integer nomorSurat1) {
        this.nomorSurat1 = nomorSurat1;
    }

    public String getNomorSurat2() {
        return nomorSurat2;
    }

    public void setNomorSurat2(String nomorSurat2) {
        this.nomorSurat2 = nomorSurat2;
    }

    public String getNomorSurat3() {
        return nomorSurat3;
    }

    public void setNomorSurat3(String nomorSurat3) {
        this.nomorSurat3 = nomorSurat3;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public List<String> getDasarList() {
        return dasarList;
    }

    public void setDasarList(List<String> dasarList) {
        this.dasarList = dasarList;
    }

    public List<String> getUntukList() {
        return untukList;
    }

    public void setUntukList(List<String> untukList) {
        this.untukList = untukList;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Long getTanggalPerintahMilis() {
        return tanggalPerintahMilis;
    }

    public void setTanggalPerintahMilis(Long tanggalPerintahMilis) {
        this.tanggalPerintahMilis = tanggalPerintahMilis;
    }

    public String getTtdPath() {
        return ttdPath;
    }

    public void setTtdPath(String ttdPath) {
        this.ttdPath = ttdPath;
    }

    public Set<String> getKdTargetPegawaiList() {
        return kdTargetPegawaiList;
    }

    public void setKdTargetPegawaiList(Set<String> kdTargetPegawaiList) {
        this.kdTargetPegawaiList = kdTargetPegawaiList;
    }

    public Set<String> getKdTembusanList() {
        return kdTembusanList;
    }

    public void setKdTembusanList(Set<String> kdTembusanList) {
        this.kdTembusanList = kdTembusanList;
    }

    public List<String> getMenimbangList() {
        return menimbangList;
    }

    public void setMenimbangList(List<String> menimbangList) {
        this.menimbangList = menimbangList;
    }

    public Set<String> getKdTargetPejabatList() {
        return kdTargetPejabatList;
    }

    public void setKdTargetPejabatList(Set<String> kdTargetPejabatList) {
        this.kdTargetPejabatList = kdTargetPejabatList;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public boolean isSuratPejabat() {
        return suratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        this.suratPejabat = suratPejabat;
    }

    public String getKdJabatanSuratPejabat() {
        return kdJabatanSuratPejabat;
    }

    public void setKdJabatanSuratPejabat(String kdJabatanSuratPejabat) {
        this.kdJabatanSuratPejabat = kdJabatanSuratPejabat;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKdSuratPerintahBawahan() {
        return kdSuratPerintahBawahan;
    }

    public void setKdSuratPerintahBawahan(String kdSuratPerintahBawahan) {
        this.kdSuratPerintahBawahan = kdSuratPerintahBawahan;
    }
}
