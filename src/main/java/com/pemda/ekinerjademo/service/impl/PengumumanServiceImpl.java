package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.Pengumuman;
import com.pemda.ekinerjademo.repository.ekinerjarepository.PengumumanDao;
import com.pemda.ekinerjademo.service.PengumumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("PengumumanService")
@Transactional("ekinerjaTransactionManager")
public class PengumumanServiceImpl implements PengumumanService {
    @Autowired private PengumumanDao pengumumanDao;

    @Override
    public List<Pengumuman> getByKdUnitKerja(String kdUnitKerja) {
        return pengumumanDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<Pengumuman> getByPembuat(String nipPembuat) {
        return pengumumanDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<Pengumuman> getByNomorTahun(Integer nomorTahun) {
        return pengumumanDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public Pengumuman getByKdPengumuman(String kdPengumuman) {
        return pengumumanDao.findByKdPengumuman(kdPengumuman);
    }

    @Override
    public void create(Pengumuman pengumuman) {
        pengumumanDao.save(pengumuman);
    }

    @Override
    public void approvePengumuman(String kdPengumuman) {
        Pengumuman pengumumanLast = pengumumanDao.findOne(kdPengumuman);
        pengumumanLast.setStatusPenyebaran(1);
        pengumumanLast.setApprovalPenandatangan(1);

        String penilaianTree = pengumumanLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<Pengumuman> pengumumanList
                    = pengumumanDao.findBylastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (Pengumuman pengumuman
                    : pengumumanList) {
                pengumuman.setApprovalPenandatangan(1);
            }
        }

    }
}
