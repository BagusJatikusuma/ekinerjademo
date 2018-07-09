package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerjaId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.RincianEKinerjaDao;
import com.pemda.ekinerjademo.service.EKinerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@Service("EkinerjaService")
@Transactional("ekinerjaTransactionManager")
public class EKinerjaServiceImpl implements EKinerjaService {
//    @Autowired private RincianEKinerjaDao rincianEKinerjaDao;
//
//    @Override
//    public void save(RincianEKinerja rincianEKinerja) {
//        rincianEKinerjaDao.save(rincianEKinerja);
//    }
//
//    @Override
//    public RincianEKinerja getRincianEKinerjaById(RincianEKinerjaId rincianEKinerjaId) {
//        return rincianEKinerjaDao.findByRincianEKinerjaId(rincianEKinerjaId);
//    }
//
//    @Override
//    public RincianEKinerja getRincianEKinerja(String kdUrtug, String nipPegawai) {
//        return null;
//    }
//
//    @Override
//    public List<RincianEKinerja> getRincianEKinerjaByUrtug(String kdUrtug) {
//        return null;
//    }
//
//    @Override
//    public List<RincianEKinerja> getRincianEKinerjaByNip(String nipPegawai) {
//        return rincianEKinerjaDao.findByAkunPegawai_NipPegawai(nipPegawai);
//    }
//
//    @Override
//    public List<RincianEKinerja> getRincianEKinerjaByNipAndDate(String nipPegawai, Date date) {
//        return rincianEKinerjaDao
//                .findByRincianEKinerjaId_NipPegawaiAndRincianEKinerjaId_TglSubmit(nipPegawai, date);
//    }
}
