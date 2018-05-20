package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.MemorandumDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.MemorandumNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.MemorandumPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanMemorandumDao;
import com.pemda.ekinerjademo.service.MemorandumService;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
@Service("MemorandumService")
@Transactional("ekinerjaTransactionManager")
public class MemorandumServiceImpl implements MemorandumService {
    @Autowired private MemorandumDao memorandumDao;
    @Autowired private MemorandumNonPejabatDao memorandumNonPejabatDao;
    @Autowired private MemorandumPejabatDao memorandumPejabatDao;
    @Autowired private TembusanMemorandumDao tembusanMemorandumDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @Override
    public List<Memorandum> getByNipPembuat(String nipPembuat) {
        return memorandumDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<Memorandum> getByNipTarget(String nipTarget) {
        return memorandumDao.findByNipPenerimaMemorandum(nipTarget);
    }

    @Override
    public List<TembusanMemorandum> getTembusanMemorandum(String kdJabatanTembusan) {
        return tembusanMemorandumDao.findByTembusanMemorandumId_KdJabatan(kdJabatanTembusan);
    }

    @Override
    public List<TembusanMemorandum> getTembusanMemorandumUnitKerja(String kdUnitKerja) {
        return tembusanMemorandumDao.findByUnitKerja(kdUnitKerja);
    }

    @Override
    public List<Memorandum> getDraftMemorandumApproval(String kdUnitKerja) {
        return memorandumDao.findByApproval(kdUnitKerja);
    }

    @Override
    public List<Memorandum> getMemorandumByUnitKerjaTarget(String kdUnitKerjaTarget) {
        return memorandumDao.findByUnitKerjaTarget(kdUnitKerjaTarget);
    }

    @Override
    public void createMemorandum(Memorandum memorandum) {
        memorandumDao.save(memorandum);
    }

    @Override
    public void createMemorandumNonPejabat(MemorandumNonPejabat memorandumNonPejabat) {
        memorandumNonPejabatDao.save(memorandumNonPejabat);
    }

    @Override
    public void createMemorandumPejabat(MemorandumPejabat memorandumPejabat) {
        memorandumPejabatDao.save(memorandumPejabat);
    }

    @Override
    public void createTembusanMemorandum(List<TembusanMemorandum> tembusanMemorandumList) {
        for (TembusanMemorandum tembusanMemorandum
                : tembusanMemorandumList) {
            tembusanMemorandumDao.save(tembusanMemorandum);
        }
    }

    @Override
    public void openMemorandum(String kdMemorandum) {
        Memorandum memorandum
                = memorandumDao.findByKdMemorandum(kdMemorandum);
        memorandum.setStatusBaca(1);
    }

    @Override
    public void openTembusanMemorandum(TembusanMemorandumId id) {
        TembusanMemorandum tembusanMemorandum
                = tembusanMemorandumDao.findOne(id);
        tembusanMemorandum.setStatusBaca(1);
    }

    @Override
    public void openMemorandumByPenilai(String kdMemorandum) {
        Memorandum memorandum
                = memorandumDao.findByKdMemorandum(kdMemorandum);
        memorandum.setStatusPenilaian(1);
    }

    @Override
    public void update(Memorandum memorandum) {
        memorandumDao.save(memorandum);
    }

    @Override
    public Memorandum getByKdMemorandum(String kdMemorandum) {
        return memorandumDao.findByKdMemorandum(kdMemorandum);
    }

    @Override
    public void approveMemorandum(String kdMemorandum) {
        Memorandum memorandumLast = memorandumDao.findOne(kdMemorandum);
        memorandumLast.setStatusPenyebaran(1);
        memorandumLast.setApprovalPenandatangan(1);

        NomorUrutSuratUnitKerja nomorUrutSurat
                = nomorUrutSuratUnitKerjaService
                .getNomorSuratByUnitKerjaAndTahun(
                        memorandumLast.getKdUnitKerja(),
                        Year.now().getValue());

        if (nomorUrutSurat == null) {
            nomorUrutSurat
                    = new NomorUrutSuratUnitKerja();
            nomorUrutSurat
                    .setNomorUrutSuratUnitKerjaId(
                            new NomorUrutSuratUnitKerjaId(memorandumLast.getKdUnitKerja(), Year.now().getValue()));
            nomorUrutSurat.setNomorUrutSurat(1);

            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
        } else {
            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);

            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
        }
        memorandumLast.setNomorUrut(nomorUrutSurat.getNomorUrutSurat());
        String kdBarcode
                = memorandumLast.getKdMemorandum()
                + memorandumLast.getNomorUrut()
                + memorandumLast.getKdUnitKerja()
                + "2";
        memorandumLast.setKdBarcode(kdBarcode);

        String penilaianTree = memorandumLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<Memorandum> memorandumList
                    = memorandumDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (Memorandum memorandum
                    : memorandumList) {
                memorandum.setApprovalPenandatangan(1);
            }
        }
    }

}
