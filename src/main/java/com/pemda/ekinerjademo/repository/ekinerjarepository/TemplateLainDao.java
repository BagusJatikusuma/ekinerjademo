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
    List<TemplateLain> findByNipPegawai(String nipPegawai);

    @Query("select tl from TemplateLain tl " +
            "where tl.pathPenilaian like concat('%',?1)")
    List<TemplateLain> findByLastTree(String kdTemplateLain);
}
