package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.MemorandumPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
@Repository
public interface MemorandumPejabatDao extends JpaRepository<MemorandumPejabat, String> {
    @Query("select mp from MemorandumPejabat mp " +
            "left join fetch mp.memorandum " +
            "where mp.kdJabatan = ?1")
    List<MemorandumPejabat> findByKdJabatan(String kdJabatan);
}
