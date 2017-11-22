package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.service.LembarDisposisiService;
import com.pemda.ekinerjademo.wrapper.input.LembarDisposisiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class LembarDisposisiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LembarDisposisiController.class);

    @Autowired private LembarDisposisiService lembarDisposisiService;

    @RequestMapping(value = "/create-lembar-disposisi", method = RequestMethod.POST)
    ResponseEntity<?> createLembarDisposisi(
            @RequestBody LembarDisposisiInputWrapper inputWrapper) {
        LOGGER.info("create lembar disposisi");

        String kdLembarDisposisi = String.valueOf(new Date().getTime());

        LembarDisposisi lembarDisposisi = new LembarDisposisi();
        lembarDisposisi.setKdLembarDisposisi(kdLembarDisposisi);

        if (inputWrapper.getKdLembarDisposisiParent() == null) {
            lembarDisposisi.setPath(kdLembarDisposisi);
        } else {
            LembarDisposisi lembarDisposisiParent
                    = lembarDisposisiService.findByKdLembarDisposisi(inputWrapper.getKdLembarDisposisiParent());
            lembarDisposisi.setPath(lembarDisposisiParent.getPath()+"."+kdLembarDisposisi);
        }

        lembarDisposisi.setNipPembuat(inputWrapper.getNipPembuat());
        lembarDisposisi.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        lembarDisposisi.setTanggalPenerimaanMilis(inputWrapper.getTanggalPenerimaanMilis());
        lembarDisposisi.setTktKeamanan(inputWrapper.getTktKeamanan());
        lembarDisposisi.setTglPenyelesaianMilis(inputWrapper.getTglPenyelesaianMilis());
        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(inputWrapper.getNoSuratDisposisi()));

        if (inputWrapper.getKdLembarDisposisiParent() == null) {
            lembarDisposisi.setKdLembarDisposisiParent(null);
        } else {
            lembarDisposisi.setKdLembarDisposisiParent(new LembarDisposisi(inputWrapper.getKdLembarDisposisiParent()));
        }
        lembarDisposisiService.create(lembarDisposisi);

        List<TargetLembarDisposisi> targetLembarDisposisiList = new ArrayList<>();
        for (String kdTarget : inputWrapper.getDaftarTargetLembarDisposisi()) {
            TargetLembarDisposisi targetLembarDisposisi = new TargetLembarDisposisi();
            targetLembarDisposisi.setTargetLembarDisposisiId(new TargetLembarDisposisiId(kdLembarDisposisi, kdTarget));
            targetLembarDisposisi.setApproveStatus(0);

            targetLembarDisposisiList.add(targetLembarDisposisi);
        }
        lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisiList);

        return new ResponseEntity<Object>(new CustomMessage("lembar disposisi created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-lembar-disposisi", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisi() {
        return null;
    }

    @RequestMapping(value = "/get-lembar-disposisi-tree/{kdLembarDisposisi}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTree(
            @PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("get lembar disposisi tree");

        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findTree(kdLembarDisposisi);

        for (LembarDisposisi lembarDisposisi
                : lembarDisposisiList) {
            LOGGER.info(lembarDisposisi.getPath());
        }

        return null;
    }

}
