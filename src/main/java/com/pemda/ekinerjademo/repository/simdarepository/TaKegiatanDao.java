package com.pemda.ekinerjademo.repository.simdarepository;

import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
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
}
