package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.repository.bismarepository.QutPegawaiDao;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@Service("QutPegawaiService")
@Transactional("bismaTransactionManager")
public class QutPegawaiServiceImpl implements QutPegawaiService {
    @Autowired private QutPegawaiDao qutPegawaiDao;

    @Override
    public QutPegawai getQutPegawai(String nip) {
        return qutPegawaiDao.findByNip(nip);
    }

    @Override
    public List<QutPegawai> getQutPegawai() {
        return qutPegawaiDao.findAll();
    }

    @Override
    public List<QutPegawai> getQutPegawaiByUnitKerja(String kdUnitKerja) {
        return qutPegawaiDao.findByKdUnitKerja(kdUnitKerja);
    }

}
