package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplate;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplateId;
import com.pemda.ekinerjademo.service.SopTemplateService;
import com.pemda.ekinerjademo.wrapper.output.*;
import com.pemda.ekinerjademo.wrapper.output.SopTemplateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bagus on 08/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SopTemplateController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SopTemplateController.class);

    @Autowired private SopTemplateService sopTemplateService;

    @RequestMapping(value = "create-sop-template", method = RequestMethod.POST)
    ResponseEntity<?> createSopTemplate(@RequestBody SopTemplateWrapper sopTemplateWrapper) {
        LOGGER.info("create sop template");

        SopTemplate sopTemplate = new SopTemplate();
        sopTemplate
                .setSopTemplateId(
                        new SopTemplateId(
                                sopTemplateWrapper.getKdSop(),
                                sopTemplateWrapper.getKdTemplate()));

        sopTemplateService.save(sopTemplate);

        return new ResponseEntity<Object>(new CustomMessage("sop template created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete-sop-template", method = RequestMethod.POST)
    ResponseEntity<?> deleteSopTemplate(@RequestBody SopTemplateWrapper sopTemplateWrapper) {
        LOGGER.info("delete sop template");

        sopTemplateService
                .delete(new SopTemplateId(
                        sopTemplateWrapper.getKdSop(),
                        sopTemplateWrapper.getKdTemplate()));

        return new ResponseEntity<Object>(new CustomMessage("sop template deleted"), HttpStatus.OK);
    }

}
