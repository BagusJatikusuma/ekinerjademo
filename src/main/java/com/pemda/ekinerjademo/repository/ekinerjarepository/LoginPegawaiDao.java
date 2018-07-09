package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.LoginPegawai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bayu on 26/12/17.
 */
public interface LoginPegawaiDao extends JpaRepository<LoginPegawai, Long> {
    List<LoginPegawai> findByBulanLoginAndTahunLoginAndNipPegawai(Integer bulanLogin, Integer tahunLogin, String nipPegawai);
}
