package com.pemda.ekinerjademo.repository.simdarepository;

import com.pemda.ekinerjademo.model.simdamodel.TaProgram;
import com.pemda.ekinerjademo.model.simdamodel.TaProgramId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 11/12/17.
 */
@Repository
public interface TaProgramDao extends JpaRepository<TaProgram, TaProgramId>{
    @Query("select tp from TaProgram tp " +
            "left join fetch tp.taSubUnit taSubUnit "+
            "left join fetch taSubUnit.refSubUnit refSubUnit " +
            "left join fetch refSubUnit.refUnit refUnit " +
            "left join fetch refUnit.refBidang refBidang " +
            "left join fetch refBidang.refUrusan " +
            "where tp.taProgramId.kdUrusan = ?1 " +
            "and tp.taProgramId.kdBIdang = ?2 " +
            "and tp.taProgramId.kdUnit = ?3")
    List<TaProgram> findAllByKdUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit);

    @Query("select tp from TaProgram tp left join fetch tp.taKegiatanList where tp.taProgramId = ?1")
    TaProgram findByTaProgramId(TaProgramId id);
}
