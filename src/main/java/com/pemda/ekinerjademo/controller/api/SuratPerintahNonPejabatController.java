package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratPerintahNonPejabatService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratperintahNonPejabatInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
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
 * Created by bagus on 14/11/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratPerintahNonPejabatController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratPerintahNonPejabatController.class);

    @Autowired private SuratPerintahNonPejabatService suratPerintahNonPejabatService;
    @Autowired
    private QutPegawaiService qutPegawaiService;
    @Autowired
    private TkdJabatanService tkdJabatanService;

    @RequestMapping(value = "/create-surat-perintah-non-pejabat", method = RequestMethod.POST)
    ResponseEntity<?> createSuratPerintahNonPejabat(
            @RequestBody SuratperintahNonPejabatInputWrapper inputWrapper) {
        LOGGER.info("create surat perintah non pejabat");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String dasarInXml, untukInXml, menimbangInXml;
        Integer nomorSurat1 = 1;
        Set<TargetSuratPerintahNonPejabat> targetSuratPerintahNonPejabatList
                = new HashSet<>();
        Set<TembusanSuratPerintahNonPejabat> tembusanSuratPerintahNonPejabatList
                = new HashSet<>();
        SuratPerintahNonPejabat suratPerintahNonPejabat
                = new SuratPerintahNonPejabat();
        String kdSuratPerintah = String.valueOf(new Date().getTime());

        //build target list
        for (String kdTarget : inputWrapper.getKdTargetList()) {
            TargetSuratPerintahNonPejabatId targetId
                    = new TargetSuratPerintahNonPejabatId(kdSuratPerintah, kdTarget);
            TargetSuratPerintahNonPejabat targetSuratPerintahNonPejabat = new TargetSuratPerintahNonPejabat();

            targetSuratPerintahNonPejabat.setTargetSuratPerintahNonPejabatId(targetId);
            targetSuratPerintahNonPejabat.setApproveStatus(0);

            targetSuratPerintahNonPejabatList.add(targetSuratPerintahNonPejabat);
        }
        //build tembusan list
        for (String kdTembusan : inputWrapper.getKdTembusanList()) {
            TembusanSuratPerintahNonPejabatId tembusanId
                    = new TembusanSuratPerintahNonPejabatId(kdSuratPerintah, kdTembusan);
            TembusanSuratPerintahNonPejabat tembusanSuratPerintahNonPejabat = new TembusanSuratPerintahNonPejabat();

            tembusanSuratPerintahNonPejabat.setTembusanSuratPerintahNonPejabatId(tembusanId);

            tembusanSuratPerintahNonPejabatList.add(tembusanSuratPerintahNonPejabat);
        }
        //build dasar list in xml
        dasarInXml
                = ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getDasarList(), "dasar");
        //build untuk list in xml
        untukInXml
                = ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getUntukList(), "untuk");
        //build menimbang in xml
        menimbangInXml
                = ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getMenimbangList(), "menimbang");

        //set nomor surat based kdUnitKerja
        if (suratPerintahNonPejabatService.getLatestNomorSuratByUnitKerja(inputWrapper.getKdUnitKerja()) != null) {
            nomorSurat1
                    = suratPerintahNonPejabatService.getLatestNomorSuratByUnitKerja(inputWrapper.getKdUnitKerja()) + 1;
        }
        //build surat target perintah non pejabat
        suratPerintahNonPejabat.setKdSuratPerintah(kdSuratPerintah);
        suratPerintahNonPejabat.setNipPembuat(inputWrapper.getNipPembuat());
        suratPerintahNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratPerintahNonPejabat.setNomorSurat1(nomorSurat1);
        suratPerintahNonPejabat.setNomorSurat2(inputWrapper.getNomorSurat2());
        suratPerintahNonPejabat.setNomorSurat3(inputWrapper.getNomorSurat3());
        suratPerintahNonPejabat.setNomorTahun(Year.now().getValue());
        suratPerintahNonPejabat.setDasar(dasarInXml);
        suratPerintahNonPejabat.setUntuk(untukInXml);
        suratPerintahNonPejabat.setMenimbang(menimbangInXml);
        suratPerintahNonPejabat.setTempat(inputWrapper.getTempat());
        suratPerintahNonPejabat.setTanggalPerintahMilis(inputWrapper.getTanggalPerintahMilis());
        suratPerintahNonPejabat.setKdJabatan(inputWrapper.getKdJabatan());
        suratPerintahNonPejabat.setTtdPath("random path");
