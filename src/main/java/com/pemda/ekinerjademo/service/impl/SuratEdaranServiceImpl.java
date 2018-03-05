package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratEdaranDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratEdaranNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratEdaranPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratEdaranSubDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import com.pemda.ekinerjademo.service.SuratEdaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratEdaranService")
@Transactional("ekinerjaTransactionManager")
public class SuratEdaranServiceImpl implements SuratEdaranService {
    @Autowired private SuratEdaranDao suratEdaranDao;
    @Autowired private SuratEdaranNonPejabatDao suratEdaranNonPejabatDao;
    @Autowired private SuratEdaranPejabatDao suratEdaranPejabatDao;
    @Autowired private SuratEdaranSubDao suratEdaranSubDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @Override
    public List<SuratEdaran> getByKdUnitKerja(String kdUnitKerja) {
        return suratEdaranDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratEdaran> getByNipPembuat(String nipPembuat) {
        return suratEdaranDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratEdaran> getByNomorTahun(Integer nomorTahun) {
        return suratEdaranDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public SuratEdaran getByKdSuratEdaran(String kdSuratEdaran) {
        return suratEdaranDao.findByKdSuratEdaran(kdSuratEdaran);
    }

    @Override
    public void create(SuratEdaran suratEdaran) {
        suratEdaranDao.save(suratEdaran);
    }

    @Override
    public void createSuratEdaranNonPejabat(SuratEdaranNonPejabat suratEdaranNonPejabat) {
        suratEdaranNonPejabatDao.save(suratEdaranNonPejabat);
    }

    @Override
    public void createSuratEdaranPejabat(SuratEdaranPejabat suratEdaranPejabat) {
        suratEdaranPejabatDao.save(suratEdaranPejabat);
    }

    @Override
    public void createSuratEdaranSub(SuratEdaranSub suratEdaranSub) {
        suratEdaranSubDao.save(suratEdaranSub);
    }

    @Override
    public void approveSuratEdaran(String kdSuratEdaran) {
        SuratEdaran suratEdaranLast = suratEdaranDao.findOne(kdSuratEdaran);
        suratEdaranLast.setStatusPenyebaran(1);
        suratEdaranLast.setApprovalPenandatangan(1);

        NomorUrutSuratUnitKerja nomorUrutSurat
                = nomorUrutSuratUnitKerjaService
                .getNomorSuratByUnitKerjaAndTahun(
                        suratEdaranLast.getKdUnitKerja(),
                        Year.now().getValue());

        if (nomorUrutSurat == null) {
            nomorUrutSurat
                    = new NomorUrutSuratUnitKerja();
            nomorUrutSurat
                    .setNomorUrutSuratUnitKerjaId(
                            new NomorUrutSuratUnitKerjaId(suratEdaranLast.getKdUnitKerja(), Year.now().getValue()));
            nomorUrutSurat.setNomorUrutSurat(1);

            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
        } else {
            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);

            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
        }
        suratEdaranLast.setNomorUrut(nomorUrutSurat.getNomorUrutSurat());
        String kdBarcode
                = suratEdaranLast.getKdSuratEdaran()
                + suratEdaranLast.getNomorUrut()
                + suratEdaranLast.getKdUnitKerja()
                + "6";
        suratEdaranLast.setKdBarcode(kdBarcode);

        String penilaianTree = suratEdaranLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<SuratEdaran> suratEdaranList
                    = suratEdaranDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (SuratEdaran suratEdaran
                    : suratEdaranList) {
                suratEdaran.setApprovalPenandatangan(1);
            }
        }
    }
}
