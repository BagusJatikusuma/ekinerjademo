package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 13/09/17.
 */
public class QutPegawaiWrapper {
    private String nipPegawai;
    private String nama;
    private String kdJabatan;
    private String jabatan;
    private String kdUnitKerja;
    private String unitKerja;
    private String pangkat;
    private String golongan;
    private String role;

    public QutPegawaiWrapper() {}
    public QutPegawaiWrapper(
            String nipPegawai,
            String nama,
            String jabatan,
            String unitKerja) {
        this.nipPegawai = nipPegawai;
        this.nama = nama;
        this.jabatan = jabatan;
        this.unitKerja = unitKerja;
    }
    public QutPegawaiWrapper(
            String nipPegawai,
            String nama,
            String kdJabatan,
            String jabatan,
            String kdUnitKerja,
            String unitKerja,
            String pangkat,
            String golongan) {
        this.nipPegawai = nipPegawai;
        this.nama = nama;
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
        this.kdUnitKerja = kdUnitKerja;
        this.unitKerja = unitKerja;
        this.pangkat = pangkat;
        this.golongan = golongan;
    }
    public QutPegawaiWrapper(
            String nipPegawai,
            String nama,
            String kdJabatan,
            String jabatan,
            String kdUnitKerja,
            String unitKerja,
            String pangkat,
            String golongan,
            String role) {
        this.nipPegawai = nipPegawai;
        this.nama = nama;
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
        this.kdUnitKerja = kdUnitKerja;
        this.unitKerja = unitKerja;
        this.pangkat = pangkat;
        this.golongan = golongan;
        this.role = role;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
