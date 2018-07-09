package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratPengantarDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratPengantarIsiDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import com.pemda.ekinerjademo.service.SuratPengantarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratPengantarService")
@Transactional("ekinerjaTransactionManager")
public class SuratPengantarServiceImpl implements SuratPengantarService {
    @Autowired private SuratPengantarDao suratPengantarDao;
    @Autowired private SuratPengantarIsiDao suratPengantarIsiDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @Override
    public List<SuratPengantar> getByKdUnitKerja(String kdUnitKerja) {
        return suratPengantarDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratPengantar> getByNipPembuat(String nipPembuat) {
        return suratPengantarDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratPengantar> getByNomorTahun(Integer nomorTahun) {
        return suratPengantarDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<SuratPengantar> getByJabatanTarget(String kdJabatan) {
        return suratPengantarDao.findByKdJabatanTarget(kdJabatan);
    }

    @Override
    public List<SuratPengantar> getByPegawaiTarget(String nipTarget) {
        return suratPengantarDao.findByNipPenerima(nipTarget);
    }

    @Override
    public List<SuratPengantar> getSuratPengantarSekretarisApproval(String kdUnitKerja) {
        return suratPengantarDao.findBySekretarisApproval(kdUnitKerja);
    }

    @Override
    public SuratPengantar getByKdSuratPengantar(String kdSuratPengantar) {
        return suratPengantarDao.findByKdSuratPengantar(kdSuratPengantar);
    }

    @Override
    public void create(SuratPengantar suratPengantar) {
        suratPengantarDao.save(suratPengantar);
    }

    @Override
    public void createSuratPengantarIsi(SuratPengantarIsi suratPengantarIsi) {
        suratPengantarIsiDao.save(suratPengantarIsi);
    }

    @Override
    public void openSuratPengantarPenilai(String kdSuratPengantar) {
        SuratPengantar suratPengantar
                = suratPengantarDao.findByKdSuratPengantar(kdSuratPengantar);
        suratPengantar.setStatusPenilaian(1);
    }

    @Override
    public void approveSuratPengantar(String kdSuratPengantar) {
        SuratPengantar suratPengantarLast = suratPengantarDao.findOne(kdSuratPengantar);
        suratPengantarLast.setStatusPenyebaran(1);
        suratPengantarLast.setApprovalPenandatangan(1);

        NomorUrutSuratUnitKerja nomorUrutSurat
                = nomorUrutSuratUnitKerjaService
                .getNomorSuratByUnitKerjaAndTahun(
                        suratPengantarLast.getKdUnitKerja(),
                        Year.now().getValue());

        if (nomorUrutSurat == null) {
            nomorUrutSurat
                    = new NomorUrutSuratUnitKerja();
            nomorUrutSurat
                    .setNomorUrutSuratUnitKerjaId(
                            new NomorUrutSuratUnitKerjaId(suratPengantarLast.getKdUnitKerja(), Year.now().getValue()));
            nomorUrutSurat.setNomorUrutSurat(1);

            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
        } else {
            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);

            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
        }
        suratPengantarLast.setNomorUrut(nomorUrutSurat.getNomorUrutSurat());
        String kdBarcode
                = suratPengantarLast.getKdSuratPengantar()
                + suratPengantarLast.getNomorUrut()
                + suratPengantarLast.getKdUnitKerja()
                + "10";
        suratPengantarLast.setKdBarcode(kdBarcode);

        String penilaianTree = suratPengantarLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            List<SuratPengantar> suratPengantarList
                    = suratPengantarDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (SuratPengantar suratPengantar
                    : suratPengantarList) {
                suratPengantar.setApprovalPenandatangan(1);
            }
        }
    }

}
