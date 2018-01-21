package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratUndanganService;
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
public class SuratUndanganController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratUndanganController.class);

    @Autowired private SuratUndanganService suratUndanganService;

    ResponseEntity<?> createSuratUndangan() {
        LOGGER.info("create surat undangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarSuratUndangan() {
        LOGGER.info("sebar surat undangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approveSuratUndangan() {
        LOGGER.info("sebar surat undangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaSuratUndangan() {
        LOGGER.info("terima surat undangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratUndanganHistoryByPegawai() {
        LOGGER.info("get surat undangan history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratUndanganTarget() {
        LOGGER.info("get surat undangan target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratUndanganTargetUnread() {
        LOGGER.info("get surat undangan target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getSuratUndanganByKdPengumuman() {
        LOGGER.info("get surat undangan kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratUndangan() {
        LOGGER.info("open surat undangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratUndanganPenilai() {
        LOGGER.info("open surat undangan penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }
}
