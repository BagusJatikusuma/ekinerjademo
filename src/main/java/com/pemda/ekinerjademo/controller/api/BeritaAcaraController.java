package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.service.BeritaAcaraService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.BeritaAcaraHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.BeritaAcaraWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import groovy.transform.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

/**
 * Created by bayu on 07/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class BeritaAcaraController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BeritaAcaraController.class);

    @Autowired private BeritaAcaraService beritaAcaraService;
    @Autowired private QutPegawaiService qutPegawaiService;

    @RequestMapping(value = "/berita-acara/create-berita-acara", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createBeritaAcara(
            @RequestBody BeritaAcaraInputWrapper inputWrapper) {
        LOGGER.info("create berita acara");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String kdBeritaAcara = String.valueOf(new Date().getTime());

        BeritaAcara beritaAcara = new BeritaAcara();

        beritaAcara.setKdBeritaAcara(kdBeritaAcara);
        beritaAcara.setNomorUrusan(inputWrapper.getNomorUrusan());
        beritaAcara.setNomorUrut(null);
        beritaAcara.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        beritaAcara.setNomorUnit(inputWrapper.getNomorUnit());
        beritaAcara.setNomorTahun(Year.now().getValue());

        beritaAcara.setNipPihakKesatu(inputWrapper.getNipPihakKesatu());
        beritaAcara.setPeranPihakKesatu(inputWrapper.getPeranPihakKesatu());
        beritaAcara.setStatusApprovalPihakKesatu(0);

        beritaAcara.setNipPihakKedua(inputWrapper.getNipPihakKedua());
        beritaAcara.setPeranPihakKedua(inputWrapper.getPeranPihakKedua());
        beritaAcara.setStatusApprovalPihakKedua(0);

        beritaAcara.setIsiBeritaAcara(
                ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(inputWrapper.getIsiBeritaAcara(), "isi"));
        beritaAcara.setDasarBeritaAcara(inputWrapper.getDasarBeritaAcara());
        beritaAcara.setNipMengetahui(inputWrapper.getNipMengetahui());
        beritaAcara.setStatusApprovalNipMengetahui(0);

        beritaAcara.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        beritaAcara.setTanggalPembuatanMilis(new Date().getTime());
        beritaAcara.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        beritaAcara.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        //tambahan revisi
        beritaAcara.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        beritaAcara.setNipPenilai("");
        beritaAcara.setStatusPenilaian(0);
        beritaAcara.setAlasanPenolakan("");

        if (inputWrapper.getKdBeritaAcaraBawahan() == null) {
            beritaAcara.setPathPenilaian(kdBeritaAcara);
        } else {
            BeritaAcara beritaAcaraBawahan
                    = beritaAcaraService.getBeritaAcara(inputWrapper.getKdBeritaAcaraBawahan());
            beritaAcara.setPathPenilaian(beritaAcaraBawahan.getPathPenilaian()+"."+kdBeritaAcara);

            beritaAcaraBawahan.setStatusPenilaian(2);
            beritaAcaraService.createBeritaAcara(beritaAcaraBawahan);
        }

        beritaAcaraService.createBeritaAcara(beritaAcara);

        return new ResponseEntity<Object>(
                new CustomMessage("berita acara created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/approve-berita-acara/{kdBeritaAcara}", method = RequestMethod.PUT)
    ResponseEntity<?> approveBeritaAcara(@PathVariable("kdBeritaAcara") String kdBeritaAcara) {
        LOGGER.info("approve berita acara");

        return new ResponseEntity<Object>(new CustomMessage("berita acara approved"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-berita-acara-history-by-pegawai/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarBeritaAcaraHistoryByPegawai(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get berita acara history by nip "+nipPembuatSurat);

        List<BeritaAcara> beritaAcaraList
                = beritaAcaraService.getByNipPembuatSurat(nipPembuatSurat);

        List<BeritaAcaraHistoryWrapper> beritaAcaraHistoryWrapperList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (BeritaAcara beritaAcara
                : beritaAcaraList) {

            beritaAcaraHistoryWrapperList
                    .add(new BeritaAcaraHistoryWrapper(
                            beritaAcara.getKdBeritaAcara(),
                            df.format(new Date(beritaAcara.getTanggalPembuatanMilis())),
                            beritaAcara.getStatusBaca()
                    ));
        }

        return new ResponseEntity<Object>(beritaAcaraHistoryWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-berita-acara-penilai/{kdBeritaAcara}/{nipPegawai}", method = RequestMethod.PUT)
    ResponseEntity<?> openBeritaAcaraPenilai(
            @PathVariable("kdBeritaAcara") String kdBeritaAcara,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("open berita acara");

        beritaAcaraService.openBeritaAcaraByPenilai(kdBeritaAcara);

        return new ResponseEntity<Object>(new CustomMessage("berita acara opened by penilai"), HttpStatus.OK);

    }

    @RequestMapping(value = "/get-berita-acara-by-kd-berita-acara/{kdBeritaAcara}", method = RequestMethod.GET)
    ResponseEntity<?> getBeritaAcaraByKdBeritaAcara(@RequestParam("kdBeritaAcara") String kdBeritaAcara) {
        LOGGER.info("get berita acara by kd berita acara");

        BeritaAcara beritaAcara = beritaAcaraService.getBeritaAcara(kdBeritaAcara);

        if (beritaAcara == null)
            return new ResponseEntity<Object>(new CustomMessage("berita acara tidak ditemukan"), HttpStatus.NOT_FOUND);

        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        //get all pegawai
        CustomPegawaiCredential
                pihakKesatu = null,
                pihakKedua = null,
                pihakMengetahui = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        // get pihak kesatu
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(beritaAcara.getNipPihakKesatu())) {
                pihakKesatu = qutPegawai;
                break;
            }
        }
        // get pihak kedua
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(beritaAcara.getNipPihakKedua())) {
                pihakKedua = qutPegawai;
                break;
            }
        }
        // get pihak mengetahui
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(beritaAcara.getNipMengetahui())) {
                pihakMengetahui = qutPegawai;
                break;
            }
        }

        BeritaAcaraWrapper beritaAcaraWrapper
                = new BeritaAcaraWrapper(
                        beritaAcara.getKdBeritaAcara(),
                        beritaAcara.getNomorUrusan(),
                        beritaAcara.getNomorUrut(),
                        beritaAcara.getNomorPasanganUrut(),
                        beritaAcara.getNomorUnit(),
                        beritaAcara.getNomorTahun(),
                        pihakKesatu.getNip(),
                        pihakKesatu.getNama(),
                        pihakKesatu.getJabatan(),
                        pihakKesatu.getUnitKerja(),
                        beritaAcara.getPeranPihakKesatu(),
                        beritaAcara.getStatusApprovalPihakKesatu(),
                        pihakKedua.getNip(),
                        pihakKedua.getNama(),
                        pihakKedua.getJabatan(),
                        pihakKedua.getUnitKerja(),
                        beritaAcara.getPeranPihakKedua(),
                        beritaAcara.getStatusApprovalPihakKedua(),
                        ekinerjaXMLParser
                                .convertXmlSuratPerintahIntoListofString(beritaAcara.getIsiBeritaAcara(), "isi"),
                        beritaAcara.getDasarBeritaAcara(),
                        pihakMengetahui.getNip(),
                        pihakMengetahui.getNama(),
                        pihakMengetahui.getJabatan(),
                        pihakMengetahui.getUnitKerja(),
                        beritaAcara.getKotaPembuatanSurat(),
                        beritaAcara.getTanggalPembuatanMilis());

        return new ResponseEntity<Object>(beritaAcaraWrapper, HttpStatus.OK);
    }

}
