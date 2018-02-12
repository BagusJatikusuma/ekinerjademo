package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratEdaranService;
import com.pemda.ekinerjademo.wrapper.input.SuratEdaranInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.SuratEdaranSubabInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratEdaranSubWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratEdaranWrapper;
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
import java.util.UUID;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratEdaranController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratEdaranController.class);

    @Autowired private SuratEdaranService suratEdaranService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-surat-edaran", method = RequestMethod.POST)
    ResponseEntity<?> createSuratEdaran(@RequestBody SuratEdaranInputWrapper inputWrapper) {
        LOGGER.info("create surat edaran");

        String kdSuratEdaran = String.valueOf(new Date().getTime());

        List<SuratEdaranSub> suratEdaranSubs
                = new ArrayList<>();

        for (SuratEdaranSubabInputWrapper sub
                : inputWrapper.getSubLain()) {
            SuratEdaranSubId id
                    = new SuratEdaranSubId(kdSuratEdaran, String.valueOf(UUID.randomUUID().toString()));

            SuratEdaranSub suratEdaranSub
                    = new SuratEdaranSub();
            suratEdaranSub.setSuratEdaranSubId(id);
            suratEdaranSub.setNamaSub(sub.getNamaSub());
            suratEdaranSub.setIsiSub(sub.getIsiSub());

            suratEdaranSubs.add(suratEdaranSub);
        }

        SuratEdaran suratEdaran = new SuratEdaran();

        suratEdaran.setKdSuratEdaran(kdSuratEdaran);

        suratEdaran.setNomorUrut(0);
        suratEdaran.setNomorTahun(Year.now().getValue());

        suratEdaran.setTentang(inputWrapper.getTentang());
        suratEdaran.setTanggalPembuatanMilis(new Date().getTime());
        suratEdaran.setKotaPembuatanSurat(inputWrapper.getTempat());
        suratEdaran.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratEdaran.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratEdaran.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratEdaran.setLatarBelakang(inputWrapper.getLatarBelakang());
        suratEdaran.setMaksudDanTujuan(inputWrapper.getMaksudDanTujuan());
        suratEdaran.setRuangLingkup(inputWrapper.getRuangLingkup());
        suratEdaran.setDasar(inputWrapper.getDasar());

        suratEdaran.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        suratEdaran.setKdUrtug(inputWrapper.getKdUrtug());
        suratEdaran.setTahunUrtug(inputWrapper.getTahunUrtug());

        if (inputWrapper.getKdSuratEdaranBawahan() == null) {
            suratEdaran.setPathPenilaian(kdSuratEdaran);
            suratEdaran.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            suratEdaran.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            SuratEdaran suratEdaranBawahan
                    = suratEdaranService.getByKdSuratEdaran(inputWrapper.getKdSuratEdaranBawahan());
            suratEdaran.setPathPenilaian(suratEdaranBawahan.getPathPenilaian()+"."+kdSuratEdaran);
            suratEdaran.setKdNaskahPenugasan(suratEdaranBawahan.getKdNaskahPenugasan());
            suratEdaran.setJenisNaskahPenugasan(suratEdaranBawahan.getJenisNaskahPenugasan());

            suratEdaranBawahan.setStatusPenilaian(2);
            suratEdaranService.create(suratEdaranBawahan);
        }

        suratEdaran.setNipPenilai(null);
        suratEdaran.setStatusPenilaian(0);
        suratEdaran.setAlasanPenolakan(null);
        suratEdaran.setStatusBaca(0);

        suratEdaranService.create(suratEdaran);
        for (SuratEdaranSub suratEdaranSub
                : suratEdaranSubs) {
            suratEdaranService.createSuratEdaranSub(suratEdaranSub);
        }

        if (inputWrapper.isSuratPejabat()) {
            SuratEdaranPejabat suratEdaranPejabat
                    = new SuratEdaranPejabat();
            suratEdaranPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            suratEdaranPejabat.setKdSuratEdaran(kdSuratEdaran);

            suratEdaranService.createSuratEdaranPejabat(suratEdaranPejabat);
        } else {
            SuratEdaranNonPejabat suratEdaranNonPejabat
                    = new SuratEdaranNonPejabat();
            suratEdaranNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());
            suratEdaranNonPejabat.setKdSuratEdaran(kdSuratEdaran);

            suratEdaranService.createSuratEdaranNonPejabat(suratEdaranNonPejabat);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat edaran created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("sebar surat edaran");

        return new ResponseEntity<Object>(new CustomMessage("surat edaran telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("sebar surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("terima surat edaran");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-edaran-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratEdaranHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat edaran history");

        List<SuratEdaran> suratEdaranList
                = suratEdaranService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> suratEdaranHistoryWrappers
                = new ArrayList<>();

        boolean isSuratPejabat = false;
        for (SuratEdaran suratEdaran
                : suratEdaranList) {
            if (suratEdaran.getSuratEdaranPejabat() != null) {
                isSuratPejabat = true;
            } else {
                isSuratPejabat = false;
            }

            suratEdaranHistoryWrappers
                    .add(new SuratPerintahHistoryWrapper(
                            suratEdaran.getKdSuratEdaran(),
                            null,
                            isSuratPejabat,
                            -1,
                            "surat edaran",
                            6,
                            suratEdaran.getTanggalPembuatanMilis(),
                            suratEdaran.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratEdaranHistoryWrappers, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-edaran-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratEdaranTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat edaran target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-edaran-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratEdaranTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat edaran target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-edaran-by-kd-surat-edaran/{kdSuratEdaran}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratEdaranByKdSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("get surat edaran kd surat edaran");

        SuratEdaran suratEdaran = suratEdaranService.getByKdSuratEdaran(kdSuratEdaran);

        SuratEdaranWrapper suratEdaranWrapper
                = new SuratEdaranWrapper();
        List<SuratEdaranSubWrapper> suratEdaranSubWrappers
                = new ArrayList<>();

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential penandatangan = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (suratEdaran.getNipPenandatangan()
                    .equals(pegawai.getNip())) {
                penandatangan = pegawai;

                break;
            }
        }

        boolean isSuratPejabat = false;

        if (suratEdaran.getSuratEdaranPejabat() != null)
            isSuratPejabat = true;

        suratEdaranWrapper.setKdSuratEdaran(suratEdaran.getKdSuratEdaran());
        suratEdaranWrapper.setNomorTahun(suratEdaran.getNomorTahun());
        suratEdaranWrapper.setNomorUrut(suratEdaran.getNomorUrut());

        suratEdaranWrapper.setTentang(suratEdaran.getTentang());
        suratEdaranWrapper.setLatarBelakang(suratEdaran.getLatarBelakang());
        suratEdaranWrapper.setMaksudDanTujuan(suratEdaran.getMaksudDanTujuan());
        suratEdaranWrapper.setRuangLingkup(suratEdaran.getRuangLingkup());
        suratEdaranWrapper.setDasar(suratEdaran.getDasar());

        for (SuratEdaranSub suratEdaranSub
                : suratEdaran.getSuratEdaranSubList()) {
            suratEdaranSubWrappers
                    .add(new SuratEdaranSubWrapper(
                            suratEdaranSub.getSuratEdaranSubId().getKdSuratEdaran(),
                            suratEdaranSub.getNamaSub(),
                            suratEdaranSub.getIsiSub()));
        }
        suratEdaranWrapper.setSubLain(suratEdaranSubWrappers);

        suratEdaranWrapper.setTanggalPembuatanMilis(suratEdaran.getTanggalPembuatanMilis());
        suratEdaranWrapper.setKotaPembuatanSurat(suratEdaran.getKotaPembuatanSurat());

        suratEdaranWrapper.setNipPenandatangan(penandatangan.getNip());
        suratEdaranWrapper.setNamaPenandatangan(penandatangan.getNama());
        suratEdaranWrapper.setJabatanPenandatangan(penandatangan.getJabatan());
        suratEdaranWrapper.setUnitKerjaPenandatangan(tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja());
        suratEdaranWrapper.setGelarDepanPenandatangan(penandatangan.getGlrDpn());
        suratEdaranWrapper.setGelarBelakangPenandatangan(penandatangan.getGlrBlk());
        suratEdaranWrapper.setPangkatPenandatangan(penandatangan.getPangkat());
        suratEdaranWrapper.setGolonganPenandatangan(penandatangan.getGol());
        suratEdaranWrapper.setSuratPejabat(isSuratPejabat);
        suratEdaranWrapper.setBarcodeImage(null);

        return new ResponseEntity<Object>(suratEdaranWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-edaran/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratEdaran(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("open surat edaran");

        SuratEdaran suratEdaran
                = suratEdaranService.getByKdSuratEdaran(kdSuratEdaran);
        suratEdaran.setStatusPenilaian(1);
        return new ResponseEntity<Object>(new CustomMessage("surat edaran opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-edaran-penilai/{kdSuratEdaran}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratEdaranPenilai(@PathVariable("kdSuratEdaran") String kdSuratEdaran) {
        LOGGER.info("open surat edaran penilai");

        SuratEdaran suratEdaran
                = suratEdaranService.getByKdSuratEdaran(kdSuratEdaran);
        suratEdaran.setStatusPenilaian(1);

        suratEdaranService.create(suratEdaran);
        return new ResponseEntity<Object>(new CustomMessage("surat edaran opened by penilai"), HttpStatus.OK);

    }

}
