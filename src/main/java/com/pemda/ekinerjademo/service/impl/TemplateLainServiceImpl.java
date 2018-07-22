package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TemplateLainDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import com.pemda.ekinerjademo.service.TemplateLainService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiBulananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
@Service("TemplateLainService")
@Transactional("ekinerjaTransactionManager")
public class TemplateLainServiceImpl implements TemplateLainService {
    @Autowired private TemplateLainDao templateLainDao;
    @Autowired private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;
    @Autowired private UraianTugasPegawaiBulananService uraianTugasPegawaiBulananService;

    @Override
    public void create(TemplateLain templateLain) {
        templateLainDao.save(templateLain);
    }

    @Override
    public void approve(String kdTemplateLain) {
        TemplateLain templateLainLast
                = templateLainDao.findOne(kdTemplateLain);
        templateLainLast.setApprovalPenandatangan(1);

        NomorUrutSuratUnitKerja nomorUrutSurat
                = nomorUrutSuratUnitKerjaService.getNomorSuratByUnitKerjaAndTahun(
                        templateLainLast.getKdUnitKerja(),
                        Year.now().getValue());

        if (nomorUrutSurat == null) {
            nomorUrutSurat
                    = new NomorUrutSuratUnitKerja();
            nomorUrutSurat
                    .setNomorUrutSuratUnitKerjaId(
                            new NomorUrutSuratUnitKerjaId(templateLainLast.getKdUnitKerja(), Year.now().getValue()));
            nomorUrutSurat.setNomorUrutSurat(1);

            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
        } else {
            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);

            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
        }

        String penilaianTree = templateLainLast.getPathPenilaian();
        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulananSKPDList
                = uraianTugasPegawaiBulananService.getByUnitKerja(
                        templateLainLast.getKdUnitKerja(),
                        templateLainLast.getBulanUrtug());


        if (penilaianTree.contains(".")) {
            List<TemplateLain> templateLains
                    = templateLainDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));

            for (TemplateLain templateLain
                    : templateLains) {
                templateLain.setApprovalPenandatangan(1);
                //algoritma untuk memberikan poin terhadap capaian kinerja pegawai
                //untuk sekarang hanya untuk yang non dpa

                for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulananSKPDList) {

                    if (uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId()
                            .equals(new UraianTugasPegawaiBulananId(templateLain.getKdUrtug(),
                                    templateLain.getKdJabatan(),
                                    templateLain.getKdJenisUrtug(),
                                    templateLain.getTahunUrtug(),
                                    templateLain.getBulanUrtug(),
                                    templateLain.getNipPegawai()))) {
                        uraianTugasPegawaiBulanan.setRealisasiKuantitas(uraianTugasPegawaiBulanan.getRealisasiKuantitas() + 1);
                        uraianTugasPegawaiBulananService.create(uraianTugasPegawaiBulanan);

                        break;

                    }
                }


            }
        }

    }

    @Override
    public TemplateLain getTemplateLain(String kdTemplateLain) {
        return templateLainDao.findOne(kdTemplateLain);
    }

    @Override
    public List<TemplateLain> getByPembuat(String nipPembuat) {
        return templateLainDao.findByNipPegawai(nipPembuat);
    }

}
