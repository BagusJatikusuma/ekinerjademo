package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.RekapitulasiPerilakuKerjaPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.RekapitulasiPerilakuKerjaPegawaiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private QutPegawaiCloneService qutPegawaiCloneService;

    @RequestMapping(value = "/get-rekapitulasi-perilaku-kerja-pegawai-by-kd-kdUnitKerja-and-bulanTahun/{kdUnitKerja}/{bulanTahunRekapitulasi}", method = RequestMethod.GET)
    ResponseEntity<?> getRekaptulasiPerilakuKerjaPegawaiByKdUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja, @PathVariable("bulanTahunRekapitulasi") long bulanTahunRekapitulasi) {

        List<QutPegawai> qutPegawaiClones = qutPegawaiCloneService.getQutPegawaiByUnitKerja(kdUnitKerja);

        List<RekapitulasiPerilakuKerjaPegawai> rekapitulasiPerilakuKerjaPegawaiTempList
                = rekapitulasiPerilakuKerjaPegawaiService.getRekaptulasiPerilakuKerjaPegawai(kdUnitKerja, bulanTahunRekapitulasi);
        List<RekapitulasiPerilakuKerjaPegawai> rekapitulasiPerilakuKerjaPegawaiList = new ArrayList<>();

        boolean found = false;
        for (QutPegawai pegawai : qutPegawaiClones) {
            found = false;
            for (RekapitulasiPerilakuKerjaPegawai rekapPegawaiInDB : rekapitulasiPerilakuKerjaPegawaiTempList) {
                if (pegawai.getNip().equals(rekapPegawaiInDB.getNipPegawai())) {
                    rekapitulasiPerilakuKerjaPegawaiList.add(rekapPegawaiInDB);

                    found = true;
                    break;
                }

                if (!found) {
                    RekapitulasiPerilakuKerjaPegawai rekapitulasiPerilakuKerjaPegawai = new RekapitulasiPerilakuKerjaPegawai();

                    rekapitulasiPerilakuKerjaPegawai.setKdUnitKerja(pegawai.getKdUnitKerja());
                    rekapitulasiPerilakuKerjaPegawai.setNamaJabatan(pegawai.getJabatan());
                    rekapitulasiPerilakuKerjaPegawai.setNipPegawai(pegawai.getNip());
                    rekapitulasiPerilakuKerjaPegawai.setNamaPegawai(pegawai.getNama());

                    rekapitulasiPerilakuKerjaPegawai.setDataHadir(0);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiHadir(0);

                    rekapitulasiPerilakuKerjaPegawai.setDataPerekamanDatangPulang(0);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiPerekamanDatangPulang(0);

                    rekapitulasiPerilakuKerjaPegawai.setDataHadirApel(0);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiHadirApel(0);

                    rekapitulasiPerilakuKerjaPegawai.setDataHadirRapat(0);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiHadirRapat(0);

                    rekapitulasiPerilakuKerjaPegawai.setDataRazia(0);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiRazia(0);

                    rekapitulasiPerilakuKerjaPegawai.setDataManipulasiData(false);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiManipulasiData(true);

                    rekapitulasiPerilakuKerjaPegawai.setTotalFaktorPeuranganTpp(0);
                    rekapitulasiPerilakuKerjaPegawai.setNilaiKebalikan(0);

                    rekapitulasiPerilakuKerjaPegawaiList.add(rekapitulasiPerilakuKerjaPegawai);
                }

            }
        }

        return new ResponseEntity<Object>(rekapitulasiPerilakuKerjaPegawaiList, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-rekapitulasi-perilaku-kerja-pegawai", method = RequestMethod.POST)
    ResponseEntity<?> createRekaptulasiPerilakuKerjaPegawai(@RequestBody RekapitulasiPerilakuKerjaPegawaiInputWrapper inputWrapper) {

        RekapitulasiPerilakuKerjaPegawai rekapitulasiPerilakuKerjaPegawai = new RekapitulasiPerilakuKerjaPegawai();

        rekapitulasiPerilakuKerjaPegawai.setKdRekap(String.valueOf(new Date().getTime()));

        rekapitulasiPerilakuKerjaPegawai.setBulanTahunRekapulasi(inputWrapper.getBulanTahunRekapitulasi());

        rekapitulasiPerilakuKerjaPegawai.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        rekapitulasiPerilakuKerjaPegawai.setNamaJabatan(inputWrapper.getNamaJabatan());
        rekapitulasiPerilakuKerjaPegawai.setNipPegawai(inputWrapper.getNipPegawai());
        rekapitulasiPerilakuKerjaPegawai.setNamaPegawai(inputWrapper.getNamaPegawai());

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
