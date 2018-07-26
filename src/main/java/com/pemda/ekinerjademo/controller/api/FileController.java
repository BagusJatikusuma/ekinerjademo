package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.TemplateLain;
import com.pemda.ekinerjademo.service.TemplateLainService;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by bagus on 27/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class FileController {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private TemplateLainService templateLainService;

    @RequestMapping(value = "/get-template-lain-file/{namaFile}/{fileExtension}", method = RequestMethod.GET)
    ResponseEntity<?> getTemplateLainFile(
            @PathVariable("namaFile") String namaFile,
            @PathVariable("fileExtension") String fileExtension) {
        LOGGER.info("get template lain file");

        byte[] file;

        File filePath = new File("/home/pemkab/project/documents/template_lain/"+namaFile+"."+fileExtension);

        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e) {
            LOGGER.error("cannot read file");
            return new ResponseEntity<>(
                    new CustomMessage("cannot read file"),
                    HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(
                file,
                getImageHttpHeader(file),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/get-template-lain-file-revisi/{namaFile}/{fileExtension}/{kdSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getTemplateLainFileRevisi(
            @PathVariable("namaFile") String namaFile,
            @PathVariable("fileExtension") String fileExtension,
            @PathVariable("kdSurat") String kdSurat,
            HttpServletResponse response
            ) {
        LOGGER.info("get template lain file");

        TemplateLain templateLain
                = templateLainService.getTemplateLain(kdSurat);
        if (templateLain.getStatusPenilaian() == 0) {
            templateLain.setStatusPenilaian(1);
        }

        templateLainService.create(templateLain);

        byte[] file = null;

        File filePath = new File("/home/pemkab/project/documents/template_lain/"+namaFile+"."+fileExtension);

        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e) {
            LOGGER.error("failed retreive file");
        }

        String filename = namaFile+"."+fileExtension;

        HttpHeaders headers = new HttpHeaders();
        if (fileExtension.equals("pdf")) {
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.add("content-disposition", "inline;filename=" + filename);
        }
        else if (fileExtension.equals("png")
                || fileExtension.equals("jpeg")
                || fileExtension.equals("jpg")
                || fileExtension.equals("gif")) {
            switch (fileExtension) {
                case "png" :
                    headers.setContentType(MediaType.IMAGE_PNG);
                    break;
                case "gif" :
                    headers.setContentType(MediaType.IMAGE_GIF);
                    break;
                default:
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    break;
            }

            headers.setContentLength(file.length);
        }
        else {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    private HttpHeaders getImageHttpHeader(byte[] image) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return headers;
    }

}
