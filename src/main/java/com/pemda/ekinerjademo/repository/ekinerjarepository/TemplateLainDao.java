package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TemplateLain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
@Repository
public interface TemplateLainDao extends JpaRepository<TemplateLain, String> {
    @Query("select tl from TemplateLain tl " +
            "where tl.nipPegawai = ?1")
    List<TemplateLain> findByNipPegawai(String nipPegawai);

    @Query("select tl from TemplateLain tl " +
            "where tl.pathPenilaian like concat(?1,'%')")
    List<TemplateLain> findByLastTree(String kdTemplateLain);

    @Query("select tl from TemplateLain tl " +
            "where tl.kdUnitKerja = ?1 " +
            "and tl.approvalSekretaris = 1")
    List<TemplateLain> findBySekretarisApproval(String kdUnitKerja);

    @Query("select tl from TemplateLain tl " +
            "where tl.kdUrtug = ?1 " +
            "and tl.kdJabatan = ?2 " +
            "and tl.tahunUrtug = ?3 " +
            "and tl.kdJenisUrtug = ?4 " +
            "and tl.bulanUrtug = ?5 " +
            "and tl.nipPegawai = ?6")
    List<TemplateLain> findByUrtugNonDpa(String kdUrtug, String kdJabatan, Integer tahunUrtug, String kdJenisUrtug, Integer bulanUrtug, String nipPembuat);
}
