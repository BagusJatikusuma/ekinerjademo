package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.NotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinasId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.NotaDinasDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanNotaDinasDao;
import com.pemda.ekinerjademo.service.NotaDinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
@Service("NotaDinasService")
@Transactional("ekinerjaTransactionManager")
public class NotaDinasServiceImpl implements NotaDinasService {
    @Autowired private NotaDinasDao notaDinasDao;
    @Autowired private TembusanNotaDinasDao tembusanNotaDinasDao;

    @Override
    public List<NotaDinas> getByKdUnitKerja(String kdUnitKerja) {
        return notaDinasDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<NotaDinas> getByNipPembuat(String nipPembuat) {
        return notaDinasDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<NotaDinas> getByByNomorTahun(Integer nomorTahun) {
        return notaDinasDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<TembusanNotaDinas> getTembusanNotaDinasByJabatan(String kdJabatan) {
        return tembusanNotaDinasDao.findByTembusanNotaDinasId_KdJabatan(kdJabatan);
    }

    @Override
    public List<NotaDinas> getNotaDinasByJabatanPenerima(String kdJabatan) {
        return notaDinasDao.findByKdJabatanPenerimaNotaDinas(kdJabatan);
    }

    @Override
    public NotaDinas findBykdNotaDinas(String kdNotaDinas) {
        return notaDinasDao.findByKdNotaDinas(kdNotaDinas);
    }

    @Override
    public void create(NotaDinas notaDinas) {
        notaDinasDao.save(notaDinas);
    }

    @Override
    public void createTembusanNotaDinas(TembusanNotaDinas tembusanNotaDinas) {
        tembusanNotaDinasDao.save(tembusanNotaDinas);
    }

    @Override
    public void openNotaDinas(String kdNotaDinas) {
        NotaDinas notaDinas
                = findBykdNotaDinas(kdNotaDinas);
        notaDinas.setStatusBaca(1);
    }

    @Override
    public void openTembusanNotaDinas(TembusanNotaDinasId id) {
        TembusanNotaDinas tembusanNotaDinas
                = tembusanNotaDinasDao.findOne(id);
        tembusanNotaDinas.setStatusBaca(1);
    }

    @Override
    public void openNotaDinasByPenilai(String kdNotaDinas) {
        NotaDinas notaDinas
                = findBykdNotaDinas(kdNotaDinas);

        notaDinas.setStatusPenilaian(1);
    }

    @Override
    public void approveNotaDinas(String kdNotaDinas) {
        NotaDinas notaDinasLast = notaDinasDao.findByKdNotaDinas(kdNotaDinas);
        notaDinasLast.setStatusPenyebaran(1);
        notaDinasLast.setApprovalPenandatangan(1);

        String penilaianTree = notaDinasLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<NotaDinas> notaDinasList
                    = notaDinasDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));

            for (NotaDinas notaDinas
                    : notaDinasList) {
                notaDinas.setApprovalPenandatangan(1);
            }
        }
    }
}
