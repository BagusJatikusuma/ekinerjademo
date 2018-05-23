package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeteranganId;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface SuratKeteranganService {
    List<SuratKeterangan> getByKdUnitKerja(String kdUnitKerja);
    List<SuratKeterangan> getByNipPembuat(String nipPembuat);
    List<SuratKeterangan> getByNomorTahun(Integer nomorTahun);
    List<TargetSuratKeterangan> getTargetSuratKeteranganByNip(String nipTarget);
    List<SuratKeterangan> getSuratKeteranganSekretarisApproval(String kdUnitKerja);

    SuratKeterangan getByKdSuratKeterangan(String kdSuratKeterangan);

    void create(SuratKeterangan suratKeterangan);
    void createTargetSuratKeterangan(TargetSuratKeterangan targetSuratKeterangan);

    void openSuratKeterangan(String kdSuratKeterangan);
    void openTargetSuratKeterangan(TargetSuratKeteranganId targetSuratKeteranganId);
    void openSuratKeteranganPenilai(String kdSuratKeterangan);

    void approveSuratKeterangan(String kdSuratKeterangan);
    
}
