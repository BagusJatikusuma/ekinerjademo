package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 25/10/17.
 */
@Repository
public interface PejabatPenilaiDinilaiDao extends JpaRepository<PejabatPenilaiDinilai, PejabatPenilaiDinilaiId> {
    PejabatPenilaiDinilai findByPejabatPenilaiDinilaiId(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId);
    PejabatPenilaiDinilai findByPejabatPenilaiDinilaiId_KdJabatanDinilai(String kdJabatanDinilai);
    List<PejabatPenilaiDinilai> findByPejabatPenilaiDinilaiId_NipPenilai(String nipPenilai);
    void deleteByPejabatPenilaiDinilaiId(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId);
}
