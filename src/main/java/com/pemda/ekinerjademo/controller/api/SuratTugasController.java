package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratTugasService;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.wrapper.input.SuratTugasInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bagus on 29/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratTugasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratTugasController.class);

    @Autowired
    private SuratTugasService suratTugasService;
    @Autowired
    private QutPegawaiService qutPegawaiService;

    @RequestMapping(value = "/create-surat-tugas", method = RequestMethod.POST)
    ResponseEntity<?> createSuratTugas(@RequestBody SuratTugasInputWrapper inputWrapper) {
        LOGGER.info("create surat tugas");

        EkinerjaXMLBuilder ekinerjaXMLBuilder
                = new EkinerjaXMLBuilder();

        String kdSuratTugas
                = String.valueOf(new Date().getTime());

        Set<TargetSuratTugasPegawai> targetSuratTugasPegawaiSet
                = new HashSet<>();
        Set<TargetSuratTugasPejabat> targetSuratTugasPejabatSet
                = new HashSet<>();
        Set<TembusanSuratTugas> tembusanSuratTugasSet
                = new HashSet<>();
        //build target surat tugas pegawai
        for (String nippegawai
                : inputWrapper.getTargetSuratTugasPegawaiSet()) {
            TargetSuratTugasPegawaiId id
                    = new TargetSuratTugasPegawaiId(kdSuratTugas, nippegawai);
            TargetSuratTugasPegawai targetSuratTugasPegawai
                    = new TargetSuratTugasPegawai();

            targetSuratTugasPegawai.setTargetSuratTugasPegawaiId(id);
            targetSuratTugasPegawai.setApproveStatus(0);
            targetSuratTugasPegawai.setStatusBaca(0);
            targetSuratTugasPegawai.setStatusDiterima(0);

            targetSuratTugasPegawaiSet.add(targetSuratTugasPegawai);
        }
        //build target surat tugas pejabat
        for (String kdJabatan
                : inputWrapper.getTargetSuratTugasPejabatSet()) {
            TargetSuratTugasPejabatId id
                    = new TargetSuratTugasPejabatId(kdSuratTugas, kdJabatan);
            TargetSuratTugasPejabat targetSuratTugasPejabat
                    = new TargetSuratTugasPejabat();

            targetSuratTugasPejabat.setTargetSuratTugasPejabatId(id);
            targetSuratTugasPejabat.setApproveStatus(0);
            targetSuratTugasPejabat.setStatusBaca(0);
            targetSuratTugasPejabat.setStatusDiterima(0);

            targetSuratTugasPejabatSet.add(targetSuratTugasPejabat);
        }
        //build tembusan surat tugas
        for (String kdJabatan
                : inputWrapper.getTembusanSuratTugasSet()) {
            TembusanSuratTugasId id
                    = new TembusanSuratTugasId(kdSuratTugas, kdJabatan);
            TembusanSuratTugas tembusanSuratTugas
                    = new TembusanSuratTugas();

            tembusanSuratTugas.setTembusanSuratTugasId(id);
            tembusanSuratTugas.setStatusBaca(0);
            tembusanSuratTugas.setStatusDiterima(0);

            tembusanSuratTugasSet.add(tembusanSuratTugas);
        }
        //build surat tugas
        SuratTugas suratTugas = new SuratTugas();

        suratTugas.setKdSuratTugas(kdSuratTugas);
        suratTugas.setNipPembuat(inputWrapper.getNipPembuat());
        suratTugas.setNipPenandatangan(inputWrapper.getNipPenandatangan());

        suratTugas.setNomorSurat1(0);
        suratTugas.setNomorSurat2(inputWrapper.getNomorUrusan());
        suratTugas.setNomorSurat3(inputWrapper.getNomorUnit());
        suratTugas.setNomorTahun(Year.now().getValue());
        suratTugas.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());

        suratTugas.setMenimbang(
                ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getMenimbang(), "menimbang"));
        suratTugas.setDasar(
                ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getDasar(), "dasar"));
        suratTugas.setUntuk(
                ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getUntuk(), "untuk"));

        suratTugas.setTempat(inputWrapper.getTempat());
        suratTugas.setTanggalTugasMilis(new Date().getTime());
        suratTugas.setTtdPath("");
        suratTugas.setStatusPenyebaran(0);
        suratTugas.setApprovalPenandatangan(0);
        if (inputWrapper.getKdSuratTugasBawahan() == null) {
            suratTugas.setPathPenilaian(kdSuratTugas);
        } else {
            SuratTugas suratTugasBawahan
                    = suratTugasService.getByKdSuratTugas(inputWrapper.getKdSuratTugasBawahan());
            suratTugas.setPathPenilaian(suratTugasBawahan.getPathPenilaian()+"."+kdSuratTugas);
        }


        return new ResponseEntity<Object>(new CustomMessage("surat tugas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/approve-surat-tugas/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratTugas(@PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("approve surat tugas");

        return new ResponseEntity<Object>(new CustomMessage("surat tugas approved"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-pembuat/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasHistoryByPembuat(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get surat tugas by pembuat");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasByTarget(
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat tugas by target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasByTargetUnread(
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat tugas unread by target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-kd-surat/{kdSuratTugas}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasbyKdSuratTugas(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("get surat tugas by kode surat");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-tugas/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratTugas(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("open surat tugas");

        return new ResponseEntity<Object>(new CustomMessage("surat tugas opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-tugas-by-penilai/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratTugasByPenilai(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("open surat tugas by penilai");

        SuratTugas suratTugas;

        return new ResponseEntity<Object>(new CustomMessage("surat tugas opened"), HttpStatus.OK);
    }

}
