package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 07/12/17.
 */
@Entity
@Table(name = "surat_kuasa")
public class SuratKuasa {
    @Id
    @Column(name = "kd_surat_kuasa")
    private String kdSuratKuasa;
    @Column(name = "nomor_urusan")
    private String nomorUrusan;
    @Column(name = "nomor_urut")
    private Integer nomorUrut;
    @Column(name = "nomor_pasangan_urut")
    private String nomorPasanganUrut;
    @Column(name = "nomor_unit")
    private String nomorUnit;
    @Column(name = "nomor_tahun")
    private Integer nomorTahun;

    @Column(name = "nip_pemberi_kuasa")
    private String nipPemberiKuasa;
    @Column(name = "nip_penerima_kuasa")
    private String nipPenerimaKuasa;

    @Column(name = "isi_kuasa")
    private String isiKuasa;

    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "tanggal_perintah_milis")
    private Long tanggalPerintahMilis;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
}
