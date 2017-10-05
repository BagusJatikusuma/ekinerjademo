package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.StatusPenanggungJawabKegiatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.StatusPenanggungJawabKegiatanDao;
import com.pemda.ekinerjademo.service.StatusPenanggungJawabKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
@Service("StatusPenanggungJawabKegiatanService")
@Transactional("ekinerjaTransactionManager")
public class StatusPenanggungJawabKegiatanServiceImpl implements StatusPenanggungJawabKegiatanService {
    @Autowired
    private StatusPenanggungJawabKegiatanDao statusPenanggungJawabKegiatanDao;

    @Override
    public List<StatusPenanggungJawabKegiatan> getAll() {
        return statusPenanggungJawabKegiatanDao.findAll();
    }

    @Override
    public StatusPenanggungJawabKegiatan get(String kdStatus) {
        return statusPenanggungJawabKegiatanDao.findByKdStatus(kdStatus);
    }

    @Override
    public void save(StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan) {
        statusPenanggungJawabKegiatanDao.save(statusPenanggungJawabKegiatan);
    }

    @Override
    public void update(StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan) {
        statusPenanggungJawabKegiatanDao.save(statusPenanggungJawabKegiatan);
    }

    @Override
    public void delete(String kdStatus) {
        statusPenanggungJawabKegiatanDao.deleteByKdStatus(kdStatus);
    }

}
