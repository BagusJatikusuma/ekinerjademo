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
            urtugPegawaiTahunan.setKuantitas(urtugPegawai.getKuantitas());
            urtugPegawaiTahunan.setSatuanKuantitas(urtugPegawai.getSatuanKuantitas());
            urtugPegawaiTahunan.setKualitas(urtugPegawai.getKualitas());
            urtugPegawaiTahunan.setWaktu(urtugPegawai.getWaktu());
            urtugPegawaiTahunan.setBiaya(urtugPegawai.getBiaya());
            urtugPegawaiTahunan.setAlasan(urtugPegawai.getAlasan());

            urtugPegawaiTahunanDao.save(urtugPegawaiTahunan);
        }

    }

    @Override
    public void deleteUraianTugasPegawaiTahunan(UraianTugasPegawaiTahunanId urtugPegawaiTahunanId) {
        urtugPegawaiTahunanDao
                .deleteByUraianTugasPegawaiTahunanId(urtugPegawaiTahunanId);
    }

    @Override
    public void deleteUraianTugasPegawaiTahunanByNipPegawaiAndTahun(String nipPegawai, Integer tahunUrtug) {

    }

    @Override
    public void approveUrtug(UraianTugasPegawaiTahunanId uraianTugasPegawaiTahunanId) {
        UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan
                = urtugPegawaiTahunanDao.findOne(uraianTugasPegawaiTahunanId);
        uraianTugasPegawaiTahunan.setStatusApproval(true);
    }

    @Override
    public void createUraianTugasPegawaiTahunan(
            UraianTugasPegawaiTahunanInputWrapper uraianTugasPegawaiTahunanInputWrapper,
            Boolean statusApproval) {
        UraianTugasPegawaiTahunan urtugPegawaiTahunan = new UraianTugasPegawaiTahunan();
        urtugPegawaiTahunan
                .setUraianTugasPegawaiTahunanId(
                        new UraianTugasPegawaiTahunanId(
                                uraianTugasPegawaiTahunanInputWrapper.getKdUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getKdJabatan(),
                                uraianTugasPegawaiTahunanInputWrapper.getKdJenisUrtug(),
                                Year.now().getValue(),
                                uraianTugasPegawaiTahunanInputWrapper.getNipPegawai()
                        ));
        urtugPegawaiTahunan.setAkunPegawai(new AkunPegawai(uraianTugasPegawaiTahunanInputWrapper.getNipPegawai()));
        urtugPegawaiTahunan.setStatusApproval(statusApproval);
        urtugPegawaiTahunan.setStatusPengerjaan(0);
        urtugPegawaiTahunan.setKuantitas(uraianTugasPegawaiTahunanInputWrapper.getKuantitas());
        urtugPegawaiTahunan.setSatuanKuantitas(uraianTugasPegawaiTahunanInputWrapper.getSatuanKuantitas());
        urtugPegawaiTahunan.setKualitas(uraianTugasPegawaiTahunanInputWrapper.getKualitas());
        urtugPegawaiTahunan.setWaktu(uraianTugasPegawaiTahunanInputWrapper.getWaktu());
        urtugPegawaiTahunan.setBiaya(uraianTugasPegawaiTahunanInputWrapper.getBiaya());
        urtugPegawaiTahunan.setAlasan(uraianTugasPegawaiTahunanInputWrapper.getAlasan());

        urtugPegawaiTahunanDao.save(urtugPegawaiTahunan);
    }

    @Override
    public List<UraianTugasPegawaiTahunan> getByNipPegawai(String nipPegawai) {
        return urtugPegawaiTahunanDao
                .findByUraianTugasPegawaiTahunanId_NipPegawai(nipPegawai);
    }

    @Override
    public List<UraianTugasPegawaiTahunan> getByUnitKerja(String kdUnitKerja) {
        return urtugPegawaiTahunanDao
                .findByUnitKerja(kdUnitKerja);
    }
}
