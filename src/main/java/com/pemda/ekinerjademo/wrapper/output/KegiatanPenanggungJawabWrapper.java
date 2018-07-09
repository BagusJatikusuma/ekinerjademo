package com.pemda.ekinerjademo.wrapper.output;

import java.math.BigDecimal;

public class KegiatanPenanggungJawabWrapper {
    private Integer kdUrusan;
    private Integer kdBIdang;
    private Integer kdUnit;
    private Integer kdSub;
    private Integer tahun;
    private Integer kdProg;
    private Integer idProg;
    private Integer kdKegiatan;
    private String ketKegiatan;
    private String lokasi;
    private String kelompokSasaran;
    private String statusKegiatan;
    private BigDecimal paguAnggaran;
    private String waktuPelaksanaan;
    private Integer kdSumber;
    private boolean bendaharaExist;
    private boolean ppbjExist;
    private boolean penggunaAnggaranExist;
    private boolean pelaksanaAdministrasiExist;
    private boolean pphpExist;
    private boolean ppkExist;
    private boolean pptkExist;
    private boolean timTeknisExist;

    public KegiatanPenanggungJawabWrapper() {
    }

    public KegiatanPenanggungJawabWrapper(Integer kdUrusan, Integer kdBIdang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg, Integer kdKegiatan, String ketKegiatan, String lokasi, String kelompokSasaran, String statusKegiatan, BigDecimal paguAnggaran, String waktuPelaksanaan, Integer kdSumber, boolean bendaharaExist, boolean ppbjExist, boolean penggunaAnggaranExist, boolean pelaksanaAdministrasiExist, boolean pphpExist, boolean ppkExist, boolean pptkExist, boolean timTeknisExist) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKegiatan = kdKegiatan;
        this.ketKegiatan = ketKegiatan;
        this.lokasi = lokasi;
        this.kelompokSasaran = kelompokSasaran;
        this.statusKegiatan = statusKegiatan;
        this.paguAnggaran = paguAnggaran;
        this.waktuPelaksanaan = waktuPelaksanaan;
        this.kdSumber = kdSumber;
        this.bendaharaExist = bendaharaExist;
        this.ppbjExist = ppbjExist;
        this.penggunaAnggaranExist = penggunaAnggaranExist;
        this.pelaksanaAdministrasiExist = pelaksanaAdministrasiExist;
        this.pphpExist = pphpExist;
        this.ppkExist = ppkExist;
        this.pptkExist = pptkExist;
        this.timTeknisExist = timTeknisExist;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBIdang() {
        return kdBIdang;
    }

    public void setKdBIdang(Integer kdBIdang) {
        this.kdBIdang = kdBIdang;
    }

    public Integer getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(Integer kdUnit) {
        this.kdUnit = kdUnit;
    }

    public Integer getKdSub() {
        return kdSub;
    }

    public void setKdSub(Integer kdSub) {
        this.kdSub = kdSub;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getKdProg() {
        return kdProg;
    }

    public void setKdProg(Integer kdProg) {
        this.kdProg = kdProg;
    }

    public Integer getIdProg() {
        return idProg;
    }

    public void setIdProg(Integer idProg) {
        this.idProg = idProg;
    }

    public Integer getKdKegiatan() {
        return kdKegiatan;
    }

    public void setKdKegiatan(Integer kdKegiatan) {
        this.kdKegiatan = kdKegiatan;
    }

    public String getKetKegiatan() {
        return ketKegiatan;
    }

    public void setKetKegiatan(String ketKegiatan) {
        this.ketKegiatan = ketKegiatan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKelompokSasaran() {
        return kelompokSasaran;
    }

    public void setKelompokSasaran(String kelompokSasaran) {
        this.kelompokSasaran = kelompokSasaran;
    }

    public String getStatusKegiatan() {
        return statusKegiatan;
    }

    public void setStatusKegiatan(String statusKegiatan) {
        this.statusKegiatan = statusKegiatan;
    }

    public BigDecimal getPaguAnggaran() {
        return paguAnggaran;
    }

    public void setPaguAnggaran(BigDecimal paguAnggaran) {
        this.paguAnggaran = paguAnggaran;
    }

    public String getWaktuPelaksanaan() {
        return waktuPelaksanaan;
    }

    public void setWaktuPelaksanaan(String waktuPelaksanaan) {
        this.waktuPelaksanaan = waktuPelaksanaan;
    }

    public Integer getKdSumber() {
        return kdSumber;
    }

    public void setKdSumber(Integer kdSumber) {
        this.kdSumber = kdSumber;
    }

    public boolean isBendaharaExist() {
        return bendaharaExist;
    }

    public void setBendaharaExist(boolean bendaharaExist) {
        this.bendaharaExist = bendaharaExist;
    }

    public boolean isPpbjExist() {
        return ppbjExist;
    }

    public void setPpbjExist(boolean ppbjExist) {
        this.ppbjExist = ppbjExist;
    }

    public boolean isPenggunaAnggaranExist() {
        return penggunaAnggaranExist;
    }

    public void setPenggunaAnggaranExist(boolean penggunaAnggaranExist) {
        this.penggunaAnggaranExist = penggunaAnggaranExist;
    }

    public boolean isPelaksanaAdministrasiExist() {
        return pelaksanaAdministrasiExist;
    }

    public void setPelaksanaAdministrasiExist(boolean pelaksanaAdministrasiExist) {
        this.pelaksanaAdministrasiExist = pelaksanaAdministrasiExist;
    }

    public boolean isPphpExist() {
        return pphpExist;
    }

    public void setPphpExist(boolean pphpExist) {
        this.pphpExist = pphpExist;
    }

    public boolean isPpkExist() {
        return ppkExist;
    }

    public void setPpkExist(boolean ppkExist) {
        this.ppkExist = ppkExist;
    }

    public boolean isPptkExist() {
        return pptkExist;
    }

    public void setPptkExist(boolean pptkExist) {
        this.pptkExist = pptkExist;
    }

    public boolean isTimTeknisExist() {
        return timTeknisExist;
    }

    public void setTimTeknisExist(boolean timTeknisExist) {
        this.timTeknisExist = timTeknisExist;
    }
}
