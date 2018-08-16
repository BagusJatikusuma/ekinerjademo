package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulananId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KegiatanPegawaiBulananDao
        extends JpaRepository<KegiatanPegawaiBulanan, KegiatanPegawaiBulananId>{
    @Query("select kpb from KegiatanPegawaiBulanan kpb " +
            "left join fetch kpb.penanggungJawabKegiatan pjk " +
            "where kpb.kegiatanPegawaiBulananId.nipPegawai = ?1 " +
            "and kpb.kegiatanPegawaiBulananId.bulanPengerjaan = ?2 " +
            "and kpb.kegiatanPegawaiBulananId.tahun = ?3 ")
    List<KegiatanPegawaiBulanan> findByNipBulanTahun(String nipPegawai, Integer bulan, Integer tahun);

    @Query("select kpb from KegiatanPegawaiBulanan kpb " +
            "left join fetch kpb.penanggungJawabKegiatan pjk " +
            "where kpb.kegiatanPegawaiBulananId.kdUrusan = ?1 " +
            "and kpb.kegiatanPegawaiBulananId.kdBidang = ?2 " +
            "and kpb.kegiatanPegawaiBulananId.kdUnit = ?3 " +
            "and kpb.kegiatanPegawaiBulananId.bulanPengerjaan = ?4 " +
            "and kpb.kegiatanPegawaiBulananId.tahun = ?5 ")
    List<KegiatanPegawaiBulanan> findByUnitKerjaBulanTahun(
            Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer bulanPengerjaan, Integer tahun);

    @Query("select kpb from KegiatanPegawaiBulanan kpb " +
            "left join fetch kpb.penanggungJawabKegiatan pjk " +
            "where kpb.kegiatanPegawaiBulananId.kdUrusan = ?1 " +
            "and kpb.kegiatanPegawaiBulananId.kdBidang = ?2 " +
            "and kpb.kegiatanPegawaiBulananId.kdUnit = ?3 " +
            "and kpb.kegiatanPegawaiBulananId.kdSub = ?4 " +
            "and kpb.kegiatanPegawaiBulananId.tahun = ?5 " +
            "and kpb.kegiatanPegawaiBulananId.kdProg = ?6 " +
            "and kpb.kegiatanPegawaiBulananId.idProg = ?7 " +
            "and kpb.kegiatanPegawaiBulananId.kdKeg = ?8 " +
            "and kpb.kegiatanPegawaiBulananId.bulanPengerjaan = ?9 ")
    List<KegiatanPegawaiBulanan> findKegiatanByBulanTahun(Integer kdUrusan,
                                                          Integer kdBidang,
                                                          Integer kdUnit,
                                                          Integer kdSub,
                                                          Integer tahun,
                                                          Integer kdProg,
                                                          Integer idProg,
                                                          Integer kdKeg,
                                                          Integer bulanPengerjaan);


}
