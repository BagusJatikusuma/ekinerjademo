package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratDinas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratDinasDao extends JpaRepository<SuratDinas, String> {
    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdUnitKerja = ?1")
    List<SuratDinas> findByKdUnitKerja(String kdUnitKerja);

    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdUnitKerja = ?1 " +
            "and sd.approvalSekretaris = 1")
    List<SuratDinas> findBySekretarisApproval(String kdUnitKerja);

    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdUnitKerja = ?1 " +
            "and sd.approvalPenandatangan = 1")
    List<SuratDinas> findByApproval(String kdUnitKerja);

    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdUnitKerjaTarget = ?1")
    List<SuratDinas> findByKdUnitKerjaTarget(String kdUnitKerjaTarget);

    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.nipPembuatSurat = ?1")
    List<SuratDinas> findByNipPembuatSurat(String nipPembuatSurat);

    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.nomorTahun = ?1")
    List<SuratDinas> findByNomorTahun(Integer nomorTahun);

    @Query("select distinct sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdJabatanPenerimaSuratDinas = ?1")
    List<SuratDinas> findByKdJabatanPenerimaSuratDinas(String kdJabatanPenerimaSuratDinas);

    @Query("select sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdSuratDinas = ?1")
    SuratDinas findByKdSuratDinas(String kdSuratDinas);

    @Query("select sd from SuratDinas sd " +
            "where sd.pathPenilaian like concat(?1,'%')")
    List<SuratDinas> findByLastTree(String kdSuratDinasRoot);
}
