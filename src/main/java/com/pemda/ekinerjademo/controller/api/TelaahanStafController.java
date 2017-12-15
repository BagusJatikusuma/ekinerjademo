package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;
import com.pemda.ekinerjademo.service.SuratKuasaService;
import com.pemda.ekinerjademo.service.TelaahanStafService;
import com.pemda.ekinerjademo.wrapper.input.SuratKuasaInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.TelaahanStafInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Date;

/**
 * Created by bayu on 15/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TelaahanStafController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TelaahanStafController.class);

    @Autowired
    private TelaahanStafService telaahanStafService;

    @RequestMapping(value = "/create-telaahan-staf", method = RequestMethod.POST)
    ResponseEntity<?> createTelaahanStaf(
            @RequestBody TelaahanStafInputWrapper inputWrapper) {
        LOGGER.info("create telaahan staf");

        String kdTelahaanStaf = String.valueOf(new Date().getTime());

        TelaahanStaf telaahanStaf = new TelaahanStaf();

        telaahanStaf.setKdTelaahanStaf(kdTelahaanStaf);
        telaahanStaf.setTentang(inputWrapper.getTentang());
        telaahanStaf.setPersoalan(inputWrapper.getPersoalan());
        telaahanStaf.setPraanggapan(inputWrapper.getPraanggapan());
        telaahanStaf.setFaktaYangMempengaruhi(inputWrapper.getFaktaYangMempengaruhi());
        telaahanStaf.setAnalisis(inputWrapper.getAnalisis());
        telaahanStaf.setSimpulan(inputWrapper.getSimpulan());
        telaahanStaf.setSaran(inputWrapper.getSaran());
        telaahanStaf.setNipPenandatangan(inputWrapper.getNipPenandatangan());

        telaahanStaf.setTanggalPembuatanMilis(new Date().getTime());
        telaahanStaf.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        telaahanStaf.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        telaahanStafService.createTelaahanStaf(telaahanStaf);

        return new ResponseEntity<Object>(
                new CustomMessage("telaahan staf created"), HttpStatus.CREATED);
    }
}
