package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintah;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.LaporanService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.LaporanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.LaporanHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.LaporanWrapper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class LaporanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LaporanController.class);

    @Autowired private LaporanService laporanService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-laporan", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createLaporan(
            @RequestBody LaporanInputWrapper inputWrapper) {
        LOGGER.info("create laporan");

        String kdLaporan = String.valueOf(new Date().getTime());

        Laporan laporan = new Laporan();

        laporan.setKdLaporan(kdLaporan);

        laporan.setTentang(inputWrapper.getTentang());
        laporan.setUmum(inputWrapper.getUmum());
        laporan.setMaksudDanTujuan(inputWrapper.getMaksudDanTujuan());

        laporan.setRuangLingkup(inputWrapper.getRuangLingkup());
        laporan.setDasar(inputWrapper.getDasar());
        laporan.setKegiatanYangDilaksanakan(inputWrapper.getKegiatanYangDilakukan());

        laporan.setHasilYangDicapai(inputWrapper.getHasilYangDicapai());
        laporan.setSimpulanDanSaran(inputWrapper.getSimpulanDanSaran());
        laporan.setPenutup(inputWrapper.getPenutup());
        laporan.setNipPenandatangan(inputWrapper.getNipPenandatangan());

        laporan.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        laporan.setTanggalPembuatanMilis(new Date().getTime());
        laporan.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        laporan.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        laporan.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        laporan.setNipPenilai("");
        laporan.setStatusPenilaian(0);
        laporan.setAlasanPenolakan("");

        if (inputWrapper.getKdLaporanBawahan() == null) {
            laporan.setPathPenilaian(kdLaporan);
        } else {
            Laporan laporanBawahan
                    = laporanService.getLaporan(inputWrapper.getKdLaporanBawahan());
            laporan.setPathPenilaian(laporanBawahan.getPathPenilaian()+"."+kdLaporan);

            laporanBawahan.setStatusPenilaian(2);
            laporanService.createLaporan(laporanBawahan);
        }

        laporanService.createLaporan(laporan);

        return new ResponseEntity<Object>(
                new CustomMessage("laporan created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-daftar-laporan-history-by-pegawai/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarLaporanHistoryByPegawai(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get laporan history by nip "+nipPembuatSurat);

        List<Laporan> laporanList
                = laporanService.getByNipPembuatSurat(nipPembuatSurat);
        List<SuratPerintahHistoryWrapper> laporanHistoryList
                = new ArrayList<>();

        for (Laporan laporan
                : laporanList) {
            laporanHistoryList
                    .add(new SuratPerintahHistoryWrapper(laporan.getKdLaporan(),
                            "",
                            false,
                            laporan.getStatusBaca(),
                            "laporan",
                            1,
                            laporan.getTanggalPembuatanMilis(),
                            laporan.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(laporanHistoryList, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-laporan-penilai/{kdLaporan}/{nipPegawai}", method = RequestMethod.PUT)
    ResponseEntity<?> openLaporanPenilai(
            @PathVariable("kdLaporan") String kdLaporan,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("open laporan");

        laporanService.openLaporanByPenilai(kdLaporan);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);

    }

    @RequestMapping(value = "/get-laporan-by-kd-laporan/{kdLaporan}", method = RequestMethod.GET)
    ResponseEntity<?> getLaporanByKdLaporan(@PathVariable("kdLaporan") String kdLaporan) {
        LOGGER.info("get laporan by kode laporan");

        Laporan laporan = laporanService.getLaporan(kdLaporan);

        if (laporan == null)
            return new ResponseEntity<Object>(new CustomMessage("laporan tidak ditemukan"), HttpStatus.NOT_FOUND);

        CustomPegawaiCredential penandatangan = null, pembuat = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        // get penandatangan
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(laporan.getNipPenandatangan())) {
                penandatangan = qutPegawai;
                break;
            }
        }
        // get pembuat
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(laporan.getNipPembuatSurat())) {
                pembuat = qutPegawai;
                break;
            }
        }

        LaporanWrapper laporanWrapper
                = new LaporanWrapper(
                        laporan.getKdLaporan(),
                        laporan.getTentang(),
                        laporan.getUmum(),
                        laporan.getMaksudDanTujuan(),
                        laporan.getRuangLingkup(),
                        laporan.getDasar(),
                        laporan.getKegiatanYangDilaksanakan(),
                        laporan.getHasilYangDicapai(),
                        laporan.getSimpulanDanSaran(),
                        laporan.getPenutup(),
                        penandatangan.getNip(),
                        penandatangan.getNama(),
                        penandatangan.getJabatan(),
                        tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                        laporan.getStatusBaca(),
                        laporan.getKotaPembuatanSurat(),
                        laporan.getTanggalPembuatanMilis(),
                        pembuat.getNip(),
                        pembuat.getNama(),
                        pembuat.getJabatan(),
                        tkdUnkDao.findOne(pembuat.getKdUnitKerja()).getUnitKerja());

        return new ResponseEntity<Object>(laporanWrapper,HttpStatus.OK);
    }

}