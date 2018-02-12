package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.Pengumuman;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.PengumumanService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.wrapper.input.PengumumanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.PengumumanWrapper;
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
import java.util.Locale;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class PengumumanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PengumumanController.class);

    @Autowired private PengumumanService pengumumanService;
    @Autowired private QutPegawaiCloneService qutPegawaiService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-pengumuman", method = RequestMethod.POST)
    ResponseEntity<?> createPengumuman(@RequestBody PengumumanInputWrapper inputWrapper) {
        LOGGER.info("create pengumuman");

        Integer nomorUrut = 0;

        String kdPengumuman = String.valueOf(new Date().getTime());

        Pengumuman pengumuman = new Pengumuman();

        pengumuman.setKdPengumuman(kdPengumuman);
        pengumuman.setNomorUrusan(inputWrapper.getNomorUrusan());
        pengumuman.setNomorUnit(inputWrapper.getNomorUnit());
        pengumuman.setNomorUrut(nomorUrut);
        pengumuman.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        pengumuman.setNomorTahun(Year.now().getValue());

        pengumuman.setTentang(inputWrapper.getTentang());
        pengumuman.setIsiPengumuman(inputWrapper.getIsiPengumuman());
        pengumuman.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        pengumuman.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        pengumuman.setTanggalPembuatanMilis(new Date().getTime());
        pengumuman.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());

        pengumuman.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        pengumuman.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        pengumuman.setNipPenilai("");
        pengumuman.setStatusBaca(0);

        pengumuman.setKdUrtug(inputWrapper.getKdUrtug());
        pengumuman.setTahunUrtug(inputWrapper.getTahunUrtug());

        if (inputWrapper.getKdPengumumanBawahan() == null) {
            pengumuman.setPathPenilaian(kdPengumuman);
            pengumuman.setStatusPenilaian(0);
            pengumuman.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            pengumuman.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            Pengumuman pengumumanBawahan
                    = pengumumanService.getByKdPengumuman(inputWrapper.getKdPengumumanBawahan());
            pengumuman.setPathPenilaian(pengumumanBawahan.getPathPenilaian()+"."+kdPengumuman);
            pengumuman.setKdNaskahPenugasan(pengumumanBawahan.getKdNaskahPenugasan());
            pengumuman.setJenisNaskahPenugasan(pengumumanBawahan.getJenisNaskahPenugasan());

            pengumumanBawahan.setStatusPenilaian(2);
            pengumumanService.create(pengumumanBawahan);
        }

        pengumumanService.create(pengumuman);

        return new ResponseEntity<Object>(new CustomMessage("pengumuman created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-pengumuman/{kdPengumuman}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarPengumuman(@PathVariable("kdPengumuman") String kdPengumuman) {
        LOGGER.info("sebar pengumuman");


        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-pengumuman/{kdPengumuman}", method = RequestMethod.PUT)
    ResponseEntity<?> approvePengumuman(@PathVariable("kdPengumuman") String kdPengumuman) {
        LOGGER.info("sebar pengumuman");



        return new ResponseEntity<>(new CustomMessage("pengumuman sudah disebar"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pengumuman-history-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getPengumumanHistoryByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get pengumuman history by pembuat");

        List<Pengumuman> pengumumanList = pengumumanService.getByPembuat(nipPembuat);

        List<SuratPerintahHistoryWrapper> pengumumanHistoryWrapperList
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");

        for (Pengumuman pengumuman : pengumumanList) {
            pengumumanHistoryWrapperList
                    .add(new SuratPerintahHistoryWrapper(
                        pengumuman.getKdPengumuman(),
                            DateUtilities.createLocalDate(new Date(pengumuman.getTanggalPembuatanMilis()), "dd MMMM yyyy", indoLocale),
                            false,
                            -1,
                            "pengumuman",
                            4,
                            pengumuman.getTanggalPembuatanMilis(),
                            pengumuman.getStatusPenilaian()
                    ));
        }

        return new ResponseEntity<Object>(pengumumanHistoryWrapperList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-pengumuman-by-kd-pengumuman/{kdPengumuman}", method = RequestMethod.GET)
    ResponseEntity<?> getPengumumanByKdPengumuman(@PathVariable("kdPengumuman") String kdPengumuman) {
        LOGGER.info("get pengumuman kd pengumuman");

        Pengumuman pengumuman = pengumumanService.getByKdPengumuman(kdPengumuman);

        CustomPegawaiCredential penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        // get penerima
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(pengumuman.getNipPenandatangan())) {
                penandatangan = qutPegawai;
                break;
            }
        }

        PengumumanWrapper pengumumanWrapper
                = new PengumumanWrapper(
                        pengumuman.getNomorUrusan(),
                        pengumuman.getNomorUrut(),
                        pengumuman.getNomorPasanganUrut(),
                        pengumuman.getNomorUnit(),
                        pengumuman.getNomorTahun(),
                        pengumuman.getTentang(),
                        pengumuman.getIsiPengumuman(),
                        penandatangan.getNip(),
                        penandatangan.getNama(),
                        penandatangan.getJabatan(),
                        tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                penandatangan.getGlrDpn(), penandatangan.getGlrBlk(),
                penandatangan.getPangkat(), penandatangan.getGol(),
                pengumuman.getKotaPembuatanSurat(),
                        pengumuman.getTanggalPembuatanMilis(),
                null);

        return new ResponseEntity<Object>(pengumumanWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-pengumuman/{kdPengumuman}", method = RequestMethod.PUT)
    ResponseEntity<?> openPengumuman(@PathVariable("kdPengumuman") String kdPengumuman) {
        LOGGER.info("open pengumuman");

        Pengumuman pengumuman = pengumumanService.getByKdPengumuman(kdPengumuman);

        return new ResponseEntity<Object>(new CustomMessage("pengumuman opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-pengumuman-penilai/{kdPengumuman}", method = RequestMethod.PUT)
    ResponseEntity<?> openPengumumanPenilai(@PathVariable("kdPengumuman") String kdPengumuman) {
        LOGGER.info("open pengumuman penilai");

        Pengumuman pengumuman = pengumumanService.getByKdPengumuman(kdPengumuman);
        pengumuman.setStatusPenilaian(1);

        pengumumanService.create(pengumuman);

        return new ResponseEntity<Object>(new CustomMessage("pengumuman opened"), HttpStatus.OK);

    }

}