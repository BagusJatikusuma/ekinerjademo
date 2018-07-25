package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UraianTugasPegawaiBulananDao
        extends JpaRepository<UraianTugasPegawaiBulanan, UraianTugasPegawaiBulananId>{

   @Query("select utpb from UraianTugasPegawaiBulanan utpb "+
           "left join fetch utpb.uraianTugasJabatanJenisUrtug utjj " +
           "left join fetch utjj.uraianTugasJabatan utj " +
           "left join fetch utj.uraianTugas " +
           "where utpb.uraianTugasPegawaiBulananId.nipPegawai = ?1")
   List<UraianTugasPegawaiBulanan> findByNip(String nip);

   @Query("select utpb from UraianTugasPegawaiBulanan utpb "+
           "left join fetch utpb.uraianTugasJabatanJenisUrtug utjj " +
           "left join fetch utjj.uraianTugasJabatan utj " +
           "left join fetch utj.uraianTugas " +
           "where utpb.uraianTugasPegawaiBulananId.nipPegawai = ?1 " +
           "and utpb.uraianTugasPegawaiBulananId.bulanUrtug = ?2")
   List<UraianTugasPegawaiBulanan> findByNipAndBulan(String nip, Integer bulan);

   @Query("select utpb from UraianTugasPegawaiBulanan utpb " +
           "left join fetch utpb.uraianTugasJabatanJenisUrtug utjj " +
           "left join fetch utjj.uraianTugasJabatan utj " +
           "left join fetch utj.uraianTugas " +
           "where utpb.uraianTugasPegawaiBulananId.kdJabatan like concat(?1,'%')")
   List<UraianTugasPegawaiBulanan> findByUnitKerja(String kdUnitKerja);

   @Query("select utpb from UraianTugasPegawaiBulanan utpb " +
           "left join fetch utpb.uraianTugasJabatanJenisUrtug utjj " +
           "left join fetch utjj.uraianTugasJabatan utj " +
           "left join fetch utj.uraianTugas " +
           "where utpb.uraianTugasPegawaiBulananId.kdJabatan like concat(?1,'%') " +
           "and utpb.uraianTugasPegawaiBulananId.bulanUrtug = ?2")
   List<UraianTugasPegawaiBulanan> findByUnitKerjaAndBulan(String kdUnitKerja, Integer bulan);

   @Query("select utpb from UraianTugasPegawaiBulanan utpb " +
           "left join fetch utpb.uraianTugasJabatanJenisUrtug utjj " +
           "left join fetch utjj.uraianTugasJabatan utj " +
           "left join fetch utj.uraianTugas " +
           "where utpb.uraianTugasPegawaiBulananId.nipPegawai = ?1 and utpb.statusApproval = 1 ")
   List<UraianTugasPegawaiBulanan> findAjuanUraianTugasPegawai(String nipPegawai);

   @Query("select utpb from UraianTugasPegawaiBulanan utpb " +
           "left join fetch utpb.uraianTugasJabatanJenisUrtug utjj " +
           "left join fetch utjj.uraianTugasJabatan utj " +
           "left join fetch utj.uraianTugas " +
           "where utpb.uraianTugasPegawaiBulananId.nipPegawai = ?1 " +
           "and utpb.uraianTugasPegawaiBulananId.bulanUrtug = ?2 " +
           "and utpb.uraianTugasPegawaiBulananId.tahunUrtug = ?3 ")
   List<UraianTugasPegawaiBulanan> findByNipBulanTahun(String nip, int bulan, int tahun);

   @Override
   void delete(UraianTugasPegawaiBulananId uraianTugasPegawaiBulananId);
}
