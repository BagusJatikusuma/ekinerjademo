package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerjaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@Repository
public interface RincianEKinerjaDao extends JpaRepository<RincianEKinerja, Long> {
    RincianEKinerja findByRincianEKinerjaId(RincianEKinerjaId rincianEKinerjaId);
    List<RincianEKinerja> findByAkunPegawai_NipPegawai(String nipPegawai);
}
