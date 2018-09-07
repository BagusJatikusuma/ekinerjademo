package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdJabatanClone;
import com.pemda.ekinerjademo.repository.bismarepository.TkdJabatanDao;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@Service("TkdJabatanService")
@Transactional("bismaTransactionManager")
public class TkdJabatanServiceImpl implements TkdJabatanService {
    @Autowired
    private TkdJabatanDao tkdJabatanDao;

    @Override
    public TkdJabatan getTkdJabatan(String kdJabatan) {
        return tkdJabatanDao.findByKdJabatan(kdJabatan);
    }

    //terdapat sedikit revisi
    @Override
    public List<TkdJabatan> getAll() {
//        List<TkdJabatan> tkdJabatanList = tkdJabatanDao.findAll();
//        List<TkdJabatan> tkdJabatanListResult = new ArrayList<>();
//
//        for (TkdJabatan tkdJabatan : tkdJabatanList) {
//            TkdJabatan newTkdJabatan = tkdJabatan;
//
//            newTkdJabatan.setKdUnitKerja(tkdJabatan.getKdUnitKerja());
//
//            tkdJabatanListResult.add(newTkdJabatan);
//        }
//
//        return tkdJabatanListResult;
        return tkdJabatanDao.findAll();
    }

    @Override
    public List<TkdJabatanClone> getAllClone() {
        return null;
    }

    @Override
    public List<TkdJabatan> getJabatanByUnitKerja(String kdUnitKerja) {
        return tkdJabatanDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<TkdJabatan> getJabatanByEselonAndUnitKerja(String eselon, String kdUnitKerja) {
        return tkdJabatanDao.findByEselonAndKdUnitKerja(eselon, kdUnitKerja);
    }

    @Override
    public TkdJabatan getCamatUnitKerja(String kdUnitKerja) {
        return tkdJabatanDao.findCamatUnitkerja(kdUnitKerja);
    }

    @Override
    public void createClone(TkdJabatanClone tkdJabatanClone) {

    }
}
