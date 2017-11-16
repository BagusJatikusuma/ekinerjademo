package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 15/11/17.
 */
public class SuratPerintahNonPejabatDokumenWrapper {
    private String kdSuratPerintah;

    private String nipPembuat;

    private String nmPembuat;

    private String kdUnitKerjaPembuat;

    private String unitKerjaPembuat;

    private String nomorSurat;

    private List<String> menimbangList;

    private List<String> dasarList;

    private List<String> untukList;

    private String tempat;

    private String tanggalDibuat;

    private String nmJabatanPembuat;

    private String ttdPath;

    private List<CustomPegawaiCredential> daftarTarget;

    private List<TkdJabatan> daftarTembusan;

    public SuratPerintahNonPejabatDokumenWrapper() {
    }
    public SuratPerintahNonPejabatDokumenWrapper(
            String kdSuratPerintah,
            String nipPembuat,
            String nmPembuat,
            String kdUnitKerjaPembuat,
            String unitKerjaPembuat,
            String nomorSurat,
            List<String> menimbangList,
            List<String> dasarList,
            List<String> untukList,
            String tempat,
            String tanggalDibuat,
            String nmJabatanPembuat,
            String ttdPath,
            List<CustomPegawaiCredential> daftarTarget,
            List<TkdJabatan> daftarTembusan) {
        this.kdSuratPerintah = kdSuratPerintah;
        this.nipPembuat = nipPembuat;
        this.nmPembuat = nmPembuat;
        this.kdUnitKerjaPembuat = kdUnitKerjaPembuat;
        this.unitKerjaPembuat = unitKerjaPembuat;
        this.nomorSurat = nomorSurat;
        this.menimbangList = menimbangList;
        this.dasarList = dasarList;
        this.untukList = untukList;
        this.tempat = tempat;
        this.tanggalDibuat = tanggalDibuat;
        this.nmJabatanPembuat = nmJabatanPembuat;
        this.ttdPath = ttdPath;
        this.daftarTarget = daftarTarget;
        this.daftarTembusan = daftarTembusan;
    }

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

    public String getNmPembuat() {
        return nmPembuat;
    }

    public void setNmPembuat(String nmPembuat) {
        this.nmPembuat = nmPembuat;
    }

    public String getKdUnitKerjaPembuat() {
        return kdUnitKerjaPembuat;
    }

    public void setKdUnitKerjaPembuat(String kdUnitKerjaPembuat) {
        this.kdUnitKerjaPembuat = kdUnitKerjaPembuat;
    }

    public String getUnitKerjaPembuat() {
        return unitKerjaPembuat;
    }

    public void setUnitKerjaPembuat(String unitKerjaPembuat) {
        this.unitKerjaPembuat = unitKerjaPembuat;
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

    public String getNmJabatanPembuat() {
        return nmJabatanPembuat;
    }

    public void setNmJabatanPembuat(String nmJabatanPembuat) {
        this.nmJabatanPembuat = nmJabatanPembuat;
    }

    public String getTtdPath() {
        return ttdPath;
    }

    public void setTtdPath(String ttdPath) {
        this.ttdPath = ttdPath;
    }

    public List<CustomPegawaiCredential> getDaftarTarget() {
        return daftarTarget;
    }

    public void setDaftarTarget(List<CustomPegawaiCredential> daftarTarget) {
        this.daftarTarget = daftarTarget;
    }

    public List<TkdJabatan> getDaftarTembusan() {
        return daftarTembusan;
    }

    public void setDaftarTembusan(List<TkdJabatan> daftarTembusan) {
        this.daftarTembusan = daftarTembusan;
    }
}
