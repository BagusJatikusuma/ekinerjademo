package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
public class UraianTugasEKinerjaWrapper {
    private String nipPegawai;
    private String namaPegawai;
    private JabatanWrapper jabatan;
    private UnitOrganisasiWrapper unitOrganisasi;
    private UnitKerjaWrapper unitKerja;
    private List<UraianTugasWrapper> uraianTugasWrapperList;

    public UraianTugasEKinerjaWrapper() {}
    public UraianTugasEKinerjaWrapper(
            String nipPegawai,
            String namaPegawai,
            JabatanWrapper jabatan,
            UnitOrganisasiWrapper unitOrganisasi,
            UnitKerjaWrapper unitKerja,
            List<UraianTugasWrapper> uraianTugasWrapperList) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.jabatan = jabatan;
        this.unitOrganisasi = unitOrganisasi;
        this.unitKerja = unitKerja;
        this.uraianTugasWrapperList = uraianTugasWrapperList;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public JabatanWrapper getJabatan() {
        return jabatan;
    }

    public void setJabatan(JabatanWrapper jabatan) {
        this.jabatan = jabatan;
    }

    public UnitOrganisasiWrapper getUnitOrganisasi() {
        return unitOrganisasi;
    }

    public void setUnitOrganisasi(UnitOrganisasiWrapper unitOrganisasi) {
        this.unitOrganisasi = unitOrganisasi;
    }

    public UnitKerjaWrapper getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(UnitKerjaWrapper unitKerja) {
        this.unitKerja = unitKerja;
    }

    public List<UraianTugasWrapper> getUraianTugasWrapperList() {
        return uraianTugasWrapperList;
    }

    public void setUraianTugasWrapperList(List<UraianTugasWrapper> uraianTugasWrapperList) {
        this.uraianTugasWrapperList = uraianTugasWrapperList;
    }
}
