package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "surat_tugas")
public class SuratTugas {
    @Id
    @Column(name = "kd_surat_tugas")
    private String kdSuratTugas;
    @Column(name = "nip_pembuat")
    private String nipPembuat;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "nomor_surat_1")
    private Integer nomorSurat1;
    @Column(name = "nomor_surat_2")
    private String nomorSurat2;
    @Column(name = "nomor_surat_3")
    private String nomorSurat3;
    @Column(name = "nomor_tahun")
    private Integer nomorTahun;
    @Column(name = "dasar")
    private String dasar;
    @Column(name = "untuk")
    private String untuk;
    @Column(name = "tempat")
    private String tempat;
    @Column(name = "tanggal_tugas_milis")
    private Long tanggalTugasMilis;
    @Column(name = "ttd_path")
    private String ttdPath;
    @Column(name = "status_penyebaran")
    private Integer statusPenyebaran;
    @Column(name = "approval_penandatangan")
    private Integer approvalPenandatangan;
    @Column(name = "path_penilaian")
    private String pathPenilaian;
    @Column(name = "status_baca")
    private Integer statusBaca;

    public String getKdSuratTugas() {
        return kdSuratTugas;
    }

    public void setKdSuratTugas(String kdSuratTugas) {
        this.kdSuratTugas = kdSuratTugas;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public Integer getNomorSurat1() {
        return nomorSurat1;
    }

    public void setNomorSurat1(Integer nomorSurat1) {
        this.nomorSurat1 = nomorSurat1;
    }

    public String getNomorSurat2() {
        return nomorSurat2;
    }

    public void setNomorSurat2(String nomorSurat2) {
        this.nomorSurat2 = nomorSurat2;
    }

    public String getNomorSurat3() {
        return nomorSurat3;
    }

    public void setNomorSurat3(String nomorSurat3) {
        this.nomorSurat3 = nomorSurat3;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getDasar() {
        return dasar;
    }

    public void setDasar(String dasar) {
        this.dasar = dasar;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Long getTanggalTugasMilis() {
        return tanggalTugasMilis;
    }

    public void setTanggalTugasMilis(Long tanggalTugasMilis) {
        this.tanggalTugasMilis = tanggalTugasMilis;
    }

    public String getTtdPath() {
        return ttdPath;
    }

    public void setTtdPath(String ttdPath) {
        this.ttdPath = ttdPath;
    }

    public Integer getStatusPenyebaran() {
        return statusPenyebaran;
    }

    public void setStatusPenyebaran(Integer statusPenyebaran) {
        this.statusPenyebaran = statusPenyebaran;
    }

    public Integer getApprovalPenandatangan() {
        return approvalPenandatangan;
    }

    public void setApprovalPenandatangan(Integer approvalPenandatangan) {
        this.approvalPenandatangan = approvalPenandatangan;
    }

    public String getPathPenilaian() {
        return pathPenilaian;
    }

    public void setPathPenilaian(String pathPenilaian) {
        this.pathPenilaian = pathPenilaian;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
