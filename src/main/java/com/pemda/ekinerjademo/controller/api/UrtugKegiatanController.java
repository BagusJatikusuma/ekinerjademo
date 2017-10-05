package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import com.pemda.ekinerjademo.service.UrtugKegiatanService;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bagus on 05/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UrtugKegiatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UrtugKegiatanController.class);

    private UrtugKegiatanService urtugKegiatanService;

    @Autowired
    public UrtugKegiatanController(UrtugKegiatanService urtugKegiatanService) {
        this.urtugKegiatanService = urtugKegiatanService;
    }

    ResponseEntity<?> getUrtugKegiatan() {
        return null;
    }

    @RequestMapping(value = "/create-urtug-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("create urtug kegiatan");

        UrtugKegiatan urtugKegiatan = new UrtugKegiatan();
        urtugKegiatan.setUrtugKegiatanId(
                new UrtugKegiatanId(
                        urtugKegiatanInputWrapper.getKdUrtug(),
                        urtugKegiatanInputWrapper.getKdJabatan(),
                        urtugKegiatanInputWrapper.getKdJenisUrtug(),
                        urtugKegiatanInputWrapper.getKdUrusan(),
                        urtugKegiatanInputWrapper.getKdBidang(),
                        urtugKegiatanInputWrapper.getKdUnit(),
                        urtugKegiatanInputWrapper.getKdSub(),
                        urtugKegiatanInputWrapper.getTahun(),
                        urtugKegiatanInputWrapper.getKdProg(),
                        urtugKegiatanInputWrapper.getIdProg(),
                        urtugKegiatanInputWrapper.getKdKeg()));

        urtugKegiatanService.save(urtugKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-urtug-kegiatan", method = RequestMethod.PUT)
    ResponseEntity<?> updateUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("update urtug kegiatan");

        UrtugKegiatan urtugKegiatan = new UrtugKegiatan();
        urtugKegiatan.setUrtugKegiatanId(
                new UrtugKegiatanId(
                        urtugKegiatanInputWrapper.getKdUrtug(),
                        urtugKegiatanInputWrapper.getKdJabatan(),
                        urtugKegiatanInputWrapper.getKdJenisUrtug(),
                        urtugKegiatanInputWrapper.getKdUrusan(),
                        urtugKegiatanInputWrapper.getKdBidang(),
                        urtugKegiatanInputWrapper.getKdUnit(),
                        urtugKegiatanInputWrapper.getKdSub(),
                        urtugKegiatanInputWrapper.getTahun(),
                        urtugKegiatanInputWrapper.getKdProg(),
                        urtugKegiatanInputWrapper.getIdProg(),
                        urtugKegiatanInputWrapper.getKdKeg()));

        urtugKegiatanService.update(urtugKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-urtug-kegiatan", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("delete urtug kegiatan");

        urtugKegiatanService.delete(
                new UrtugKegiatanId(
                        urtugKegiatanInputWrapper.getKdUrtug(),
                        urtugKegiatanInputWrapper.getKdJabatan(),
                        urtugKegiatanInputWrapper.getKdJenisUrtug(),
                        urtugKegiatanInputWrapper.getKdUrusan(),
                        urtugKegiatanInputWrapper.getKdBidang(),
                        urtugKegiatanInputWrapper.getKdUnit(),
                        urtugKegiatanInputWrapper.getKdSub(),
                        urtugKegiatanInputWrapper.getTahun(),
                        urtugKegiatanInputWrapper.getKdProg(),
                        urtugKegiatanInputWrapper.getIdProg(),
                        urtugKegiatanInputWrapper.getKdKeg()));

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan deleted"), HttpStatus.OK);
    }

}
