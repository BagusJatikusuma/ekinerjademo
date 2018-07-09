package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.NodinTemplateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 04/11/17.
 */
@Repository
public interface NodinTemplateHistoryDao extends JpaRepository<NodinTemplateHistory, String> {
    NodinTemplateHistory findByKdHistory(String kdHistory);
    List<NodinTemplateHistory> findByNipPegawai(String nipPegawai);
    List<NodinTemplateHistory> findByKdUnitKerja(String kdUnitKerja);
    List<NodinTemplateHistory> findByKdUnitKerjaAndNomorSuratTahun(String kdUnitKerja, Integer nomorSuratTahun);
    void deleteByKdHistory(String kdHistory);
}
