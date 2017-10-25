package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 25/10/17.
 */
@Repository
public interface PejabatPenilaiDinilaiDao extends JpaRepository<PejabatPenilaiDinilai, PejabatPenilaiDinilaiId> {
    PejabatPenilaiDinilai findByPejabatPenilaiDinilaiId(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId);
    PejabatPenilaiDinilai findByPejabatPenilaiDinilaiId_KdJabatanDinilai(String kdJabatanDinilai);
    void deleteByPejabatPenilaiDinilaiId(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId);
}
