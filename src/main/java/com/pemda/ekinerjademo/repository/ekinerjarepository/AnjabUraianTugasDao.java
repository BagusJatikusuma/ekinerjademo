package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.AnjabUraianTugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("ekinerjaTransactionManager")
public interface AnjabUraianTugasDao
        extends JpaRepository<AnjabUraianTugas, Integer>{

}
