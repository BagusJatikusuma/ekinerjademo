package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by bayu on 08/12/17.
 */
@Entity
@Table(name = "nota_dinas")
public class NotaDinas {
    @Id
    @Column(name = "kd_nota_dinas")
    private String kdNotaDinas;
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

    @Column(name = "kd_jabatan_penerima_nota_dinas")
    private String kdJabatanPenerimaNotaDinas;
    @Column(name = "nip_pemberi_nota_dinas")
    private String nipPemberiNotaDinas;
    @Column(name = "hal")
    private String hal;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "isi_nota_dinas")
    private String isiNotaDinas;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;

//    @OneToMany(mappedBy = "notaDinas")
//    private Set<TembusanNotaDinas> tembusanNotaDinasList;

}
