package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.AnjabUraianTugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional("ekinerjaTransactionManager")
public interface AnjabUraianTugasDao
        extends JpaRepository<AnjabUraianTugas, Integer>{

    @Query("select aut from AnjabUraianTugas aut " +
            "left join fetch aut.anjabJabatan")
    List<AnjabUraianTugas> findAll();
}
