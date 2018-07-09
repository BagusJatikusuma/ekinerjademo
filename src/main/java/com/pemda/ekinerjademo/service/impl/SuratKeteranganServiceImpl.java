package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratKeteranganDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetSuratKeteranganDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import com.pemda.ekinerjademo.service.SuratKeteranganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratKeteranganService")
@Transactional("ekinerjaTransactionManager")
public class SuratKeteranganServiceImpl implements SuratKeteranganService {
    @Autowired private SuratKeteranganDao suratKeteranganDao;
    @Autowired private TargetSuratKeteranganDao targetSuratKeteranganDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @Override
    public List<SuratKeterangan> getByKdUnitKerja(String kdUnitKerja) {
        return suratKeteranganDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratKeterangan> getByNipPembuat(String nipPembuat) {
        return suratKeteranganDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratKeterangan> getByNomorTahun(Integer nomorTahun) {
        return suratKeteranganDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<TargetSuratKeterangan> getTargetSuratKeteranganByNip(String nipTarget) {
        return targetSuratKeteranganDao.findByTargetSuratKeteranganId_NipPegawai(nipTarget);
    }

    @Override
    public List<SuratKeterangan> getSuratKeteranganSekretarisApproval(String kdUnitKerja) {
        return suratKeteranganDao.findBySekretarisApproval(kdUnitKerja);
    }

    @Override
    public SuratKeterangan getByKdSuratKeterangan(String kdSuratKeterangan) {
        return suratKeteranganDao.findByKdSuratKeterangan(kdSuratKeterangan);
    }

    @Override
    public void create(SuratKeterangan suratKeterangan) {
        suratKeteranganDao.save(suratKeterangan);
    }

    @Override
    public void createTargetSuratKeterangan(TargetSuratKeterangan targetSuratKeterangan) {
        targetSuratKeteranganDao.save(targetSuratKeterangan);
    }

    @Override
    public void openSuratKeterangan(String kdSuratKeterangan) {
        SuratKeterangan suratKeterangan
                = suratKeteranganDao.findByKdSuratKeterangan(kdSuratKeterangan);
        suratKeterangan.setStatusBaca(1);
    }

    @Override
    public void openTargetSuratKeterangan(TargetSuratKeteranganId targetSuratKeteranganId) {
        TargetSuratKeterangan targetSuratKeterangan
                = targetSuratKeteranganDao.findOne(targetSuratKeteranganId);
        if (targetSuratKeterangan != null)
            targetSuratKeterangan.setStatusBaca(1);
    }

    @Override
    public void openSuratKeteranganPenilai(String kdSuratKeterangan) {
        SuratKeterangan suratKeterangan
                = suratKeteranganDao.findByKdSuratKeterangan(kdSuratKeterangan);
        suratKeterangan.setStatusPenilaian(1);
    }

    @Override
    public void approveSuratKeterangan(String kdSuratKeterangan) {
        SuratKeterangan suratKeteranganLast = suratKeteranganDao.findOne(kdSuratKeterangan);
        suratKeteranganLast.setStatusPenyebaran(1);
        suratKeteranganLast.setApprovalPenandatangan(1);

        NomorUrutSuratUnitKerja nomorUrutSurat
                = nomorUrutSuratUnitKerjaService
                .getNomorSuratByUnitKerjaAndTahun(
                        suratKeteranganLast.getKdUnitKerja(),
                        Year.now().getValue());

        if (nomorUrutSurat == null) {
            nomorUrutSurat
                    = new NomorUrutSuratUnitKerja();
            nomorUrutSurat
                    .setNomorUrutSuratUnitKerjaId(
                            new NomorUrutSuratUnitKerjaId(suratKeteranganLast.getKdUnitKerja(), Year.now().getValue()));
            nomorUrutSurat.setNomorUrutSurat(1);

            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
        } else {
            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);

            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
        }
        suratKeteranganLast.setNomorUrut(nomorUrutSurat.getNomorUrutSurat());
        String kdBarcode
                = suratKeteranganLast.getKdSuratKeterangan()
                + suratKeteranganLast.getNomorUrut()
                + suratKeteranganLast.getKdUnitKerja()
                + "8";
        suratKeteranganLast.setKdBarcode(kdBarcode);

        String penilaianTree = suratKeteranganLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<SuratKeterangan> suratKeteranganList
                    = suratKeteranganDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (SuratKeterangan suratKeterangan
                    : suratKeteranganList) {
                suratKeterangan.setApprovalPenandatangan(1);
            }
        }

    }

}
