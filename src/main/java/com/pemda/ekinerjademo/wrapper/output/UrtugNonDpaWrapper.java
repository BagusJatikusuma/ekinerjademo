package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 31/10/17.
 */
public class UrtugNonDpaWrapper {
    List<UraianTugasJabatanJenisUrtugWrapper> urtugTidakDipilihList;
    List<UraianTugasPegawaiTahunanWrapper> urtugDipilihList;

    public UrtugNonDpaWrapper() {
    }
    public UrtugNonDpaWrapper(
            List<UraianTugasJabatanJenisUrtugWrapper> urtugTidakDipilihList,
            List<UraianTugasPegawaiTahunanWrapper> urtugDipilihList) {
        this.urtugTidakDipilihList = urtugTidakDipilihList;
        this.urtugDipilihList = urtugDipilihList;
    }

    public List<UraianTugasJabatanJenisUrtugWrapper> getUrtugTidakDipilihList() {
        return urtugTidakDipilihList;
    }

    public void setUrtugTidakDipilihList(List<UraianTugasJabatanJenisUrtugWrapper> urtugTidakDipilihList) {
        this.urtugTidakDipilihList = urtugTidakDipilihList;
    }

    public List<UraianTugasPegawaiTahunanWrapper> getUrtugDipilihList() {
        return urtugDipilihList;
    }

    public void setUrtugDipilihList(List<UraianTugasPegawaiTahunanWrapper> urtugDipilihList) {
        this.urtugDipilihList = urtugDipilihList;
    }
}
