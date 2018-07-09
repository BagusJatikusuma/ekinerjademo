package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TembusanNotaDinasDao
        extends JpaRepository<TembusanNotaDinas, TembusanNotaDinasId> {
    @Query("select tnd from TembusanNotaDinas tnd " +
            "left join fetch tnd.notaDinas " +
            "where tnd.tembusanNotaDinasId.kdJabatan = ?1")
    List<TembusanNotaDinas> findByTembusanNotaDinasId_KdJabatan(String kdJabatan);
}
