package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 15/11/17.
 */
public class SuratPerintahNonPejabatDokumenWrapper {
    private String kdSuratPerintah;

    private String nipPenandatangan;

    private String nmPenandatangan;

    private String nomorSurat;

    private List<String> menimbangList;

    private List<String> dasarList;

    private List<String> untukList;

    private String tempat;

    private String tanggalDibuat;

    private String nmJabatanPenandatangan;

    private String ttdPath;

    private List<CustomPegawaiCredential> daftarTargetPegawai;

    private List<TkdJabatan> daftarTargetPejabat;

    private List<TkdJabatan> daftarTembusan;

    private boolean isSuratPejabat;

    private String kdUnitKerjaPenandatangan;

    private String unitKerjaPenandatangan;

    private String kdJabatanPenandatangan;

    private String jabatanPenandatangan;

    public SuratPerintahNonPejabatDokumenWrapper() {
    }
    public SuratPerintahNonPejabatDokumenWrapper(
            String kdSuratPerintah,
            String nipPenandatangan,
            String nmPenandatangan,
            String nomorSurat,
            List<String> menimbangList,
            List<String> dasarList,
            List<String> untukList,
            String tempat,
            String tanggalDibuat,
            String nmJabatanPenandatangan,
            String ttdPath,
            List<CustomPegawaiCredential> daftarTargetPegawai,
            List<TkdJabatan> daftarTargetPejabat,
            List<TkdJabatan> daftarTembusan,
            boolean isSuratPejabat,
            String kdUnitKerjaPenandatangan,
            String unitKerjaPenandatangan,
            String kdJabatanPenandatangan,
            String jabatanPenandatangan) {
        this.kdSuratPerintah = kdSuratPerintah;
        this.nipPenandatangan = nipPenandatangan;
        this.nmPenandatangan = nmPenandatangan;
        this.nomorSurat = nomorSurat;
        this.menimbangList = menimbangList;
        this.dasarList = dasarList;
        this.untukList = untukList;
        this.tempat = tempat;
        this.tanggalDibuat = tanggalDibuat;
        this.nmJabatanPenandatangan = nmJabatanPenandatangan;
        this.ttdPath = ttdPath;
        this.daftarTargetPegawai = daftarTargetPegawai;
        this.daftarTargetPejabat = daftarTargetPejabat;
        this.daftarTembusan = daftarTembusan;
        this.isSuratPejabat = isSuratPejabat;
        this.kdUnitKerjaPenandatangan = kdUnitKerjaPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.kdJabatanPenandatangan = kdJabatanPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNmPenandatangan() {
        return nmPenandatangan;
    }

    public void setNmPenandatangan(String nmPenandatangan) {
        this.nmPenandatangan = nmPenandatangan;
    }

    public String getKdUnitKerjaPenandatangan() {
        return kdUnitKerjaPenandatangan;
    }

    public void setKdUnitKerjaPenandatangan(String kdUnitKerjaPenandatangan) {
        this.kdUnitKerjaPenandatangan = kdUnitKerjaPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public List<String> getMenimbangList() {
        return menimbangList;
    }

    public void setMenimbangList(List<String> menimbangList) {
        this.menimbangList = menimbangList;
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

    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getNmJabatanPenandatangan() {
        return nmJabatanPenandatangan;
    }

    public void setNmJabatanPenandatangan(String nmJabatanPenandatangan) {
        this.nmJabatanPenandatangan = nmJabatanPenandatangan;
    }

    public String getTtdPath() {
        return ttdPath;
    }

    public void setTtdPath(String ttdPath) {
        this.ttdPath = ttdPath;
    }

    public List<CustomPegawaiCredential> getDaftarTargetPegawai() {
        return daftarTargetPegawai;
    }

    public void setDaftarTargetPegawai(List<CustomPegawaiCredential> daftarTargetPegawai) {
        this.daftarTargetPegawai = daftarTargetPegawai;
    }

    public List<TkdJabatan> getDaftarTembusan() {
        return daftarTembusan;
    }

    public void setDaftarTembusan(List<TkdJabatan> daftarTembusan) {
        this.daftarTembusan = daftarTembusan;
    }

    public List<TkdJabatan> getDaftarTargetPejabat() {
        return daftarTargetPejabat;
    }

    public void setDaftarTargetPejabat(List<TkdJabatan> daftarTargetPejabat) {
        this.daftarTargetPejabat = daftarTargetPejabat;
    }

    public String getKdJabatanPenandatangan() {
        return kdJabatanPenandatangan;
    }

    public void setKdJabatanPenandatangan(String kdJabatanPenandatangan) {
        this.kdJabatanPenandatangan = kdJabatanPenandatangan;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public boolean isSuratPejabat() {
        return isSuratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        isSuratPejabat = suratPejabat;
    }
}
