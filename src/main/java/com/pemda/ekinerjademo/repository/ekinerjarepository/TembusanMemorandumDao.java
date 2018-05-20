package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanMemorandum;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanMemorandumId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
@Repository
public interface TembusanMemorandumDao extends JpaRepository<TembusanMemorandum, TembusanMemorandumId> {
    @Query("select tm from TembusanMemorandum tm " +
            "left join fetch tm.memorandum " +
            "where tm.tembusanMemorandumId.kdJabatan = ?1")
    List<TembusanMemorandum> findByTembusanMemorandumId_KdJabatan(String kdJabatan);

    @Query("select tm from TembusanMemorandum tm " +
            "left join fetch tm.memorandum " +
            "where tm.kdUnitKerja = ?1")
    List<TembusanMemorandum> findByUnitKerja(String kdUnitKerja);

}
