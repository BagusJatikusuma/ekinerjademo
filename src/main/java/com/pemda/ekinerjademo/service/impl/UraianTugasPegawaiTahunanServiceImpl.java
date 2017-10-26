package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunanId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasPegawaiTahunanDao;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiTahunanService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiTahunanInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bagus on 15/10/17.
 */
@Service("UraianTugasPegawaiTahunanService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasPegawaiTahunanServiceImpl implements UraianTugasPegawaiTahunanService {
    @Autowired private UraianTugasPegawaiTahunanDao urtugPegawaiTahunanDao;

    @Override
    public void createUraianTugasPegawaiTahunanList(List<UraianTugasPegawaiTahunanInputWrapper> urtugPegawaiList) {
        for (UraianTugasPegawaiTahunanInputWrapper urtugPegawai : urtugPegawaiList) {
            UraianTugasPegawaiTahunan urtugPegawaiTahunan = new UraianTugasPegawaiTahunan();
            urtugPegawaiTahunan
                    .setUraianTugasPegawaiTahunanId(
                            new UraianTugasPegawaiTahunanId(
                                    urtugPegawai.getKdUrtug(),
                                    urtugPegawai.getKdJabatan(),
                                    urtugPegawai.getKdJenisUrtug(),
                                    Year.now().getValue(),
                                    urtugPegawai.getNipPegawai()
                            ));
            urtugPegawaiTahunan.setAkunPegawai(new AkunPegawai(urtugPegawai.getNipPegawai()));
            urtugPegawaiTahunan.setStatusApproval(false);
            urtugPegawaiTahunan.setStatusPengerjaan(0);

            urtugPegawaiTahunanDao.save(urtugPegawaiTahunan);
        }

    }

    @Override
    public void deleteUraianTugasPegawaiTahunan(UraianTugasPegawaiTahunanId urtugPegawaiTahunanId) {
        urtugPegawaiTahunanDao
                .deleteByUraianTugasPegawaiTahunanId(urtugPegawaiTahunanId);
    }

    @Override
    public List<UraianTugasPegawaiTahunan> getByNipPegawai(String nipPegawai) {
        return urtugPegawaiTahunanDao
                .findByUraianTugasPegawaiTahunanId_NipPegawai(nipPegawai);
    }
}
