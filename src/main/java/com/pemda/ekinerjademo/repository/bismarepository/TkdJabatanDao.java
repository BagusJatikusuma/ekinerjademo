package com.pemda.ekinerjademo.repository.bismarepository;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.wrapper.BismaModelWrapper.KdNmJabatanProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 07/09/17.
 */
@Repository
public interface TkdJabatanDao extends JpaRepository<TkdJabatan, Long> {
    @Query("select tj from TkdJabatan tj " +
            "left join fetch tj.kdUnitKerja")
    List<TkdJabatan> findAll();
    @Query("select tj from TkdJabatan tj " +
            "left join fetch tj.kdUnitKerja unk " +
            "where unk.kdUnK = ?1")
    List<TkdJabatan> findByKdUnitKerja(String kdUnitKerja);
    @Query("select tj from TkdJabatan tj " +
            "left join fetch tj.kdUnitKerja " +
            "where tj.kdJabatan = ?1")
    TkdJabatan findByKdJabatan(String KdJabatan);

    @Query("select tj from TkdJabatan tj " +
            "left join fetch tj.kdUnitKerja " +
            "where tj.eselon = ?1 " +
            "and tj.kdUnitKerja = ?2")
    List<TkdJabatan> findByEselonAndKdUnitKerja(String eselon, String kdUnitKerja);

    @Query("select tj from TkdJabatan tj " +
            "left join fetch tj.kdUnitKerja unk " +
            "where unk.kdUnK = ?1 and tj.jabatan like concat('camat ','%') ")
    TkdJabatan findCamatUnitkerja(String kdUnitKerja);
}
