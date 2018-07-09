package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 05/12/17.
 */
@Entity
@Table(name = "nomor_urut_surat_unit_kerja")
public class NomorUrutSuratUnitKerja {
    @EmbeddedId
    private NomorUrutSuratUnitKerjaId nomorUrutSuratUnitKerjaId;
    @Column(name = "nomor_urut_surat")
    private Integer nomorUrutSurat;

    public NomorUrutSuratUnitKerjaId getNomorUrutSuratUnitKerjaId() {
        return nomorUrutSuratUnitKerjaId;
    }

    public void setNomorUrutSuratUnitKerjaId(NomorUrutSuratUnitKerjaId nomorUrutSuratUnitKerjaId) {
        this.nomorUrutSuratUnitKerjaId = nomorUrutSuratUnitKerjaId;
    }

    public Integer getNomorUrutSurat() {
        return nomorUrutSurat;
    }

    public void setNomorUrutSurat(Integer nomorUrutSurat) {
        this.nomorUrutSurat = nomorUrutSurat;
    }
}
