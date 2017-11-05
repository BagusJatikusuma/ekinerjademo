package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.NodinTemplateHistory;

import java.util.List;

/**
 * Created by bagus on 04/11/17.
 */
public interface NodinTemplateHistoryService {
    NodinTemplateHistory findByKdHistory(String kdHistory);
    List<NodinTemplateHistory> findByKdUnitKerja(String kdUnitKerja);
    List<NodinTemplateHistory> findByKdUnitKerjaAndNomorSuratTahun(String kdUnitKerja, Integer nomorSuratTahun);
    void deleteByKdHistory(String kdHistory);
    void save(NodinTemplateHistory nodinTemplateHistory);
}
