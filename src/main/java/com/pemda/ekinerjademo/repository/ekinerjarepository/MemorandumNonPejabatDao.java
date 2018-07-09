package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.MemorandumNonPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
@Repository
public interface MemorandumNonPejabatDao extends JpaRepository<MemorandumNonPejabat, String> {
    @Query("select mnp from MemorandumNonPejabat mnp " +
            "left join fetch mnp.memorandum " +
            "where mnp.kdUnitKerja = ?1")
    List<MemorandumNonPejabat> findByKdUnitKerja(String kdUnitKerja);
}
