package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.service.PejabatPenilaiDinilaiService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 25/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class PejabatPenilaiDinilaiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PejabatPenilaiDinilaiController.class);

    private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;
    private QutPegawaiCloneService qutPegawaiService;

    @Autowired
    public PejabatPenilaiDinilaiController(
            PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService,
            QutPegawaiCloneService qutPegawaiService) {
        this.pejabatPenilaiDinilaiService = pejabatPenilaiDinilaiService;
        this.qutPegawaiService = qutPegawaiService;
    }

    @RequestMapping(value = "/choose-pejabat-penilai", method = RequestMethod.POST)
    ResponseEntity<?> choosePejabatPenilai(@RequestBody PejabatanPenilaiDinilaiInputWrapper inputWrapper) {
        LOGGER.info("choose pejabat penilai dinilai");

        List<PejabatPenilaiDinilai> pejabatPenilaiDinilaiTempList
                = pejabatPenilaiDinilaiService.findByKdJabatanDinilai(inputWrapper.getKdJabatanDinilai());

        if (pejabatPenilaiDinilaiTempList.isEmpty()) {
            PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId
                    = new PejabatPenilaiDinilaiId(
                            inputWrapper.getNipPenilai(), inputWrapper.getKdJabatanDinilai());

            PejabatPenilaiDinilai pejabatPenilaiDinilaiTemp = new PejabatPenilaiDinilai();
            pejabatPenilaiDinilaiTemp
                    .setPejabatPenilaiDinilaiId(pejabatPenilaiDinilaiId);

            LOGGER.info(
                    pejabatPenilaiDinilaiTemp.getPejabatPenilaiDinilaiId().getNipPenilai()+
                    " : "+
                    pejabatPenilaiDinilaiTemp.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());

            pejabatPenilaiDinilaiService.create(pejabatPenilaiDinilaiTemp);
        } else {
            pejabatPenilaiDinilaiService
                    .delete(new PejabatPenilaiDinilaiId(
                            pejabatPenilaiDinilaiTempList.get(0).getPejabatPenilaiDinilaiId().getNipPenilai(),
                            pejabatPenilaiDinilaiTempList.get(0).getPejabatPenilaiDinilaiId().getKdJabatanDinilai()));

            PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId
                    = new PejabatPenilaiDinilaiId(
                    inputWrapper.getNipPenilai(), inputWrapper.getKdJabatanDinilai());

            PejabatPenilaiDinilai newPejabatPenilaiDinilai = new PejabatPenilaiDinilai();
            newPejabatPenilaiDinilai
                    .setPejabatPenilaiDinilaiId(pejabatPenilaiDinilaiId);

            pejabatPenilaiDinilaiService.create(newPejabatPenilaiDinilai);
        }

        return new ResponseEntity<Object>(new CustomMessage("pejabat penilai choosed"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pejabat-penilai/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getPejabatPenilai(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get pejabat penilai");

        List<PejabatPenilaiDinilai> pejabatPenilaiDinilaiList
                = pejabatPenilaiDinilaiService.findByKdJabatanDinilai(kdJabatan);

        QutPegawai qutPegawai =
                qutPegawaiService
                        .getQutPegawai(pejabatPenilaiDinilaiList.get(0).getPejabatPenilaiDinilaiId().getNipPenilai());

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

    @RequestMapping(value = "/get-pegawai-bawahan/{nipPenilai}", method = RequestMethod.GET)
    ResponseEntity<?> getPegawaiBawahan(@PathVariable("nipPenilai") String nipPenilai) {
        LOGGER.info("get pegawai bawahan");

        List<QutPegawaiClone> pegawaiBawahanList = new ArrayList<>();
        List<PejabatPenilaiDinilai> kdJabatanPegawaiBawahanList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);

        //ambil data pegawai bawahan terlebih dahulu
        for (PejabatPenilaiDinilai jabatan : kdJabatanPegawaiBawahanList) {
            List<QutPegawaiClone> pegawaiBawahanJabatanList
                    = qutPegawaiService.getQutPegawaiByKdJabatan(jabatan.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());
            for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanJabatanList) {
                pegawaiBawahanList.add(pegawaiBawahan);
            }
        }

        return new ResponseEntity<>(pegawaiBawahanList, HttpStatus.OK);
    }

}
