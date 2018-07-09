package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;
import com.pemda.ekinerjademo.service.SopUraianTugasJabatanService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasJabatanIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import com.pemda.ekinerjademo.wrapper.output.SopUraianTugasJabatanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SopUraianTugasJabatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SopUraianTugasJabatanController.class);

    @Autowired private SopUraianTugasJabatanService sopUraianTugasJabatanService;

//    @RequestMapping(value = "/get-sop-uraian-tugas-jabatan", method = RequestMethod.POST)
//    ResponseEntity<?> getSopUraianTugasJabatan(@RequestBody UraianTugasJabatanIdInputWrapper urtugJabatanWrapper) {
//        LOGGER.info("get sop uraian tugas jabatan");
//
//        List<SopUraianTugasJabatan> sopUraianTugasJabatanList
//                = sopUraianTugasJabatanService.findByUraianTugasJabatan(
//                        new UraianTugasJabatanId(urtugJabatanWrapper.getKdUrtug(), urtugJabatanWrapper.getKdJabatan()));
//
//
//
//        return new ResponseEntity<Object>();
//
//    }

    @RequestMapping(value = "/create-sop-uraian-tugas-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> createSopUraianTugasJabatan(
            @RequestBody SopUraianTugasJabatanWrapper sopUraianTugasJabatanWrapper) {
        LOGGER.info("create sop uraian tugas jabatan");

        SopUraianTugasJabatan sopUraianTugasJabatan = new SopUraianTugasJabatan();
        sopUraianTugasJabatan
                .setSopUraianTugasJabatanId(
                        new SopUraianTugasJabatanId(
                                sopUraianTugasJabatanWrapper.getKdSop(),
                                sopUraianTugasJabatanWrapper.getKdUrtug(),
                                sopUraianTugasJabatanWrapper.getKdJabatan()));

        sopUraianTugasJabatanService.save(sopUraianTugasJabatan);

        return new ResponseEntity<Object>(new CustomMessage("sop uraian tugas jabatan created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete-sop-uraian-tugas-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> deleteSopUraianTugasJabatan(
            @RequestBody SopUraianTugasJabatanWrapper sopUraianTugasJabatanWrapper) {
        LOGGER.info("delete sop uraian tugas jabatan");

        sopUraianTugasJabatanService
                .delete(new SopUraianTugasJabatanId(
                        sopUraianTugasJabatanWrapper.getKdSop(),
                        sopUraianTugasJabatanWrapper.getKdUrtug(),
                        sopUraianTugasJabatanWrapper.getKdJabatan()));

        return new ResponseEntity<Object>(new CustomMessage("sop uraian tugas jabatan deleted"), HttpStatus.OK);
    }
}
