package com.pemda.ekinerjademo.wrapper.output;

public class SuratDinasWrapper {
    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String sifat;
    private String lampiran;
    private String hal;

    private String kdJabatanPenerimaSuratDinas;
    private String jabatanPenerimaSuratDinas;

    private Long tanggalPembuatanMilis;
    private String kotaPembuatanSurat;
    private String isiSuratDinas;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;

    public SuratDinasWrapper() {
    }

    public SuratDinasWrapper(String nomorUrusan,
                             Integer nomorUrut,
                             String nomorPasanganUrut,
                             String nomorUnit,
                             Integer nomorTahun,
                             String sifat,
                             String lampiran,
                             String hal,
                             String kdJabatanPenerimaSuratDinas,
                             String jabatanPenerimaSuratDinas,
                             Long tanggalPembuatanMilis,
                             String kotaPembuatanSurat,
                             String isiSuratDinas,
                             String nipPenandatangan,
                             String namaPenandatangan,
                             String jabatanPenandatangan,
                             String unitKerjaPenandatangan) {
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.sifat = sifat;
        this.lampiran = lampiran;
        this.hal = hal;
        this.kdJabatanPenerimaSuratDinas = kdJabatanPenerimaSuratDinas;
        this.jabatanPenerimaSuratDinas = jabatanPenerimaSuratDinas;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.isiSuratDinas = isiSuratDinas;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public String getNomorUnit() {
        return nomorUnit;
    }

    public void setNomorUnit(String nomorUnit) {
        this.nomorUnit = nomorUnit;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getKdJabatanPenerimaSuratDinas() {
        return kdJabatanPenerimaSuratDinas;
    }

    public void setKdJabatanPenerimaSuratDinas(String kdJabatanPenerimaSuratDinas) {
        this.kdJabatanPenerimaSuratDinas = kdJabatanPenerimaSuratDinas;
    }

    public String getJabatanPenerimaSuratDinas() {
        return jabatanPenerimaSuratDinas;
    }

    public void setJabatanPenerimaSuratDinas(String jabatanPenerimaSuratDinas) {
        this.jabatanPenerimaSuratDinas = jabatanPenerimaSuratDinas;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public String getIsiSuratDinas() {
        return isiSuratDinas;
    }

    public void setIsiSuratDinas(String isiSuratDinas) {
        this.isiSuratDinas = isiSuratDinas;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNamaPenandatangan() {
        return namaPenandatangan;
    }

    public void setNamaPenandatangan(String namaPenandatangan) {
        this.namaPenandatangan = namaPenandatangan;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }
}
