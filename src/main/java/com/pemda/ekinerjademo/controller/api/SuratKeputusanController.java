package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratKeputusanService;
import com.pemda.ekinerjademo.wrapper.input.SuratKeputusanInputWrapper;
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
public class SuratKeputusanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKeputusanController.class);

    @Autowired private SuratKeputusanService suratKeputusanService;

    @RequestMapping(value = "/create-surat-keputusan", method = RequestMethod.POST)
    ResponseEntity<?> createSuratKeputusan(@RequestBody SuratKeputusanInputWrapper inputWrapper) {
        LOGGER.info("create surat keputusan");

        return new ResponseEntity<Object>(new CustomMessage("surat keputusan created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("sebar surat keputusan");

        return new ResponseEntity<Object>(new CustomMessage("surat keputusan telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("sebar surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("terima surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keputusan-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeputusanHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat keputusan history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keputusan-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeputusanTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat keputusan target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keputusan-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeputusanTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat keputusan target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-keputusan-by-kd-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratKeputusanByKdSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("get surat keputusan kd surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("open surat keputusan");

        return new ResponseEntity<Object>(new CustomMessage("surat keputusan opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keputusan-penilai/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeputusanPenilai(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("open surat keputusan penilai");

        return new ResponseEntity<Object>(new CustomMessage("surat keputusan opened by penilai"), HttpStatus.OK);

    }
}
