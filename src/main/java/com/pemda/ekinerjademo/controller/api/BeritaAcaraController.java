package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.service.BeritaAcaraService;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
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
public class BeritaAcaraController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BeritaAcaraController.class);

    @Autowired private BeritaAcaraService beritaAcaraService;


    @RequestMapping(value = "/create-berita-acara", method = RequestMethod.POST)
    ResponseEntity<?> createBeritaAcara(
            @RequestBody BeritaAcaraInputWrapper inputWrapper) {
        LOGGER.info("create berita acara");

        String kdBeritaAcara = String.valueOf(new Date().getTime());

        BeritaAcara beritaAcara = new BeritaAcara();

        beritaAcara.setKdBeritaAcara(kdBeritaAcara);
        beritaAcara.setNomorUrusan(inputWrapper.getNomorUrusan());
        beritaAcara.setNomorUrut(null);
        beritaAcara.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        beritaAcara.setNomorUnit(inputWrapper.getNomorUnit());
        beritaAcara.setNomorTahun(Year.now().getValue());

        beritaAcara.setNipPihakKesatu(inputWrapper.getNipPihakKesatu());
        beritaAcara.setPeranPihakKesatu(inputWrapper.getPeranPihakKesatu());
        beritaAcara.setStatusApprovalPihakKesatu(0);

        beritaAcara.setNipPihakKedua(inputWrapper.getNipPihakKedua());
        beritaAcara.setPeranPihakKedua(inputWrapper.getPeranPihakKedua());
        beritaAcara.setStatusApprovalPihakKedua(0);

        beritaAcara.setIsiBeritaAcara(inputWrapper.getIsiBeritaAcara());
        beritaAcara.setDasarBeritaAcara(inputWrapper.getDasarBeritaAcara());
        beritaAcara.setNipMengetahui(inputWrapper.getNipMengetahui());
        beritaAcara.setStatusApprovalNipMengetahui(0);

        beritaAcara.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        beritaAcara.setTanggalPembuatanMilis(new Date().getTime());
        beritaAcara.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        beritaAcara.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        beritaAcaraService.createBeritaAcara(beritaAcara);

        return new ResponseEntity<Object>(
                new CustomMessage("berita acara created"), HttpStatus.CREATED);
    }
}
