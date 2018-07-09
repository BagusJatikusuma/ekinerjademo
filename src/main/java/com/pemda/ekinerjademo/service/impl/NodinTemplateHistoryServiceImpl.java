package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.NodinTemplateHistory;
import com.pemda.ekinerjademo.repository.ekinerjarepository.NodinTemplateHistoryDao;
import com.pemda.ekinerjademo.service.NodinTemplateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 05/11/17.
 */
@Service("NodinTemplateHistoryService")
@Transactional("ekinerjaTransactionManager")
public class NodinTemplateHistoryServiceImpl implements NodinTemplateHistoryService {
    @Autowired
    private NodinTemplateHistoryDao nodinTemplateHistoryDao;

    @Override
    public NodinTemplateHistory findByKdHistory(String kdHistory) {
        return nodinTemplateHistoryDao.findByKdHistory(kdHistory);
    }

    @Override
    public List<NodinTemplateHistory> findByNipPegawai(String nipPegawai) {
        return nodinTemplateHistoryDao.findByNipPegawai(nipPegawai);
    }

    @Override
    public List<NodinTemplateHistory> findByKdUnitKerja(String kdUnitKerja) {
        return nodinTemplateHistoryDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<NodinTemplateHistory> findByKdUnitKerjaAndNomorSuratTahun(String kdUnitKerja, Integer nomorSuratTahun) {
        return nodinTemplateHistoryDao
                .findByKdUnitKerjaAndNomorSuratTahun(kdUnitKerja, nomorSuratTahun);
    }

    @Override
    public void deleteByKdHistory(String kdHistory) {
        nodinTemplateHistoryDao.deleteByKdHistory(kdHistory);
    }

    @Override
    public void save(NodinTemplateHistory nodinTemplateHistory) {
        nodinTemplateHistoryDao.save(nodinTemplateHistory);
    }
}
