package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratTugasPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 30/01/18.
 */
@Repository
public interface SuratTugasPejabatDao
        extends JpaRepository<SuratTugasPejabat, String>{

}
