package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;

/**
 * Created by bagus on 25/10/17.
 */
public interface PejabatPenilaiDinilaiService {
    void create(PejabatPenilaiDinilai pejabatPenilaiDinilai);
    void updatePejabatPenilai(PejabatPenilaiDinilai pejabatPenilaiDinilai);
    void updatePejabatPenilaiByKdJabatanDinilai(String nipPenilai, String kdJabatanDinilai);
    PejabatPenilaiDinilai findByKdJabatanDinilai(String kdJabatanDinilai);
    void delete(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId);
}
