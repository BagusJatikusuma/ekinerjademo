package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.JenisUrtugUrtug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 27/09/17.
 */
@Repository
public interface JenisUrtugUrtugDao extends JpaRepository<JenisUrtugUrtug, Long> {
    @Query(value =  "select j from JenisUrtugUrtug j " +
                    "left join fetch j.jenisUrtug " +
                    "left join fetch j.uraianTugasJabatan urtugJabatan " +
                    "left join fetch urtugJabatan.uraianTugas")
    List<JenisUrtugUrtug> findAll();
    @Query(value =  "select j from JenisUrtugUrtug j " +
                    "left join fetch j.jenisUrtug " +
                    "left join fetch j.uraianTugasJabatan urtugJabatan " +
                    "left join fetch urtugJabatan.uraianTugas " +
                    "where j.jenisUrtugUrtugId.kdJabatan = ?1")
    List<JenisUrtugUrtug> findAllByKdJabatan(String kdJabatan);
}
