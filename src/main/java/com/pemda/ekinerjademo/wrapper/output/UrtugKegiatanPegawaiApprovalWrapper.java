package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 02/11/17.
 */
public class UrtugKegiatanPegawaiApprovalWrapper {
    private String kdUrtug;
    private String deskripsiUrtug;
    private String kdJabatan;
    private String kdJenisUrtug;
    private Integer tahunUrtug;
    List<KegiatanApprovalWrapper> urtugKegiatanApprovalList;

    public UrtugKegiatanPegawaiApprovalWrapper() {
    }
    public UrtugKegiatanPegawaiApprovalWrapper(
            String kdUrtug,
            String deskripsiUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer tahunUrtug,
            List<KegiatanApprovalWrapper> urtugKegiatanApprovalList) {
        this.kdUrtug = kdUrtug;
        this.deskripsiUrtug = deskripsiUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.tahunUrtug = tahunUrtug;
        this.urtugKegiatanApprovalList = urtugKegiatanApprovalList;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getDeskripsiUrtug() {
        return deskripsiUrtug;
    }

    public void setDeskripsiUrtug(String deskripsiUrtug) {
        this.deskripsiUrtug = deskripsiUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public List<KegiatanApprovalWrapper> getUrtugKegiatanApprovalList() {
        return urtugKegiatanApprovalList;
    }

    public void setUrtugKegiatanApprovalList(List<KegiatanApprovalWrapper> urtugKegiatanApprovalList) {
        this.urtugKegiatanApprovalList = urtugKegiatanApprovalList;
    }
}
