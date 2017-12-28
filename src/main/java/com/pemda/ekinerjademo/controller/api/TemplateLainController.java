package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.TemplateLain;
import com.pemda.ekinerjademo.service.TemplateLainService;
import com.pemda.ekinerjademo.util.FileUploader;
import com.pemda.ekinerjademo.wrapper.input.TemplateLainInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.TemplateLainHistoryWrapper;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
//            templateLain.setPathFile(kdTemplateLain+"."+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
            templateLain.setPathFile(kdTemplateLain+"."+ FilenameUtils.getExtension(fileTemplateLain.getOriginalFilename()));
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

    @RequestMapping(value = "/create-template-lain-data", method = RequestMethod.POST)
    ResponseEntity<?> createTemplateLainData(@RequestBody TemplateLainInputWrapper templateLainInputWrapper) {
        LOGGER.info("create template lain data");

        String kdTemplateLain = String.valueOf(new Date().getTime());

        TemplateLain templateLain = new TemplateLain();

        templateLain.setKdTemplateLain(kdTemplateLain);
        templateLain.setKdUnitKerja(templateLainInputWrapper.getKdUnitKerja());
        templateLain.setNipPegawai(templateLainInputWrapper.getNipPegawai());
        templateLain.setKeterangan(templateLainInputWrapper.getKeterangan());
        templateLain.setDurasiPengerjaan(templateLainInputWrapper.getDurasiPengerjaan());
        templateLain.setKdNaskahPenugasan(templateLainInputWrapper.getKdNaskahPenugasan());
        templateLain.setJenisNaskahPenugasan(templateLainInputWrapper.getJenisNaskahPenugasan());
        templateLain.setKdUrtug(templateLainInputWrapper.getKdUrtug());
        templateLain.setKdJabatan(templateLainInputWrapper.getKdJabatan());
        templateLain.setTahunUrtug(templateLainInputWrapper.getTahunUrtug());
        templateLain.setStatusPenilaian(0);
        templateLain.setTanggalPembuatanMilis(new Date().getTime());

        if (templateLainInputWrapper.getKdTemplateLainBawahan() == null) {
//            templateLain.setPathFile(kdTemplateLain+"."+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
            templateLain.setPathFile(kdTemplateLain+"."+ FilenameUtils.getExtension(templateLainInputWrapper.getNamaFile()));
            templateLain.setPathPenilaian(kdTemplateLain);
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
                new CustomMessage(kdTemplateLain), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create-template-lain-file",
            method = RequestMethod.POST)
    ResponseEntity<?> createTemplateLainFile(@RequestParam("file") MultipartFile fileTemplateLain) {
        LOGGER.info("create template lain");

        FileUploader uploader = new FileUploader();
//            templateLain.setPathFile(kdTemplateLain+"."+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
//            templateLain.setPathFile(kdTemplateLain+"."+ FilenameUtils.getExtension(fileTemplateLain.getOriginalFilename()));
        uploader.uploadFileTemplateLain(fileTemplateLain, FilenameUtils.removeExtension(fileTemplateLain.getOriginalFilename()));

        return new ResponseEntity<Object>(
                new CustomMessage("template lain created"), HttpStatus.CREATED);
    }



    @RequestMapping(value = "/get-template-lain-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getTemplateLainByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get template lain by pembuat");

        List<TemplateLainHistoryWrapper> templateLainHistoryWrapperList
                = new ArrayList<>();
        List<TemplateLain> templateLainList
                = templateLainService.getByPembuat(nipPembuat);

        for (TemplateLain templateLain : templateLainList) {
            templateLainHistoryWrapperList
                    .add(new TemplateLainHistoryWrapper(
                            templateLain.getKdTemplateLain(),
                            templateLain.getKeterangan(),
                            FilenameUtils.removeExtension(templateLain.getPathFile()),
                            FilenameUtils.getExtension(templateLain.getPathFile()),
                            templateLain.getStatusPenilaian(),
                            templateLain.getTanggalPembuatanMilis(),
                            templateLain.getAlasanPenolakan(),
                            "template lain",
                            15));
        }

        return new ResponseEntity<Object>(templateLainHistoryWrapperList, HttpStatus.OK);
    }

}
