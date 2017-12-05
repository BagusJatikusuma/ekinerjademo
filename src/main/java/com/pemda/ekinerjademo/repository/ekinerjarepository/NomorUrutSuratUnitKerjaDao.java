package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerjaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 05/12/17.
 */
@Repository
public interface NomorUrutSuratUnitKerjaDao
        extends JpaRepository<NomorUrutSuratUnitKerja, NomorUrutSuratUnitKerjaId> {
    NomorUrutSuratUnitKerja findByNomorUrutSuratUnitKerjaId(NomorUrutSuratUnitKerjaId nomorUrutSuratUnitKerjaId);
}
