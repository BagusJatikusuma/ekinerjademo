package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawaiId;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.RekapitulasiPerilakuKerjaPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.RekapitulasiPerilakuKerjaPegawaiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.RekapitulasiPerilakuKerjaPegawaiOutputWrapper;
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

        List<RekapitulasiPerilakuKerjaPegawaiOutputWrapper> rekapitulasiPerilakuKerjaPegawaiOutputWrappers = new ArrayList<>();

        boolean found = false;
        for (QutPegawai pegawai : qutPegawaiClones) {
            found = false;
            for (RekapitulasiPerilakuKerjaPegawai rekapPegawaiInDB : rekapitulasiPerilakuKerjaPegawaiTempList) {
                if (pegawai.getNip().equals(rekapPegawaiInDB.getRekapitulasiPerilakuKerjaPegawaiId().getNipPegawai())) {

                    RekapitulasiPerilakuKerjaPegawaiOutputWrapper rekapitulasiPerilakuKerjaPegawaiOutputWrapper = new RekapitulasiPerilakuKerjaPegawaiOutputWrapper();

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setBulanTahunRekapulasi(rekapPegawaiInDB.getRekapitulasiPerilakuKerjaPegawaiId().getBulanTahunRekapulasi());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNipPegawai(rekapPegawaiInDB.getRekapitulasiPerilakuKerjaPegawaiId().getNipPegawai());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setKdUnitKerja(rekapPegawaiInDB.getKdUnitKerja());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNamaJabatan(rekapPegawaiInDB.getNamaJabatan());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNamaPegawai(rekapPegawaiInDB.getNamaPegawai());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setDataHadir(rekapPegawaiInDB.getDataHadir());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiHadir(rekapPegawaiInDB.getNilaiHadir());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setDataPerekamanDatangPulang(rekapPegawaiInDB.getDataPerekamanDatangPulang());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiPerekamanDatangPulang(rekapPegawaiInDB.getNilaiPerekamanDatangPulang());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setDataHadirApel(rekapPegawaiInDB.getDataHadirApel());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiHadirApel(rekapPegawaiInDB.getNilaiHadirApel());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setDataHadirRapat(rekapPegawaiInDB.getDataHadirRapat());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiHadirRapat(rekapPegawaiInDB.getNilaiHadirRapat());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setDataRazia(rekapPegawaiInDB.getDataRazia());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiRazia(rekapPegawaiInDB.getNilaiRazia());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setDataManipulasiData(rekapPegawaiInDB.isDataManipulasiData());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiManipulasiData(rekapPegawaiInDB.isNilaiManipulasiData());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setTotalFaktorPeuranganTpp(rekapPegawaiInDB.getTotalFaktorPeuranganTpp());
                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setNilaiKebalikan(rekapPegawaiInDB.getNilaiKebalikan());

                    rekapitulasiPerilakuKerjaPegawaiOutputWrapper.setSudahDiatur(true);

                    rekapitulasiPerilakuKerjaPegawaiOutputWrappers.add(rekapitulasiPerilakuKerjaPegawaiOutputWrapper);


                    found = true;
                    break;
                }
            }

            if (!found) {
                RekapitulasiPerilakuKerjaPegawaiOutputWrapper rekapitulasiPerilakuKerjaPegawai = new RekapitulasiPerilakuKerjaPegawaiOutputWrapper();

                rekapitulasiPerilakuKerjaPegawai.setNipPegawai(pegawai.getNip());
                rekapitulasiPerilakuKerjaPegawai.setBulanTahunRekapulasi(bulanTahunRekapitulasi);

                rekapitulasiPerilakuKerjaPegawai.setKdUnitKerja(pegawai.getKdUnitKerja());
                rekapitulasiPerilakuKerjaPegawai.setNamaJabatan(pegawai.getJabatan());
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

                rekapitulasiPerilakuKerjaPegawai.setSudahDiatur(false);

                rekapitulasiPerilakuKerjaPegawaiOutputWrappers.add(rekapitulasiPerilakuKerjaPegawai);
            }

        }

        return new ResponseEntity<Object>(rekapitulasiPerilakuKerjaPegawaiOutputWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-rekapitulasi-perilaku-kerja-pegawai", method = RequestMethod.POST)
    ResponseEntity<?> createRekaptulasiPerilakuKerjaPegawai(@RequestBody RekapitulasiPerilakuKerjaPegawaiInputWrapper inputWrapper) {

        RekapitulasiPerilakuKerjaPegawai rekapitulasiPerilakuKerjaPegawai = new RekapitulasiPerilakuKerjaPegawai();

        RekapitulasiPerilakuKerjaPegawaiId id
                = new RekapitulasiPerilakuKerjaPegawaiId(inputWrapper.getBulanTahunRekapitulasi(), inputWrapper.getNipPegawai());

        rekapitulasiPerilakuKerjaPegawai.setRekapitulasiPerilakuKerjaPegawaiId(id);

        rekapitulasiPerilakuKerjaPegawai.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        rekapitulasiPerilakuKerjaPegawai.setNamaJabatan(inputWrapper.getNamaJabatan());
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
