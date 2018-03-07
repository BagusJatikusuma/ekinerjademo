package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.util.BarcodeGenerator;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

    @RequestMapping(value = "/get-barcode-example", method = RequestMethod.GET)
    ResponseEntity<?> getBarcodeExample() throws IOException {
        LOGGER.info("get logo bekasi");

        byte[] imageByte;

        BarcodeGenerator generator = new BarcodeGenerator();

        Image image
                = generator.generateBarcode("151894753534533240");
        BufferedImage bImage
                = generator.convertImageIntoBufferedImage(image);

        ByteArrayOutputStream baos
                = new ByteArrayOutputStream();

        ImageIO.write(bImage, "jpg", baos);

        baos.flush();

        imageByte = baos.toByteArray();

        baos.close();

        return new ResponseEntity<>(
                imageByte,
                getImageHttpHeader(imageByte),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/get-logo-bekasi-in-base64", method = RequestMethod.GET)
    ResponseEntity<?> getLogoBekasiInBase64() throws IOException {
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

        BarcodeGenerator generator = new BarcodeGenerator();

        InputStream in = new ByteArrayInputStream(image);
        BufferedImage bImageFromConvert = ImageIO.read(in);

        String base64BarcodeImage
                = generator.convertBarcodeImageIntoBase64String(bImageFromConvert);

        return new ResponseEntity<>(
                new CustomMessage(base64BarcodeImage),
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
