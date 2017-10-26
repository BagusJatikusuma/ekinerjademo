package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UrtugKegiatanPegawaiDao;
import com.pemda.ekinerjademo.service.UrtugKegiatanPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 06/10/17.
 */
@Service("UrtugKegiatanPegawaiService")
@Transactional("ekinerjaTransactionManager")
public class UrtugKegiatanPegawaiServiceImpl implements UrtugKegiatanPegawaiService {
    @Autowired
    private UrtugKegiatanPegawaiDao urtugKegiatanPegawaiDao;

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugJabatan(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugJabatan(uraianTugasJabatanJenisUrtugId);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByNipPegawai(String nipPegawai) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByNipPegawai(nipPegawai);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugKegiatan(UrtugKegiatanId urtugKegiatanId) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugKegiatan(urtugKegiatanId);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugJabatanAndNipPegawai(UraianTugasJabatanId uraianTugasJabatanId, String nipPegawai) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugJabatanAndNip(uraianTugasJabatanId, nipPegawai);
    }

    @Override
    public void save(UrtugKegiatanPegawai urtugKegiatanPegawai) {
        urtugKegiatanPegawaiDao.save(urtugKegiatanPegawai);
    }

    @Override
    public void update(UrtugKegiatanPegawai urtugKegiatanPegawai) {
        urtugKegiatanPegawaiDao.save(urtugKegiatanPegawai);
    }

    @Override
    public void delete(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId) {
        urtugKegiatanPegawaiDao.deleteByUrtugKegiatanPegawaiId(urtugKegiatanPegawaiId);
    }
}
