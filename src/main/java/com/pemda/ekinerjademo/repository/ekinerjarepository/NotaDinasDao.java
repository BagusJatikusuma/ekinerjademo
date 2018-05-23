package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.NotaDinas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface NotaDinasDao extends JpaRepository<NotaDinas, String> {
    @Query("select distinct nd from NotaDinas nd " +
            "left join fetch nd.tembusanNotaDinasList " +
            "where nd.kdUnitKerja = ?1")
    List<NotaDinas> findByKdUnitKerja(String kdUnitKerja);

    @Query("select distinct nd from NotaDinas nd " +
            "left join fetch nd.tembusanNotaDinasList " +
            "where nd.kdUnitKerja = ?1 " +
            "and nd.approvalSekretaris = 1")
    List<NotaDinas> findBySekretarisApproval(String kdUnitKerja);

    @Query("select distinct nd from NotaDinas nd " +
            "left join fetch nd.tembusanNotaDinasList " +
            "where nd.nipPembuatSurat = ?1")
    List<NotaDinas> findByNipPembuatSurat(String nipPmebuatSurat);
    @Query("select distinct nd from NotaDinas nd " +
            "left join fetch nd.tembusanNotaDinasList " +
            "where nd.nomorTahun = ?1")
    List<NotaDinas> findByNomorTahun(Integer nomorTahun);

    @Query("select distinct nd from NotaDinas nd " +
            "left join fetch nd.tembusanNotaDinasList " +
            "where nd.kdJabatanPenerimaNotaDinas = ?1")
    List<NotaDinas> findByKdJabatanPenerimaNotaDinas(String kdJabatanPenerimaNotaDinas);

    @Query("select nd from NotaDinas nd " +
            "left join fetch nd.tembusanNotaDinasList " +
            "where nd.kdNotaDinas = ?1")
    NotaDinas findByKdNotaDinas(String kdNotaDinas);

    @Query("select nd from NotaDinas nd " +
            "where nd.pathPenilaian like concat(?1,'%')")
    List<NotaDinas> findByLastTree(String kdNotaDinasRoot);

}
