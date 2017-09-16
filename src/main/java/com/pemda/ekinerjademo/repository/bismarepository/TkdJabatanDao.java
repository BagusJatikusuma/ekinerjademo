package com.pemda.ekinerjademo.repository.bismarepository;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.wrapper.BismaModelWrapper.KdNmJabatanProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 07/09/17.
 */
@Repository
public interface TkdJabatanDao extends JpaRepository<TkdJabatan, Long> {
    List<TkdJabatan> findAll();
    List<TkdJabatan> findByKdUnitKerja(String kdUnitKerja);
    TkdJabatan findByKdJabatan(String KdJabatan);
}
