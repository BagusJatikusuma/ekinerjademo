package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bayu on 15/12/17.
 */
public interface TelaahanStafDao extends JpaRepository<TelaahanStaf, String> {
    List<TelaahanStaf> findByNipPembuatSurat(String nipPembuatSurat);

    @Query("select ts from TelaahanStaf ts " +
            "where ts.pathPenilaian like concat('%',?1)")
    List<TelaahanStaf> findByLastTree(String kdTelaahanStaf);
}
