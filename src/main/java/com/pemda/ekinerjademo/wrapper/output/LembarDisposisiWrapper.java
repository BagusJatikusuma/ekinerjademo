package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 27/11/17.
 */
public class LembarDisposisiWrapper {
    private String kdLembarDisposisi;
    private String path;
    private String tanggalPenerimaan;
    private Long tanggalPenerimaanMilis;
    private Integer tktKeamanan;
    private String tglPenyelesaian;
    private Long tglPenyelesaianMilis;
    private Integer statusBaca;
    private String tglPengiriman;
    private Long tglPengirimanMilis;
    private String nipPengirim;
    private String namaPengirim;
    private String ringkasanIsi;
    private String jenisSurat;
    private Integer kdJenisSurat;

    public LembarDisposisiWrapper() {
    }
    public LembarDisposisiWrapper(String kdLembarDisposisi, String path, String tanggalPenerimaan, Integer tktKeamanan, String tglPenyelesaian) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
    }
    public LembarDisposisiWrapper(
            String kdLembarDisposisi,
            String path,
            String tanggalPenerimaan,
            Integer tktKeamanan,
            String tglPenyelesaian,
            Integer statusBaca,
            String tglPengiriman) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.statusBaca = statusBaca;
        this.tglPengiriman = tglPengiriman;
    }
    public LembarDisposisiWrapper(
            String kdLembarDisposisi,
            String path,
            String tanggalPenerimaan,
            Long tanggalPenerimaanMilis,
            Integer tktKeamanan,
            String tglPenyelesaian,
            Long tglPenyelesaianMilis,
            Integer statusBaca,
            String tglPengiriman,
            Long tglPengirimanMilis,
            String nipPengirim,
            String namaPengirim) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.statusBaca = statusBaca;
        this.tglPengiriman = tglPengiriman;
        this.tglPengirimanMilis = tglPengirimanMilis;
        this.nipPengirim = nipPengirim;
        this.namaPengirim = namaPengirim;
    }
    public LembarDisposisiWrapper(
            String kdLembarDisposisi,
            String path,
            String tanggalPenerimaan,
            Long tanggalPenerimaanMilis,
            Integer tktKeamanan,
            String tglPenyelesaian,
            Long tglPenyelesaianMilis,
            Integer statusBaca,
            String tglPengiriman,
            Long tglPengirimanMilis,
            String nipPengirim,
            String namaPengirim,
            String ringkasanIsi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.statusBaca = statusBaca;
        this.tglPengiriman = tglPengiriman;
        this.tglPengirimanMilis = tglPengirimanMilis;
        this.nipPengirim = nipPengirim;
        this.namaPengirim = namaPengirim;
        this.ringkasanIsi = ringkasanIsi;
    }

    public LembarDisposisiWrapper(String kdLembarDisposisi, String path, String tanggalPenerimaan, Long tanggalPenerimaanMilis, Integer tktKeamanan, String tglPenyelesaian, Long tglPenyelesaianMilis, Integer statusBaca, String tglPengiriman, Long tglPengirimanMilis) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.statusBaca = statusBaca;
        this.tglPengiriman = tglPengiriman;
        this.tglPengirimanMilis = tglPengirimanMilis;
    }

    public LembarDisposisiWrapper(String kdLembarDisposisi, String path, String tanggalPenerimaan, Long tanggalPenerimaanMilis, Integer tktKeamanan, String tglPenyelesaian, Long tglPenyelesaianMilis, Integer statusBaca, String tglPengiriman, Long tglPengirimanMilis, String nipPengirim, String namaPengirim, String ringkasanIsi, String jenisSurat, Integer kdJenisSurat) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.path = path;
        this.tanggalPenerimaan = tanggalPenerimaan;
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
        this.tktKeamanan = tktKeamanan;
        this.tglPenyelesaian = tglPenyelesaian;
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
        this.statusBaca = statusBaca;
        this.tglPengiriman = tglPengiriman;
        this.tglPengirimanMilis = tglPengirimanMilis;
        this.nipPengirim = nipPengirim;
        this.namaPengirim = namaPengirim;
        this.ringkasanIsi = ringkasanIsi;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
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

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getTglPengiriman() {
        return tglPengiriman;
    }

    public void setTglPengiriman(String tglPengiriman) {
        this.tglPengiriman = tglPengiriman;
    }

    public String getNipPengirim() {
        return nipPengirim;
    }

    public void setNipPengirim(String nipPengirim) {
        this.nipPengirim = nipPengirim;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public Long getTanggalPenerimaanMilis() {
        return tanggalPenerimaanMilis;
    }

    public void setTanggalPenerimaanMilis(Long tanggalPenerimaanMilis) {
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
    }

    public Long getTglPenyelesaianMilis() {
        return tglPenyelesaianMilis;
    }

    public void setTglPenyelesaianMilis(Long tglPenyelesaianMilis) {
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
    }

    public Long getTglPengirimanMilis() {
        return tglPengirimanMilis;
    }

    public void setTglPengirimanMilis(Long tglPengirimanMilis) {
        this.tglPengirimanMilis = tglPengirimanMilis;
    }

    public String getRingkasanIsi() {
        return ringkasanIsi;
    }

    public void setRingkasanIsi(String ringkasanIsi) {
        this.ringkasanIsi = ringkasanIsi;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }
}
