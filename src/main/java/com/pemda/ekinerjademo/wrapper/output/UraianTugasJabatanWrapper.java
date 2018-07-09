package com.pemda.ekinerjademo.wrapper.output;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
public class UraianTugasJabatanWrapper {
    private String kdJabatan;
    private String jabatan;
    //list uraiantugas yang dimiliki jabatan
    private List<UraianTugasJenisWrapper> jabatanUraianTugasList;
    //list uraiantugas yang tidak dimiliki jabatan
    private List<UraianTugasWrapper> notJabatanUraianTugasList;

    public UraianTugasJabatanWrapper() {}
    public UraianTugasJabatanWrapper(
            String kdJabatan,
            String jabatan,
            List<UraianTugasJenisWrapper> jabatanUraianTugasList,
            List<UraianTugasWrapper> notJabatanUraianTugasList) {
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
        this.jabatanUraianTugasList = jabatanUraianTugasList;
        this.notJabatanUraianTugasList = notJabatanUraianTugasList;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public List<UraianTugasJenisWrapper> getJabatanUraianTugasList() {
        return jabatanUraianTugasList;
    }

    public void setJabatanUraianTugasList(List<UraianTugasJenisWrapper> jabatanUraianTugasList) {
        this.jabatanUraianTugasList = jabatanUraianTugasList;
    }

    public List<UraianTugasWrapper> getNotJabatanUraianTugasList() {
        return notJabatanUraianTugasList;
    }

    public void setNotJabatanUraianTugasList(List<UraianTugasWrapper> notJabatanUraianTugasList) {
        this.notJabatanUraianTugasList = notJabatanUraianTugasList;
    }
}
