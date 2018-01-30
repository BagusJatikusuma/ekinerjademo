package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 29/01/18.
 */
public interface SuratTugasService {
    SuratTugas getByKdSuratTugas(String kdSuratTugas);
    Set<SuratTugas> getByKdUnitKerja(String kdUnitKerja);
    Set<SuratTugas> getByNomorTahun(Integer nomorTahun);
    Set<SuratTugas> getByNipPembuat(String nipPembuat);

    List<TargetSuratTugasPegawai> getTargetSuratTugasPegawai(String nipTargetSurat);
    List<TargetSuratTugasPejabat> getTargetSuratTugasPejabat(String kdJabatanTarget);
    List<TembusanSuratTugas> getTembusanSuratTugas(String kdJabatanTembusan);

    void create(SuratTugas suratTugas);
    void createTargetSuratTugasPegawai(TargetSuratTugasPegawai targetSuratTugasPegawai);
    void createTargetSuratTugasPejabat(TargetSuratTugasPejabat targetSuratTugasPejabat);
    void createTembusanSuratTugas(TembusanSuratTugas tembusanSuratTugas);
    void createSuratTugasPejabat(SuratTugasPejabat suratTugasPejabat);
    void createSuratTugasNonPejabat(SuratTugasNonPejabat suratTugasNonPejabat);

    void openSuratTugas(String kdSuratTugas);
    void openSuratTugasPenilai(String kdSuratTugas);

}
