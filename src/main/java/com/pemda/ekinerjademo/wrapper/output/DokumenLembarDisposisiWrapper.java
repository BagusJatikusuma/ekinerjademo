package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 27/11/17.
 */
public class DokumenLembarDisposisiWrapper {
    private String kdLembarDisposisi;
    private String path;
    private String tanggalPenerimaan;
    private Long tanggalPenerimaanMilis;
    private Integer tktKeamanan;
    private String tglPenyelesaian;
    private Long tglPenyelesaianMilis;
    private Long tangglPengirimanMilis;
    private String noSuratDisposisi;
    private String isiDisposisi;
    private String tanggalSuratDisposisi;
    private Long tanggalSuratDisposisiMilis;
    private String dari;
    private String ringkasanIsi;
    private String lampiran;
    private List<QutPegawaiWrapper> targetPegawaiLembarDisposisi;
    private List<JabatanWrapper> targeJabatanLembarDisposisiList;
    private boolean isTargetjabatan;
    private byte[] barcodeImage;

    public DokumenLembarDisposisiWrapper() {
    }

    public DokumenLembarDisposisiWrapper(String kdLembarDisposisi, String path, String tanggalPenerimaan, Long tanggalPenerimaanMilis, Integer tktKeamanan, String tglPenyelesaian, Long tglPenyelesaianMilis, Long tangglPengirimanMilis, String noSuratDisposisi, String isiDisposisi, String tanggalSuratDisposisi, Long tanggalSuratDisposisiMilis, String dari, String ringkasanIsi, String lampiran, List<QutPegawaiWrapper> targetPegawaiLembarDisposisi, byte[] barcodeImage) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.tangglPengirimanMilis = tangglPengirimanMilis;
        this.noSuratDisposisi = noSuratDisposisi;
        this.isiDisposisi = isiDisposisi;
        this.tanggalSuratDisposisi = tanggalSuratDisposisi;
        this.tanggalSuratDisposisiMilis = tanggalSuratDisposisiMilis;
        this.dari = dari;
        this.ringkasanIsi = ringkasanIsi;
        this.lampiran = lampiran;
        this.targetPegawaiLembarDisposisi = targetPegawaiLembarDisposisi;
        this.barcodeImage = barcodeImage;
    }

    public DokumenLembarDisposisiWrapper(
            String kdLembarDisposisi,
            String path,
            String tanggalPenerimaan,
            Long tanggalPenerimaanMilis,
            Integer tktKeamanan,
            String tglPenyelesaian,
            Long tglPenyelesaianMilis,
            Long tangglPengirimanMilis,
            String noSuratDisposisi,
            String isiDisposisi,
            String tanggalSuratDisposisi,
            Long tanggalSuratDisposisiMilis,
            String dari,
            String ringkasanIsi,
            String lampiran,
            List<QutPegawaiWrapper> targetPegawaiLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.tangglPengirimanMilis = tangglPengirimanMilis;
        this.noSuratDisposisi = noSuratDisposisi;
        this.isiDisposisi = isiDisposisi;
        this.tanggalSuratDisposisi = tanggalSuratDisposisi;
        this.tanggalSuratDisposisiMilis = tanggalSuratDisposisiMilis;
        this.dari = dari;
        this.ringkasanIsi = ringkasanIsi;
        this.lampiran = lampiran;
        this.targetPegawaiLembarDisposisi = targetPegawaiLembarDisposisi;
    }

    public DokumenLembarDisposisiWrapper(String kdLembarDisposisi, String path, String tanggalPenerimaan, Long tanggalPenerimaanMilis, Integer tktKeamanan, String tglPenyelesaian, Long tglPenyelesaianMilis, Long tangglPengirimanMilis, String noSuratDisposisi, String isiDisposisi, String tanggalSuratDisposisi, Long tanggalSuratDisposisiMilis, String dari, String ringkasanIsi, String lampiran, List<QutPegawaiWrapper> targetPegawaiLembarDisposisi, List<JabatanWrapper> targeJabatanLembarDisposisiList, boolean isTargetjabatan, byte[] barcodeImage) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.tangglPengirimanMilis = tangglPengirimanMilis;
        this.noSuratDisposisi = noSuratDisposisi;
        this.isiDisposisi = isiDisposisi;
        this.tanggalSuratDisposisi = tanggalSuratDisposisi;
        this.tanggalSuratDisposisiMilis = tanggalSuratDisposisiMilis;
        this.dari = dari;
        this.ringkasanIsi = ringkasanIsi;
        this.lampiran = lampiran;
        this.targetPegawaiLembarDisposisi = targetPegawaiLembarDisposisi;
        this.targeJabatanLembarDisposisiList = targeJabatanLembarDisposisiList;
        this.isTargetjabatan = isTargetjabatan;
        this.barcodeImage = barcodeImage;
    }

    public String getKdLembarDisposisi() {
        return kdLembarDisposisi;
    }

    public void setKdLembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTanggalPenerimaan() {
        return tanggalPenerimaan;
    }

    public void setTanggalPenerimaan(String tanggalPenerimaan) {
        this.tanggalPenerimaan = tanggalPenerimaan;
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

    public String getTglPenyelesaian() {
        return tglPenyelesaian;
    }

    public void setTglPenyelesaian(String tglPenyelesaian) {
        this.tglPenyelesaian = tglPenyelesaian;
    }

    public Long getTglPenyelesaianMilis() {
        return tglPenyelesaianMilis;
    }

    public void setTglPenyelesaianMilis(Long tglPenyelesaianMilis) {
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
    }

    public Long getTangglPengirimanMilis() {
        return tangglPengirimanMilis;
    }

    public void setTangglPengirimanMilis(Long tangglPengirimanMilis) {
        this.tangglPengirimanMilis = tangglPengirimanMilis;
    }

    public String getNoSuratDisposisi() {
        return noSuratDisposisi;
    }

    public void setNoSuratDisposisi(String noSuratDisposisi) {
        this.noSuratDisposisi = noSuratDisposisi;
    }

    public String getIsiDisposisi() {
        return isiDisposisi;
    }

    public void setIsiDisposisi(String isiDisposisi) {
        this.isiDisposisi = isiDisposisi;
    }

    public String getTanggalSuratDisposisi() {
        return tanggalSuratDisposisi;
    }

    public void setTanggalSuratDisposisi(String tanggalSuratDisposisi) {
        this.tanggalSuratDisposisi = tanggalSuratDisposisi;
    }

    public Long getTanggalSuratDisposisiMilis() {
        return tanggalSuratDisposisiMilis;
    }

    public void setTanggalSuratDisposisiMilis(Long tanggalSuratDisposisiMilis) {
        this.tanggalSuratDisposisiMilis = tanggalSuratDisposisiMilis;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getRingkasanIsi() {
        return ringkasanIsi;
    }

    public void setRingkasanIsi(String ringkasanIsi) {
        this.ringkasanIsi = ringkasanIsi;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public List<QutPegawaiWrapper> getTargetPegawaiLembarDisposisi() {
        return targetPegawaiLembarDisposisi;
    }

    public void setTargetPegawaiLembarDisposisi(List<QutPegawaiWrapper> targetPegawaiLembarDisposisi) {
        this.targetPegawaiLembarDisposisi = targetPegawaiLembarDisposisi;
    }

    public byte[] getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }

    public List<JabatanWrapper> getTargeJabatanLembarDisposisiList() {
        return targeJabatanLembarDisposisiList;
    }

    public void setTargeJabatanLembarDisposisiList(List<JabatanWrapper> targeJabatanLembarDisposisiList) {
        this.targeJabatanLembarDisposisiList = targeJabatanLembarDisposisiList;
    }

    public boolean isTargetjabatan() {
        return isTargetjabatan;
    }

    public void setTargetjabatan(boolean targetjabatan) {
        isTargetjabatan = targetjabatan;
    }
}
