package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bayu on 15/12/17.
 */
public interface TelaahanStafDao extends JpaRepository<TelaahanStaf, String> {
    List<TelaahanStaf> findByNipPembuatSurat(String nipPembuatSurat);
}
