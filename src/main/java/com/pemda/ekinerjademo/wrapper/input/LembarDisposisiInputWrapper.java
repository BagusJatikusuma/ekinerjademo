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
    private Long tanggalSuratDisposisiMilis;
    private String dariSuratDisposisi;
    private String ringkasanIsiSuratDisposisi;
    private String lampiran;
    private List<String> daftarTargetLembarDisposisi;
    private boolean isTargetJabatan;
    private String isiDisposisi;
    private Integer durasiPengerjaan;

    private String kdUrtug;
    private Integer tahunUrtug;
    private String namaFileSuratLain;

    private Integer jenisSuratPenugasan;
    private String kdSuratPenugasan;

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

    public Long getTanggalSuratDisposisiMilis() {
        return tanggalSuratDisposisiMilis;
    }

    public void setTanggalSuratDisposisiMilis(Long tanggalSuratDisposisiMilis) {
        this.tanggalSuratDisposisiMilis = tanggalSuratDisposisiMilis;
    }

    public String getDariSuratDisposisi() {
        return dariSuratDisposisi;
    }

    public void setDariSuratDisposisi(String dariSuratDisposisi) {
        this.dariSuratDisposisi = dariSuratDisposisi;
    }

    public String getRingkasanIsiSuratDisposisi() {
        return ringkasanIsiSuratDisposisi;
    }

    public void setRingkasanIsiSuratDisposisi(String ringkasanIsiSuratDisposisi) {
        this.ringkasanIsiSuratDisposisi = ringkasanIsiSuratDisposisi;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public List<String> getDaftarTargetLembarDisposisi() {
        return daftarTargetLembarDisposisi;
    }

    public void setDaftarTargetLembarDisposisi(List<String> daftarTargetLembarDisposisi) {
        this.daftarTargetLembarDisposisi = daftarTargetLembarDisposisi;
    }

    public String getIsiDisposisi() {
        return isiDisposisi;
    }

    public void setIsiDisposisi(String isiDisposisi) {
        this.isiDisposisi = isiDisposisi;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public Integer getJenisSuratPenugasan() {
        return jenisSuratPenugasan;
    }

    public void setJenisSuratPenugasan(Integer jenisSuratPenugasan) {
        this.jenisSuratPenugasan = jenisSuratPenugasan;
    }

    public String getKdSuratPenugasan() {
        return kdSuratPenugasan;
    }

    public void setKdSuratPenugasan(String kdSuratPenugasan) {
        this.kdSuratPenugasan = kdSuratPenugasan;
    }

    public String getNamaFileSuratLain() {
        return namaFileSuratLain;
    }

    public void setNamaFileSuratLain(String namaFileSuratLain) {
        this.namaFileSuratLain = namaFileSuratLain;
    }

    public boolean isTargetJabatan() {
        return isTargetJabatan;
    }

    public void setTargetJabatan(boolean targetJabatan) {
        isTargetJabatan = targetJabatan;
    }
}
