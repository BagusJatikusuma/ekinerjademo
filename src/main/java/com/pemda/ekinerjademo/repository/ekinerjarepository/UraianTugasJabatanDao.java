package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@Repository
public interface UraianTugasJabatanDao extends JpaRepository<UraianTugasJabatan, Long> {
    @Query("select u from UraianTugasJabatan u " +
            "left join fetch u.uraianTugas " +
            "left join fetch u.jenisUrtug " +
            "where u.uraianTugasJabatanId.kdJabatan = ?1")
    List<UraianTugasJabatan> findByUraianTugasJabatanIdKdJabatan(String kdJabatan);
    UraianTugasJabatan findByUraianTugasJabatanId(UraianTugasJabatanId uraianTugasJabatanId);
    void deleteByUraianTugasJabatanId(UraianTugasJabatanId uraianTugasJabatanId);
//    @Query(name = "SELECT * FROM uraian_tugas_jabatan WHERE kd_jabatan = ?1", nativeQuery = true)
//    void deleteAllByUraianTugasJabatanIdKdJabatan(String kdJabatan);
//    @Query(value = "INSERT INTO uraian_tugas_jabatan VALUES (?1,?2)", nativeQuery = true)
//    void saveNativeQuery(String kdUrtug, String kdJabatan);
}
