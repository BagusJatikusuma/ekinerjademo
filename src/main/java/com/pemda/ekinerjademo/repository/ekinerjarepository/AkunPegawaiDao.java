package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Repository
public interface AkunPegawaiDao extends JpaRepository<AkunPegawai, Long>{
    List<AkunPegawai> findAll();
    AkunPegawai findByNipPegawai(String nipPegawai);
}
