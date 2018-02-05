package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratDinasService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.SuratDinasInputWrapper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratDinasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratDinasController.class);

    @Autowired private SuratDinasService suratDinasService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private TkdJabatanService tkdJabatanService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-surat-dinas", method = RequestMethod.POST)
    ResponseEntity<?> createSuratDinas(@RequestBody SuratDinasInputWrapper inputWrapper) {
        LOGGER.info("create surat dinas");

        String kdSuratDinas = String.valueOf(new Date().getTime());

        List<TembusanSuratDinas> tembusanSuratDinasList
                = new ArrayList<>();
        //build tembusan surat dinas list
        for (String jabatanTembusan
                : inputWrapper.getKdTembusanList()) {
            TembusanSuratDinasId tembusanSuratDinasId
                    = new TembusanSuratDinasId(kdSuratDinas, jabatanTembusan);
            TembusanSuratDinas tembusanSuratDinas
                    = new TembusanSuratDinas();
            tembusanSuratDinas.setTembusanSuratDinasId(tembusanSuratDinasId);
            tembusanSuratDinas.setStatusBaca(0);
            tembusanSuratDinas.setStatusDiterima(0);

            tembusanSuratDinasList.add(tembusanSuratDinas);
        }
        //build surat dinas
        SuratDinas suratDinas = new SuratDinas();

        suratDinas.setKdSuratDinas(kdSuratDinas);
        suratDinas.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratDinas.setNomorUrut(0);
        suratDinas.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratDinas.setNomorUnit(inputWrapper.getNomorUnit());
        suratDinas.setNomorTahun(Year.now().getValue());

        suratDinas.setSifat(inputWrapper.getSifat());
        suratDinas.setLampiran(inputWrapper.getLampiran());
        suratDinas.setHal(inputWrapper.getHal());
        suratDinas.setKdJabatanPenerimaSuratDinas(inputWrapper.getKdJabatanPenerimaSuratDinas());
        suratDinas.setTanggalPembuatanMilis(new Date().getTime());
        suratDinas.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSuratDinas());
        suratDinas.setIsiSuratDinas(inputWrapper.getIsiSuratDinas());
        suratDinas.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratDinas.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratDinas.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        suratDinas.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
        suratDinas.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        suratDinas.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        if (inputWrapper.getKdSuratDinasBawahan() == null) {
            suratDinas.setPathPenilaian(kdSuratDinas);
        } else {
            SuratDinas suratDinasBawahan
                    = suratDinasService.getByKdSuratDinas(inputWrapper.getKdSuratDinasBawahan());
            suratDinas.setPathPenilaian(suratDinasBawahan.getPathPenilaian()+"."+kdSuratDinas);

            suratDinasBawahan.setStatusPenilaian(2);
            suratDinasService.create(suratDinasBawahan);
        }

        suratDinas.setNipPenilai("");
        suratDinas.setStatusPenilaian(0);
        suratDinas.setAlasanPenolakan("");

        suratDinasService.create(suratDinas);
        for (TembusanSuratDinas tembusanSuratDinas
                : tembusanSuratDinasList) {
            suratDinasService.createTembusanSuratDinas(tembusanSuratDinas);
        }

        if (inputWrapper.isSuratPejabat()) {
            SuratDinasPejabat suratDinasPejabat
                    = new SuratDinasPejabat();
            suratDinasPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            suratDinasPejabat.setKdSuratDinas(kdSuratDinas);

            suratDinasService.createSuratDinasPejabat(suratDinasPejabat);
        } else {
            SuratDinasNonPejabat suratDinasNonPejabat
                    = new SuratDinasNonPejabat();
            suratDinasNonPejabat.setKdSuratDinas(kdSuratDinas);
            suratDinasNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());

            suratDinasService.createSuratDinasNonPejabat(suratDinasNonPejabat);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat dinas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/sebar-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("sebar surat dinas");

        return new ResponseEntity<Object>(new CustomMessage("surat dinas telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("sebar surat dinas");



        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("terima surat dinas");


        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat dinas history");

        List<SuratDinas> suratDinasList
                = suratDinasService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> suratDinasHistoryList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        for (SuratDinas suratDinas
                : suratDinasList) {

            if (suratDinas.getSuratDinasPejabat() != null) {
                isSuratPejabat = true;
            } else {
                isSuratPejabat = false;
            }

            suratDinasHistoryList
                    .add(new SuratPerintahHistoryWrapper(
                            suratDinas.getKdSuratDinas(),
                            null,
                            isSuratPejabat,
                            suratDinas.getStatusBaca(),
                            "surat dinas",
                            5,
                            suratDinas.getTanggalPembuatanMilis(),
                            suratDinas.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratDinasHistoryList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat dinas target");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> targetSuratDinasListWrapper
                = new ArrayList<>();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<SuratDinas> suratDinasTargetList
                = suratDinasService.getByJabatanPenerima(pegawaiTarget.getKdJabatan());
        List<TembusanSuratDinas> tembusanSuratDinasTargetList
                = suratDinasService.getTembusanSuratDinas(pegawaiTarget.getKdJabatan());

        //get by target
        boolean isSuratPejabat = false;
        for (SuratDinas suratDinas
                : suratDinasTargetList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratDinas.getNipPenandatangan())) {

                    if (suratDinas.getSuratDinasPejabat() != null) {
                        isSuratPejabat = true;
                    } else {
                        isSuratPejabat = false;
                    }

                    targetSuratDinasListWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    suratDinas.getKdSuratDinas(),
                                    "",
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan()));
                    break;
                }
            }
        }
        //get by tembusan
        for (TembusanSuratDinas tembusanSuratDinas
                : tembusanSuratDinasTargetList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getKdJabatan()
                        .equals(tembusanSuratDinas.getSuratDinas().getKdJabatanPenerimaSuratDinas())) {

                    if (tembusanSuratDinas.getSuratDinas().getSuratDinasPejabat() != null) {
                        isSuratPejabat = true;
                    } else {
                        isSuratPejabat = false;
                    }

                    targetSuratDinasListWrapper
                            .add(new SuratPerintahTargetWrapper(
                                    tembusanSuratDinas.getSuratDinas().getKdSuratDinas(),
                                    "",
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan()));
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(targetSuratDinasListWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat dinas target unread");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> targetSuratDinasListWrapper
                = new ArrayList<>();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<SuratDinas> suratDinasTargetList
                = suratDinasService.getByJabatanPenerima(pegawaiTarget.getKdJabatan());
        List<TembusanSuratDinas> tembusanSuratDinasTargetList
                = suratDinasService.getTembusanSuratDinas(pegawaiTarget.getKdJabatan());

        //get by target
        boolean isSuratPejabat = false;
        for (SuratDinas suratDinas
                : suratDinasTargetList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratDinas.getNipPenandatangan())) {

                    if (suratDinas.getSuratDinasPejabat() != null) {
                        isSuratPejabat = true;
                    } else {
                        isSuratPejabat = false;
                    }

                    if (suratDinas.getStatusBaca() == 0) {
                        targetSuratDinasListWrapper
                                .add(new SuratPerintahTargetWrapper(
                                        suratDinas.getKdSuratDinas(),
                                        "",
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan()));
                    }
                    break;
                }
            }
        }
        //get by tembusan
        for (TembusanSuratDinas tembusanSuratDinas
                : tembusanSuratDinasTargetList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getKdJabatan()
                        .equals(tembusanSuratDinas.getSuratDinas().getKdJabatanPenerimaSuratDinas())) {

                    if (tembusanSuratDinas.getSuratDinas().getSuratDinasPejabat() != null) {
                        isSuratPejabat = true;
                    } else {
                        isSuratPejabat = false;
                    }

                    if (tembusanSuratDinas.getStatusBaca() == 0) {
                        targetSuratDinasListWrapper
                                .add(new SuratPerintahTargetWrapper(
                                        tembusanSuratDinas.getSuratDinas().getKdSuratDinas(),
                                        "",
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan()));
                    }
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(targetSuratDinasListWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-dinas-by-kd-surat-dinas/{kdSuratDinas}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratDinasByKdSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("get surat dinas kd surat dinas");

        SuratDinas suratDinas
                = suratDinasService.getByKdSuratDinas(kdSuratDinas);
        SuratDinasWrapper suratDinasWrapper
                = new SuratDinasWrapper();

        suratDinasWrapper.setNomorUrusan(suratDinas.getNomorUrusan());
        suratDinasWrapper.setNomorUrut(suratDinas.getNomorUrut());
        suratDinasWrapper.setNomorPasanganUrut(suratDinas.getNomorPasanganUrut());
        suratDinasWrapper.setNomorUnit(suratDinas.getNomorUnit());
        suratDinasWrapper.setNomorTahun(suratDinas.getNomorTahun());

        suratDinasWrapper.setSifat(suratDinas.getSifat());
        suratDinasWrapper.setHal(suratDinas.getHal());
        suratDinasWrapper.setLampiran(suratDinas.getLampiran());

        List<TkdJabatan> tkdJabatanList
                = tkdJabatanService.getAll();
        TkdJabatan jabatanPenerima = new TkdJabatan();
        List<JabatanWrapper> tembusanJabatanWrapperList
                = new ArrayList<>();

        for (TkdJabatan tkdJabatan
                : tkdJabatanList) {
            if (tkdJabatan.getKdJabatan()
                    .equals(suratDinas.getKdJabatanPenerimaSuratDinas())) {
                jabatanPenerima = tkdJabatan;
                break;
            }
        }
        for (TembusanSuratDinas tembusanSuratDinas
                : suratDinas.getTembusanSuratDinasList()) {
            for (TkdJabatan tkdJabatan
                    : tkdJabatanList) {
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusanSuratDinas.getTembusanSuratDinasId().getKdJabatan())) {
                    tembusanJabatanWrapperList
                            .add(new JabatanWrapper(
                                    tkdJabatan.getKdJabatan(),
                                    tkdJabatan.getJabatan(),
                                    tkdJabatan.getEselon()));
                    break;
                }
            }
        }

        suratDinasWrapper.setKdJabatanPenerimaSuratDinas(jabatanPenerima.getKdJabatan());
        suratDinasWrapper.setJabatanPenerimaSuratDinas(jabatanPenerima.getJabatan());
        suratDinasWrapper.setTanggalPembuatanMilis(suratDinas.getTanggalPembuatanMilis());
        suratDinasWrapper.setKotaPembuatanSurat(suratDinas.getKotaPembuatanSurat());
        suratDinasWrapper.setIsiSuratDinas(suratDinas.getIsiSuratDinas());

        suratDinasWrapper.setTembusanSuratDinasWrapper(tembusanJabatanWrapperList);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential penandatangan = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (suratDinas.getNipPenandatangan()
                    .equals(pegawai.getNip())) {
                penandatangan = pegawai;

                break;
            }
        }
        suratDinasWrapper.setNipPenandatangan(penandatangan.getNip());
        suratDinasWrapper.setNamaPenandatangan(penandatangan.getNama());
        suratDinasWrapper.setJabatanPenandatangan(penandatangan.getJabatan());
        suratDinasWrapper.setUnitKerjaPenandatangan(tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja());
        suratDinasWrapper.setPangkatPenandatangan(penandatangan.getPangkat());
        suratDinasWrapper.setGelarDepanPenandatangan(penandatangan.getGlrDpn());
        suratDinasWrapper.setGelarBelakangPenandatangan(penandatangan.getGlrBlk());
        boolean isSuratPejabat = false;

        if (suratDinas.getSuratDinasPejabat() != null) isSuratPejabat = true;

        suratDinasWrapper.setSuratPejabat(isSuratPejabat);

        return new ResponseEntity<Object>(suratDinasWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("open surat dinas");

        SuratDinas suratDinas = suratDinasService.getByKdSuratDinas(kdSuratDinas);
        suratDinas.setStatusBaca(1);
        return new ResponseEntity<Object>(new CustomMessage("surat dinas opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-dinas-penilai/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratDinasPenilai(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("open surat dinas penilai");

        SuratDinas suratDinas
                = suratDinasService.getByKdSuratDinas(kdSuratDinas);
        suratDinas.setStatusPenilaian(1);

        suratDinasService.create(suratDinas);
        return new ResponseEntity<Object>(new CustomMessage("surat dinas opened by penilai"), HttpStatus.OK);

    }

}
