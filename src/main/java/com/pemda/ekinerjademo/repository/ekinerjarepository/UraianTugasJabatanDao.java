package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@Repository
public interface UraianTugasJabatanDao extends JpaRepository<UraianTugasJabatan, Long> {
    UraianTugasJabatan findByUraianTugasJabatanId(UraianTugasJabatanId uraianTugasJabatanId);
    List<UraianTugasJabatan> findByUraianTugasJabatanIdKdJabatan(String kdJabatan);
}
