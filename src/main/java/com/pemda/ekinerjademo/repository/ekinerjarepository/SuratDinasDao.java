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
    @Query("select sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdUnitKerja = ?1")
    List<SuratDinas> findByKdUnitKerja(String kdUnitKerja);

    @Query("select sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.nipPembuatSurat = ?1")
    List<SuratDinas> findByNipPembuatSurat(String nipPembuatSurat);

    @Query("select sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.nomorTahun = ?1")
    List<SuratDinas> findByNomorTahun(Integer nomorTahun);

    @Query("select sd from SuratDinas sd " +
            "left join fetch sd.suratDinasNonPejabat " +
            "left join fetch sd.suratDinasPejabat " +
            "left join fetch sd.tembusanSuratDinasList " +
            "where sd.kdSuratDinas = ?1")
    SuratDinas findByKdSuratDinas(String kdSuratDinas);

}
