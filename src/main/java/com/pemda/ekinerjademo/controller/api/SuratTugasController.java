package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bagus on 29/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratTugasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratTugasController.class);

    @RequestMapping(value = "/create-surat-tugas", method = RequestMethod.POST)
    ResponseEntity<?> createSuratTugas() {
        LOGGER.info("create surat tugas");

        return new ResponseEntity<Object>(new CustomMessage("surat tugas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/approve-surat-tugas/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratTugas() {
        LOGGER.info("approve surat tugas");

        return new ResponseEntity<Object>(new CustomMessage("surat tugas approved"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-pembuat/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasHistoryByPembuat(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get surat tugas by pembuat");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasByTarget(
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat tugas by target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasByTargetUnread(
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat tugas unread by target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-kd-surat/{kdSuratTugas}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasbyKdSuratTugas(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("get surat tugas by kode surat");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-tugas/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratTugas(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("open surat tugas");

        return new ResponseEntity<Object>(new CustomMessage("surat tugas opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-tugas-by-penilai/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratTugasByPenilai(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("open surat tugas by penilai");

        return new ResponseEntity<Object>(new CustomMessage("surat tugas opened"), HttpStatus.OK);
    }

}
