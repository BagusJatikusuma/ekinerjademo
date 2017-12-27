package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 24/12/17.
 */
public class LaporanBawahanWrapper {
    private String kdSurat;
    private String jenisSurat;
    private String nipBawahan;
    private String namaBawahan;
    private Integer StatusPenilaian;
    private Integer kdJenisSurat;
    private Integer suratPejabat;
    private Long tanggalDibuatMilis;
    private String namaFileTemplateLain;

    public LaporanBawahanWrapper() {
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            Integer statusPenilaian) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        StatusPenilaian = statusPenilaian;
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            Integer statusPenilaian,
            Integer kdJenisSurat) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        StatusPenilaian = statusPenilaian;
        this.kdJenisSurat = kdJenisSurat;
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            Integer statusPenilaian,
            Integer kdJenisSurat,
            Integer suratPejabat) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        StatusPenilaian = statusPenilaian;
        this.kdJenisSurat = kdJenisSurat;
        this.suratPejabat = suratPejabat;
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            String namaBawahan,
            Integer statusPenilaian,
            Integer kdJenisSurat,
            Integer suratPejabat,
            Long tanggalDibuatMilis) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        this.namaBawahan = namaBawahan;
        StatusPenilaian = statusPenilaian;
        this.kdJenisSurat = kdJenisSurat;
        this.suratPejabat = suratPejabat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            String namaBawahan,
            Integer statusPenilaian,
            Integer kdJenisSurat,
            Integer suratPejabat,
            Long tanggalDibuatMilis,
            String namaFileTemplateLain) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        this.namaBawahan = namaBawahan;
        StatusPenilaian = statusPenilaian;
        this.kdJenisSurat = kdJenisSurat;
        this.suratPejabat = suratPejabat;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.namaFileTemplateLain = namaFileTemplateLain;
    }

    public String getKdSurat() {
        return kdSurat;
    }

    public void setKdSurat(String kdSurat) {
        this.kdSurat = kdSurat;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getNipBawahan() {
        return nipBawahan;
    }

    public void setNipBawahan(String nipBawahan) {
        this.nipBawahan = nipBawahan;
    }

    public Integer getStatusPenilaian() {
        return StatusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        StatusPenilaian = statusPenilaian;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }

    public Integer isSuratPejabat() {
        return suratPejabat;
    }

    public void setSuratPejabat(Integer suratPejabat) {
        this.suratPejabat = suratPejabat;
    }

    public Integer getSuratPejabat() {
        return suratPejabat;
    }

    public String getNamaBawahan() {
        return namaBawahan;
    }

    public void setNamaBawahan(String namaBawahan) {
        this.namaBawahan = namaBawahan;
    }

    public Long getTanggalDibuatMilis() {
        return tanggalDibuatMilis;
    }

    public void setTanggalDibuatMilis(Long tanggalDibuatMilis) {
        this.tanggalDibuatMilis = tanggalDibuatMilis;
    }

    public String getNamaFileTemplateLain() {
        return namaFileTemplateLain;
    }

    public void setNamaFileTemplateLain(String namaFileTemplateLain) {
        this.namaFileTemplateLain = namaFileTemplateLain;
    }
}
