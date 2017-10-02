package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.PasswordAkunPegawaiProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Repository
public interface AkunPegawaiDao extends JpaRepository<AkunPegawai, Long> {
    @Query(value = "select a from AkunPegawai a left join fetch a.role")
    List<AkunPegawai> findAll();
    @Query(value = "select a from AkunPegawai a left join fetch a.role where a.nipPegawai = ?1")
    AkunPegawai findByNipPegawai(String nipPegawai);
    @Modifying @Query(value = "update akun_pegawai a set a.role_id = ?1 where a.nip_pegawai = ?2", nativeQuery = true)
    void updatePegawaiRole(String role, String nipPegawai);

//    //only for testing
//    @Query(value = "SELECT a.password FROM akun_pegawai a WHERE a.nip_pegawai = ?1", nativeQuery = true)
//    PasswordAkunPegawaiProjection findPasswordByNipPegawai(String nipPegawai);

}
