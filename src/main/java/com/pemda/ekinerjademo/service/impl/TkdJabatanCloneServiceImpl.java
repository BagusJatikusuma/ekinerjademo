package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdJabatanClone;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdUnkClone;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdJabatanCloneDao;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("TkdJabatanCloneService")
@Transactional("ekinerjaTransactionManager")
public class TkdJabatanCloneServiceImpl implements TkdJabatanService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TkdJabatanCloneServiceImpl.class);

    @Autowired private TkdJabatanCloneDao tkdJabatanCloneDao;

    @Override
    public TkdJabatan getTkdJabatan(String kdJabatan) {
        return convertFromCLonetoOriginal(tkdJabatanCloneDao.findByKdJabatan(kdJabatan));
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
        return convertFromCLonetoOriginal(tkdJabatanCloneDao.findAll());
    }

    @Override
    public List<TkdJabatanClone> getAllClone() {
        return tkdJabatanCloneDao.findAll();
    }

    @Override
    public List<TkdJabatan> getJabatanByUnitKerja(String kdUnitKerja) {
        return convertFromCLonetoOriginal(tkdJabatanCloneDao.findByKdUnitKerja(kdUnitKerja));
    }

    @Override
    public List<TkdJabatan> getJabatanByEselonAndUnitKerja(String eselon, String kdUnitKerja) {
        return convertFromCLonetoOriginal(tkdJabatanCloneDao.findByEselonAndKdUnitKerja(eselon, kdUnitKerja));
    }

    @Override
    public TkdJabatan getCamatUnitKerja(String kdUnitKerja) {
        return convertFromCLonetoOriginal(tkdJabatanCloneDao.findCamatUnitkerja(kdUnitKerja));
    }

    @Override
    public void createClone(TkdJabatanClone tkdJabatanClone) {
        tkdJabatanCloneDao.save(tkdJabatanClone);
    }

    private TkdJabatan convertFromCLonetoOriginal(TkdJabatanClone obj) {
        TkdJabatan tkdJabatan = new TkdJabatan();

        tkdJabatan.setKdJabatan(obj.getKdJabatan());
        tkdJabatan.setJabatan(obj.getJabatan());
        tkdJabatan.setUnitKerja(convertFromUnkClonetoOriginal(obj.getKdUnitKerja()).getUnitKerja());
        tkdJabatan.setEselon(obj.getEselon());
        tkdJabatan.setPada(obj.getPada());
        tkdJabatan.setUnitKerja(obj.getUnitKerja());
        tkdJabatan.setTnjJabatan(obj.getTnjJabatan());
        tkdJabatan.setTnjJabatanLm(obj.getTnjJabatanLm());
        tkdJabatan.setKdUnitKerja(convertFromUnkClonetoOriginal(obj.getKdUnitKerja()));
        tkdJabatan.setKet(obj.getKet());

        return tkdJabatan;
    }

    private List<TkdJabatan> convertFromCLonetoOriginal(List<TkdJabatanClone> objList) {
        List<TkdJabatan> tkdJabatans = new ArrayList<>();

        for (TkdJabatanClone obj : objList) {
            tkdJabatans.add(convertFromCLonetoOriginal(obj));
        }

        return tkdJabatans;
    }

    private TkdUnk convertFromUnkClonetoOriginal(TkdUnkClone obj) {
        TkdUnk tkdUnk = new TkdUnk();

        tkdUnk.setKdUnK(obj.getKdUnK());
        tkdUnk.setUnitKerja(obj.getUnitKerja());
        tkdUnk.setAlamatUnitKerja(obj.getAlamatUnitKerja());
        tkdUnk.setTeleponUnK(obj.getTeleponUnK());
        tkdUnk.setFaxUnk(obj.getFaxUnk());
        tkdUnk.setTypeSKPD(obj.getTypeSKPD());

        return tkdUnk;
    }
}
