package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasTemplateLain;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasTemplateLainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UraianTugasTemplateLainDao
        extends JpaRepository<UraianTugasTemplateLain, UraianTugasTemplateLainId> {
    @Query("select utpl from UraianTugasTemplateLain utpl " +
            "left join fetch utpl.templateLain")
    List<UraianTugasTemplateLain> findAll();

    @Query("select utpl from UraianTugasTemplateLain utpl " +
            "left join fetch utpl.templateLain " +
            "where utpl.uraianTugasTemplateLainId.kdUrtug = ?1 " +
            "and utpl.uraianTugasTemplateLainId.kdJabatan = ?2 " +
            "and utpl.uraianTugasTemplateLainId.tahunUrtug = ?3 " +
            "and utpl.uraianTugasTemplateLainId.kdJenisUrtug = ?4 " +
            "and utpl.uraianTugasTemplateLainId.bulanUrtug = ?5 " +
            "and utpl.uraianTugasTemplateLainId.nipPegawai = ?6")
    List<UraianTugasTemplateLain> findByUrtug(
            String kdUrtug,
            String kdJabatan,
            Integer tahunUrtug,
            String kdJenisUrtug,
            Integer bulanUrtug,
            String nipPegawai);
}
