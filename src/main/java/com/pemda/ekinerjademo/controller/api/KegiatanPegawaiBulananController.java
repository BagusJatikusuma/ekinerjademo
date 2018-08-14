package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.service.KegiatanPegawaiBulananService;
import com.pemda.ekinerjademo.service.TaKegiatanService;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import com.pemda.ekinerjademo.wrapper.input.KegiatanPegawaiBulananInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.KegiatanBulananPegawaiWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class KegiatanPegawaiBulananController {
    public static final Logger LOGGER = LoggerFactory.getLogger(KegiatanPegawaiBulananController.class);

    @Autowired private KegiatanPegawaiBulananService kegiatanPegawaiBulananService;
    @Autowired private TaKegiatanService taKegiatanService;
    @Autowired private UnitKerjaKegiatanService unitKerjaKegiatanService;

    /**
     *
     * digunakan untuk membuat kontrak kerja dpa per bulan
     *
     * @return
     */
    @RequestMapping(value = "/create-kegiatan-bulanan-pegawai", method = RequestMethod.POST)
    ResponseEntity<?> createKegiatanBulananPegawai(
            @RequestBody List<KegiatanPegawaiBulananInputWrapper> inputWrappers) {
        LOGGER.info("create kegiatan bulanan pegawai");

        kegiatanPegawaiBulananService.create(inputWrappers);

        return new ResponseEntity<>(new CustomMessage("daftar SKP berhasil dibuat"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mendapatkan kontrak kerja dpa bulanan
     *
     * @return
     */
    @RequestMapping(value = "/get-kegiatan-bulanan-pegawai/{nipPegawai}/{kdUnitKerja}/{bulan}/{tahun}", method = RequestMethod.GET)
    ResponseEntity<?> getDPABulananPegawai(@PathVariable("nipPegawai") String nipPegawai,
                                           @PathVariable("kdUnitKerja") String kdUnitKerja,
                                           @PathVariable("bulan") Integer bulan,
                                           @PathVariable("tahun") Integer tahun) {
        LOGGER.info("get kegiatan bulanan pegawai");

        List<KegiatanPegawaiBulanan> kegiatanPegawaiBulanans
                = kegiatanPegawaiBulananService.getKegiatanBulananPegawai(nipPegawai, bulan, tahun);
        UnitKerjaKegiatan unitKerjaKegiatan = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);
        List<TaKegiatan> taKegiatans = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
        List<KegiatanBulananPegawaiWrapper> outputWrappers
                = new ArrayList<>();

        for (KegiatanPegawaiBulanan kegiatanPegawai : kegiatanPegawaiBulanans) {
            for (TaKegiatan taKegiatan : taKegiatans) {
                if (kegiatanPegawai.getKegiatanPegawaiBulananId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg())
                        && kegiatanPegawai.getKegiatanPegawaiBulananId().getIdProg().equals(taKegiatan.getTaKegiatanId().getIdProg())
                        && kegiatanPegawai.getKegiatanPegawaiBulananId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())
                        && (tahun.intValue() == taKegiatan.getTaKegiatanId().getTahun().intValue())) {
                    outputWrappers
                            .add(new KegiatanBulananPegawaiWrapper(kegiatanPegawai.getKegiatanPegawaiBulananId().getKdUrusan(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getKdBidang(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getKdUnit(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getKdSub(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getTahun(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getKdProg(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getIdProg(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getKdKeg(),
                                    taKegiatan.getKetKegiatan(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getNipPegawai(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getBulanPengerjaan(),
                                    kegiatanPegawai.getKegiatanPegawaiBulananId().getKdStatusPenanggungJawab(),
                                    kegiatanPegawai.getTargetKuantitas(),
                                    kegiatanPegawai.getTargetSatuanKuantitas(),
                                    kegiatanPegawai.getTargetKualitas(),
                                    kegiatanPegawai.getWaktu(),
                                    kegiatanPegawai.getBiaya(),
                                    Long.valueOf(kegiatanPegawai.getTargetKuantitas() * kegiatanPegawai.getWaktu()),
                                    kegiatanPegawai.getRealisasiKuantitas(),
                                    kegiatanPegawai.getRealisasiKualitas(),
                                    kegiatanPegawai.getRealisasiBiaya()));

                    break;
                }
            }
        }

        return new ResponseEntity<>(outputWrappers, HttpStatus.OK);
    }

}
