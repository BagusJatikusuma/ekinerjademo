package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bagus on 07/02/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class PenanggungJawabKegiatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PenanggungJawabKegiatanController.class);

    @RequestMapping(value = "/create-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createPenanggungJawabKegiatan() {
        LOGGER.info("create penanggung jawab kegiatan");

        return new ResponseEntity<Object>(new CustomMessage("penanggung jawab berhasil dipasang"), HttpStatus.OK);
    }


    @RequestMapping(value = "/get-penanggung-jawab-kegiatan/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getPenanggungJawabKegiatan(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get penanggung jawab kegiatan "+kdUnitKerja);

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> deletePenanggungJawabKegiatan() {
        LOGGER.info("delete penanggung jawab kegiatan");

        return new ResponseEntity<Object>(new CustomMessage("penanggung jawab berhasil dihapus"), HttpStatus.OK);
    }

}
