package com.pemda.ekinerjademo.wrapper.input;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratDisposisi;

import java.util.List;

/**
 * Created by bagus on 20/11/17.
 */
public class LembarDisposisiInputWrapper {
    private String nipPembuat;
    private String kdUnitKerja;
    private Long tanggalPenerimaanMilis;
    private Integer tktKeamanan;
    private Long tglPenyelesaianMilis;
    private String kdLembarDisposisiParent;
    private String noSuratDisposisi;
    private List<String> daftarTargetLembarDisposisi;

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Long getTanggalPenerimaanMilis() {
        return tanggalPenerimaanMilis;
    }

    public void setTanggalPenerimaanMilis(Long tanggalPenerimaanMilis) {
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
    }

    public Integer getTktKeamanan() {
        return tktKeamanan;
    }

    public void setTktKeamanan(Integer tktKeamanan) {
        this.tktKeamanan = tktKeamanan;
    }

    public Long getTglPenyelesaianMilis() {
        return tglPenyelesaianMilis;
    }

    public void setTglPenyelesaianMilis(Long tglPenyelesaianMilis) {
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
    }

    public String getKdLembarDisposisiParent() {
        return kdLembarDisposisiParent;
    }

    public void setKdLembarDisposisiParent(String kdLembarDisposisiParent) {
        this.kdLembarDisposisiParent = kdLembarDisposisiParent;
    }

    public String getNoSuratDisposisi() {
        return noSuratDisposisi;
    }

    public void setNoSuratDisposisi(String noSuratDisposisi) {
        this.noSuratDisposisi = noSuratDisposisi;
    }

    public List<String> getDaftarTargetLembarDisposisi() {
        return daftarTargetLembarDisposisi;
    }

    public void setDaftarTargetLembarDisposisi(List<String> daftarTargetLembarDisposisi) {
        this.daftarTargetLembarDisposisi = daftarTargetLembarDisposisi;
    }
}
