package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;

import java.util.List;

/**
 * Created by bagus on 25/10/17.
 */
public interface PejabatPenilaiDinilaiService {
    void create(PejabatPenilaiDinilai pejabatPenilaiDinilai);
    void updatePejabatPenilai(PejabatPenilaiDinilai pejabatPenilaiDinilai);
    void updatePejabatPenilaiByKdJabatanDinilai(String nipPenilai, String kdJabatanDinilai);
    PejabatPenilaiDinilai findByKdJabatanDinilai(String kdJabatanDinilai);
    List<PejabatPenilaiDinilai> findPegawaiDinilai(String nipPenilai);
    void delete(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId);
}
