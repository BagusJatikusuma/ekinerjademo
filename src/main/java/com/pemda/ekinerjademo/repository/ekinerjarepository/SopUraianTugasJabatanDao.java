package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@Repository
public interface SopUraianTugasJabatanDao extends JpaRepository<SopUraianTugasJabatan, String> {
    SopUraianTugasJabatan findBySopUraianTugasJabatanId(SopUraianTugasJabatanId sopUraianTugasJabatanId);
    void deleteBySopUraianTugasJabatanId(SopUraianTugasJabatanId sopUraianTugasJabatanId);
    @Query("select sopUrtugJabatan from SopUraianTugasJabatan sopUrtugJabatan " +
            "left join fetch sopUrtugJabatan.sop " +
            "where sopUrtugJabatan.sopUraianTugasJabatanId.kdUrtug = ?1 " +
            "and sopUrtugJabatan.sopUraianTugasJabatanId.kdJabatan = ?2")
    List<SopUraianTugasJabatan> findBySopUraianTugasJabatanId_KdUrtugAndSopUraianTugasJabatanId_KdJabatan(
            String kdUrtug,
            String kdJabatan);
}
