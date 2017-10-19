package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 08/10/17.
 */
public class PenanggungJawabKegiatanWrapper {
    private String nipPegawai;
    private String namaPegawai;
    private String kdStatusPenanggungJawab;
    private String statusPenanggungJawab;

    public PenanggungJawabKegiatanWrapper() {
    }
    public PenanggungJawabKegiatanWrapper(
            String nipPegawai,
            String kdStatusPenanggungJawab,
            String statusPenanggungJawab) {
        this.nipPegawai = nipPegawai;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
        this.statusPenanggungJawab = statusPenanggungJawab;
    }
    public PenanggungJawabKegiatanWrapper(
            String nipPegawai,
            String namaPegawai,
            String kdStatusPenanggungJawab,
            String statusPenanggungJawab) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
        this.statusPenanggungJawab = statusPenanggungJawab;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdStatusPenanggungJawab() {
        return kdStatusPenanggungJawab;
    }

    public void setKdStatusPenanggungJawab(String kdStatusPenanggungJawab) {
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
    }

    public String getStatusPenanggungJawab() {
        return statusPenanggungJawab;
    }

    public void setStatusPenanggungJawab(String statusPenanggungJawab) {
        this.statusPenanggungJawab = statusPenanggungJawab;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }
}
