package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 31/01/18.
 */
public class NotaDinasWrapper {
    private String kdNotaDinas;

    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String kdJabatanPenerimaNotaDinas;
    private String jabatanPenerimaNotaDinas;

    private CustomPegawaiCredential pemberiNotaDinas;
    private String hal;
    private Long tanggalPembuatanMilis;
    private String isiNotaDinas;
    private CustomPegawaiCredential penandatangan;

    private List<JabatanWrapper> tembusanNotaDinasList;
    private byte[] barcodeImage;

    public NotaDinasWrapper() {
    }

    public NotaDinasWrapper(String kdNotaDinas,
                            String nomorUrusan,
                            Integer nomorUrut,
                            String nomorPasanganUrut,
                            String nomorUnit,
                            Integer nomorTahun,
                            String kdJabatanPenerimaNotaDinas,
                            String jabatanPenerimaNotaDinas,
                            CustomPegawaiCredential pemberiNotaDinas,
                            String hal,
                            Long tanggalPembuatanMilis,
                            String isiNotaDinas,
                            CustomPegawaiCredential penandatangan,
                            List<JabatanWrapper> tembusanNotaDinasList,
                            byte[] barcodeImage) {
        this.kdNotaDinas = kdNotaDinas;
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.kdJabatanPenerimaNotaDinas = kdJabatanPenerimaNotaDinas;
        this.jabatanPenerimaNotaDinas = jabatanPenerimaNotaDinas;
        this.pemberiNotaDinas = pemberiNotaDinas;
        this.hal = hal;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.isiNotaDinas = isiNotaDinas;
        this.penandatangan = penandatangan;
        this.tembusanNotaDinasList = tembusanNotaDinasList;
        this.barcodeImage = barcodeImage;
    }

    public String getKdNotaDinas() {
        return kdNotaDinas;
    }

    public void setKdNotaDinas(String kdNotaDinas) {
        this.kdNotaDinas = kdNotaDinas;
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

    public String getKdJabatanPenerimaNotaDinas() {
        return kdJabatanPenerimaNotaDinas;
    }

    public void setKdJabatanPenerimaNotaDinas(String kdJabatanPenerimaNotaDinas) {
        this.kdJabatanPenerimaNotaDinas = kdJabatanPenerimaNotaDinas;
    }

    public String getJabatanPenerimaNotaDinas() {
        return jabatanPenerimaNotaDinas;
    }

    public void setJabatanPenerimaNotaDinas(String jabatanPenerimaNotaDinas) {
        this.jabatanPenerimaNotaDinas = jabatanPenerimaNotaDinas;
    }

    public CustomPegawaiCredential getPemberiNotaDinas() {
        return pemberiNotaDinas;
    }

    public void setPemberiNotaDinas(CustomPegawaiCredential pemberiNotaDinas) {
        this.pemberiNotaDinas = pemberiNotaDinas;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getIsiNotaDinas() {
        return isiNotaDinas;
    }

    public void setIsiNotaDinas(String isiNotaDinas) {
        this.isiNotaDinas = isiNotaDinas;
    }

    public CustomPegawaiCredential getPenandatangan() {
        return penandatangan;
    }

    public void setPenandatangan(CustomPegawaiCredential penandatangan) {
        this.penandatangan = penandatangan;
    }

    public List<JabatanWrapper> getTembusanNotaDinasList() {
        return tembusanNotaDinasList;
    }

    public void setTembusanNotaDinasList(List<JabatanWrapper> tembusanNotaDinasList) {
        this.tembusanNotaDinasList = tembusanNotaDinasList;
    }

    public byte[] getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
