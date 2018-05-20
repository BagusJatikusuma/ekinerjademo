package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface SuratDinasService {
    List<SuratDinas> getByKdUnitKerja(String kdUnitKerja);
    List<SuratDinas> getByKdUnitKerjaTarget(String kdUnitKerjaTarget);
    List<SuratDinas> getByNipPembuat(String nipPembuat);
    List<SuratDinas> getByNomorTahun(Integer nomorTahun);
    List<SuratDinas> getByJabatanPenerima(String kdJabatan);
    List<SuratDinas> getDraftApproval(String kdUnitKerja);

    List<TembusanSuratDinas> getTembusanSuratDinas(String kdJabatan);
    List<TembusanSuratDinas> getTembusanSuratDinasUnitKerja(String kdUnitKerja);

    SuratDinas getByKdSuratDinas(String kdSuratDinas);

    void create(SuratDinas suratDinas);
    void createSuratDinasPejabat(SuratDinasPejabat suratDinasPejabat);
    void createSuratDinasNonPejabat(SuratDinasNonPejabat suratDinasNonPejabat);
    void createTembusanSuratDinas(TembusanSuratDinas tembusanSuratDinas);

    void openSuratDinas(String kdSuratDinas);
    void openTembusanSuratDinas(TembusanSuratDinasId tembusanSuratDinasId);
    void openSuratDinasPenilai(String kdSuratDinas);

    void approveSuratDinas(String kdSuratDinas);


}
