package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;
import com.pemda.ekinerjademo.repository.ekinerjarepository.RekapitulasiPerilakuKerjaPegawaiDao;
import com.pemda.ekinerjademo.service.RekapitulasiPerilakuKerjaPegawaiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/07/18.
 */

@Service("RekapitulasiPerilakuKerjaPegawaiService")
@Transactional("ekinerjaTransactionManager")
public class RekapitulasiPerilakuKerjaPegawaiImpl implements RekapitulasiPerilakuKerjaPegawaiService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RekapitulasiPerilakuKerjaPegawaiImpl.class);

    @Autowired
    private RekapitulasiPerilakuKerjaPegawaiDao rekapitulasiPerilakuKerjaPegawaiDao;

    @Override
    public void createRekaptulasiPerilakuKerjaPegawai(RekapitulasiPerilakuKerjaPegawai rekapitulasiPerilakuKerjaPegawai) {
        rekapitulasiPerilakuKerjaPegawaiDao.save(rekapitulasiPerilakuKerjaPegawai);
    }

    @Override
    public List<RekapitulasiPerilakuKerjaPegawai> getRekaptulasiPerilakuKerjaPegawai(String kdUnitKerja, long bulanTahunRekapitulasi) {
        return rekapitulasiPerilakuKerjaPegawaiDao.findByRekapitulasiPerilakuKerjaPegawaiId_BulanTahunRekapulasiAndKdUnitKerja(bulanTahunRekapitulasi, kdUnitKerja);
    }
}
