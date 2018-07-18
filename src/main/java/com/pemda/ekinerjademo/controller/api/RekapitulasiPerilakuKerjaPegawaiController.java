package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;
import com.pemda.ekinerjademo.service.RekapitulasiPerilakuKerjaPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.RekapitulasiPerilakuKerjaPegawaiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bayu on 18/07/18.
 */

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class RekapitulasiPerilakuKerjaPegawaiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RekapitulasiPerilakuKerjaPegawaiController.class);

    @Autowired
    private RekapitulasiPerilakuKerjaPegawaiService rekapitulasiPerilakuKerjaPegawaiService;

    @RequestMapping(value = "/get-rekapitulasi-perilaku-kerja-pegawai-by-kd-kdUnitKerja/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getRekaptulasiPerilakuKerjaPegawaiByKdUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja) {

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-rekapitulasi-perilaku-kerja-pegawai", method = RequestMethod.POST)
    ResponseEntity<?> createRekaptulasiPerilakuKerjaPegawai(@RequestBody RekapitulasiPerilakuKerjaPegawaiInputWrapper inputWrapper) {

        RekapitulasiPerilakuKerjaPegawai rekapitulasiPerilakuKerjaPegawai = new RekapitulasiPerilakuKerjaPegawai();

        rekapitulasiPerilakuKerjaPegawai.setBulanTahunRekapulasi(inputWrapper.getBulanTahunRekapulasi());

        rekapitulasiPerilakuKerjaPegawai.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        rekapitulasiPerilakuKerjaPegawai.setKdJabatan(inputWrapper.getKdJabatan());
        rekapitulasiPerilakuKerjaPegawai.setNipPegawai(inputWrapper.getNipPegawai());

        rekapitulasiPerilakuKerjaPegawai.setDataHadir(inputWrapper.getDataHadir());
        rekapitulasiPerilakuKerjaPegawai.setNilaiHadir(inputWrapper.getNilaiHadir());

        rekapitulasiPerilakuKerjaPegawai.setDataPerekamanDatangPulang(inputWrapper.getDataPerekamanDatangPulang());
        rekapitulasiPerilakuKerjaPegawai.setNilaiPerekamanDatangPulang(inputWrapper.getNilaiPerekamanDatangPulang());

        rekapitulasiPerilakuKerjaPegawai.setDataHadirApel(inputWrapper.getDataHadirApel());
        rekapitulasiPerilakuKerjaPegawai.setNilaiHadirApel(inputWrapper.getNilaiHadirApel());

        rekapitulasiPerilakuKerjaPegawai.setDataHadirRapat(inputWrapper.getDataHadirRapat());
        rekapitulasiPerilakuKerjaPegawai.setNilaiHadirRapat(inputWrapper.getNilaiHadirRapat());

        rekapitulasiPerilakuKerjaPegawai.setDataRazia(inputWrapper.getDataRazia());
        rekapitulasiPerilakuKerjaPegawai.setNilaiRazia(inputWrapper.getNilaiRazia());

        rekapitulasiPerilakuKerjaPegawai.setDataManipulasiData(inputWrapper.isDataManipulasiData());
        rekapitulasiPerilakuKerjaPegawai.setNilaiManipulasiData(inputWrapper.isNilaiManipulasiData());

        rekapitulasiPerilakuKerjaPegawai.setTotalFaktorPeuranganTpp(inputWrapper.getTotalFaktorPeuranganTpp());
        rekapitulasiPerilakuKerjaPegawai.setNilaiKebalikan(inputWrapper.getNilaiKebalikan());

        //save
        rekapitulasiPerilakuKerjaPegawaiService.createRekaptulasiPerilakuKerjaPegawai(rekapitulasiPerilakuKerjaPegawai);

        return new ResponseEntity<Object>(new CustomMessage("1 rekapitulai perilaku kerja pegawai created"), HttpStatus.OK);
    }

}
