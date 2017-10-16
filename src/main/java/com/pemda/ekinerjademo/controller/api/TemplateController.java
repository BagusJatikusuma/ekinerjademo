package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplate;
import com.pemda.ekinerjademo.model.ekinerjamodel.Template;
import com.pemda.ekinerjademo.service.SopTemplateService;
import com.pemda.ekinerjademo.service.TemplateService;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SopTemplateListWrapper;
import com.pemda.ekinerjademo.wrapper.output.TemplateWrapper;
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
 * Created by bagus on 08/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TemplateController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @Autowired private TemplateService templateService;
    @Autowired private SopTemplateService sopTemplateService;

    @RequestMapping(value = "/get-all-template", method = RequestMethod.GET)
    ResponseEntity<?> getAllTemplate() {
        LOGGER.info("get all template");

        List<Template> templateList = templateService.getAll();
        List<TemplateWrapper> templateWrapperList = new ArrayList<>();

        for (Template template : templateList) {
            templateWrapperList
                    .add(new TemplateWrapper(
                            template.getKdTemplate(),
                            template.getNmTemplate(),
                            template.getUrl()));
        }

        return new ResponseEntity<Object>(templateWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all-template-by-sop/{kdSop}", method = RequestMethod.GET)
    ResponseEntity<?> getAllTemplateBySop(@PathVariable("kdSop") String kdSop) {
        List<TemplateWrapper> currentTemplateWrapperList = new ArrayList<>();
        List<TemplateWrapper> notTemplateWrapperList = new ArrayList<>();
        SopTemplateListWrapper sopTemplateListWrapper = new SopTemplateListWrapper();

        List<Template> templateList = templateService.getAll();
        List<SopTemplate> sopTemplateList = sopTemplateService.getSopTemplateBySop(kdSop);

        if (sopTemplateList.isEmpty()) {
            for (Template template : templateList) {
                notTemplateWrapperList
                        .add(new TemplateWrapper(
                                template.getKdTemplate(),
                                template.getNmTemplate(),
                                template.getUrl()));
            }
        } else {
            Boolean found = false;
            for (Template template : templateList) {
                found = false;
                for (SopTemplate sopTemplate : sopTemplateList) {
                    if (template.getKdTemplate().equals(sopTemplate.getSopTemplateId().getKdTemplate())) {
                        currentTemplateWrapperList
                                .add(new TemplateWrapper(
                                        template.getKdTemplate(),
                                        template.getNmTemplate(),
                                        template.getUrl()));

                        found = true;
                        break;
                    }

                }

                if (!found) {
                    notTemplateWrapperList
                            .add(new TemplateWrapper(
                                    template.getKdTemplate(),
                                    template.getNmTemplate(),
                                    template.getUrl()));
                }

            }

        }

        sopTemplateListWrapper.setNotTemplateList(notTemplateWrapperList);
        sopTemplateListWrapper.setTemplateList(currentTemplateWrapperList);

        return new ResponseEntity<Object>(sopTemplateListWrapper, HttpStatus.OK);
    }



    @RequestMapping(value = "/create-template", method = RequestMethod.POST)
    ResponseEntity<?> createTemplate(@RequestBody TemplateWrapper templateWrapper) {
        LOGGER.info("create new template");

        Template template = new Template();
        template.setKdTemplate(String.valueOf(new Date().getTime()));
        template.setNmTemplate(templateWrapper.getNmTemplate());
        template.setUrl(templateWrapper.getUrl());

        templateService.save(template);

        return new ResponseEntity<Object>(new CustomMessage("sop created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-template", method = RequestMethod.PUT)
    ResponseEntity<?> updateTemplate(@RequestBody TemplateWrapper templateWrapper) {
        LOGGER.info("update template "+templateWrapper.getKdTemplate());

        Template template = new Template();
        template.setKdTemplate(templateWrapper.getKdTemplate());
        template.setNmTemplate(templateWrapper.getNmTemplate());
        template.setUrl(templateWrapper.getUrl());

        templateService.update(template);

        return new ResponseEntity<Object>(new CustomMessage("sop updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-template", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTemplate(@PathVariable("kdTemplate") String kdTemplate) {
        LOGGER.info("delete template");

        templateService.delete(kdTemplate);

        return new ResponseEntity<Object>(new CustomMessage("sop deleted"), HttpStatus.OK);
    }
}
