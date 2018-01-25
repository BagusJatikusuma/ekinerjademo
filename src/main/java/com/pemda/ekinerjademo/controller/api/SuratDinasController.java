package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratDinasService;
import com.pemda.ekinerjademo.wrapper.input.SuratDinasInputWrapper;
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
public class SuratDinasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratDinasController.class);

    @Autowired private SuratDinasService suratDinasService;

    @RequestMapping(value = "/create-surat-dinas", method = RequestMethod.POST)
    ResponseEntity<?> createSuratDinas(@RequestBody SuratDinasInputWrapper inputWrapper) {
        LOGGER.info("create surat dinas");



        return new ResponseEntity<Object>(new CustomMessage("surat dinas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/sebar-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("sebar surat dinas");

        return new ResponseEntity<Object>(new CustomMessage("surat dinas telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("sebar surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("terima surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat dinas history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat dinas target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat dinas target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-dinas-by-kd-surat-dinas/{kdSuratDinas}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratDinasByKdSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("get surat dinas kd surat dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("open surat dinas");

        return new ResponseEntity<Object>(new CustomMessage("surat dinas opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-dinas-penilai/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratDinasPenilai(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("open surat dinas penilai");

        return new ResponseEntity<Object>(new CustomMessage("surat dinas opened by penilai"), HttpStatus.OK);

    }

}
