package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Repository
public interface UraianTugasDao extends JpaRepository<UraianTugas, Long> {
//    List<UraianTugas> findAll();
//    List<UraianTugas> findAllByOrderByKdUrtugDesc();
    UraianTugas findByKdUrtug(String kdUrtug);
    void deleteByKdUrtug(String kdUrtug);

    @Query("select distinct(ut.jabatan) from UraianTugas ut")
    List<String> findAllByDistinctJabatan();
    @Query("select distinct(ut.jabatan) from UraianTugas ut where ut.unitKerja = ?1")
    List<String> findAllByDistinctJabatan(String unitKerja);
    @Query("select distinct(ut.unitKerja) from UraianTugas ut")
    List<String> findAllByDistinctUnitKerja();
    @Query("select distinct(ut.unitKerja) from UraianTugas ut where ut.jabatan = ?1")
    List<String> findAllByDistinctUnitKerja(String jabatan);
    @Query("select ut from UraianTugas ut where jabatan = ?1")
    List<UraianTugas> findAllByJabatan(String jabatan);
    @Query("select ut from UraianTugas ut where ut.jabatan = ?1 and ut.unitKerja = ?2")
    List<UraianTugas> findAllByJabatan(String jabatan, String unitKerja);

}
