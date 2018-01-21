package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratKeteranganService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratKeteranganController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKeteranganController.class);

    @Autowired private SuratKeteranganService suratKeteranganService;
    
    ResponseEntity<?> createSuratKeterangan() {
        LOGGER.info("create surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarSuratKeterangan() {
        LOGGER.info("sebar surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approveSuratKeterangan() {
        LOGGER.info("sebar surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaSuratKeterangan() {
        LOGGER.info("terima surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratKeteranganHistoryByPegawai() {
        LOGGER.info("get surat keterangan history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratKeteranganTarget() {
        LOGGER.info("get surat keterangan target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratKeteranganTargetUnread() {
        LOGGER.info("get surat keterangan target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getSuratKeteranganByKdPengumuman() {
        LOGGER.info("get surat keterangan kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratKeterangan() {
        LOGGER.info("open surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratKeteranganPenilai() {
        LOGGER.info("open surat keterangan penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }
    
}
