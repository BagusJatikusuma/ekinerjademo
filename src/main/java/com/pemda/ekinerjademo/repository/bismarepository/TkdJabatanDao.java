package com.pemda.ekinerjademo.repository.bismarepository;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 07/09/17.
 */
@Repository
public interface TkdJabatanDao extends JpaRepository<TkdJabatan, Long> {
    List<TkdJabatan> findAll();
    TkdJabatan findByKdJabatan(String KdJabatan);
}