//        suratPerintahNonPejabat.setTargetSuratPerintahNonPejabatList(targetSuratPerintahNonPejabatList);
//        suratPerintahNonPejabat.setTembusanSuratPerintahNonPejabatList(tembusanSuratPerintahNonPejabatList);

        suratPerintahNonPejabatService.creteSurat(suratPerintahNonPejabat);
        suratPerintahNonPejabatService.createTargetSurat(targetSuratPerintahNonPejabatList);
        suratPerintahNonPejabatService.createTembusanSurat(tembusanSuratPerintahNonPejabatList);

        return new ResponseEntity<Object>(
                new CustomMessage("surat perintah non pejabat creted"), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/get-daftar-surat-perintah-history-by-pegawai/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPerintahHistoryByPegawai(
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get surat perintah history by nip "+nipPegawai);

        Set<SuratPerintahNonPejabat> suratPerintahNonPejabatSet
                = suratPerintahNonPejabatService.getByNipPembuat(nipPegawai);

        Set<SuratPerintahHistoryWrapper> suratPerintahHistoryWrappers
                = new HashSet<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (SuratPerintahNonPejabat suratPerintahNonPejabat
                : suratPerintahNonPejabatSet) {
            suratPerintahHistoryWrappers
                    .add(new SuratPerintahHistoryWrapper(
                            suratPerintahNonPejabat.getKdSuratPerintah(),
                            df.format(new Date(suratPerintahNonPejabat.getTanggalPerintahMilis())),
                            false));
        }

        return new ResponseEntity<Object>(suratPerintahHistoryWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-surat-perintah-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPerintahTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat perintah nip "+nipTarget);

        List<TargetSuratPerintahNonPejabat> daftarSuratPerintahTarget
                = suratPerintahNonPejabatService.getSuratPerintahTarget(nipTarget);
        List<SuratPerintahTargetWrapper> daftarSuratPerintahTargetWrapper
                = new ArrayList<>();
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        Locale indoLocale = new Locale("id", "ID");

        for (TargetSuratPerintahNonPejabat suratTarget
                : daftarSuratPerintahTarget) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintahNonPejabat().getNipPembuat())) {
                    daftarSuratPerintahTargetWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    suratTarget.getSuratPerintahNonPejabat().getKdSuratPerintah(),
                                    DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintahNonPejabat().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                    false,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan())
                            );
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(daftarSuratPerintahTargetWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-perintah-non-pejabat-by-kd-surat/{kdSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratPerintahNonPejabatByKdSurat(
            @PathVariable("kdSurat") String kdSurat) {
        LOGGER.info("get surat perintah by kd surat "+kdSurat);

        SuratPerintahNonPejabat suratPerintahNonPejabat = null;
        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();


        suratPerintahNonPejabat
                = suratPerintahNonPejabatService.getSuratPerintahNonPejabatByKdSuratPerintah(kdSurat);

        if (suratPerintahNonPejabat == null)
            return new ResponseEntity<Object>(new CustomMessage("surat tidak ditemukan"), HttpStatus.OK);

        LOGGER.info(suratPerintahNonPejabat.getMenimbang());
        LOGGER.info(suratPerintahNonPejabat.getDasar());
        LOGGER.info(suratPerintahNonPejabat.getUntuk());

        String nomorSurat
                = String.valueOf(suratPerintahNonPejabat.getNomorSurat1()) +
                    suratPerintahNonPejabat.getNomorSurat2() +
                    suratPerintahNonPejabat.getNomorSurat3() +
                    String.valueOf(suratPerintahNonPejabat.getNomorTahun());
        List<String> menimbangList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratPerintahNonPejabat.getMenimbang(), "menimbang");
        List<String> dasarList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratPerintahNonPejabat.getDasar(), "dasar");
        List<String> untukList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratPerintahNonPejabat.getUntuk(), "untuk");

        //get all pegawai
        CustomPegawaiCredential pembuatSurat = null;
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<CustomPegawaiCredential> daftarTargetPegawaiSuratPerintah
                = new ArrayList<>();
        List<TkdJabatan> daftarTembusanSuratperintah
                = new ArrayList<>();

        //get pembuat surat credential
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(suratPerintahNonPejabat.getNipPembuat())) {
                pembuatSurat = qutPegawai;
                break;
            }
        }
        for (TargetSuratPerintahNonPejabat target
                :suratPerintahNonPejabat.getTargetSuratPerintahNonPejabatList()) {
            for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(target.getTargetSuratPerintahNonPejabatId().getNipPegawai())) {
                    daftarTargetPegawaiSuratPerintah.add(customPegawaiCredential);
                    break;
                }
            }
        }
        //get pejabat unit kerja name
        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        for (TembusanSuratPerintahNonPejabat tembusan
                :suratPerintahNonPejabat.getTembusanSuratPerintahNonPejabatList()) {
            LOGGER.info(tembusan.getTembusanSuratPerintahNonPejabatId().getKdJabatan());
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusan.getTembusanSuratPerintahNonPejabatId().getKdJabatan())) {
                    LOGGER.info("found");
                    daftarTembusanSuratperintah.add(tkdJabatan);
                    break;
                }

            }

        }

        Locale indoLocale = new Locale("id", "ID");
        String tanggalSuratDibuat
                = DateUtilities.createLocalDate(
                        new Date(suratPerintahNonPejabat.getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale);

        SuratPerintahNonPejabatDokumenWrapper suratPerintah
                = new SuratPerintahNonPejabatDokumenWrapper(
                        suratPerintahNonPejabat.getKdSuratPerintah(),
                        pembuatSurat.getNip(),
                        pembuatSurat.getNama(),
                        pembuatSurat.getKdUnitKerja(),
                        pembuatSurat.getUnitKerja(),
                        nomorSurat,
                        menimbangList,
                        dasarList,
                        untukList,
                        suratPerintahNonPejabat.getTempat(),
                        tanggalSuratDibuat,
                        pembuatSurat.getJabatan(),
                        "",
                        daftarTargetPegawaiSuratPerintah,
                        daftarTembusanSuratperintah);

        return new ResponseEntity<Object>(suratPerintah, HttpStatus.OK);
    }


}
