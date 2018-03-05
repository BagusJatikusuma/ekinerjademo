package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerjaId;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeputusan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratKeputusanDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import com.pemda.ekinerjademo.service.SuratKeputusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratKeputusanService")
@Transactional("ekinerjaTransactionManager")
public class SuratKeputusanServiceImpl implements SuratKeputusanService {
    @Autowired private SuratKeputusanDao suratKeputusanDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @Override
    public List<SuratKeputusan> getByKdUnitKerja(String kdUnitKerja) {
        return suratKeputusanDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratKeputusan> getByNipPembuat(String nipPembuat) {
        return suratKeputusanDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratKeputusan> getByNomorTahun(Integer nomorTahun) {
        return suratKeputusanDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public SuratKeputusan getByKdSuratKeputusan(String kdSuratKeputusan) {
        return suratKeputusanDao.findByKdSuratKeputusan(kdSuratKeputusan);
    }

    @Override
    public void create(SuratKeputusan suratKeputusan) {
        suratKeputusanDao.save(suratKeputusan);
    }

    @Override
    public void approveSuratKeputusan(String kdSuratKeputusan) {
        SuratKeputusan suratKeputusanLast = suratKeputusanDao.findOne(kdSuratKeputusan);
        suratKeputusanLast.setStatusPenyebaran(1);
        suratKeputusanLast.setApprovalPenandatangan(1);

        NomorUrutSuratUnitKerja nomorUrutSurat
                = nomorUrutSuratUnitKerjaService
                .getNomorSuratByUnitKerjaAndTahun(
                        suratKeputusanLast.getKdUnitKerja(),
                        Year.now().getValue());

        if (nomorUrutSurat == null) {
            nomorUrutSurat
                    = new NomorUrutSuratUnitKerja();
            nomorUrutSurat
                    .setNomorUrutSuratUnitKerjaId(
                            new NomorUrutSuratUnitKerjaId(suratKeputusanLast.getKdUnitKerja(), Year.now().getValue()));
            nomorUrutSurat.setNomorUrutSurat(1);

            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
        } else {
            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);

            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
        }
        suratKeputusanLast.setNomorUrut(nomorUrutSurat.getNomorUrutSurat());
        String kdBarcode
                = suratKeputusanLast.getKdSuratKeputusan()
                + suratKeputusanLast.getNomorUrut()
                + suratKeputusanLast.getKdUnitKerja()
                + "7";
        suratKeputusanLast.setKdBarcode(kdBarcode);

        String penilaianTree = suratKeputusanLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<SuratKeputusan> suratKeputusanList
                    = suratKeputusanDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (SuratKeputusan suratKeputusan
                    : suratKeputusanList) {
                suratKeputusan.setApprovalPenandatangan(1);
            }
        }
    }
}
