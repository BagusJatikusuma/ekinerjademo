package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.PengumumanService;
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
public class PengumumanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PengumumanController.class);

    @Autowired private PengumumanService pengumumanService;

    ResponseEntity<?> createPengumuman() {
        LOGGER.info("create pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> sebarPengumuman() {
        LOGGER.info("sebar pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> approvePengumuman() {
        LOGGER.info("sebar pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> terimaPengumuman() {
        LOGGER.info("terima pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarPengumumanHistoryByPegawai() {
        LOGGER.info("get pengumuman history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarPengumumanTarget() {
        LOGGER.info("get pengumuman target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getDaftarPengumumanTargetUnread() {
        LOGGER.info("get pengumuman target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> getPengumumanByKdPengumuman() {
        LOGGER.info("get pengumuman kd pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openPengumuman() {
        LOGGER.info("open pengumuman");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    ResponseEntity<?> openPengumumanPenilai() {
        LOGGER.info("open pengumuman penilai");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

}
