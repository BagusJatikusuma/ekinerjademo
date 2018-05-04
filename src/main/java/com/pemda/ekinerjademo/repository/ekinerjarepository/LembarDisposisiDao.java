package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.LembarDisposisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@Repository
public interface LembarDisposisiDao extends JpaRepository<LembarDisposisi, String> {
    @Query("select l from LembarDisposisi l where l.path LIKE concat(?1,'%') ")
    List<LembarDisposisi> findLembarDisposisiTree(String parentPath);
    List<LembarDisposisi> findByNipPembuat(String nipPembuat);
    @Query("select l from LembarDisposisi l " +
            "left join fetch l.targetLembarDisposisiSet " +
            "where l.kdLembarDisposisi = ?1")
    LembarDisposisi findDokumenLembarDisposisi(String kdLembarDisposisi);
    List<LembarDisposisi> findByPath(String path);

    @Query("select l from LembarDisposisi l " +
            "where l.kdUnitKerja = ?1 " +
            "and l.approvalPenandatangan = 1 " +
            "and l.statusPenyebaran = 0")
    List<LembarDisposisi> findDraftLembarDisposisiApproval(String kdUnitKerja);
}
