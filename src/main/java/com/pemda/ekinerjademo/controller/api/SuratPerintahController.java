package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratPerintahService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratPerintahInputWrapper;
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
public class SuratPerintahController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratPerintahController.class);

    @Autowired private SuratPerintahService suratPerintahService;
    @Autowired
    private QutPegawaiService qutPegawaiService;
    @Autowired
    private TkdJabatanService tkdJabatanService;

    @RequestMapping(value = "/create-surat-perintah", method = RequestMethod.POST)
    ResponseEntity<?> createSuratPerintah(
            @RequestBody SuratPerintahInputWrapper inputWrapper) {
        LOGGER.info("create surat perintah non pejabat");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String dasarInXml, untukInXml, menimbangInXml;
        Integer nomorSurat1 = 1;

        Set<TargetSuratPerintahPegawai> targetSuratPerintahPegawaiList
                = new HashSet<>();
        Set<TargetSuratPerintahPejabat> targetSuratPerintahPejabatSet
                = new HashSet<>();
        Set<TembusanSuratPerintah> tembusanSuratPerintahList
                = new HashSet<>();
        SuratPerintah suratPerintah
                = new SuratPerintah();

        String kdSuratPerintah = String.valueOf(new Date().getTime());

        //build target pegawai list
        for (String kdTarget : inputWrapper.getKdTargetPegawaiList()) {
            TargetSuratPerintahPegawaiId targetId
                    = new TargetSuratPerintahPegawaiId(kdSuratPerintah, kdTarget);
            TargetSuratPerintahPegawai targetSuratPerintahPegawai = new TargetSuratPerintahPegawai();

            targetSuratPerintahPegawai.setTargetSuratPerintahPegawaiId(targetId);
            targetSuratPerintahPegawai.setApproveStatus(0);

            targetSuratPerintahPegawaiList.add(targetSuratPerintahPegawai);
        }
        //build target pejabat list
        for (String kdTargetPejabat : inputWrapper.getKdTargetPejabatList()) {
            TargetSuratPerintahPejabatId targetId
                    = new TargetSuratPerintahPejabatId(kdSuratPerintah, kdTargetPejabat);
            TargetSuratPerintahPejabat targetSuratPerintahPejabat = new TargetSuratPerintahPejabat();

            targetSuratPerintahPejabat.setTargetSuratPerintahPejabatId(targetId);
            targetSuratPerintahPejabat.setApproveStatus(0);

            targetSuratPerintahPejabatSet.add(targetSuratPerintahPejabat);
        }

        //build tembusan list
        for (String kdTembusan : inputWrapper.getKdTembusanList()) {
            TembusanSuratPerintahId tembusanId
                    = new TembusanSuratPerintahId(kdSuratPerintah, kdTembusan);
            TembusanSuratPerintah tembusanSuratPerintah = new TembusanSuratPerintah();

            tembusanSuratPerintah.setTembusanSuratPerintahId(tembusanId);

            tembusanSuratPerintahList.add(tembusanSuratPerintah);
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
        if (suratPerintahService.getLatestNomorSuratByUnitKerja(inputWrapper.getKdUnitKerja()) != null) {
            nomorSurat1
                    = suratPerintahService.getLatestNomorSuratByUnitKerja(inputWrapper.getKdUnitKerja()) + 1;
        }
        //build surat target perintah non pejabat
        suratPerintah.setKdSuratPerintah(kdSuratPerintah);
        suratPerintah.setNipPembuat(inputWrapper.getNipPembuat());
        suratPerintah.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratPerintah.setNomorSurat1(nomorSurat1);
        suratPerintah.setNomorSurat2(inputWrapper.getNomorSurat2());
        suratPerintah.setNomorSurat3(inputWrapper.getNomorSurat3());
        suratPerintah.setNomorTahun(Year.now().getValue());
        suratPerintah.setDasar(dasarInXml);
        suratPerintah.setUntuk(untukInXml);
        suratPerintah.setMenimbang(menimbangInXml);
        suratPerintah.setTempat(inputWrapper.getTempat());
        suratPerintah.setTanggalPerintahMilis(inputWrapper.getTanggalPerintahMilis());
        suratPerintah.setTtdPath("random path");
        suratPerintah.setKdUnitKerja(inputWrapper.getKdUnitKerja());
//        suratPerintah.setTargetSuratPerintahPegawaiList(targetSuratPerintahPegawaiList);
//        suratPerintah.setTembusanSuratPerintahList(tembusanSuratPerintahList);

        suratPerintahService.creteSurat(suratPerintah);
        suratPerintahService.createTargetSuratPegawai(targetSuratPerintahPegawaiList);
        suratPerintahService.createTargetSuratPejabat(targetSuratPerintahPejabatSet);
        suratPerintahService.createTembusanSurat(tembusanSuratPerintahList);

        if (inputWrapper.isSuratPejabat()) {
            SuratPerintahPejabat suratPerintahPejabat
                    = new SuratPerintahPejabat();
            suratPerintahPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            suratPerintahPejabat.setKdSuratPerintah(kdSuratPerintah);

            suratPerintahService.createSuratPerintahPejabat(suratPerintahPejabat);
        } else {
            SuratPerintahNonPejabat suratPerintahNonPejabat
                    = new SuratPerintahNonPejabat();
            suratPerintahNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());
            suratPerintahNonPejabat.setKdSuratPerintah(kdSuratPerintah);

            suratPerintahService.createSuratPerintahNonPejabat(suratPerintahNonPejabat);
        }

        return new ResponseEntity<Object>(
                new CustomMessage("surat perintah creted"), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/get-daftar-surat-perintah-history-by-pegawai/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPerintahHistoryByPegawai(
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get surat perintah history by nip "+nipPegawai);

        Set<SuratPerintah> suratPerintahSet
                = suratPerintahService.getByNipPembuat(nipPegawai);

        Set<SuratPerintahHistoryWrapper> suratPerintahHistoryWrappers
                = new HashSet<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;
        for (SuratPerintah suratPerintah
                : suratPerintahSet) {

            if (suratPerintah.getSuratPerintahPejabat() != null)
                isSuratPejabat = true;
            else
                isSuratPejabat = false;

            suratPerintahHistoryWrappers
                    .add(new SuratPerintahHistoryWrapper(
                            suratPerintah.getKdSuratPerintah(),
                            df.format(new Date(suratPerintah.getTanggalPerintahMilis())),
                            isSuratPejabat));
        }

        return new ResponseEntity<Object>(suratPerintahHistoryWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-surat-perintah-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPerintahTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat perintah nip "+nipTarget);

        List<TargetSuratPerintahPegawai> daftarSuratPerintahTarget
                = suratPerintahService.getTargetSuratPerintahPegawai(nipTarget);
        List<SuratPerintahTargetWrapper> daftarSuratPerintahTargetWrapper
                = new ArrayList<>();
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        Locale indoLocale = new Locale("id", "ID");

        boolean isSuratPejabat = false;
        for (TargetSuratPerintahPegawai suratTarget
                : daftarSuratPerintahTarget) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPembuat())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    daftarSuratPerintahTargetWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                    DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                    isSuratPejabat,
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

    @RequestMapping(value = "/get-surat-perintah-by-kd-surat/{kdSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratPerintahtByKdSurat(
            @PathVariable("kdSurat") String kdSurat) {
        LOGGER.info("get surat perintah by kd surat "+kdSurat);

        SuratPerintah suratPerintah = null;
        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        suratPerintah
                = suratPerintahService.getSuratPerintahByKdSuratPerintah(kdSurat);

        if (suratPerintah == null)
            return new ResponseEntity<Object>(new CustomMessage("surat tidak ditemukan"), HttpStatus.OK);

        LOGGER.info(suratPerintah.getMenimbang());
        LOGGER.info(suratPerintah.getDasar());
        LOGGER.info(suratPerintah.getUntuk());

        String nomorSurat
                = String.valueOf(suratPerintah.getNomorSurat1()) +
                    suratPerintah.getNomorSurat2() +
                    suratPerintah.getNomorSurat3() +
                    String.valueOf(suratPerintah.getNomorTahun());
        List<String> menimbangList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratPerintah.getMenimbang(), "menimbang");
        List<String> dasarList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratPerintah.getDasar(), "dasar");
        List<String> untukList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratPerintah.getUntuk(), "untuk");

        //get all pegawai
        CustomPegawaiCredential penandatanganSurat = null;
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<CustomPegawaiCredential> daftarTargetPegawaiSuratPerintah
                = new ArrayList<>();
        List<TkdJabatan> daftarTargetPejabatSuratPerintah
                = new ArrayList<>();
        List<TkdJabatan> daftarTembusanSuratperintah
                = new ArrayList<>();

        //get pembuat surat credential
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(suratPerintah.getNipPenandatangan())) {
                penandatanganSurat = qutPegawai;
                break;
            }
        }
        for (TargetSuratPerintahPegawai target
                :suratPerintah.getTargetSuratPerintahPegawaiList()) {
            for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(target.getTargetSuratPerintahPegawaiId().getNipPegawai())) {
                    daftarTargetPegawaiSuratPerintah.add(customPegawaiCredential);
                    break;
                }
            }
        }
        //get pejabat unit kerja name
        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        for (TargetSuratPerintahPejabat target
                : suratPerintah.getTargetSuratPerintahPejabatSet()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(target.getTargetSuratPerintahPejabatId().getKdJabatan())) {
                    daftarTargetPejabatSuratPerintah.add(tkdJabatan);
                    break;

                }

            }

        }

        for (TembusanSuratPerintah tembusan
                :suratPerintah.getTembusanSuratPerintahList()) {
            LOGGER.info(tembusan.getTembusanSuratPerintahId().getKdJabatan());
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusan.getTembusanSuratPerintahId().getKdJabatan())) {
                    LOGGER.info("found");
                    daftarTembusanSuratperintah.add(tkdJabatan);
                    break;
                }

            }

        }

        Locale indoLocale = new Locale("id", "ID");
        String tanggalSuratDibuat
                = DateUtilities.createLocalDate(
                        new Date(suratPerintah.getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale);

        boolean isSuratPejabat = false;
        String kdUnitKerjaPenandatangan = null;
        String unitKerjaPenandatangan = null;
        String kdJabatanPenandatangan = null;
        String jabatanPenandatangan = null;

        if (suratPerintah.getSuratPerintahPejabat() != null) {
            isSuratPejabat = true;
            kdJabatanPenandatangan = penandatanganSurat.getKdJabatan();
            jabatanPenandatangan = penandatanganSurat.getJabatan();
        } else {
            kdUnitKerjaPenandatangan = penandatanganSurat.getKdUnitKerja();
            unitKerjaPenandatangan = penandatanganSurat.getUnitKerja();
        }

        SuratPerintahNonPejabatDokumenWrapper suratPerintahWrapper
                = new SuratPerintahNonPejabatDokumenWrapper(
                        suratPerintah.getKdSuratPerintah(),
                        penandatanganSurat.getNip(),
                        penandatanganSurat.getNama(),
                        nomorSurat,
                        menimbangList,
                        dasarList,
                        untukList,
                        suratPerintah.getTempat(),
                        tanggalSuratDibuat,
                        penandatanganSurat.getJabatan(),
                        "",
                        daftarTargetPegawaiSuratPerintah,
                        daftarTargetPejabatSuratPerintah,
                        daftarTembusanSuratperintah,
                        isSuratPejabat,
                        kdUnitKerjaPenandatangan,
                        unitKerjaPenandatangan,
                        kdJabatanPenandatangan,
                        jabatanPenandatangan);

        return new ResponseEntity<Object>(suratPerintah, HttpStatus.OK);
    }


}
