package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdJabatanClone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TkdJabatanCloneDao extends JpaRepository<TkdJabatanClone, String> {

    @Query("select tj from TkdJabatanClone tj " +
            "left join fetch tj.kdUnitKerja")
    TkdJabatanClone findOne(TkdJabatanClone tkdJabatanClone);

    @Query("select tj from TkdJabatanClone tj " +
            "join fetch tj.kdUnitKerja")
    List<TkdJabatanClone> findAll();
    @Query("select tj from TkdJabatanClone tj " +
            "left join fetch tj.kdUnitKerja unk " +
            "where unk.kdUnK = ?1")
    List<TkdJabatanClone> findByKdUnitKerja(String kdUnitKerja);
    @Query("select tj from TkdJabatanClone tj " +
            "left join fetch tj.kdUnitKerja " +
            "where tj.kdJabatan = ?1")
    TkdJabatanClone findByKdJabatan(String KdJabatan);

    @Query("select tj from TkdJabatanClone tj " +
            "left join fetch tj.kdUnitKerja " +
            "where tj.eselon = ?1 " +
            "and tj.kdUnitKerja = ?2")
    List<TkdJabatanClone> findByEselonAndKdUnitKerja(String eselon, String kdUnitKerja);

    @Query("select tj from TkdJabatanClone tj " +
            "left join fetch tj.kdUnitKerja unk " +
            "where unk.kdUnK = ?1 and tj.jabatan like concat('camat ','%') ")
    TkdJabatanClone findCamatUnitkerja(String kdUnitKerja);
}
