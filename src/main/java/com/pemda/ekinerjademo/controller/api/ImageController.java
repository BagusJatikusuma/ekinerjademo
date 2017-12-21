package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by bagus on 07/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class ImageController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping(value = "/get-logo-bekasi", method = RequestMethod.GET)
    ResponseEntity<?> getLogoBekasi() {
        LOGGER.info("get logo bekasi");

        byte[] image;

        File imgPath = new File("/home/pemkab/ekinerja_images/LOGO-KABUPATEN-BEKASI.png");

        try {
            image = Files.readAllBytes(imgPath.toPath());
        } catch (IOException e) {
            LOGGER.error("cannot read image");
            return new ResponseEntity<>(
                    new CustomMessage("cannot read image"),
                    HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(
                image,
                getImageHttpHeader(image),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/get-logo-ekinerja", method = RequestMethod.GET)
    ResponseEntity<?> getLogoEkinerja() {
        LOGGER.info("get logo ekinerja");

        byte[] image;

        File imgPath = new File("/home/pemkab/ekinerja_images/farm_to_table.png");

        try {
            image = Files.readAllBytes(imgPath.toPath());
        } catch (IOException e) {
            LOGGER.error("cannot read image");
            return new ResponseEntity<>(
                    new CustomMessage("cannot read image"),
                    HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(
                image,
                getImageHttpHeader(image),
                HttpStatus.OK);
    }

    private HttpHeaders getImageHttpHeader(byte[] image) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);

        return headers;
    }

}
