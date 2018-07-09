package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtug;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtugId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 10/10/17.
 */
@Repository
public interface UraianTugasJabatanJenisUrtugDao extends JpaRepository<UraianTugasJabatanJenisUrtug, UraianTugasJabatanJenisUrtugId> {
    @Query("select utj from UraianTugasJabatanJenisUrtug utj " +
            "left join fetch utj.uraianTugasJabatan uj " +
            "left join fetch uj.uraianTugas " +
            "left join fetch utj.jenisUrtug " +
            "where utj.uraianTugasJabatanJenisUrtugId.kdUrtug = ?1 " +
            "and utj.uraianTugasJabatanJenisUrtugId.kdJabatan = ?2")
    List<UraianTugasJabatanJenisUrtug> findByUrtugJabatan(String kdUrtug, String kdJabatan);

    @Query("select utj from UraianTugasJabatanJenisUrtug utj " +
            "left join fetch utj.uraianTugasJabatan uj " +
            "left join fetch uj.uraianTugas " +
            "left join fetch utj.jenisUrtug " +
            "where utj.uraianTugasJabatanJenisUrtugId.kdJabatan = ?1 " +
            "and utj.uraianTugasJabatanJenisUrtugId.kdJenisUrtug = ?2")
    List<UraianTugasJabatanJenisUrtug> findUrtugNonDpaByJabatan(String jabatan, String nonDpa);

    @Query("select utj from UraianTugasJabatanJenisUrtug utj " +
            "left join fetch utj.uraianTugasJabatan uj " +
            "left join fetch uj.uraianTugas " +
            "left join fetch utj.jenisUrtug " +
            "where utj.uraianTugasJabatanJenisUrtugId.kdJabatan = ?1")
    List<UraianTugasJabatanJenisUrtug> findByJabatan(String kdJabatan);

    @Query("select utj from UraianTugasJabatanJenisUrtug utj " +
            "left join fetch utj.uraianTugasJabatan uj " +
            "left join fetch uj.uraianTugas " +
            "left join fetch utj.jenisUrtug " +
            "where utj.uraianTugasJabatanJenisUrtugId.kdJabatan like concat(?1,'%') " +
            "and utj.uraianTugasJabatanJenisUrtugId.kdJenisUrtug = ?2")
    List<UraianTugasJabatanJenisUrtug> findUrtugNonDpaByUnitKerja(String kdUnitKerja, String nonDpa);

    UraianTugasJabatanJenisUrtug findByUraianTugasJabatanJenisUrtugId(UraianTugasJabatanJenisUrtugId urtugId);
    void deleteByUraianTugasJabatanJenisUrtugId(UraianTugasJabatanJenisUrtugId urtugId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UraianTugasJabatanJenisUrtug u " +
            "where u.uraianTugasJabatanJenisUrtugId.kdUrtug = ?1 " +
            "and u.uraianTugasJabatanJenisUrtugId.kdJabatan = ?2 " +
            "and u.uraianTugasJabatanJenisUrtugId.kdJenisUrtug = ?3 " +
            "and u.uraianTugasJabatanJenisUrtugId.tahunUrtug = ?4")
    void deleteData(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug);
}
