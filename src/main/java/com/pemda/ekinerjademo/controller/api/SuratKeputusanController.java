package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratKeputusanService;
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
public class SuratKeputusanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKeputusanController.class);

    @Autowired private SuratKeputusanService suratKeputusanService;
    
    ResponseEntity<?> createSuratKeputusan() {
        LOGGER.info("create surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarSuratKeputusan() {
        LOGGER.info("sebar surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approveSuratKeputusan() {
        LOGGER.info("sebar surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaSuratKeputusan() {
        LOGGER.info("terima surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratKeputusanHistoryByPegawai() {
        LOGGER.info("get surat keputusan history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratKeputusanTarget() {
        LOGGER.info("get surat keputusan target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratKeputusanTargetUnread() {
        LOGGER.info("get surat keputusan target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getSuratKeputusanByKdPengumuman() {
        LOGGER.info("get surat keputusan kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratKeputusan() {
        LOGGER.info("open surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratKeputusanPenilai() {
        LOGGER.info("open surat keputusan penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }
}
