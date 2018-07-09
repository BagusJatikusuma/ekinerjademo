package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.JenisUrtug;
import com.pemda.ekinerjademo.service.JenisUrtugService;
import com.pemda.ekinerjademo.wrapper.output.JenisUrtugWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class JenisUrtugController {
    public static final Logger LOGGER = LoggerFactory.getLogger(JenisUrtugController.class);

    private JenisUrtugService jenisUrtugService;

    @Autowired
    public JenisUrtugController(JenisUrtugService jenisUrtugService) {
        this.jenisUrtugService = jenisUrtugService;
    }

    @RequestMapping(value = "/get-jenis-urtug", method = RequestMethod.GET)
    ResponseEntity<?> getJenisUrtug() {
        LOGGER.info("get jenis urtug");

        List<JenisUrtug> jenisUrtugList = jenisUrtugService.getJenisUrtug();
        List<JenisUrtugWrapper> jenisUrtugWrappers = new ArrayList<>();

        for (JenisUrtug jenisUrtug : jenisUrtugList) {
            jenisUrtugWrappers.add(
                    new JenisUrtugWrapper(jenisUrtug.getKdJenisUrtug(), jenisUrtug.getJenisUrtug()));
        }

        return new ResponseEntity<Object>(jenisUrtugWrappers, HttpStatus.OK);

    }

}
