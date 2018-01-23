package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface SuratKuasaDao extends JpaRepository<SuratKuasa, String> {
    List<SuratKuasa> findByNipPembuatSurat(String nipPembuatSurat);
    List<SuratKuasa> findByNipPenerimaKuasa(String nipPenerimaKuasa);
}
