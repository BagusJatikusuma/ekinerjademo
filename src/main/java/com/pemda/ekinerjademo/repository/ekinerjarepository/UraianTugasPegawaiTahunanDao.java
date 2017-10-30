package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 14/10/17.
 */
@Repository
public interface UraianTugasPegawaiTahunanDao extends JpaRepository<UraianTugasPegawaiTahunan, UraianTugasPegawaiTahunanId>{
    @Query("select utpt from UraianTugasPegawaiTahunan utpt " +
            "left join fetch utpt.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utj.uraianTugas " +
            "where utpt.uraianTugasPegawaiTahunanId.nipPegawai = ?1")
    List<UraianTugasPegawaiTahunan> findByUraianTugasPegawaiTahunanId_NipPegawai(String nipPegawai);

    @Query("select utpt from UraianTugasPegawaiTahunan utpt " +
            "where utpt.uraianTugasPegawaiTahunanId.kdJabatan like concat(?1,'%')")
    List<UraianTugasPegawaiTahunan> findByUnitKerja(String kdUnitKerja);

    void deleteByUraianTugasPegawaiTahunanId(UraianTugasPegawaiTahunanId urtugPegawaiTahunanId);
}
