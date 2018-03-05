package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.BarcodeGenerator;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratInstruksiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import groovy.transform.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

/**
 * Created by bagus on 23/11/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratInstruksiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratInstruksiController.class);

    @Autowired private SuratInstruksiService suratInstruksiService;
    @Autowired private QutPegawaiCloneService qutPegawaiService;
    @Autowired private TkdJabatanService tkdJabatanService;

    @Autowired private BeritaAcaraService beritaAcaraService;
    @Autowired private SuratKuasaController suratKuasaController;
    @Autowired private SuratPerintahService suratPerintahService;

    @Autowired
    private TkdUnkDao tkdUnkDao;


    @RequestMapping(value = "/create-surat-instruksi", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createSuratInstruksi(
            @RequestBody SuratInstruksiInputWrapper inputWrapper) {
        LOGGER.info("create surat instruksi");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();
        String isiInstruksiInXml;

        String kdSuratInstruksi = String.valueOf(new Date().getTime());

        SuratInstruksi suratInstruksi = new SuratInstruksi();
        suratInstruksi.setKdInstruksi(kdSuratInstruksi);
        suratInstruksi.setJudulInstruksi(inputWrapper.getJudulInstruksi());
        suratInstruksi.setNomor(inputWrapper.getNomor());
        suratInstruksi.setTahun(Year.now().getValue());
        suratInstruksi.setTentang(inputWrapper.getTentang());
        suratInstruksi.setAlasan(inputWrapper.getAlasan());
        suratInstruksi.setNipPenandatangan(inputWrapper.getNipPenandatangan());

        isiInstruksiInXml
                = ekinerjaXMLBuilder.convertDaftarIsiInstruksiIntoXml(inputWrapper.getDaftarIsiInstruksi());

        suratInstruksi.setIsiInstruksi(isiInstruksiInXml);
        suratInstruksi.setDikeluarkanDi(inputWrapper.getDikeluarkanDi());
        suratInstruksi.setCreateddateMilis(inputWrapper.getTanggalDibuat());
        suratInstruksi.setNipPembuat(inputWrapper.getNipPembuat());
        suratInstruksi.setStatusBaca(0);
        suratInstruksi.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        suratInstruksi.setKdUrtug(inputWrapper.getKdUrtug());
        suratInstruksi.setTahunUrtug(inputWrapper.getTahunUrtug());

        if (inputWrapper.getKdSuratInstruksiParent() == null) {
            suratInstruksi.setPath(kdSuratInstruksi);
        } else {
            SuratInstruksi suratInstruksiParent
                    = suratInstruksiService.getSuratInstruksi(inputWrapper.getKdSuratInstruksiParent());
            suratInstruksi.setPath(suratInstruksiParent.getPath()+"."+kdSuratInstruksi);
        }

        suratInstruksiService.createSuratInstruksi(suratInstruksi);

        //build target pegawai
        for (String kdTargetPegawai : inputWrapper.getTargetPegawaiList()) {
            InstruksiPegawai instruksiPegawai = new InstruksiPegawai();
            instruksiPegawai
                    .setInstruksiPegawaiId(new InstruksiPegawaiId(kdTargetPegawai, kdSuratInstruksi));
            instruksiPegawai.setApproveStatus(0);
            instruksiPegawai.setStatusBaca(0);

            suratInstruksiService.createInstruksiPegawai(instruksiPegawai);
        }
        //build target pejabat
        for (String kdTargetPejabat : inputWrapper.getTargetJabatanList()) {
            InstruksiPejabat instruksiPejabat = new InstruksiPejabat();
            instruksiPejabat
                    .setInstruksiPejabatId(new InstruksiPejabatId(kdTargetPejabat, kdSuratInstruksi));
            instruksiPejabat.setApproveStatus(0);
            instruksiPejabat.setStatusBaca(0);

            suratInstruksiService.createInstruksiPejabat(instruksiPejabat);
        }

        if (inputWrapper.isSuratPejabat()) {
            SuratInstruksiPejabat suratInstruksiPejabat
                    = new SuratInstruksiPejabat();
            suratInstruksiPejabat.setKdInstruksi(kdSuratInstruksi);
            suratInstruksiPejabat.setSuratInstruksi(suratInstruksi);
            suratInstruksiPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());

            suratInstruksiService.createSuratInstruksiPejabat(suratInstruksiPejabat);
        } else {
            SuratInstruksiNonPejabat suratInstruksiNonPejabat
                    = new SuratInstruksiNonPejabat();
            suratInstruksiNonPejabat.setKdInstruksi(kdSuratInstruksi);
            suratInstruksiNonPejabat.setSuratInstruksi(suratInstruksi);
            suratInstruksiNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());
            suratInstruksiService.createSuratInstruksiNonPejabat(suratInstruksiNonPejabat);
        }


        return new ResponseEntity<Object>(new CustomMessage("surat instruksi created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-instruksi-by-pembuat/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratInstruksiByPembuat(
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get surat instruksi by pembuat");

        List<SuratInstruksiWrapper> suratInstruksiWrapperList
                = new ArrayList<>();
        List<SuratInstruksi> suratInstruksiList
                = suratInstruksiService.getSuratInstruksiByNip(nipPegawai);

        LOGGER.info("length is "+suratInstruksiList.size());

        QutPegawai pembuat = qutPegawaiService.getQutPegawai(nipPegawai);
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(pembuat.getKdUnitKerja());

        Locale indoLocale = new Locale("id", "ID");
        boolean isSuratPejabat;
        for (SuratInstruksi suratInstruksi
                : suratInstruksiList) {
            isSuratPejabat = true;
            if (suratInstruksi.getSuratInstruksiPejabat() == null) {
                isSuratPejabat = false;
            }

            suratInstruksiWrapperList
                    .add(new SuratInstruksiWrapper(
                            suratInstruksi.getKdInstruksi(),
                            suratInstruksi.getJudulInstruksi(),
                            DateUtilities.createLocalDate(new Date(suratInstruksi.getCreateddateMilis()), "dd MMMM yyyy", indoLocale),
                            suratInstruksi.getCreateddateMilis(),
                            isSuratPejabat,
                            suratInstruksi.getStatusBaca(),
                            null,
                            null,
                            suratInstruksi.getStatusBaca(),
                            null,
                            null
                    ));
        }

        return new ResponseEntity<Object>(suratInstruksiWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-dokumen-surat-instruksi/{kdSuratInstruksi}", method = RequestMethod.GET)
    ResponseEntity<?> getDokumenSuratInstruksi(
            @PathVariable("kdSuratInstruksi") String kdSuratInstruksi) {
        LOGGER.info("get dokumen surat instruksi");

        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        SuratInstruksi suratInstruksi
                = suratInstruksiService.getDokumenSuratInstruksi(kdSuratInstruksi);
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<TkdJabatan> tkdJabatanList
                = tkdJabatanService.getAll();

        List<String> daftarIsiInstruksi
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratInstruksi.getIsiInstruksi(), "instruksi");
//        List<String> targetNamaPegawai = new ArrayList<>();
        List<QutPegawaiWrapper> targetPegawai = new ArrayList<>();
        List<JabatanWrapper> targetJabatan = new ArrayList<>();

        TkdJabatan tkdJabatan = null;
        CustomPegawaiCredential penandatanganSurat = null;

        DokumenSuratInstruksiWrapper dokumenSuratInstruksiWrapper
                = new DokumenSuratInstruksiWrapper();

        for (CustomPegawaiCredential customPegawaiCredential
                : qutPegawaiList) {
            if (customPegawaiCredential.getNip()
                    .equals(suratInstruksi.getNipPembuat())) {
                penandatanganSurat = customPegawaiCredential;
                break;
            }
        }

        for (InstruksiPegawai instruksiPegawai
                :suratInstruksi.getInstruksiPegawaiSet()) {
            for (CustomPegawaiCredential pegawai : qutPegawaiList) {
                if (pegawai.getNip()
                        .equals(instruksiPegawai.getInstruksiPegawaiId().getNipPegawai())) {
//                    targetNamaPegawai.add(pegawai.getNama());
                    targetPegawai
                            .add(new QutPegawaiWrapper(
                                    pegawai.getNip(),
                                    pegawai.getNama(),
                                    pegawai.getKdJabatan(),
                                    pegawai.getJabatan(),
                                    pegawai.getKdUnitKerja(),
                                    pegawai.getUnitKerja(),
                                    pegawai.getPangkat(),
                                    pegawai.getGol(),
                                    "",
                                    pegawai.getGlrDpn(),
                                    pegawai.getGlrBlk()));
                    break;
                }

            }

        }

        for (InstruksiPejabat instruksiPejabat
                :suratInstruksi.getInstruksiPejabatSet()) {
            for (TkdJabatan jabatan : tkdJabatanList) {
                if (jabatan.getKdJabatan()
                        .equals(instruksiPejabat.getInstruksiPejabatId().getKdJabatan())) {
                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    targetJabatan.add(jabatanWrapper);
                    break;
                }

            }

        }

        String base64BarcodeImage = null;
        String kdBarcode
                = suratInstruksi.getKdBarcode()+suratInstruksi.getNomor()+penandatanganSurat.getKdUnitKerja()+"16";
        BarcodeGenerator generator = new BarcodeGenerator();

        base64BarcodeImage
                = generator.convertBarcodeImageIntoBase64String(generator.generateBarcode(suratInstruksi.getKdBarcode()));

        dokumenSuratInstruksiWrapper.setKdInstruksi(suratInstruksi.getKdInstruksi());
        dokumenSuratInstruksiWrapper.setJudulInstruksi(suratInstruksi.getJudulInstruksi());
        dokumenSuratInstruksiWrapper.setNomor(suratInstruksi.getNomor());
        dokumenSuratInstruksiWrapper.setTahun(suratInstruksi.getTahun());
        dokumenSuratInstruksiWrapper.setTentang(suratInstruksi.getTentang());
        dokumenSuratInstruksiWrapper.setKdJabatanPenandatangan(penandatanganSurat.getKdJabatan());
        dokumenSuratInstruksiWrapper.setJabatanPenandaTangan(penandatanganSurat.getJabatan());
        dokumenSuratInstruksiWrapper.setNamaPenandatangan(penandatanganSurat.getNama());
        dokumenSuratInstruksiWrapper.setNipPenandatangan(penandatanganSurat.getNip());
        dokumenSuratInstruksiWrapper.setAlasan(suratInstruksi.getAlasan());
        dokumenSuratInstruksiWrapper.setDaftarIsiInstruksi(daftarIsiInstruksi);
        Locale indoLocale = new Locale("id", "ID");
        dokumenSuratInstruksiWrapper
                .setTanggalDibuat(DateUtilities.createLocalDate(new Date(suratInstruksi.getCreateddateMilis()), "dd MMMM yyyy", indoLocale));
        dokumenSuratInstruksiWrapper.setDikeluarkanDi(suratInstruksi.getDikeluarkanDi());
        dokumenSuratInstruksiWrapper.setTargetPegawaiList(targetPegawai);
        dokumenSuratInstruksiWrapper.setTargetJabatanList(targetJabatan);
        dokumenSuratInstruksiWrapper.setGelarDepanPenandatangan(penandatanganSurat.getGlrDpn());
        dokumenSuratInstruksiWrapper.setGelarBelakangPenandantangan(penandatanganSurat.getGlrBlk());
        dokumenSuratInstruksiWrapper.setPangkatPenandatangan(penandatanganSurat.getPangkat());
        dokumenSuratInstruksiWrapper.setGolonganPenandatangan(penandatanganSurat.getGol());
        dokumenSuratInstruksiWrapper.setBarcodeImage(base64BarcodeImage);

        if (suratInstruksi.getSuratInstruksiPejabat() != null) {
            tkdJabatan
                    = tkdJabatanService.getTkdJabatan(suratInstruksi.getSuratInstruksiPejabat().getKdJabatan());
            dokumenSuratInstruksiWrapper.setSuratPejabat(true);
            dokumenSuratInstruksiWrapper.setJabatanSuratPejabat(tkdJabatan.getJabatan());
            dokumenSuratInstruksiWrapper.setUnitKerja(null);
        } else {
            dokumenSuratInstruksiWrapper.setSuratPejabat(false);
            dokumenSuratInstruksiWrapper.setJabatanSuratPejabat(null);

            TkdUnk tkdUnk = tkdUnkDao.findOne(penandatanganSurat.getKdUnitKerja());

            dokumenSuratInstruksiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
        }


        return new ResponseEntity<Object>(dokumenSuratInstruksiWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-instruksi-pegawai/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getInstruksiPegawai(
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get instruksi pegawai");

        List<SuratPerintahTargetWrapper> suratInstruksiWrapperList
                = new ArrayList<>();
        List<InstruksiPegawai> instruksiPegawaiList
                = suratInstruksiService.getInstruksiPegawai(nipPegawai);

        Locale indoLocale = new Locale("id", "ID");
        boolean isSuratPejabat;
        for (InstruksiPegawai instruksiPegawai
                : instruksiPegawaiList) {
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(instruksiPegawai.getSuratInstruksi().getNipPembuat());

            isSuratPejabat = true;
            if (instruksiPegawai.getSuratInstruksi().getSuratInstruksiPejabat() == null) {
                isSuratPejabat = false;
            }
            suratInstruksiWrapperList
                    .add(new SuratPerintahTargetWrapper(
                            instruksiPegawai.getSuratInstruksi().getKdInstruksi(),
                            "",
                            instruksiPegawai.getSuratInstruksi().getCreateddateMilis(),
                            isSuratPejabat,
                            pegawaiPengirim.getNip(),
                            pegawaiPengirim.getNama(),
                            pegawaiPengirim.getJabatan(),
                            instruksiPegawai.getStatusBaca(),
                            "Surat Instruksi",
                            3
                    ));

        }

        return new ResponseEntity<Object>(suratInstruksiWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-instruksi-pegawai-unread/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getInstruksiPegawaiUnread(
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get instruksi pegawai unread");

        List<SuratPerintahTargetWrapper> suratInstruksiWrapperList
                = new ArrayList<>();
        List<InstruksiPegawai> instruksiPegawaiList
                = suratInstruksiService.getInstruksiPegawai(nipPegawai);

        Locale indoLocale = new Locale("id", "ID");
        boolean isSuratPejabat;
        for (InstruksiPegawai instruksiPegawai
                : instruksiPegawaiList) {
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(instruksiPegawai.getSuratInstruksi().getNipPembuat());

            isSuratPejabat = true;
            if (instruksiPegawai.getSuratInstruksi().getSuratInstruksiPejabat() == null) {
                isSuratPejabat = false;
            }
            if (instruksiPegawai.getStatusBaca() == 0) {
                suratInstruksiWrapperList
                        .add(new SuratPerintahTargetWrapper(
                                instruksiPegawai.getSuratInstruksi().getKdInstruksi(),
                                "",
                                instruksiPegawai.getSuratInstruksi().getCreateddateMilis(),
                                isSuratPejabat,
                                pegawaiPengirim.getNip(),
                                pegawaiPengirim.getNama(),
                                pegawaiPengirim.getJabatan(),
                                instruksiPegawai.getStatusBaca(),
                                "Surat Instruksi",
                                3
                        ));
            }

        }

        return new ResponseEntity<Object>(suratInstruksiWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-instruksi-pejabat/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getInstruksiPejabat(
            @PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get instruksi pejabat");

        List<SuratInstruksiWrapper> suratInstruksiWrapperList
                = new ArrayList<>();
        List<InstruksiPejabat> instruksiPejabatList
                = suratInstruksiService.getInstruksiPejabat(kdJabatan);

        Locale indoLocale = new Locale("id", "ID");
        boolean isSuratPejabat;
        for (InstruksiPejabat instruksiPejabat
                : instruksiPejabatList) {
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(instruksiPejabat.getSuratInstruksi().getNipPembuat());

            isSuratPejabat = true;
            if (instruksiPejabat.getSuratInstruksi().getSuratInstruksiPejabat() == null) {
                isSuratPejabat = false;
            }
            suratInstruksiWrapperList
                    .add(new SuratInstruksiWrapper(
                            instruksiPejabat.getSuratInstruksi().getKdInstruksi(),
                            instruksiPejabat.getSuratInstruksi().getJudulInstruksi(),
                            DateUtilities.createLocalDate(new Date(instruksiPejabat.getSuratInstruksi().getCreateddateMilis()), "dd MMMM yyyy", indoLocale),
                            instruksiPejabat.getSuratInstruksi().getCreateddateMilis(),
                            isSuratPejabat,
                            null,
                            instruksiPejabat.getSuratInstruksi().getNipPembuat(),
                            pegawaiPengirim.getNama(),
                            instruksiPejabat.getStatusBaca()
                            ));

        }

        return new ResponseEntity<Object>(suratInstruksiWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-instruksi-pejabat-unread/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getInstruksiPejabatUnread(
            @PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get instruksi pejabat unread");

        List<SuratInstruksiWrapper> suratInstruksiWrapperList
                = new ArrayList<>();
        List<InstruksiPejabat> instruksiPejabatList
                = suratInstruksiService.getInstruksiPejabat(kdJabatan);

        Locale indoLocale = new Locale("id", "ID");
        boolean isSuratPejabat;
        for (InstruksiPejabat instruksiPejabat
                : instruksiPejabatList) {
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(instruksiPejabat.getSuratInstruksi().getNipPembuat());

            isSuratPejabat = true;
            if (instruksiPejabat.getSuratInstruksi().getSuratInstruksiPejabat() == null) {
                isSuratPejabat = false;
            }
            if (instruksiPejabat.getStatusBaca() == 0) {
                suratInstruksiWrapperList
                        .add(new SuratInstruksiWrapper(
                                instruksiPejabat.getSuratInstruksi().getKdInstruksi(),
                                instruksiPejabat.getSuratInstruksi().getJudulInstruksi(),
                                DateUtilities.createLocalDate(new Date(instruksiPejabat.getSuratInstruksi().getCreateddateMilis()), "dd MMMM yyyy", indoLocale),
                                instruksiPejabat.getSuratInstruksi().getCreateddateMilis(),
                                isSuratPejabat,
                                instruksiPejabat.getStatusBaca(),
                                instruksiPejabat.getSuratInstruksi().getNipPembuat(),
                                pegawaiPengirim.getNama(),
                                instruksiPejabat.getStatusBaca()
                        ));
            }

        }

        return new ResponseEntity<Object>(suratInstruksiWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-instruksi-tree/{kdSuratInstruksi}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratInstruksiTree(
            @PathVariable("kdSuratInstruksi") String kdSuratInstruksi) {
        LOGGER.info("get surat instruksi tree");

        List<SuratInstruksi> suratInstruksiList
                = suratInstruksiService.findTree(kdSuratInstruksi);
        List<SuratInstruksiWrapper> suratInstruksiWrapperList
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");

        boolean isSuratPejabat;
        for (SuratInstruksi suratInstruksi
                : suratInstruksiList) {
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(suratInstruksi.getNipPembuat());

            isSuratPejabat = true;
            if (suratInstruksi.getSuratInstruksiPejabat() == null) {
                isSuratPejabat = false;
            }
            suratInstruksiWrapperList
                    .add(new SuratInstruksiWrapper(suratInstruksi.getKdInstruksi(),
                            suratInstruksi.getJudulInstruksi(),
                            DateUtilities.createLocalDate(new Date(suratInstruksi.getCreateddateMilis()), "dd MMMM yyyy", indoLocale),
                            suratInstruksi.getCreateddateMilis(),
                            isSuratPejabat,
                            suratInstruksi.getStatusBaca(),
                            suratInstruksi.getNipPembuat(),
                            pegawaiPengirim.getNama(),
                            suratInstruksi.getStatusBaca(),
                            suratInstruksi.getPath()
                            ));
        }

        return new ResponseEntity<Object>(suratInstruksiWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-instruksi-pegawai/{kdSuratInstruksi}/{nipPegawai}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratInstruksi(
            @PathVariable("kdSuratInstruksi") String kdSuratInstruksi,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("open surat instruksi");

        suratInstruksiService.openSuratInstruksi(kdSuratInstruksi);
        suratInstruksiService.openSuratInstruksiTarget(new InstruksiPegawaiId(nipPegawai, kdSuratInstruksi));

        return new ResponseEntity<Object>(new CustomMessage("surat instruksi opened"), HttpStatus.OK);
    }

}
