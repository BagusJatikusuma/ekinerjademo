package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratTugasNonPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 30/01/18.
 */
@Repository
public interface SuratTugasNonPejabatDao
        extends JpaRepository<SuratTugasNonPejabat, String>{
}
