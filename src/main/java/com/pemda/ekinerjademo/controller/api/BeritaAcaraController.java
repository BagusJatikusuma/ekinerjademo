package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.BeritaAcaraService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.util.BarcodeGenerator;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.BeritaAcaraHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.BeritaAcaraWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratPerintahHistoryWrapper;
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
    @Autowired private TkdUnkDao tkdUnkDao;

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
        beritaAcara.setStatusBaca(0);
        beritaAcara.setStatusPenyebaran(0);

        beritaAcara.setKdUrtug(inputWrapper.getKdUrtug());
        beritaAcara.setTahunUrtug(inputWrapper.getTahunUrtug());

        if (inputWrapper.getKdBeritaAcaraBawahan() == null) {
            beritaAcara.setPathPenilaian(kdBeritaAcara);
            beritaAcara.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            beritaAcara.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            BeritaAcara beritaAcaraBawahan
                    = beritaAcaraService.getBeritaAcara(inputWrapper.getKdBeritaAcaraBawahan());
            beritaAcara.setPathPenilaian(beritaAcaraBawahan.getPathPenilaian()+"."+kdBeritaAcara);
            beritaAcara.setKdNaskahPenugasan(beritaAcaraBawahan.getKdNaskahPenugasan());
            beritaAcara.setJenisNaskahPenugasan(beritaAcaraBawahan.getJenisNaskahPenugasan());

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

        //proses approve
        beritaAcaraService.approveBeritaAcara(kdBeritaAcara);

        return new ResponseEntity<Object>(new CustomMessage("berita acara approved"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-berita-acara-history-by-pegawai/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarBeritaAcaraHistoryByPegawai(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get berita acara history by nip "+nipPembuatSurat);

        List<BeritaAcara> beritaAcaraList
                = beritaAcaraService.getByNipPembuatSurat(nipPembuatSurat);
        List<SuratPerintahHistoryWrapper> beritaAcaraHistoryList
                = new ArrayList<>();

        for (BeritaAcara beritaAcara
                : beritaAcaraList) {
            beritaAcaraHistoryList
                    .add(new SuratPerintahHistoryWrapper(
                            beritaAcara.getKdBeritaAcara(),
                            "",
                            false,
                            beritaAcara.getStatusBaca(),
                            "Berita Acara",
                            0,
                            beritaAcara.getTanggalPembuatanMilis(),
                            beritaAcara.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(beritaAcaraHistoryList, HttpStatus.OK);
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
    ResponseEntity<?> getBeritaAcaraByKdBeritaAcara(@PathVariable("kdBeritaAcara") String kdBeritaAcara) {
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

        String base64BarcodeImage = null;
//        String kdBarcode
//                = beritaAcara.getKdBarcode()+beritaAcara.getNomorUrut()+beritaAcara.getKdUnitKerja()+"0";

        if (beritaAcara.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(beritaAcara.getKdBarcode()));
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
                        tkdUnkDao.findOne(pihakKesatu.getKdUnitKerja()).getUnitKerja(),
                        pihakKesatu.getPangkat(),
                        pihakKesatu.getGol(),
                        beritaAcara.getPeranPihakKesatu(),
                        pihakKesatu.getGlrDpn(),
                        pihakKesatu.getGlrBlk(),
                        beritaAcara.getStatusApprovalPihakKesatu(),
                        pihakKedua.getNip(),
                        pihakKedua.getNama(),
                        pihakKedua.getJabatan(),
                        tkdUnkDao.findOne(pihakKedua.getKdUnitKerja()).getUnitKerja(),
                        pihakKedua.getPangkat(),
                        pihakKedua.getGol(),
                        beritaAcara.getPeranPihakKedua(),
                        pihakKedua.getGlrDpn(),
                        pihakKedua.getGlrBlk(),
                        beritaAcara.getStatusApprovalPihakKedua(),
                        ekinerjaXMLParser
                                .convertXmlSuratPerintahIntoListofString(beritaAcara.getIsiBeritaAcara(), "isi"),
                        beritaAcara.getDasarBeritaAcara(),
                        pihakMengetahui.getNip(),
                        pihakMengetahui.getNama(),
                        pihakMengetahui.getJabatan(),
                        tkdUnkDao.findOne(pihakMengetahui.getKdUnitKerja()).getUnitKerja(),
                        pihakMengetahui.getPangkat(),
                        pihakMengetahui.getGlrDpn(),
                        pihakMengetahui.getGlrBlk(),
                        beritaAcara.getKotaPembuatanSurat(),
                        beritaAcara.getTanggalPembuatanMilis(),
                        base64BarcodeImage);

        return new ResponseEntity<Object>(beritaAcaraWrapper, HttpStatus.OK);
    }

    /**
     *
     * digunakan oleh lembardisposisicontroller
     *
     * @param kdBeritaAcara
     * @return
     */
    public BeritaAcaraWrapper getBeritaAcaraWrapper(String kdBeritaAcara) {
        BeritaAcara beritaAcara = beritaAcaraService.getBeritaAcara(kdBeritaAcara);

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

        String base64BarcodeImage = null;
//        String kdBarcode
//                = beritaAcara.getKdBarcode()+beritaAcara.getNomorUrut()+beritaAcara.getKdUnitKerja()+"0";

        if (beritaAcara.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(beritaAcara.getKdBarcode()));
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
                tkdUnkDao.findOne(pihakKesatu.getKdUnitKerja()).getUnitKerja(),
                pihakKesatu.getPangkat(),
                pihakKesatu.getGol(),
                beritaAcara.getPeranPihakKesatu(),
                pihakKesatu.getGlrDpn(),
                pihakKesatu.getGlrBlk(),
                beritaAcara.getStatusApprovalPihakKesatu(),
                pihakKedua.getNip(),
                pihakKedua.getNama(),
                pihakKedua.getJabatan(),
                tkdUnkDao.findOne(pihakKedua.getKdUnitKerja()).getUnitKerja(),
                pihakKedua.getPangkat(),
                pihakKedua.getGol(),
                beritaAcara.getPeranPihakKedua(),
                pihakKedua.getGlrDpn(),
                pihakKedua.getGlrBlk(),
                beritaAcara.getStatusApprovalPihakKedua(),
                ekinerjaXMLParser
                        .convertXmlSuratPerintahIntoListofString(beritaAcara.getIsiBeritaAcara(), "isi"),
                beritaAcara.getDasarBeritaAcara(),
                pihakMengetahui.getNip(),
                pihakMengetahui.getNama(),
                pihakMengetahui.getJabatan(),
                tkdUnkDao.findOne(pihakMengetahui.getKdUnitKerja()).getUnitKerja(),
                pihakMengetahui.getPangkat(),
                pihakMengetahui.getGlrDpn(),
                pihakMengetahui.getGlrBlk(),
                beritaAcara.getKotaPembuatanSurat(),
                beritaAcara.getTanggalPembuatanMilis(),
                base64BarcodeImage);

        return beritaAcaraWrapper;
    }

}
