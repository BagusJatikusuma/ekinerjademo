package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.TemplateLain;
import com.pemda.ekinerjademo.service.TemplateLainService;
import com.pemda.ekinerjademo.util.FileUploader;
import com.pemda.ekinerjademo.wrapper.input.TemplateLainInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TemplateLainController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TemplateLainController.class);

    @Autowired
    private TemplateLainService templateLainService;

    @RequestMapping(value = "/create-template-lain",
            method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    ResponseEntity<?> createTemplateLain(
            @RequestPart("metadata") TemplateLainInputWrapper templateLainInputWrapper,
            @RequestPart("file") MultipartFile fileTemplateLain) {
        LOGGER.info("create template lain");

        FileUploader uploader = new FileUploader();

        String kdTemplateLain = String.valueOf(new Date().getTime());

        TemplateLain templateLain = new TemplateLain();

        templateLain.setKdTemplateLain(kdTemplateLain);
        templateLain.setKdUnitKerja(templateLainInputWrapper.getKdUnitKerja());
        templateLain.setNipPegawai(templateLainInputWrapper.getNipPegawai());
        templateLain.setKeterangan(templateLainInputWrapper.getKeterangan());
        templateLain.setKdNaskahPenugasan(templateLainInputWrapper.getKdNaskahPenugasan());
        templateLain.setJenisNaskahPenugasan(templateLainInputWrapper.getJenisNaskahPenugasan());
        templateLain.setKdUrtug(templateLainInputWrapper.getKdUrtug());
        templateLain.setKdJabatan(templateLainInputWrapper.getKdJabatan());
        templateLain.setTahunUrtug(templateLainInputWrapper.getTahunUrtug());
        templateLain.setStatusPenilaian(0);
        templateLain.setTanggalPembuatanMilis(new Date().getTime());

        if (templateLainInputWrapper.getKdTemplateLainBawahan() == null) {
            templateLain.setPathFile(kdTemplateLain+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
            templateLain.setPathPenilaian(kdTemplateLain);

            uploader.uploadFileTemplateLain(fileTemplateLain, kdTemplateLain);
        } else {
            TemplateLain templateLainBawahan
                    = templateLainService.getTemplateLain(templateLainInputWrapper.getKdTemplateLainBawahan());

            templateLain.setPathFile(templateLainInputWrapper.getNamaFileLaporanBawahan());
            templateLain.setPathPenilaian(templateLainBawahan.getPathPenilaian()+"."+kdTemplateLain);

            templateLainBawahan.setStatusPenilaian(2);
            templateLainService.create(templateLainBawahan);
        }

        templateLainService.create(templateLain);

        return new ResponseEntity<Object>(
                new CustomMessage("template lain created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-template-lain-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getTemplateLainByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get template lain by pembuat");

        List<TemplateLain> templateLainList
                = templateLainService.getByPembuat(nipPembuat);

        return new ResponseEntity<Object>(templateLainList, HttpStatus.OK);
    }

}
