package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasPegawaiBulananDao;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiBulananService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiBulananInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("UraianTugasPegawaiBulananService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasPegawaiBulanServiceImpl
        implements UraianTugasPegawaiBulananService {
    @Autowired
    private UraianTugasPegawaiBulananDao uraianTugasPegawaiBulananDao;
    @Autowired
    @Qualifier("TkdJabatanCloneService")
    private TkdJabatanService tkdJabatanService;

    @Override
    public void create(UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan) {
        uraianTugasPegawaiBulananDao.save(uraianTugasPegawaiBulanan);
    }

    @Override
    public void create(UraianTugasPegawaiBulananInputWrapper inputWrapper, Integer statusApproval) {
        save(inputWrapper, statusApproval);
    }

    @Override
    public void create(List<UraianTugasPegawaiBulananInputWrapper> inputWrappers, Integer statusApproval) {
        for (UraianTugasPegawaiBulananInputWrapper inputWrapper : inputWrappers) {
            save(inputWrapper, statusApproval);
        }
    }

    @Override
    public void updateTargetSKPBulananPegawai(UraianTugasPegawaiBulananId urtugId, Integer targetBaru) {
        UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan
                = uraianTugasPegawaiBulananDao.findOne(urtugId);

        uraianTugasPegawaiBulanan.setTargetKuantitas(targetBaru);
    }

    @Override
    public void approveAjuanKontrakBulanan(UraianTugasPegawaiBulananInputWrapper inputWrapper) {
        UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan
                = uraianTugasPegawaiBulananDao.findOne(
                        new UraianTugasPegawaiBulananId(inputWrapper.getKdUrtug(),
                                                        inputWrapper.getKdJabatan(),
                                                        inputWrapper.getKdJenisUrtug(),
                                                        inputWrapper.getTahunUrtug(),inputWrapper.getBulanUrtug(),
                                                        inputWrapper.getNipPegawai()));

        uraianTugasPegawaiBulanan.setStatusApproval(1);
    }

    @Override
    public void approveAjuanKontrakBulanan(List<UraianTugasPegawaiBulananInputWrapper> inputWrappers) {
        for (UraianTugasPegawaiBulananInputWrapper inputWrapper : inputWrappers) {
            UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan
                    = uraianTugasPegawaiBulananDao.findOne(
                    new UraianTugasPegawaiBulananId(inputWrapper.getKdUrtug(),
                                                    inputWrapper.getKdJabatan(),
                                                    inputWrapper.getKdJenisUrtug(),
                                                    inputWrapper.getTahunUrtug(),inputWrapper.getBulanUrtug(),
                                                    inputWrapper.getNipPegawai()));

            uraianTugasPegawaiBulanan.setStatusApproval(1);
        }
    }

    @Override
    public void deleteById(UraianTugasPegawaiBulananId id) {
        uraianTugasPegawaiBulananDao.delete(id);
    }

    @Override
    public UraianTugasPegawaiBulanan getById(UraianTugasPegawaiBulananId id) {
        return uraianTugasPegawaiBulananDao.findOne(id);
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getByNip(String nipPegawai) {
        return uraianTugasPegawaiBulananDao.findByNip(nipPegawai);
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getByNip(String nipPegawai, Integer bulan) {
        return uraianTugasPegawaiBulananDao.findByNipAndBulan(nipPegawai, bulan);
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja) {
        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulananFilter
                = new ArrayList<>();
        List<TkdJabatan> tkdJabatanList
                = tkdJabatanService.getJabatanByUnitKerja(unitKerja);
        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = uraianTugasPegawaiBulananDao.findAll();

        for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan
                : uraianTugasPegawaiBulanans) {
            for (TkdJabatan tkdJabatan : tkdJabatanList) {
                if (uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan()
                        .equals(tkdJabatan.getKdJabatan())) {
                    uraianTugasPegawaiBulananFilter.add(uraianTugasPegawaiBulanan);
                    break;
                }
            }
        }


        return uraianTugasPegawaiBulananFilter;
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja, Integer bulan) {
        return uraianTugasPegawaiBulananDao.findByUnitKerjaAndBulan(unitKerja, bulan);
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja, Integer bulan, Integer tahun) {
        return uraianTugasPegawaiBulananDao.findByUnitKerjaAndBulanAndTahun(unitKerja, bulan, tahun);
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getAjuanUraianTugaspegawaiBulanan(String nipPegawai) {
        return uraianTugasPegawaiBulananDao.findAjuanUraianTugasPegawai(nipPegawai);
    }

    @Override
    public List<UraianTugasPegawaiBulanan> getAjuanKontrakBulanByNipPegawai(String nipPegawai, int bulan, int tahun) {
        return uraianTugasPegawaiBulananDao.findByNipBulanTahun(nipPegawai, bulan, tahun);
    }

    private void save(UraianTugasPegawaiBulananInputWrapper inputWrapper, Integer statusApproval) {
        UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan = new UraianTugasPegawaiBulanan();

        UraianTugasPegawaiBulananId id
                = new UraianTugasPegawaiBulananId(
                inputWrapper.getKdUrtug(),
                inputWrapper.getKdJabatan(),
                inputWrapper.getKdJenisUrtug(),
                inputWrapper.getTahunUrtug(),
                inputWrapper.getBulanUrtug(),
                inputWrapper.getNipPegawai());
        uraianTugasPegawaiBulanan.setUraianTugasPegawaiBulananId(id);
        uraianTugasPegawaiBulanan.setAkunPegawai(new AkunPegawai(inputWrapper.getNipPegawai()));

        uraianTugasPegawaiBulanan.setStatusApproval(statusApproval);
        uraianTugasPegawaiBulanan.setBiaya(0);
        uraianTugasPegawaiBulanan.setTargetKualitas(inputWrapper.getTargetKualitas());
        uraianTugasPegawaiBulanan.setTargetKuantitas(inputWrapper.getTargetKuantitas());
        uraianTugasPegawaiBulanan.setTargetSatuanKuantitas(inputWrapper.getSatuanKuantitas());
        uraianTugasPegawaiBulanan.setWaktu(inputWrapper.getWaktu());

        uraianTugasPegawaiBulanan.setRealisasiKualitas(0);
        uraianTugasPegawaiBulanan.setRealisasiKuantitas(0);
        uraianTugasPegawaiBulanan.setRealisasiWaktu(0);

        uraianTugasPegawaiBulananDao.save(uraianTugasPegawaiBulanan);
    }
}
