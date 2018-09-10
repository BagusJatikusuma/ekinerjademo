package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TugasTambahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TugasTambahanDao extends JpaRepository<TugasTambahan, String> {
    List<TugasTambahan> findByNipPegawaiAndBulanAndTahun(String nipPegawai, Short Bulan, Short tahun);
}
