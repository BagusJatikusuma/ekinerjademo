package com.pemda.ekinerjademo.repository.bismarepository;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 09/09/17.
 */
@Repository
public interface QutPegawaiDao extends JpaRepository<QutPegawai, Long>{
    QutPegawai findByNip(String nip);
}
