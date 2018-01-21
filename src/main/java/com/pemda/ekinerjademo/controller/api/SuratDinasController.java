package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratDinasService;
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
public class SuratDinasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratDinasController.class);

    @Autowired private SuratDinasService suratDinasService;

    ResponseEntity<?> createSuratDinas() {
        LOGGER.info("create surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarSuratDinas() {
        LOGGER.info("sebar surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approveSuratDinas() {
        LOGGER.info("sebar surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaSuratDinas() {
        LOGGER.info("terima surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratDinasHistoryByPegawai() {
        LOGGER.info("get surat dinas history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratDinasTarget() {
        LOGGER.info("get surat dinas target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratDinasTargetUnread() {
        LOGGER.info("get surat dinas target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getSuratDinasByKdPengumuman() {
        LOGGER.info("get surat dinas kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratDinas() {
        LOGGER.info("open surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratDinasPenilai() {
        LOGGER.info("open surat dinas penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

}
