package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratKeteranganService;
import com.pemda.ekinerjademo.wrapper.input.SuratKeteranganInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratKeteranganController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKeteranganController.class);

    @Autowired private SuratKeteranganService suratKeteranganService;

    @RequestMapping(value = "/create-surat-keterangan", method = RequestMethod.POST)
    ResponseEntity<?> createSuratKeterangan(@RequestBody SuratKeteranganInputWrapper inputWrapper) {
        LOGGER.info("create surat keterangan");

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("sebar surat keterangan");

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("sebar surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("terima surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keterangan-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeteranganHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat keterangan history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keterangan-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeteranganTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat keterangan target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keterangan-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeteranganTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat keterangan target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-keterangan-by-kd-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratKeteranganByKdSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("get surat keterangan kd surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("open surat keterangan");

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keterangan-penilai/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeteranganPenilai(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("open surat keterangan penilai");

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan opened by penilai"), HttpStatus.OK);

    }
    
}
