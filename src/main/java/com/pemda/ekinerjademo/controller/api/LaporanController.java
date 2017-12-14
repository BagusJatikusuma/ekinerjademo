package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import com.pemda.ekinerjademo.service.LaporanService;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.LaporanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Date;

/**
 * Created by bayu on 07/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class LaporanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LaporanController.class);

    @Autowired private LaporanService laporanService;

    @RequestMapping(value = "/create-laporan", method = RequestMethod.POST)
    ResponseEntity<?> createLaporan(
            @RequestBody LaporanInputWrapper inputWrapper) {
        LOGGER.info("create laporan");

        String kdLaporan = String.valueOf(new Date().getTime());

        Laporan laporan = new Laporan();

        laporan.setKdLaporan(kdLaporan);

        laporan.setTentang(inputWrapper.getTentang());
        laporan.setUmum(inputWrapper.getUmum());
        laporan.setMaksudDanTujuan(inputWrapper.getMaksudDanTujuan());

        laporan.setRuangLingkup(inputWrapper.getRuangLingkup());
        laporan.setDasar(inputWrapper.getDasar());
        laporan.setKegiatanYangDilaksanakan(inputWrapper.getKegiatanYangDilakukan());

        laporan.setHasilYangDicapai(inputWrapper.getHasilYangDicapai());
        laporan.setSimpulanDanSaran(inputWrapper.getSimpulanDanSaran());
        laporan.setPenutup(inputWrapper.getPenutup());
        laporan.setNipPenandatangan(inputWrapper.getNipPenandatangan());

        laporan.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        laporan.setTanggalPembuatanMilis(new Date().getTime());
        laporan.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        laporan.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        laporanService.createLaporan(laporan);

        return new ResponseEntity<Object>(
                new CustomMessage("laporan created"), HttpStatus.CREATED);
    }
}