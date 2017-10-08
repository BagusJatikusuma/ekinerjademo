package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@Repository
public interface SopUraianTugasJabatanDao extends JpaRepository<SopUraianTugasJabatan, String> {
    SopUraianTugasJabatan findBySopUraianTugasJabatanId(SopUraianTugasJabatan sopUraianTugasJabatan);
    void deleteBySopUraianTugasJabatanId(SopUraianTugasJabatanId sopUraianTugasJabatanId);
    List<SopUraianTugasJabatan> findBySopUraianTugasJabatanId_KdUrtugAndSopUraianTugasJabatanId_KdJabatanAndSopUraianTugasJabatanId_KdJenisUrtug(
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug);
}
