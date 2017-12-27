package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by bagus on 27/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class FileController {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/get-template-lain-file/{namaFile}", method = RequestMethod.GET)
    ResponseEntity<?> getTemplateLainFile(
            @PathVariable("kdTemplateLain") String namaFile) {
        LOGGER.info("get template lain file");

        byte[] file;

        File filePath = new File("/home/pemkab/project/documents/template_lain/"+namaFile);

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

    private HttpHeaders getImageHttpHeader(byte[] image) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_PLAIN);

        return headers;
    }

}
