package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;
import com.pemda.ekinerjademo.service.PejabatPenilaiDinilaiService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.PejabatanPenilaiDinilaiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.QutPegawaiWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bagus on 25/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class PejabatPenilaiDinilaiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PejabatPenilaiDinilaiController.class);

    private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;
    private QutPegawaiService qutPegawaiService;

    @Autowired
    public PejabatPenilaiDinilaiController(
            PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService,
            QutPegawaiService qutPegawaiService) {
        this.pejabatPenilaiDinilaiService = pejabatPenilaiDinilaiService;
        this.qutPegawaiService = qutPegawaiService;
    }

    @RequestMapping(value = "/choose-pejabat-penilai", method = RequestMethod.POST)
    ResponseEntity<?> choosePejabatPenilai(PejabatanPenilaiDinilaiInputWrapper inputWrapper) {
        LOGGER.info("choose pejabat penilai dinilai");

        PejabatPenilaiDinilai pejabatPenilaiDinilai
                = pejabatPenilaiDinilaiService.findByKdJabatanDinilai(inputWrapper.getKdJabatanDinilai());

        if (pejabatPenilaiDinilai == null) {
            pejabatPenilaiDinilai
                    .setPejabatPenilaiDinilaiId(
                            new PejabatPenilaiDinilaiId(
                                    inputWrapper.getNipPenilai(),
                                    inputWrapper.getKdJabatanDinilai()));

            pejabatPenilaiDinilaiService.create(pejabatPenilaiDinilai);
        } else {
            pejabatPenilaiDinilaiService
                    .updatePejabatPenilaiByKdJabatanDinilai(
                            inputWrapper.getNipPenilai(),
                            inputWrapper.getKdJabatanDinilai());
        }

        return new ResponseEntity<Object>(new CustomMessage("pejabat penilai choosed"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pejabat-penilai/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getPejabatPenilai(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get pejabat penilai");

        PejabatPenilaiDinilai pejabatPenilaiDinilai
                = pejabatPenilaiDinilaiService.findByKdJabatanDinilai(kdJabatan);

        QutPegawai qutPegawai =
                qutPegawaiService
                        .getQutPegawai(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getNipPenilai());

        QutPegawaiWrapper qutPegawaiWrapper
                = new QutPegawaiWrapper(
                qutPegawai.getNip(),
                qutPegawai.getNama(),
                qutPegawai.getKdJabatan(),
                qutPegawai.getJabatan(),
                qutPegawai.getKdUnitKerja(),
                qutPegawai.getUnitKerja(),
                qutPegawai.getPangkat(),
                qutPegawai.getGol());

        return new ResponseEntity<Object>(qutPegawaiWrapper, HttpStatus.OK);
    }

}