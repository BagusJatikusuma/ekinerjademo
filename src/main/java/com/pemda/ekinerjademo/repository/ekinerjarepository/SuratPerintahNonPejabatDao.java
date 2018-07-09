package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintahNonPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 28/11/17.
 */
@Repository
public interface SuratPerintahNonPejabatDao
        extends JpaRepository<SuratPerintahNonPejabat, String> {
    @Query("select spnp from SuratPerintahNonPejabat spnp " +
            "left join fetch spnp.suratPerintah " +
            "where spnp.kdUnitKerja = ?1")
    List<SuratPerintahNonPejabat> findByKdUnitKerja(String kdUnitKerja);
}
