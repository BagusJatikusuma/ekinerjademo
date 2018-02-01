package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantar;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantarIsi;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratPengantarDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratPengantarIsiDao;
import com.pemda.ekinerjademo.service.SuratPengantarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratPengantarService")
@Transactional("ekinerjaTransactionManager")
public class SuratPengantarServiceImpl implements SuratPengantarService {
    @Autowired private SuratPengantarDao suratPengantarDao;
    @Autowired private SuratPengantarIsiDao suratPengantarIsiDao;

    @Override
    public List<SuratPengantar> getByKdUnitKerja(String kdUnitKerja) {
        return suratPengantarDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratPengantar> getByNipPembuat(String nipPembuat) {
        return suratPengantarDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratPengantar> getByNomorTahun(Integer nomorTahun) {
        return suratPengantarDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<SuratPengantar> getByJabatanTarget(String kdJabatan) {
        return suratPengantarDao.findByKdJabatanTarget(kdJabatan);
    }

    @Override
    public List<SuratPengantar> getByPegawaiTarget(String nipTarget) {
        return suratPengantarDao.findByNipPenerima(nipTarget);
    }

    @Override
    public SuratPengantar getByKdSuratPengantar(String kdSuratPengantar) {
        return suratPengantarDao.findByKdSuratPengantar(kdSuratPengantar);
    }

    @Override
    public void create(SuratPengantar suratPengantar) {
        suratPengantarDao.save(suratPengantar);
    }

    @Override
    public void createSuratPengantarIsi(SuratPengantarIsi suratPengantarIsi) {
        suratPengantarIsiDao.save(suratPengantarIsi);
    }

    @Override
    public void openSuratPengantarPenilai(String kdSuratPengantar) {
        SuratPengantar suratPengantar
                = suratPengantarDao.findByKdSuratPengantar(kdSuratPengantar);
        suratPengantar.setStatusPenilaian(1);
    }

}
