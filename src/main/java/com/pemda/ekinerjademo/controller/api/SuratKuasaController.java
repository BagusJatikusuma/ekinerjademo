package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import com.pemda.ekinerjademo.service.SuratKuasaService;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.SuratKuasaInputWrapper;
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
public class SuratKuasaController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKuasaController.class);

    @Autowired private SuratKuasaService suratKuasaService;

    @RequestMapping(value = "/create-berita-acara", method = RequestMethod.POST)
    ResponseEntity<?> createSuratKuasa(
            @RequestBody SuratKuasaInputWrapper inputWrapper) {
        LOGGER.info("create surat kuasa");

        String kdSuratKuasa = String.valueOf(new Date().getTime());

        SuratKuasa suratKuasa = new SuratKuasa();

        suratKuasa.setKdSuratKuasa(kdSuratKuasa);
        suratKuasa.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratKuasa.setNomorUrut(null);
        suratKuasa.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratKuasa.setNomorUnit(inputWrapper.getNomorUnit());
        suratKuasa.setNomorTahun(Year.now().getValue());

        suratKuasa.setNipPemberiKuasa(inputWrapper.getNipPemberiKuasa());
        suratKuasa.setNipPenerimaKuasa(inputWrapper.getNipPenerimaKuasa());
        suratKuasa.setIsiKuasa(inputWrapper.getIsiKuasa());

        suratKuasa.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        suratKuasa.setTanggalPembuatanMilis(new Date().getTime());
        suratKuasa.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratKuasa.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        suratKuasaService.createSuratKuasa(suratKuasa);

        return new ResponseEntity<Object>(
                new CustomMessage("surat kuasa created"), HttpStatus.CREATED);
    }
}
