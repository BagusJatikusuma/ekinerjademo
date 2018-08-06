package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulananId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.KegiatanPegawaiBulananDao;
import com.pemda.ekinerjademo.service.KegiatanPegawaiBulananService;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import com.pemda.ekinerjademo.wrapper.input.KegiatanPegawaiBulananInputWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("KegiatanPegawaiBulananService")
@Transactional("ekinerjaTransactionManager")
public class KegiatanPegawaiBulananServiceImpl
        implements KegiatanPegawaiBulananService {
    @Autowired private KegiatanPegawaiBulananDao kegiatanPegawaiBulananDao;
    @Autowired private UnitKerjaKegiatanService unitKerjaKegiatanService;

    @Override
    public void create(KegiatanPegawaiBulanan kegiatanPegawaiBulanan) {
        kegiatanPegawaiBulananDao.save(kegiatanPegawaiBulanan);
    }

    @Override
    public void create(List<KegiatanPegawaiBulananInputWrapper> inputWrappers) {
        for (KegiatanPegawaiBulananInputWrapper inputWrapper : inputWrappers) {
            save(inputWrapper);
        }
    }

    @Override
    public void delete(KegiatanPegawaiBulananId id) {
        kegiatanPegawaiBulananDao.delete(id);
    }

    @Override
    public KegiatanPegawaiBulanan getKegiatanBulananPegawai(KegiatanPegawaiBulananId id) {
        return kegiatanPegawaiBulananDao.findOne(id);
    }

    @Override
    public List<KegiatanPegawaiBulanan> getKegiatanBulananPegawai(String nipPegawai) {
        return null;
    }

    @Override
    public List<KegiatanPegawaiBulanan> getKegiatanBulananPegawai(String nipPegawai, Integer bulan, Integer tahun) {
        return kegiatanPegawaiBulananDao.findByNipBulanTahun(nipPegawai, bulan, tahun);
    }

    @Override
    public List<KegiatanPegawaiBulanan> getKegiatanBulananUnitKerja(String kdUnitKerja) {
        return null;
    }

    @Override
    public List<KegiatanPegawaiBulanan> getKegiatanBulananUnitKerja(String kdUnitKerja, Integer bulan, Integer tahun) {
        UnitKerjaKegiatan unitKerjaKegiatan = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);

        return kegiatanPegawaiBulananDao
                .findByUnitKerjaBulanTahun(
                        unitKerjaKegiatan.getKdUrusan(),
                        unitKerjaKegiatan.getKdBidang(),
                        unitKerjaKegiatan.getKdUnit(),
                        bulan,
                        tahun);
    }

    private void save(KegiatanPegawaiBulananInputWrapper inputWrapper) {
        KegiatanPegawaiBulanan kegiatanPegawaiBulanan
                = new KegiatanPegawaiBulanan();

        KegiatanPegawaiBulananId id
                = new KegiatanPegawaiBulananId(inputWrapper.getKdUrusan(),
                inputWrapper.getKdBidang(),
                inputWrapper.getKdUnit(),
                inputWrapper.getKdSub(),
                inputWrapper.getTahun(),
                inputWrapper.getKdProg(),
                inputWrapper.getIdProg(),
                inputWrapper.getKdKeg(),
                inputWrapper.getNipPegawai(),
                inputWrapper.getKdStatusPenanggungJawab(),
                inputWrapper.getBulan());

        kegiatanPegawaiBulanan.setKegiatanPegawaiBulananId(id);
        kegiatanPegawaiBulanan.setTargetKuantitas(inputWrapper.getTargetKuantitas());
        kegiatanPegawaiBulanan.setTargetSatuanKuantitas(inputWrapper.getTargetSatuanKuantitas());
        kegiatanPegawaiBulanan.setTargetKualitas(100);
        kegiatanPegawaiBulanan.setBiaya(inputWrapper.getBiaya());
        kegiatanPegawaiBulanan.setWaktu(inputWrapper.getWaktu());
        kegiatanPegawaiBulanan.setRealisasiBiaya(0);
        kegiatanPegawaiBulanan.setRealisasiKualitas(0);
        kegiatanPegawaiBulanan.setRealisasiKuantitas(0);
        kegiatanPegawaiBulanan.setRealisasiWaktu(0);

        kegiatanPegawaiBulananDao.save(kegiatanPegawaiBulanan);
    }
}
