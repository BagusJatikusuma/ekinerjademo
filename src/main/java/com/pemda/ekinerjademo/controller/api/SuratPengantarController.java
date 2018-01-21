package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratPengantarService;
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
public class SuratPengantarController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratPengantarController.class);

    @Autowired private SuratPengantarService suratPengantarService;
    
    ResponseEntity<?> createSuratPengantar() {
        LOGGER.info("create surat pengantar");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarSuratPengantar() {
        LOGGER.info("sebar surat pengantar");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approveSuratPengantar() {
        LOGGER.info("sebar surat pengantar");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaSuratPengantar() {
        LOGGER.info("terima surat pengantar");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratPengantarHistoryByPegawai() {
        LOGGER.info("get surat pengantar history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratPengantarTarget() {
        LOGGER.info("get surat pengantar target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarSuratPengantarTargetUnread() {
        LOGGER.info("get surat pengantar target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getSuratPengantarByKdPengumuman() {
        LOGGER.info("get surat pengantar kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratPengantar() {
        LOGGER.info("open surat pengantar");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openSuratPengantarPenilai() {
        LOGGER.info("open surat pengantar penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }
}
