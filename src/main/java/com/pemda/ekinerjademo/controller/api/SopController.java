package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.Sop;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;
import com.pemda.ekinerjademo.service.SopService;
import com.pemda.ekinerjademo.service.SopUraianTugasJabatanService;
import com.pemda.ekinerjademo.wrapper.input.SopInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugJabatanIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SopTemplateListWrapper;
import com.pemda.ekinerjademo.wrapper.output.SopUraianTugasJabatanListWrapper;
import com.pemda.ekinerjademo.wrapper.output.SopWrapper;
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
 * Created by bagus on 26/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SopController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SopController.class);

    @Autowired private SopService sopService;
    @Autowired private SopUraianTugasJabatanService sopUraianTugasJabatanService;

    @RequestMapping(value = "/get-all-sop", method = RequestMethod.GET)
    ResponseEntity<?> getAllSop() {
        LOGGER.info("get all sop");

        List<Sop> sopList = sopService.getSop();
        List<SopWrapper> sopWrapperList = new ArrayList<>();

        for (Sop sop : sopList) {
            sopWrapperList.add(new SopWrapper(sop.getKdSop(), sop.getSop()));
        }

        return new ResponseEntity<Object>(sopWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all-sop-by-urtug-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> getAllSopByUrtugJabatan(
            @RequestBody UrtugJabatanIdInputWrapper urtugJabatanIdInputWrapper) {
        LOGGER.info("get sop by urtug jabatan");

        List<SopWrapper> currentSopWrapperList = new ArrayList<>();
        List<SopWrapper> notSopWrapperList = new ArrayList<>();

        List<Sop> sopList = sopService.getSop();
        List<SopUraianTugasJabatan> sopUraianTugasJabatans
                = sopUraianTugasJabatanService
                .findByUraianTugasJabatan(new UraianTugasJabatanId(
                        urtugJabatanIdInputWrapper.getKdUrtug(),
                        urtugJabatanIdInputWrapper.getKdJabatan()));

        if (sopUraianTugasJabatans.isEmpty()) {
            for (Sop sop : sopList) {
                notSopWrapperList.add(new SopWrapper(sop.getKdSop(), sop.getSop()));
            }
        } else {
            boolean found = false;
            for (Sop sop : sopList) {
                found = false;
                for (SopUraianTugasJabatan sopUraianTugasJabatan : sopUraianTugasJabatans) {
                    if (sop.getKdSop()
                            .equals(sopUraianTugasJabatan.getSopUraianTugasJabatanId().getKdSop())) {
                        currentSopWrapperList
                                .add(new SopWrapper(sop.getKdSop(), sop.getSop()));

                        found = true;
                        break;
                    }

                }

                if (!found) {
                    notSopWrapperList
                            .add(new SopWrapper(sop.getKdSop(), sop.getSop()));
                }

            }

        }

        SopUraianTugasJabatanListWrapper sopUraianTugasJabatanListWrapper
                = new SopUraianTugasJabatanListWrapper(currentSopWrapperList, notSopWrapperList);

        return new ResponseEntity<Object>(sopUraianTugasJabatanListWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-sop", method = RequestMethod.POST)
    ResponseEntity<?> createSop(@RequestBody SopInputWrapper sopInputWrapper) {
        LOGGER.info("create new sop");

        Sop sop = new Sop();
        sop.setKdSop(String.valueOf(new Date().getTime()));
        sop.setSop(sopInputWrapper.getSop());

        sopService.save(sop);

        return new ResponseEntity<Object>(new CustomMessage("sop created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-sop", method = RequestMethod.PUT)
    ResponseEntity<?> updateSop(@RequestBody SopInputWrapper sopInputWrapper) {
        LOGGER.info("update sop "+sopInputWrapper.getKdSop());

        Sop sop = new Sop();
        sop.setKdSop(sopInputWrapper.getKdSop());
        sop.setSop(sopInputWrapper.getSop());

        sopService.update(sop);

        return new ResponseEntity<Object>(new CustomMessage("sop updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-sop/{kdSop}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSop(@PathVariable("kdSop") String kdSop) {
        LOGGER.info("delete sop "+kdSop);

        sopService.delete(kdSop);

        return new ResponseEntity<Object>(new CustomMessage("sop deleted"), HttpStatus.OK);
    }

}
