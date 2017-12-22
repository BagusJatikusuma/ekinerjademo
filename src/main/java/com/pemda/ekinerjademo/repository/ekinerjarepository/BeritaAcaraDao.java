package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface BeritaAcaraDao extends JpaRepository<BeritaAcara, String> {
    List<BeritaAcara> findByNipPembuatSurat(String nipPembuatSurat);
}
