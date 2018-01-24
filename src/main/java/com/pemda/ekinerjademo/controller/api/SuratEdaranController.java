package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.service.SuratEdaranService;
import com.pemda.ekinerjademo.wrapper.input.SuratEdaranInputWrapper;
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
public class SuratEdaranController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratEdaranController.class);

    @Autowired private SuratEdaranService suratEdaranService;

    @RequestMapping(value = "/create-surat-edaran", method = RequestMethod.POST)
    ResponseEntity<?> createSuratEdaran(@RequestBody SuratEdaranInputWrapper inputWrapper) {
        LOGGER.info("create surat edaran");

        return new ResponseEntity<Object>(new CustomMessage("surat edaran created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("sebar surat edaran");

        return new ResponseEntity<Object>(new CustomMessage("surat edaran telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("sebar surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("terima surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-edaran-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratEdaranHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat edaran history");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-edaran-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratEdaranTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat edaran target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-edaran-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratEdaranTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat edaran target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-edaran-by-kd-surat-edaran/{kdSuratEdaran}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratEdaranByKdSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("get surat edaran kd surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("open surat edaran");

        return new ResponseEntity<Object>(new CustomMessage("surat edaran opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-edaran-penilai/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratEdaranPenilai(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("open surat edaran penilai");

        return new ResponseEntity<Object>(new CustomMessage("surat edaran opened by penilai"), HttpStatus.OK);

    }

}
