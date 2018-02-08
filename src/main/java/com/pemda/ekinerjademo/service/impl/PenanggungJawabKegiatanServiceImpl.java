package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatanId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.PenanggungJawabKegiatanDao;
import com.pemda.ekinerjademo.service.PenanggungJawabKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
@Service("PenanggungJawabKegiatanService")
@Transactional("ekinerjaTransactionManager")
public class PenanggungJawabKegiatanServiceImpl implements PenanggungJawabKegiatanService {
    @Autowired private PenanggungJawabKegiatanDao penanggungJawabKegiatanDao;

    @Override
    public void create(PenanggungJawabKegiatan penanggungJawabKegiatan) {
        penanggungJawabKegiatanDao.save(penanggungJawabKegiatan);
    }

    @Override
    public List<PenanggungJawabKegiatan> getByUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit) {
        return penanggungJawabKegiatanDao.findByUnitKerja(kdUrusan, kdBidang, kdUnit);
    }

    @Override
    public void delete(PenanggungJawabKegiatanId id) {
        penanggungJawabKegiatanDao.delete(id);
    }
}
