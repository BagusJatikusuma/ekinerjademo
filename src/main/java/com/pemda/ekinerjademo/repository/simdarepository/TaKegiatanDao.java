package com.pemda.ekinerjademo.repository.simdarepository;

import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 29/09/17.
 */
@Repository
public interface TaKegiatanDao extends JpaRepository<TaKegiatan, Long> {
    @Query("select taKegiatan from TaKegiatan taKegiatan " +
            "left join fetch taKegiatan.taProgram taProgram " +
            "left join fetch taProgram.taSubUnit taSubUnit " +
            "left join fetch taSubUnit.refSubUnit refSubUnit " +
            "left join fetch refSubUnit.refUnit refUnit " +
            "left join fetch refUnit.refBidang refBidang " +
            "left join fetch refBidang.refUrusan")
    List<TaKegiatan> findAllByQuery();

    @Query("select taKegiatan from TaKegiatan taKegiatan " +
            "left join fetch taKegiatan.taProgram taProgram " +
            "left join fetch taProgram.taSubUnit taSubUnit " +
            "left join fetch taSubUnit.refSubUnit refSubUnit " +
            "left join fetch refSubUnit.refUnit refUnit " +
            "left join fetch refUnit.refBidang refBidang " +
            "left join fetch refBidang.refUrusan " +
            "where taKegiatan.taKegiatanId.kdUrusan = ?1 " +
            "and taKegiatan.taKegiatanId.kdBIdang = ?2 " +
            "and taKegiatan.taKegiatanId.kdUnit = ?3")
    List<TaKegiatan> findAllByKdUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit);

    @Query("select taKegiatan from TaKegiatan taKegiatan " +
            "left join fetch taKegiatan.taProgram taProgram " +
            "left join fetch taProgram.taSubUnit taSubUnit " +
            "left join fetch taSubUnit.refSubUnit refSubUnit " +
            "left join fetch refSubUnit.refUnit refUnit " +
            "left join fetch refUnit.refBidang refBidang " +
            "left join fetch refBidang.refUrusan " +
            "where taKegiatan.taKegiatanId.kdUrusan = ?1 " +
            "and taKegiatan.taKegiatanId.kdBIdang = ?2 " +
            "and taKegiatan.taKegiatanId.kdUnit = ?3 " +
            "and taKegiatan.taKegiatanId.kdSub = ?4 " +
            "and taKegiatan.taKegiatanId.tahun = ?5 " +
            "and taKegiatan.taKegiatanId.kdProg= ?6 " +
            "and taKegiatan.taKegiatanId.idProg = ?7")
    List<TaKegiatan> findByProgram(Integer kdUrusan,
                                   Integer kdBidang,
                                   Integer kdUnit,
                                   Integer kdSub,
                                   Integer tahun,
                                   Integer kdProg,
                                   Integer idProg);

    TaKegiatan findByTaKegiatanId(TaKegiatanId taKegiatanId);
}
