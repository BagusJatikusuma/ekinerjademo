package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratTugasService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.util.BarcodeGenerator;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratTugasInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.*;

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
    @Autowired
    private TkdJabatanService tkdJabatanService;
    @Autowired
    private TkdUnkDao tkdUnkDao;

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
            suratTugas.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            suratTugas.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            SuratTugas suratTugasBawahan
                    = suratTugasService.getByKdSuratTugas(inputWrapper.getKdSuratTugasBawahan());
            suratTugas.setPathPenilaian(suratTugasBawahan.getPathPenilaian()+"."+kdSuratTugas);
            suratTugas.setKdNaskahPenugasan(suratTugasBawahan.getKdNaskahPenugasan());
            suratTugas.setJenisNaskahPenugasan(suratTugasBawahan.getJenisNaskahPenugasan());

            suratTugasBawahan.setStatusPenilaian(2);
            suratTugasService.create(suratTugasBawahan);
        }

        suratTugas.setStatusBaca(0);
        suratTugas.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        suratTugas.setNipPenilai(null);
        suratTugas.setStatusPenilaian(0);
        suratTugas.setAlasanPenolakan(null);
        suratTugas.setStatusPenyebaran(0);

        suratTugas.setKdUrtug(inputWrapper.getKdUrtug());
        suratTugas.setTahunUrtug(inputWrapper.getTahunUrtug());

        suratTugas.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        suratTugasService.create(suratTugas);

        for (TargetSuratTugasPegawai targetSuratTugasPegawai
                : targetSuratTugasPegawaiSet) {
            suratTugasService.createTargetSuratTugasPegawai(targetSuratTugasPegawai);
        }

        for (TargetSuratTugasPejabat targetSuratTugasPejabat
                : targetSuratTugasPejabatSet) {
            suratTugasService.createTargetSuratTugasPejabat(targetSuratTugasPejabat);
        }

        for (TembusanSuratTugas tembusanSuratTugas
                : tembusanSuratTugasSet) {
            suratTugasService.createTembusanSuratTugas(tembusanSuratTugas);
        }

        if (inputWrapper.isSuratPejabat()) {
            SuratTugasPejabat suratTugasPejabat
                    = new SuratTugasPejabat();
            suratTugasPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            suratTugasPejabat.setKdSuratTugas(kdSuratTugas);

            suratTugasService.createSuratTugasPejabat(suratTugasPejabat);
        } else {
            SuratTugasNonPejabat suratTugasNonPejabat
                    = new SuratTugasNonPejabat();
            suratTugasNonPejabat.setKdSuratTugas(kdSuratTugas);
            suratTugasNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());

            suratTugasService.createSuratTugasNonPejabat(suratTugasNonPejabat);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat tugas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/approve-surat-tugas/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratTugas(@PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("approve surat tugas");

        suratTugasService.approveSuratTugas(kdSuratTugas);

        return new ResponseEntity<Object>(new CustomMessage("surat tugas approved"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-pembuat/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasHistoryByPembuat(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get surat tugas by pembuat");

        Set<SuratTugas> suratTugasSet
                = suratTugasService.getByNipPembuat(nipPembuatSurat);
        List<SuratPerintahHistoryWrapper> suratTugasHistoryList
                = new ArrayList<>();

        boolean isSuratPejabat = false;
        for (SuratTugas suratTugas
                : suratTugasSet) {
            if (suratTugas.getSuratTugasPejabat() != null)
                isSuratPejabat = true;
            else
                isSuratPejabat = false;

            suratTugasHistoryList
                    .add(new SuratPerintahHistoryWrapper(
                            suratTugas.getKdSuratTugas(),
                            "",
                            isSuratPejabat,
                            suratTugas.getStatusBaca(),
                            "surat tugas",
                            12,
                            suratTugas.getTanggalTugasMilis(),
                            suratTugas.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratTugasHistoryList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasByTarget(
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat tugas by target");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> suratTugasTargetList
                = new ArrayList<>();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<TargetSuratTugasPegawai> targetSuratTugasPegawaiList
                = suratTugasService.getTargetSuratTugasPegawai(pegawaiTarget.getNip());
        List<TargetSuratTugasPejabat> targetSuratTugasPejabatList
                = suratTugasService.getTargetSuratTugasPejabat(pegawaiTarget.getKdJabatan());
        List<TembusanSuratTugas> tembusanSuratTugasList
                = suratTugasService.getTembusanSuratTugas(pegawaiTarget.getKdJabatan());

        boolean isSuratPejabat = false;
        for (TargetSuratTugasPegawai suratTarget
                : targetSuratTugasPegawaiList) {
            if (suratTarget.getSuratTugas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratTarget.getSuratTugas().getNipPenandatangan())) {
                        if (suratTarget.getSuratTugas().getSuratTugasPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        suratTugasTargetList
                                .add(new SuratPerintahTargetWrapper(
                                        suratTarget.getSuratTugas().getKdSuratTugas(),
                                        "",
                                        suratTarget.getSuratTugas().getTanggalTugasMilis(),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        suratTarget.getStatusBaca(),
                                        "Surat Tugas",
                                        4));
                        break;
                    }
                }
            }
        }
        for (TargetSuratTugasPejabat suratTarget
                : targetSuratTugasPejabatList) {
            if (suratTarget.getSuratTugas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratTarget.getSuratTugas().getNipPenandatangan())) {
                        if (suratTarget.getSuratTugas().getSuratTugasPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        suratTugasTargetList
                                .add(new SuratPerintahTargetWrapper(
                                        suratTarget.getSuratTugas().getKdSuratTugas(),
                                        "",
                                        suratTarget.getSuratTugas().getTanggalTugasMilis(),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        suratTarget.getStatusBaca(),
                                        "Surat Tugas",
                                        4));
                        break;
                    }
                }
            }
        }

        for (TembusanSuratTugas suratTarget
                : tembusanSuratTugasList) {
            if (suratTarget.getSuratTugas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratTarget.getSuratTugas().getNipPenandatangan())) {
                        if (suratTarget.getSuratTugas().getSuratTugasPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        suratTugasTargetList
                                .add(new SuratPerintahTargetWrapper(
                                        suratTarget.getSuratTugas().getKdSuratTugas(),
                                        "",
                                        suratTarget.getSuratTugas().getTanggalTugasMilis(),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        suratTarget.getStatusBaca(),
                                        "Surat Tugas",
                                        4));
                        break;
                    }
                }
            }
        }


        return new ResponseEntity<Object>(suratTugasTargetList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasByTargetUnread(
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat tugas unread by target");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> suratTugasTargetList
                = new ArrayList<>();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<TargetSuratTugasPegawai> targetSuratTugasPegawaiList
                = suratTugasService.getTargetSuratTugasPegawai(pegawaiTarget.getNip());
        List<TargetSuratTugasPejabat> targetSuratTugasPejabatList
                = suratTugasService.getTargetSuratTugasPejabat(pegawaiTarget.getKdJabatan());
        List<TembusanSuratTugas> tembusanSuratTugasList
                = suratTugasService.getTembusanSuratTugas(pegawaiTarget.getKdJabatan());

        boolean isSuratPejabat = false;
        for (TargetSuratTugasPegawai suratTarget
                : targetSuratTugasPegawaiList) {
            if (suratTarget.getSuratTugas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratTarget.getSuratTugas().getNipPenandatangan())) {
                        if (suratTarget.getSuratTugas().getSuratTugasPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        if (suratTarget.getStatusBaca() == 0) {
                            suratTugasTargetList
                                    .add(new SuratPerintahTargetWrapper(
                                            suratTarget.getSuratTugas().getKdSuratTugas(),
                                            "",
                                            suratTarget.getSuratTugas().getTanggalTugasMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            suratTarget.getStatusBaca(),
                                            "Surat Tugas",
                                            4));
                        }
                        break;
                    }
                }
            }
        }
        for (TargetSuratTugasPejabat suratTarget
                : targetSuratTugasPejabatList) {
            if (suratTarget.getSuratTugas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratTarget.getSuratTugas().getNipPenandatangan())) {
                        if (suratTarget.getSuratTugas().getSuratTugasPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        if (suratTarget.getStatusBaca() == 0) {
                            suratTugasTargetList
                                    .add(new SuratPerintahTargetWrapper(
                                            suratTarget.getSuratTugas().getKdSuratTugas(),
                                            "",
                                            suratTarget.getSuratTugas().getTanggalTugasMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            suratTarget.getStatusBaca(),
                                            "Surat Tugas",
                                            4));
                        }

                        break;
                    }
                }
            }
        }

        for (TembusanSuratTugas suratTarget
                : tembusanSuratTugasList) {
            if (suratTarget.getSuratTugas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratTarget.getSuratTugas().getNipPenandatangan())) {
                        if (suratTarget.getSuratTugas().getSuratTugasPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        if (suratTarget.getStatusBaca() == 0) {
                            suratTugasTargetList
                                    .add(new SuratPerintahTargetWrapper(
                                            suratTarget.getSuratTugas().getKdSuratTugas(),
                                            "",
                                            suratTarget.getSuratTugas().getTanggalTugasMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            suratTarget.getStatusBaca(),
                                            "Surat Tugas",
                                            4));
                        }
                        break;
                    }
                }
            }
        }

        return new ResponseEntity<Object>(suratTugasTargetList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-surat-tugas-by-kd-surat/{kdSuratTugas}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratTugasbyKdSuratTugas(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("get surat tugas by kode surat");

        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        SuratTugas suratTugas = suratTugasService.getByKdSuratTugas(kdSuratTugas);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> suratTugasTargetList
                = new ArrayList<>();
        Set<CustomPegawaiCredential> targetSuratTugasPegawaiListWrapper
                = new HashSet<>();
        Set<JabatanWrapper> targetSuratTugasPejabatListWrapper
                = new HashSet<>();
        Set<JabatanWrapper> tembusanSuratTugasListWrapper
                = new HashSet<>();


        CustomPegawaiCredential penandatanganSurat = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (suratTugas.getNipPenandatangan().equals(pegawai.getNip())) {
                penandatanganSurat = pegawai;

                break;
            }
        }
        for (TargetSuratTugasPegawai target
                :suratTugas.getTargetSuratTugasPegawaiSet()) {
            for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(target.getTargetSuratTugasPegawaiId().getNipPegawai())) {
                    targetSuratTugasPegawaiListWrapper.add(customPegawaiCredential);

                    break;
                }
            }
        }

        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        for (TargetSuratTugasPejabat target
                : suratTugas.getTargetSuratTugasPejabatSet()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(target.getTargetSuratTugasPejabatId().getKdJabatan())) {

                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    targetSuratTugasPejabatListWrapper.add(jabatanWrapper);

                    break;

                }

            }

        }
        for (TembusanSuratTugas target
                : suratTugas.getTembusanSuratTugasSet()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(target.getTembusanSuratTugasId().getKdJabatan())) {

                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    tembusanSuratTugasListWrapper.add(jabatanWrapper);

                    break;

                }

            }

        }

        boolean isSuratPejabat = false;
        String kdUnitKerjaPenandatangan = null;
        String unitKerjaPenandatangan = null;
        String kdJabatanPenandatangan = null;
        String jabatanPenandatangan = null;

        if (suratTugas.getSuratTugasPejabat() != null) {
            isSuratPejabat = true;
            kdJabatanPenandatangan = penandatanganSurat.getKdJabatan();
            jabatanPenandatangan = penandatanganSurat.getJabatan();
        } else {
            kdUnitKerjaPenandatangan = penandatanganSurat.getKdUnitKerja();

            TkdUnk tkdUnk = tkdUnkDao.findOne(kdUnitKerjaPenandatangan);

            unitKerjaPenandatangan = tkdUnk.getUnitKerja();
        }

        String base64BarcodeImage = null;
        String kdBarcode
                = suratTugas.getKdBarcode()+suratTugas.getNomorSurat1()+suratTugas.getKdUnitKerja()+"12";
        BarcodeGenerator generator = new BarcodeGenerator();

        base64BarcodeImage
                = generator.convertBarcodeImageIntoBase64String(
                generator.generateBarcode(suratTugas.getKdBarcode()));

        SuratTugasWrapper suratTugasWrapper
                = new SuratTugasWrapper(suratTugas.getKdSuratTugas(),
                penandatanganSurat,
                suratTugas.getNomorSurat1(),
                suratTugas.getNomorSurat2(),
                suratTugas.getNomorSurat3(),
                suratTugas.getNomorTahun(),suratTugas.getNomorPasanganUrut(),
                ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratTugas.getMenimbang(), "menimbang"),
                ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratTugas.getDasar(), "dasar"),
                ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratTugas.getUntuk(), "untuk"),
                suratTugas.getTempat(),
                suratTugas.getTanggalTugasMilis(),
                suratTugas.getKdUnitKerja(),
                isSuratPejabat,
                penandatanganSurat.getKdUnitKerja(),
                tkdUnkDao.findOne(penandatanganSurat.getKdUnitKerja()).getUnitKerja(),
                penandatanganSurat.getKdJabatan(),
                penandatanganSurat.getJabatan(),
                penandatanganSurat.getGlrDpn(),
                penandatanganSurat.getGlrBlk(),
                penandatanganSurat.getGol(),
                targetSuratTugasPegawaiListWrapper,
                targetSuratTugasPejabatListWrapper,
                tembusanSuratTugasListWrapper);

        return new ResponseEntity<Object>(suratTugasWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-tugas/{kdSuratTugas}/{nipTarget}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratTugas(
            @PathVariable("kdSuratTugas") String kdSuratTugas,
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("open surat tugas");
        QutPegawai pegawaiTarget = qutPegawaiService.getQutPegawai(nipTarget);

        suratTugasService.openSuratTugas(kdSuratTugas);

        TargetSuratTugasPegawai targetSuratTugasPegawai
                = suratTugasService
                        .getTargetSuratTugasPegawaiById(
                                new TargetSuratTugasPegawaiId(kdSuratTugas, nipTarget));
        TargetSuratTugasPejabat targetSuratTugasPejabat
                = suratTugasService
                        .getTargetSuratTugasPejabatById(
                                new TargetSuratTugasPejabatId(kdSuratTugas, pegawaiTarget.getKdJabatan()));
        TembusanSuratTugas tembusanSuratTugas
                = suratTugasService
                        .getTembusanSuratTugasById(
                                new TembusanSuratTugasId(kdSuratTugas, pegawaiTarget.getKdJabatan()));

        if (targetSuratTugasPegawai != null)
            suratTugasService
                    .openTargetSuratTugasPegawai(targetSuratTugasPegawai.getTargetSuratTugasPegawaiId());
        if (targetSuratTugasPejabat != null)
            suratTugasService
                    .openTargetSuratTugasPejabat(targetSuratTugasPejabat.getTargetSuratTugasPejabatId());
        if (tembusanSuratTugas != null) {
            suratTugasService
                    .openTembusanSuratTugas(tembusanSuratTugas.getTembusanSuratTugasId());
        }

        return new ResponseEntity<Object>(new CustomMessage("surat tugas opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-tugas-by-penilai/{kdSuratTugas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratTugasByPenilai(
            @PathVariable("kdSuratTugas") String kdSuratTugas) {
        LOGGER.info("open surat tugas by penilai");

        suratTugasService.openSuratTugasPenilai(kdSuratTugas);

        return new ResponseEntity<Object>(new CustomMessage("surat tugas opened"), HttpStatus.OK);
    }

}
