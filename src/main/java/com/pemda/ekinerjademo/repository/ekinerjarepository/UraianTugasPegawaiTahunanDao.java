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
            "left join fetch utpt.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utj.uraianTugas " +
            "where utpt.uraianTugasPegawaiTahunanId.kdJabatan like concat(?1,'%')")
    List<UraianTugasPegawaiTahunan> findByUnitKerja(String kdUnitKerja);

    //salah, uraian tugas yang diajukan memiliki status approval bernilai 0 sedangkan yang ditolak bernilai 3
    @Query("select utpt from UraianTugasPegawaiTahunan utpt " +
            "left join fetch utpt.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utj.uraianTugas " +
            "where utpt.uraianTugasPegawaiTahunanId.nipPegawai = ?1 and utpt.statusApproval = 1 ")
    List<UraianTugasPegawaiTahunan> findUraianTugasPegawaiYangDiAjukan(String nipPegawai);

    @Query("select utpt from UraianTugasPegawaiTahunan utpt "+
            "where utpt.uraianTugasPegawaiTahunanId.kdUrtug = ?1 " +
            "and utpt.uraianTugasPegawaiTahunanId.kdJabatan = ?2 " +
            "and utpt.uraianTugasPegawaiTahunanId.kdJenisUrtug = ?3 " +
            "and utpt.uraianTugasPegawaiTahunanId.tahunUrtug = ?4")
    List<UraianTugasPegawaiTahunan> findByUrtugJabatanJenis(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug);

    void deleteByUraianTugasPegawaiTahunanId(UraianTugasPegawaiTahunanId urtugPegawaiTahunanId);

}
