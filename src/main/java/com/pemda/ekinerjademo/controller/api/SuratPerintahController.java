package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratPerintahInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
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
 * Created by bagus on 14/11/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratPerintahController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratPerintahController.class);

    @Autowired
    private SuratPerintahService suratPerintahService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired
    private TkdJabatanService tkdJabatanService;
    @Autowired
    private TkdUnkDao tkdUnkDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @RequestMapping(value = "/create-surat-perintah", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createSuratPerintah(
            @RequestBody SuratPerintahInputWrapper inputWrapper) {
        LOGGER.info("create surat perintah non pejabat");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String dasarInXml, untukInXml, menimbangInXml;
        Integer nomorSurat1 = 0;

//        NomorUrutSuratUnitKerja nomorUrutSurat
//                = nomorUrutSuratUnitKerjaService.getNomorSuratByUnitKerjaAndTahun(inputWrapper.getKdUnitKerja(), Year.now().getValue());
//
//        if (nomorUrutSurat == null) {
//            nomorUrutSurat
//                    = new NomorUrutSuratUnitKerja();
//            nomorUrutSurat
//                    .setNomorUrutSuratUnitKerjaId(
//                            new NomorUrutSuratUnitKerjaId(inputWrapper.getKdUnitKerja(), Year.now().getValue()));
//            nomorUrutSurat.setNomorUrutSurat(1);
//
//            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
//        } else {
//            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);
//
//            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
//        }

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
            targetSuratPerintahPegawai.setStatusDiterima(0);
            targetSuratPerintahPegawai.setStatusBaca(0);

            targetSuratPerintahPegawaiList.add(targetSuratPerintahPegawai);
        }
        //build target pejabat list
        for (String kdTargetPejabat : inputWrapper.getKdTargetPejabatList()) {
            TargetSuratPerintahPejabatId targetId
                    = new TargetSuratPerintahPejabatId(kdSuratPerintah, kdTargetPejabat);
            TargetSuratPerintahPejabat targetSuratPerintahPejabat = new TargetSuratPerintahPejabat();

            targetSuratPerintahPejabat.setTargetSuratPerintahPejabatId(targetId);
            targetSuratPerintahPejabat.setApproveStatus(0);
            targetSuratPerintahPejabat.setStatusDiterima(0);
            targetSuratPerintahPejabat.setStatusBaca(0);

            targetSuratPerintahPejabatSet.add(targetSuratPerintahPejabat);
        }

        //build tembusan list
        for (String kdTembusan : inputWrapper.getKdTembusanList()) {
            TembusanSuratPerintahId tembusanId
                    = new TembusanSuratPerintahId(kdSuratPerintah, kdTembusan);
            TembusanSuratPerintah tembusanSuratPerintah = new TembusanSuratPerintah();

            tembusanSuratPerintah.setTembusanSuratPerintahId(tembusanId);
            tembusanSuratPerintah.setStatusDiterima(0);
            tembusanSuratPerintah.setStatusBaca(0);

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
        suratPerintah.setTanggalPerintahMilis(new Date().getTime());
        suratPerintah.setTtdPath("random path");
        suratPerintah.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratPerintah.setApprovalPenandatangan(0);
        suratPerintah.setStatusPenyebaran(0);
        suratPerintah.setPathPenilaian(kdSuratPerintah);
        suratPerintah.setStatusBaca(0);
        suratPerintah.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        suratPerintah.setNipPenilai("");
        suratPerintah.setStatusPenilaian(0);
        suratPerintah.setAlasanPenolakan("");

        suratPerintah.setKdUrtug(inputWrapper.getKdUrtug());
        suratPerintah.setTahunUrtug(inputWrapper.getTahunUrtug());
//        suratPerintah.setTargetSuratPerintahPegawaiList(targetSuratPerintahPegawaiList);
//        suratPerintah.setTembusanSuratPerintahList(tembusanSuratPerintahList);

        if (inputWrapper.getKdSuratPerintahBawahan() == null) {
            suratPerintah.setPathPenilaian(kdSuratPerintah);
            suratPerintah.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            suratPerintah.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            SuratPerintah suratPerintahBawahan
                    = suratPerintahService.getSuratPerintahByKdSuratPerintah(inputWrapper.getKdSuratPerintahBawahan());
            suratPerintah.setPathPenilaian(suratPerintahBawahan.getPathPenilaian()+"."+kdSuratPerintah);
            suratPerintah.setKdNaskahPenugasan(suratPerintahBawahan.getKdNaskahPenugasan());
            suratPerintah.setJenisNaskahPenugasan(suratPerintahBawahan.getJenisNaskahPenugasan());

            suratPerintahBawahan.setStatusPenilaian(2);
            suratPerintahService.creteSurat(suratPerintahBawahan);
        }

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
                new CustomMessage("surat perintah created"), HttpStatus.CREATED);

    }


    @RequestMapping(value = "/lanjut-surat-perintah-bawahan", method = RequestMethod.POST)
    ResponseEntity<?> lanjutSuratPerintahBawahan(
            @RequestBody SuratPerintahInputWrapper inputWrapper) {
        LOGGER.info("lanjut surat perintah bawahan");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String dasarInXml, untukInXml, menimbangInXml;
        Integer nomorSurat1 = 0;
        SuratPerintah suratPerintahBawahan
                = suratPerintahService.getSuratPerintahByKdSuratPerintah(inputWrapper.getKdSuratPerintahBawahan());

//        NomorUrutSuratUnitKerja nomorUrutSurat
//                = nomorUrutSuratUnitKerjaService.getNomorSuratByUnitKerjaAndTahun(inputWrapper.getKdUnitKerja(), Year.now().getValue());
//
//        if (nomorUrutSurat == null) {
//            nomorUrutSurat
//                    = new NomorUrutSuratUnitKerja();
//            nomorUrutSurat
//                    .setNomorUrutSuratUnitKerjaId(
//                            new NomorUrutSuratUnitKerjaId(inputWrapper.getKdUnitKerja(), Year.now().getValue()));
//            nomorUrutSurat.setNomorUrutSurat(1);
//
//            nomorUrutSuratUnitKerjaService.createNomorUrutSurat(nomorUrutSurat);
//        } else {
//            nomorUrutSurat.setNomorUrutSurat(nomorUrutSurat.getNomorUrutSurat() + 1);
//
//            nomorUrutSuratUnitKerjaService.updateNomorUrutSurat(nomorUrutSurat);
//        }

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
            targetSuratPerintahPegawai.setStatusDiterima(0);
            targetSuratPerintahPegawai.setStatusBaca(0);

            targetSuratPerintahPegawaiList.add(targetSuratPerintahPegawai);
        }
        //build target pejabat list
        for (String kdTargetPejabat : inputWrapper.getKdTargetPejabatList()) {
            TargetSuratPerintahPejabatId targetId
                    = new TargetSuratPerintahPejabatId(kdSuratPerintah, kdTargetPejabat);
            TargetSuratPerintahPejabat targetSuratPerintahPejabat = new TargetSuratPerintahPejabat();

            targetSuratPerintahPejabat.setTargetSuratPerintahPejabatId(targetId);
            targetSuratPerintahPejabat.setApproveStatus(0);
            targetSuratPerintahPejabat.setStatusDiterima(0);
            targetSuratPerintahPejabat.setStatusBaca(0);

            targetSuratPerintahPejabatSet.add(targetSuratPerintahPejabat);
        }

        //build tembusan list
        for (String kdTembusan : inputWrapper.getKdTembusanList()) {
            TembusanSuratPerintahId tembusanId
                    = new TembusanSuratPerintahId(kdSuratPerintah, kdTembusan);
            TembusanSuratPerintah tembusanSuratPerintah = new TembusanSuratPerintah();

            tembusanSuratPerintah.setTembusanSuratPerintahId(tembusanId);
            tembusanSuratPerintah.setStatusDiterima(0);
            tembusanSuratPerintah.setStatusBaca(0);

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
        suratPerintah.setApprovalPenandatangan(0);
        suratPerintah.setStatusPenyebaran(0);
        suratPerintah.setPathPenilaian(suratPerintahBawahan.getPathPenilaian()+"."+kdSuratPerintah);
        suratPerintah.setStatusBaca(0);
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
                new CustomMessage("surat sudah dinilai dan dilaporkan ke atasan"),
                HttpStatus.OK);
    }

    //sebar surat perintah
    @RequestMapping(value = "/sebar-surat-perintah/{kdSuratPerintah}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratPerintah(@PathVariable("kdSuratPerintah") String kdSuratPerintah) {
        LOGGER.info("sebar surat perintah");

        SuratPerintah suratPerintah
                = suratPerintahService.getSuratPerintahByKdSuratPerintah(kdSuratPerintah);
        suratPerintah.setStatusPenyebaran(1);

        suratPerintahService.update(suratPerintah);

        return new ResponseEntity<Object>(new CustomMessage("surat perintah sudah di sebar"), HttpStatus.OK);
    }

    //approve surat perintah
    @RequestMapping(value = "/approve-surat-perintah/{kdSuratPerintah}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratPerintah(
            @PathVariable("kdSuratPerintah") String kdSuratPerintah) {
        LOGGER.info("approve surat perintah");

        SuratPerintah suratPerintah
                = suratPerintahService.getSuratPerintahByKdSuratPerintah(kdSuratPerintah);
        suratPerintah.setApprovalPenandatangan(1);

        suratPerintahService.update(suratPerintah);

        return new ResponseEntity<Object>(new CustomMessage("surat perintah sudah disetujui"), HttpStatus.OK);
    }

    //terima surat perintah
    @RequestMapping(value = "/terima-surat-perintah/{kdSuratPerintah}/{kdUnitKerja}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratPerintah(
            @PathVariable("kdSuratPerintah") String kdSuratPerintah,
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("terima surat perintah");

        SuratPerintah suratPerintah
                = suratPerintahService.getSuratPerintahByKdSuratPerintah(kdSuratPerintah);
        List<QutPegawai> pegawaiUnitKerjaList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<TkdJabatan> jabatanUnitKerjaList
                = tkdJabatanService.getJabatanByUnitKerja(kdUnitKerja);

        for (TargetSuratPerintahPegawai targetSuratPerintahPegawai
             : suratPerintah.getTargetSuratPerintahPegawaiList()) {
            for (QutPegawai pegawai
                    : pegawaiUnitKerjaList) {
                if (targetSuratPerintahPegawai.getTargetSuratPerintahPegawaiId().getNipPegawai()
                        .equals(pegawai.getNip())) {
                    targetSuratPerintahPegawai.setStatusDiterima(1);
                    suratPerintahService.updateTargetSuratPegawai(targetSuratPerintahPegawai);

                    break;
                }

            }

        }

        for (TargetSuratPerintahPejabat targetSuratPerintahPejabat
             : suratPerintah.getTargetSuratPerintahPejabatSet()) {
            for (TkdJabatan jabatan
                    : jabatanUnitKerjaList) {
                if (targetSuratPerintahPejabat.getTargetSuratPerintahPejabatId().getKdJabatan()
                        .equals(jabatan.getKdJabatan())) {
                    targetSuratPerintahPejabat.setStatusDiterima(1);
                    suratPerintahService.updateTargetSuratPejabat(targetSuratPerintahPejabat);

                    break;
                }
            }
        }

        for (TembusanSuratPerintah tembusanSuratPerintah
                : suratPerintah.getTembusanSuratPerintahList()) {
            for (TkdJabatan jabatan
                    : jabatanUnitKerjaList) {
                LOGGER.info(tembusanSuratPerintah.getTembusanSuratPerintahId().getKdJabatan()+" : "+jabatan.getKdJabatan());
                if (tembusanSuratPerintah.getTembusanSuratPerintahId().getKdJabatan()
                        .equals(jabatan.getKdJabatan())) {
                    LOGGER.info("update => "+tembusanSuratPerintah.getTembusanSuratPerintahId().getKdJabatan()+" : "+jabatan.getKdJabatan());
                    tembusanSuratPerintah.setStatusDiterima(1);
                    suratPerintahService.updateTembusanSurat(tembusanSuratPerintah);

                    break;
                }
            }
        }


        return null;
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
                            isSuratPejabat,
                            suratPerintah.getStatusBaca(),
                            "surat perintah",
                            11,
                            suratPerintah.getTanggalPerintahMilis(),
                            suratPerintah.getStatusPenilaian()
                    ));
        }

        return new ResponseEntity<Object>(suratPerintahHistoryWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-surat-perintah-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPerintahTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat perintah nip "+nipTarget);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<TargetSuratPerintahPegawai> daftarSuratPerintahPegawaiTarget
                = suratPerintahService.getTargetSuratPerintahPegawai(nipTarget);
        List<TargetSuratPerintahPejabat> daftarSuratPerintahPejabatTarget
                = suratPerintahService.getTargetSuratPerintahPejabat(pegawaiTarget.getKdJabatan());
        List<TembusanSuratPerintah> daftarSuratPerintahTembusan
                = suratPerintahService.getTembusanSuratPerintah(pegawaiTarget.getKdJabatan());

        List<SuratPerintahTargetWrapper> daftarSuratPerintahTargetWrapper
                = new ArrayList<>();
        Locale indoLocale = new Locale("id", "ID");

        boolean isSuratPejabat = false;
        //get surat perintah berdasarkan target pegawai
        for (TargetSuratPerintahPegawai suratTarget
                : daftarSuratPerintahPegawaiTarget) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPenandatangan())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    daftarSuratPerintahTargetWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                    DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                    suratTarget.getSuratPerintah().getTanggalPerintahMilis(),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan(),
                                    suratTarget.getStatusBaca(),
                                    "Surat Perintah",
                                    2)
                            );
                    break;
                }
            }
        }
        //get surat perintah berdasarkan target pegawai
        for (TargetSuratPerintahPejabat suratTarget
                : daftarSuratPerintahPejabatTarget) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPenandatangan())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    daftarSuratPerintahTargetWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                    DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                    suratTarget.getSuratPerintah().getTanggalPerintahMilis(),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan(),
                                    suratTarget.getStatusBaca(),
                                    "Surat Perintah",
                                    2)
                            );
                    break;
                }
            }
        }
        //get surat perintah berdasarkan tembusan
        for (TembusanSuratPerintah suratTarget
                : daftarSuratPerintahTembusan) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPenandatangan())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    daftarSuratPerintahTargetWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                    DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                    suratTarget.getSuratPerintah().getTanggalPerintahMilis(),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan(),
                                    suratTarget.getStatusBaca(),
                                    "Surat Perintah",
                                    2)
                            );
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(daftarSuratPerintahTargetWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-perintah-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPerintahTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat perintah unread nip "+nipTarget);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<TargetSuratPerintahPegawai> daftarSuratPerintahPegawaiTarget
                = suratPerintahService.getTargetSuratPerintahPegawai(nipTarget);
        List<TargetSuratPerintahPejabat> daftarSuratPerintahPejabatTarget
                = suratPerintahService.getTargetSuratPerintahPejabat(pegawaiTarget.getKdJabatan());
        List<TembusanSuratPerintah> daftarSuratPerintahTembusan
                = suratPerintahService.getTembusanSuratPerintah(pegawaiTarget.getKdJabatan());

        List<SuratPerintahTargetWrapper> daftarSuratPerintahTargetWrapper
                = new ArrayList<>();
        Locale indoLocale = new Locale("id", "ID");

        boolean isSuratPejabat = false;
        //get surat perintah berdasarkan target pegawai
        for (TargetSuratPerintahPegawai suratTarget
                : daftarSuratPerintahPegawaiTarget) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPenandatangan())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    if (suratTarget.getStatusBaca() == 0) {
                        daftarSuratPerintahTargetWrapper
                                .add(new SuratPerintahTargetWrapper(
                                        suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                        DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                        suratTarget.getSuratPerintah().getTanggalPerintahMilis(),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        suratTarget.getStatusBaca(),
                                        "Surat Perintah",
                                        2)
                                );
                    }
                    break;
                }
            }
        }
        //get surat perintah berdasarkan target pegawai
        for (TargetSuratPerintahPejabat suratTarget
                : daftarSuratPerintahPejabatTarget) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPenandatangan())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    if (suratTarget.getStatusBaca() == 0) {
                        daftarSuratPerintahTargetWrapper
                                .add(new SuratPerintahTargetWrapper(
                                        suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                        DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                        suratTarget.getSuratPerintah().getTanggalPerintahMilis(),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        suratTarget.getStatusBaca(),
                                        "Surat Perintah",
                                        2)
                                );
                    }
                    break;
                }
            }
        }
        //get surat perintah berdasarkan tembusan
        for (TembusanSuratPerintah suratTarget
                : daftarSuratPerintahTembusan) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getSuratPerintah().getNipPenandatangan())) {

                    if (suratTarget.getSuratPerintah().getSuratPerintahPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    if (suratTarget.getStatusBaca() == 0) {
                        daftarSuratPerintahTargetWrapper
                                .add(new SuratPerintahTargetWrapper(
                                        suratTarget.getSuratPerintah().getKdSuratPerintah(),
                                        DateUtilities.createLocalDate(new Date(suratTarget.getSuratPerintah().getTanggalPerintahMilis()), "dd MMMM yyyy", indoLocale),
                                        suratTarget.getSuratPerintah().getTanggalPerintahMilis(),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        suratTarget.getStatusBaca(),
                                        "Surat Perintah",
                                        2)
                                );
                    }
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
            return new ResponseEntity<Object>(new CustomMessage("surat tidak ditemukan"), HttpStatus.NOT_FOUND);

        LOGGER.info(suratPerintah.getMenimbang());
        LOGGER.info(suratPerintah.getDasar());
        LOGGER.info(suratPerintah.getUntuk());

        String nomorSurat
                = String.valueOf(suratPerintah.getNomorSurat1()) +"-"+
                    suratPerintah.getNomorSurat2() +"-"+
                    suratPerintah.getNomorSurat3() +"-"+
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

        List<JabatanWrapper> daftarTargetPejabatSuratPerintahString
                = new ArrayList<>();
        List<JabatanWrapper> daftarTembusanSuratperintahString
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
                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    daftarTargetPejabatSuratPerintahString.add(jabatanWrapper);

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

                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    daftarTembusanSuratperintahString.add(jabatanWrapper);

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

            TkdUnk tkdUnk = tkdUnkDao.findOne(kdUnitKerjaPenandatangan);

            unitKerjaPenandatangan = tkdUnk.getUnitKerja();
        }

        SuratPerintahNonPejabatDokumenWrapper suratPerintahWrapper
                = new SuratPerintahNonPejabatDokumenWrapper(
                        suratPerintah.getKdSuratPerintah(),
                        penandatanganSurat.getNip(),
                        penandatanganSurat.getNama(),
                        nomorSurat,
                        suratPerintah.getNomorSurat2(),
                        suratPerintah.getNomorSurat3(),
                        suratPerintah.getNomorTahun(),
                        menimbangList,
                        dasarList,
                        untukList,
                        suratPerintah.getTempat(),
                        tanggalSuratDibuat,
                        suratPerintah.getTanggalPerintahMilis(),
                        penandatanganSurat.getJabatan(),
                        "",
                        daftarTargetPegawaiSuratPerintah,
                        daftarTargetPejabatSuratPerintahString,
                        daftarTembusanSuratperintahString,
                        isSuratPejabat,
                        kdUnitKerjaPenandatangan,
                        unitKerjaPenandatangan,
                        kdJabatanPenandatangan,
                        jabatanPenandatangan,
                        penandatanganSurat.getGlrDpn(),
                        penandatanganSurat.getGlrBlk(),
                        penandatanganSurat.getPangkat(),
                        penandatanganSurat.getGol(),
                null);

        return new ResponseEntity<Object>(suratPerintahWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-perintah-pegawai/{kdSuratPerintah}/{nipPegawai}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratPerintah(
            @PathVariable("kdSuratPerintah") String kdSuratPerintah,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("open surat perintah");

        QutPegawai pegawaiTarget = qutPegawaiService.getQutPegawai(nipPegawai);

        suratPerintahService.openSuratPerintah(kdSuratPerintah);
        suratPerintahService.openSuratPerintahPegawai(new TargetSuratPerintahPegawaiId(kdSuratPerintah, nipPegawai));
        suratPerintahService.openSuratTembusan(new TembusanSuratPerintahId(kdSuratPerintah, pegawaiTarget.getKdJabatan()));

        return new ResponseEntity<Object>(new CustomMessage("surat perintah opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-perintah-penilai/{kdSuratPerintah}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratPerintahPenilai(
            @PathVariable("kdSuratPerintah") String kdSuratPerintah) {
        LOGGER.info("open surat penilai");

        suratPerintahService.openSuratPeintahByPenilai(kdSuratPerintah);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);

    }

}