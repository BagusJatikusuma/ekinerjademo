package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.StatusPenanggungJawabKegiatan;
import com.pemda.ekinerjademo.service.StatusPenanggungJawabKegiatanService;
import com.pemda.ekinerjademo.wrapper.input.StatusPenanggungJawabKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

/**
 * Created by bagus on 24/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class StatusPeanggungJawabController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestServiceController.class);

    private StatusPenanggungJawabKegiatanService spjkService;

    @Autowired
    public StatusPeanggungJawabController(StatusPenanggungJawabKegiatanService spjk) {
        this.spjkService = spjk;
    }

    @RequestMapping(value = "/create-status-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createStatusPenanggungJawabKegiatan(
            @RequestBody StatusPenanggungJawabKegiatanInputWrapper inputWrapper) {
        LOGGER.info("create status penanggung jawab kegiatan");

        StatusPenanggungJawabKegiatan spjk
                = new StatusPenanggungJawabKegiatan();
        spjk.setKdStatus(String.valueOf(Year.now().getValue()));
        spjk.setStatus(inputWrapper.getStatus());

        spjkService.save(spjk);

        return new ResponseEntity<Object>(
                new CustomMessage("status penanggung jawab created"),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-status-penanggung-jawab-kegiatan", method = RequestMethod.PUT)
    ResponseEntity<?> updateStatusPenanggungJawabKegiatan(
            @RequestBody StatusPenanggungJawabKegiatan inputWrapper) {
        LOGGER.info("update status penanggung jawab kegiatan");

        StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan
                = spjkService.get(inputWrapper.getKdStatus());
        statusPenanggungJawabKegiatan.setStatus(inputWrapper.getStatus());

        spjkService.update(statusPenanggungJawabKegiatan);

        return new ResponseEntity<Object>(
                new CustomMessage("status penanggung jawab updated"),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-status-penanggung-jawab-kegiatan/{kdStatus}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteStatusPenanggungJawabKegiatan(
            @PathVariable("kdStatus") String kdStatus) {
        LOGGER.info("delete status penanggung jawab kegiatan");

        spjkService.delete(kdStatus);

        return new ResponseEntity<Object>(
                new CustomMessage("status penanggung jawab deleted"),
                HttpStatus.OK);
    }

}
