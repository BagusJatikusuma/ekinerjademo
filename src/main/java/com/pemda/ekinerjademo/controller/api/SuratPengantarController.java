package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantar;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantarIsi;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantarIsiId;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratPengantarService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.SuratPengantarInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.SuratPengantarIsiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratPengantarHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratPengantarTargetWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratPengantarWrapper;
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
public class SuratPengantarController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratPengantarController.class);

    @Autowired private SuratPengantarService suratPengantarService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private TkdJabatanService tkdJabatanService;

    @RequestMapping(value = "/create-surat-pengantar", method = RequestMethod.POST)
    ResponseEntity<?> createSuratPengantar(@RequestBody SuratPengantarInputWrapper inputWrapper) {
        LOGGER.info("create surat pengantar");

        Integer nomorSurat = 0;
        String kdSuratPengantar = String.valueOf(new Date().getTime());

        List<SuratPengantarIsi> isiSuratPengantarList = new ArrayList<>();

        //create surat pengantar isi list
        int incrementId = 0;
        for (SuratPengantarIsiInputWrapper isiInputWrapper
                : inputWrapper.getSuratPengantarIsiList()) {
            SuratPengantarIsiId id
                    = new SuratPengantarIsiId(
                            kdSuratPengantar,
                    kdSuratPengantar+String.valueOf(incrementId));

            SuratPengantarIsi suratPengantarIsi = new SuratPengantarIsi();

            suratPengantarIsi.setSuratPengantarIsiId(id);
            suratPengantarIsi.setBanyakNaskah(isiInputWrapper.getBanyakNaskah());
            suratPengantarIsi.setKeterangan(isiInputWrapper.getKeterangan());
            suratPengantarIsi.setNaskahDinasYangDikirim(isiInputWrapper.getNaskahDinasYangDikirim());

            isiSuratPengantarList.add(suratPengantarIsi);

            incrementId++;
        }

        //create surat pengantar
        SuratPengantar suratPengantar = new SuratPengantar();

        suratPengantar.setKdSuratPengantar(kdSuratPengantar);
        suratPengantar.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratPengantar.setNomorUrut(nomorSurat);
        suratPengantar.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratPengantar.setNomorUnit(inputWrapper.getNomorUnit());
        suratPengantar.setNomorTahun(Year.now().getValue());

        suratPengantar.setTanggalPembuatanMilis(new Date().getTime());
        suratPengantar.setTanggalDiterimaSuratPengantar(inputWrapper.getTanggalDiterimaSuratPengantar());
        suratPengantar.setKdJabatanPenerimaSuratPengantar(inputWrapper.getKdJabatanPenerimaSuratPengantar());
        suratPengantar.setNipPemberiSuratPengantar(inputWrapper.getNipPemberiSuratPengantar());
        suratPengantar.setNomorTeleponPemberi(inputWrapper.getNomorTeleponPemberi());
        suratPengantar.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());

        suratPengantar.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratPengantar.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        suratPengantar.setNipPenilai("");
        suratPengantar.setStatusPenilaian(0);
        suratPengantar.setAlasanPenolakan("");

        if (inputWrapper.getKdSuratPengantarBawahan() == null) {
            suratPengantar.setPathPenilaian(kdSuratPengantar);
        } else {
            SuratPengantar suratPengantarBawahan
                    = suratPengantarService.getByKdSuratPengantar(inputWrapper.getKdSuratPengantarBawahan());
            suratPengantar.setPathPenilaian(suratPengantarBawahan.getPathPenilaian()+"."+kdSuratPengantar);

            suratPengantarBawahan.setStatusPenilaian(2);
            suratPengantarService.create(suratPengantarBawahan);

        }
        //save surat pengantar
        suratPengantarService.create(suratPengantar);
        //save surat pengantar isi
        for (SuratPengantarIsi isi : isiSuratPengantarList) {
            suratPengantarService.createSuratPengantarIsi(isi);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat pengantar created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-pengantar/{kdSuratPengantar}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratPengantar(@PathVariable("kdSuratPengantar") String kdSuratPengantar) {
        LOGGER.info("sebar surat pengantar");

        SuratPengantar suratPengantar = suratPengantarService.getByKdSuratPengantar(kdSuratPengantar);

        return new ResponseEntity<Object>(new CustomMessage("surat pengantar telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-pengantar/{kdSuratPengantar}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratPengantar(@PathVariable("kdSuratPengantar") String kdSuratPengantar) {
        LOGGER.info("sebar surat pengantar");


        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-pengantar/{kdSuratPengantar}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratPengantar(@PathVariable("kdSuratPengantar") String kdSuratPengantar) {
        LOGGER.info("terima surat pengantar");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-pengantar-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPengantarHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat pengantar history");

        List<SuratPengantar> suratPengantarList = suratPengantarService.getByNipPembuat(nipPembuat);
        List<SuratPengantarHistoryWrapper> suratPengantarHistoryWrapperList = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (SuratPengantar suratPengantar : suratPengantarList) {
            suratPengantarHistoryWrapperList
                    .add(new SuratPengantarHistoryWrapper(
                            suratPengantar.getKdSuratPengantar(),
                            df.format(new Date(suratPengantar.getTanggalPembuatanMilis())),
                            false,
                            0,
                            "surat pengantar",
                            10,
                            suratPengantar.getTanggalPembuatanMilis(),
                            suratPengantar.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratPengantarHistoryWrapperList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-pengantar-by-target/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPengantarTarget(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat pengantar target");

        List<SuratPengantar> suratPengantarTargetList
                = suratPengantarService.getByJabatanTarget(kdJabatanTarget);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        List<SuratPengantarTargetWrapper> suratPengantarTargetWrappers
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (SuratPengantar suratTarget : suratPengantarTargetList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getNipPemberiSuratPengantar())) {
                    suratPengantarTargetWrappers
                            .add(new SuratPengantarTargetWrapper(
                                    suratTarget.getKdSuratPengantar(),
                                    df.format(new Date(suratTarget.getTanggalPembuatanMilis())),
                                    suratTarget.getTanggalPembuatanMilis(),
                                    false,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan(),
                                    0));
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(suratPengantarTargetWrappers, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-pengantar-by-target-unread/{kdJabatanTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratPengantarTargetUnread(@PathVariable("kdJabatanTarget") String kdJabatanTarget) {
        LOGGER.info("get surat pengantar target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-pengantar-by-kd-surat/{kdSuratPengantar}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratPengantarByKdSurat(@PathVariable("kdSuratPengantar") String kdSuratPengantar) {
        LOGGER.info("get surat pengantar kd surat");

        SuratPengantar suratPengantar
                = suratPengantarService.getByKdSuratPengantar(kdSuratPengantar);

        CustomPegawaiCredential pemberiSurat = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (suratPengantar.getNipPemberiSuratPengantar()
                    .equals(pegawai.getNip())) {
                pemberiSurat = pegawai;

                break;
            }
        }

        TkdJabatan jabatan
                = tkdJabatanService.getTkdJabatan(suratPengantar.getKdJabatanPenerimaSuratPengantar());

        SuratPengantarWrapper suratPengantarWrapper
                = new SuratPengantarWrapper(
                        suratPengantar.getNomorUrusan(),
                        suratPengantar.getNomorUrut(),
                        suratPengantar.getNomorPasanganUrut(),
                        suratPengantar.getNomorUnit(),
                        suratPengantar.getNomorTahun(),
                        suratPengantar.getTanggalPembuatanMilis(),
                        suratPengantar.getTanggalDiterimaSuratPengantar(),
                        jabatan.getKdJabatan(),
                        jabatan.getJabatan(),
                        pemberiSurat.getNip(),
                        pemberiSurat.getNama(),
                        pemberiSurat.getJabatan(),
                        pemberiSurat.getUnitKerja(),
                        suratPengantar.getNomorTeleponPemberi());

        return new ResponseEntity<Object>(suratPengantarWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-pengantar/{kdSuratPengantar}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratPengantar(@PathVariable("kdSuratPengantar") String kdSuratPengantar) {
        LOGGER.info("open surat pengantar");

        SuratPengantar suratPengantar
                = suratPengantarService.getByKdSuratPengantar(kdSuratPengantar);
        suratPengantar.setStatusPenilaian(1);

        return new ResponseEntity<Object>(new CustomMessage("surat pengantar opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-pengantar-penilai/{kdSuratPengantar}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratPengantarPenilai(@PathVariable("kdSuratPengantar") String kdSuratPengantar) {
        LOGGER.info("open surat pengantar penilai");

        SuratPengantar suratPengantar
                = suratPengantarService.getByKdSuratPengantar(kdSuratPengantar);
        suratPengantar.setStatusPenilaian(1);

        suratPengantarService.create(suratPengantar);

        return new ResponseEntity<Object>(new CustomMessage("surat pengantar opened by penilai"), HttpStatus.OK);

    }

}
