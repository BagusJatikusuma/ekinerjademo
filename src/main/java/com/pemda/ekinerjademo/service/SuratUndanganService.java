package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface SuratUndanganService {
    List<SuratUndangan> getByKdUnitKerja(String kdUnitKerja);
    List<SuratUndangan> getByNipPembuat(String nipPembuat);
    List<SuratUndangan> getByNomorTahun(Integer nomorTahun);

    SuratUndangan getByKdSuratUndangan(String kdSuratUndangan);

    void create(SuratUndangan suratUndangan);
    void createSuratUndanganNonPejabat(SuratUndanganNonPejabat suratUndanganNonPejabat);
    void createSuratUndanganPejabat(SuratUndanganPejabat suratUndanganPejabat);
    void tembusanSuratUndangan(TembusanSuratUndangan tembusanSuratUndangan);

    void openSuratUndangan(String kdSuratUndangan);
    void openTembusanSuratUndangan(TembusanSuratUndanganId tembusanSuratUndanganId);
    void openSuratUndanganPenilai(String kdSuratUndangan);
}
