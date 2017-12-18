package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.controller.api.SuratInstruksiController;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.*;
import com.pemda.ekinerjademo.service.SuratInstruksiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
@Service("SuratInstruksiService")
@Transactional("ekinerjaTransactionManager")
public class SuratInstruksiServiceImpl implements SuratInstruksiService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratInstruksiServiceImpl.class);

    @Autowired private SuratInstruksiDao suratInstruksiDao;
    @Autowired private InstruksiPejabatDao instruksiPejabatDao;
    @Autowired private InstruksiPegawaiDao instruksiPegawaiDao;
    @Autowired private SuratInstruksiPejabatDao suratInstruksiPejabatDao;
    @Autowired private SuratInstruksinonPejabatDao suratInstruksinonPejabatDao;

    @Override
    public void createSuratInstruksi(SuratInstruksi suratInstruksi) {
        suratInstruksiDao.save(suratInstruksi);
    }

    @Override
    public void createInstruksiPegawai(InstruksiPegawai instruksiPegawai) {
        instruksiPegawaiDao.save(instruksiPegawai);
    }

    @Override
    public void createInstruksiPejabat(InstruksiPejabat instruksiPejabat) {
        instruksiPejabatDao.save(instruksiPejabat);
    }

    @Override
    public void createSuratInstruksiPejabat(SuratInstruksiPejabat suratInstruksiPejabat) {
        suratInstruksiPejabatDao.save(suratInstruksiPejabat);
    }

    @Override
    public void createSuratInstruksiNonPejabat(SuratInstruksiNonPejabat suratInstruksiNonPejabat) {
        suratInstruksinonPejabatDao.save(suratInstruksiNonPejabat);
    }

    @Override
    public void openSuratInstruksi(String kdSuratInstruksi) {
        SuratInstruksi suratInstruksi
                = suratInstruksiDao.findOne(kdSuratInstruksi);

        suratInstruksi.setStatusBaca(1);
    }

    @Override
    public SuratInstruksi getSuratInstruksi(String kdSuratInstruksi) {
        return suratInstruksiDao.findOne(kdSuratInstruksi);
    }

    @Override
    public SuratInstruksi getDokumenSuratInstruksi(String kdSuratInstruksi) {
        return suratInstruksiDao.findByKdInstruksi(kdSuratInstruksi);
    }

    @Override
    public List<InstruksiPegawai> getInstruksiPegawai(String nipPegawai) {
        return instruksiPegawaiDao.findInstruksiPegawaiByPegawai(nipPegawai);
    }

    @Override
    public List<InstruksiPejabat> getInstruksiPejabat(String kdJabatan) {
        return instruksiPejabatDao.findInstruksiPejabatByPejabat(kdJabatan);
    }

    @Override
    public List<SuratInstruksiPejabat> getSuratInstruksiPejabat(String kdJabatan) {
        return suratInstruksiPejabatDao.findSuratInstruksiPejabatByPejabat(kdJabatan);
    }

    @Override
    public List<SuratInstruksiNonPejabat> getSuratInstruksiUnitKerja(String kdUnitKerja) {
        return suratInstruksinonPejabatDao.findSuratInstruksiNonPejabatByUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratInstruksi> getSuratInstruksiByNip(String nipPegawai) {
        return suratInstruksiDao.findByNipPembuat(nipPegawai);
    }
}
