package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeputusan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratKeputusanService;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratKeputusanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratKeputusanWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratPerintahHistoryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratKeputusanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKeputusanController.class);

    @Autowired private SuratKeputusanService suratKeputusanService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-surat-keputusan", method = RequestMethod.POST)
    ResponseEntity<?> createSuratKeputusan(@RequestBody SuratKeputusanInputWrapper inputWrapper) {
        LOGGER.info("create surat keputusan");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String kdSuratKeputusan = String.valueOf(new Date().getTime());

        SuratKeputusan suratKeputusan = new SuratKeputusan();

        suratKeputusan.setKdSuratKeputusan(kdSuratKeputusan);
        suratKeputusan.setNomorUrut(0);
        suratKeputusan.setNomorTahun(Year.now().getValue());

        suratKeputusan.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratKeputusan.setSelaku(inputWrapper.getSelaku());
        suratKeputusan.setTentang(inputWrapper.getTentang());
        suratKeputusan
                .setMenimbang(ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(inputWrapper.getMenimbang(), "menimbang"));
        suratKeputusan
                .setMengingat(ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(inputWrapper.getMengingat(), "mengingat"));
        suratKeputusan
                .setMenetapkan(ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(inputWrapper.getMenetapkan(), "menetapkan"));

        suratKeputusan.setTanggalPembuatanMilis(new Date().getTime());
        suratKeputusan.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        suratKeputusan.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratKeputusan.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratKeputusan.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        if (inputWrapper.getKdSuratKeputusanBawahan() == null) {
            suratKeputusan.setPathPenilaian(kdSuratKeputusan);
            suratKeputusan.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            suratKeputusan.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            SuratKeputusan suratKeputusanBawahan
                    = suratKeputusanService.getByKdSuratKeputusan(inputWrapper.getKdSuratKeputusanBawahan());
            suratKeputusan.setPathPenilaian(suratKeputusanBawahan.getPathPenilaian()+"."+kdSuratKeputusan);
            suratKeputusan.setKdNaskahPenugasan(suratKeputusanBawahan.getKdNaskahPenugasan());
            suratKeputusan.setJenisNaskahPenugasan(suratKeputusanBawahan.getJenisNaskahPenugasan());

            suratKeputusanBawahan.setStatusPenilaian(2);
            suratKeputusanService.create(suratKeputusan);
        }

        suratKeputusan.setNipPenilai("");
        suratKeputusan.setStatusPenilaian(0);
        suratKeputusan.setAlasanPenolakan("");
        suratKeputusan.setStatusBaca(0);

        suratKeputusan.setKdUrtug(inputWrapper.getKdUrtug());
        suratKeputusan.setTahunUrtug(inputWrapper.getTahunUrtug());

        suratKeputusanService.create(suratKeputusan);

        return new ResponseEntity<Object>(new CustomMessage("surat keputusan created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("sebar surat keputusan");

        return new ResponseEntity<Object>(new CustomMessage("surat keputusan telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("sebar surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("terima surat keputusan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keputusan-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeputusanHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat keputusan history");

        List<SuratKeputusan> suratKeputusanList
                = suratKeputusanService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> suratKeputusanHistoryList
                = new ArrayList<>();

        for (SuratKeputusan suratKeputusan
                : suratKeputusanList) {
            suratKeputusanHistoryList
                    .add(new SuratPerintahHistoryWrapper(suratKeputusan.getKdSuratKeputusan(),
                            "",
                            false,
                            -1,
                            "surat keputusan",
                            7,
                            suratKeputusan.getTanggalPembuatanMilis(),
                            suratKeputusan.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratKeputusanHistoryList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keputusan-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeputusanTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat keputusan target");



        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keputusan-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeputusanTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat keputusan target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-keputusan-by-kd-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratKeputusanByKdSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("get surat keputusan kd surat keputusan");

        SuratKeputusan suratKeputusan = suratKeputusanService.getByKdSuratKeputusan(kdSuratKeputusan);

        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        CustomPegawaiCredential penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
            if (customPegawaiCredential.getNip()
                    .equals(suratKeputusan.getNipPenandatangan())) {
                penandatangan = customPegawaiCredential;
                break;
            }
        }

        SuratKeputusanWrapper suratKeputusanWrapper
                = new SuratKeputusanWrapper(
                        suratKeputusan.getKdSuratKeputusan(),
                        suratKeputusan.getNomorUrut(),
                        suratKeputusan.getNomorTahun(),
                        penandatangan.getNip(),
                        penandatangan.getNama(),
                        penandatangan.getJabatan(),
                        tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                        penandatangan.getGlrDpn(),
                        penandatangan.getGlrBlk(),
                        penandatangan.getPangkat(),
                        penandatangan.getGol(),
                        suratKeputusan.getSelaku(),
                        suratKeputusan.getTentang(),
                        ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(suratKeputusan.getMenimbang(), "menimbang"),
                        ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(suratKeputusan.getMengingat(), "mengingat"),
                        ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(suratKeputusan.getMenetapkan(), "menetapkan"),
                        suratKeputusan.getTanggalPembuatanMilis(),
                        suratKeputusan.getKotaPembuatanSurat(),
                        null);


        return new ResponseEntity<Object>(suratKeputusanWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keputusan/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeputusan(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("open surat keputusan");

        SuratKeputusan suratKeputusan
                = suratKeputusanService.getByKdSuratKeputusan(kdSuratKeputusan);
        suratKeputusan.setStatusPenilaian(1);
        return new ResponseEntity<Object>(new CustomMessage("surat keputusan opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keputusan-penilai/{kdSuratKeputusan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeputusanPenilai(@PathVariable("kdSuratKeputusan") String kdSuratKeputusan) {
        LOGGER.info("open surat keputusan penilai");

        SuratKeputusan suratKeputusan
                = suratKeputusanService.getByKdSuratKeputusan(kdSuratKeputusan);
        suratKeputusan.setStatusPenilaian(1);

        suratKeputusanService.create(suratKeputusan);
        return new ResponseEntity<Object>(new CustomMessage("surat keputusan opened by penilai"), HttpStatus.OK);

    }
}
