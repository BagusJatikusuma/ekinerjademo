package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import com.pemda.ekinerjademo.service.LaporanService;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.LaporanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.LaporanHistoryWrapper;
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

    @RequestMapping(value = "/create-laporan", method = RequestMethod.POST)
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

    @RequestMapping(value = "/get-daftar-laporan-history-by-pegawai/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarLaporanHistoryByPegawai(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get laporan history by nip "+nipPembuatSurat);

        List<Laporan> laporanList
                = laporanService.getByNipPembuatSurat(nipPembuatSurat);

        List<LaporanHistoryWrapper> laporanHistoryWrapperList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (Laporan laporan
                : laporanList) {

            laporanHistoryWrapperList
                    .add(new LaporanHistoryWrapper(
                            laporan.getKdLaporan(),
                            df.format(new Date(laporan.getTanggalPembuatanMilis())),
                            laporan.getStatusBaca()
                    ));
        }

        return new ResponseEntity<Object>(laporanHistoryWrapperList, HttpStatus.OK);
    }
}
