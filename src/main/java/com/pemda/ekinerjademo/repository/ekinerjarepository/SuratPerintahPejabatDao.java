package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintahPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 28/11/17.
 */
@Repository
public interface SuratPerintahPejabatDao
        extends JpaRepository<SuratPerintahPejabat, String> {
    @Query("select spp from SuratPerintahPejabat spp " +
            "left join fetch spp.suratPerintah " +
            "where spp.kdJabatan = ?1")
    List<SuratPerintahPejabat> findByKdJabatan(String kdJabatan);
}
