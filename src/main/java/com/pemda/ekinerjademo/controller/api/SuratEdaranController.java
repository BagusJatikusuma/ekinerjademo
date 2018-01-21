package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratEdaranService;
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
public class SuratEdaranController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratEdaranController.class);

    @Autowired private SuratEdaranService suratEdaranService;

    ResponseEntity<?> createSuratEdaran() {
        LOGGER.info("create surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarSuratEdaran() {
        LOGGER.info("sebar surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approveSuratEdaran() {
        LOGGER.info("sebar surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaSuratEdaran() {
        LOGGER.info("terima surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratEdaranHistoryByPegawai() {
        LOGGER.info("get surat edaran history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratEdaranTarget() {
        LOGGER.info("get surat edaran target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratEdaranTargetUnread() {
        LOGGER.info("get surat edaran target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getSuratEdaranByKdPengumuman() {
        LOGGER.info("get surat edaran kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratEdaran() {
        LOGGER.info("open surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratEdaranPenilai() {
        LOGGER.info("open surat edaran penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

}
