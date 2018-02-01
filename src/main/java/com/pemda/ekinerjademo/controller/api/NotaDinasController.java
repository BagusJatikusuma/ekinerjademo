package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.NotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinasId;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.NotaDinasService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.NotaDinasInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class NotaDinasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PejabatPenilaiDinilaiController.class);

    @Autowired private NotaDinasService notaDinasService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired
    private TkdJabatanService tkdJabatanService;
    @Autowired
    private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-nota-dinas", method = RequestMethod.POST)
    ResponseEntity<?> createNotaDinas(@RequestBody NotaDinasInputWrapper inputWrapper) {
        LOGGER.info("create nota dinas");

        String kdNotaDinas = String.valueOf(new Date().getTime());

        List<TembusanNotaDinas> tembusanNotaDinasList
                = new ArrayList<>();
        //build termbusan nota dinas
        for (String kdJabatan
                : inputWrapper.getKdTembusanList()) {
            TembusanNotaDinasId id
                    = new TembusanNotaDinasId(kdNotaDinas, kdJabatan);
            TembusanNotaDinas tembusanNotaDinas = new TembusanNotaDinas();

            tembusanNotaDinas.setTembusanNotaDinasId(id);
            tembusanNotaDinas.setStatusBaca(0);
            tembusanNotaDinas.setStatusDiterima(0);

            tembusanNotaDinasList.add(tembusanNotaDinas);
        }

        NotaDinas notaDinas = new NotaDinas();

        notaDinas.setKdNotaDinas(kdNotaDinas);
        notaDinas.setNomorUrusan(inputWrapper.getNomorUrusan());
        notaDinas.setNomorUnit(inputWrapper.getNomorUnit());
        notaDinas.setNomorUrut(0);
        notaDinas.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        notaDinas.setNomorTahun(Year.now().getValue());

        notaDinas.setKdJabatanPenerimaNotaDinas(inputWrapper.getKdJabatanPenerimaNotaDinas());
        notaDinas.setNipPemberiNotaDinas(inputWrapper.getNipPemberiNotaDinas());
        notaDinas.setHal(inputWrapper.getHal());
        notaDinas.setTanggalPembuatanMilis(new Date().getTime());
        notaDinas.setIsiNotaDinas(inputWrapper.getIsiNotaDinas());
        notaDinas.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        notaDinas.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        notaDinas.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        notaDinas.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
        notaDinas.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());

        notaDinas.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        notaDinas.setNipPenilai("");
        notaDinas.setStatusPenilaian(0);
        notaDinas.setAlasanPenolakan("");

        notaDinasService.create(notaDinas);

        for (TembusanNotaDinas tembusanNotaDinas
                : tembusanNotaDinasList) {
            notaDinasService.createTembusanNotaDinas(tembusanNotaDinas);
        }

        return new ResponseEntity<Object>(new CustomMessage("nota dinas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get nota dinas by pembuat");

        List<NotaDinas> notaDinasList
                = notaDinasService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> notaDinasHistory
                = new ArrayList<>();

        for (NotaDinas notaDinas
                : notaDinasList) {
            notaDinasHistory
                    .add(new SuratPerintahHistoryWrapper(
                            notaDinas.getKdNotaDinas(),
                            "",
                            false,
                            -1,
                            "nota dinas",
                            3,
                            notaDinas.getTanggalPembuatanMilis(),
                            notaDinas.getStatusPenilaian()));

        }

        return new ResponseEntity<Object>(notaDinasHistory, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get nota dinas by target");

        List<SuratPerintahTargetWrapper> notaDinasTargetWrappers
                = new ArrayList<>();
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<TembusanNotaDinas> tembusanNotaDinasList
                = notaDinasService.getTembusanNotaDinasByJabatan(pegawaiTarget.getKdJabatan());

        for (TembusanNotaDinas suratTarget
                : tembusanNotaDinasList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getNotaDinas().getNipPenandatangan())) {
                    notaDinasTargetWrappers
                            .add(new SuratPerintahTargetWrapper(suratTarget.getNotaDinas().getKdNotaDinas(),
                                    "",
                                    false,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan()));
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(notaDinasTargetWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get nota dinas by target unread");

        List<SuratPerintahTargetWrapper> notaDinasTargetWrappers
                = new ArrayList<>();
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<TembusanNotaDinas> tembusanNotaDinasList
                = notaDinasService.getTembusanNotaDinasByJabatan(pegawaiTarget.getKdJabatan());

        for (TembusanNotaDinas suratTarget
                : tembusanNotaDinasList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratTarget.getNotaDinas().getNipPenandatangan())) {
                    if (suratTarget.getStatusBaca() == 0) {
                        notaDinasTargetWrappers
                                .add(new SuratPerintahTargetWrapper(suratTarget.getNotaDinas().getKdNotaDinas(),
                                        "",
                                        false,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan()));
                    }

                    break;
                }
            }
        }

        return new ResponseEntity<Object>(notaDinasTargetWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-kd-nota-dinas/{kdNotaDinas}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByKdNotaDinas(@PathVariable("kdNotaDinas") String kdNotaDinas) {
        LOGGER.info("get nota dinas by kode nota dinas");

        NotaDinas notaDinas = notaDinasService.findBykdNotaDinas(kdNotaDinas);


        List<JabatanWrapper> tembusanNotaDinasList
                = new ArrayList<>();
        CustomPegawaiCredential penandatangan = null,
                                pemberiNotaDinas = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(notaDinas.getNipPenandatangan())) {
                penandatangan = qutPegawai;
                break;
            }
        }

        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(notaDinas.getNipPemberiNotaDinas())) {
                pemberiNotaDinas = qutPegawai;
                break;
            }
        }

        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        TkdJabatan jabatanPenerimaNotaDinas = new TkdJabatan();
        for (TkdJabatan tkdJabatan : tkdJabatanList){
            if (tkdJabatan.getKdJabatan()
                    .equals(notaDinas.getKdJabatanPenerimaNotaDinas())) {
                jabatanPenerimaNotaDinas = tkdJabatan;

                break;

            }

        }

        for (TembusanNotaDinas target
                : notaDinas.getTembusanNotaDinasList()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(target.getTembusanNotaDinasId().getKdJabatan())) {
                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    tembusanNotaDinasList.add(jabatanWrapper);

                    break;

                }

            }

        }

        pemberiNotaDinas.setUnitKerja(tkdUnkDao.findOne(pemberiNotaDinas.getKdUnitKerja()).getUnitKerja());
        penandatangan.setUnitKerja(tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja());

        NotaDinasWrapper notaDinasWrapper
                = new NotaDinasWrapper(
                        notaDinas.getKdNotaDinas(),
                        notaDinas.getNomorUrusan(),
                        notaDinas.getNomorUrut(),
                        notaDinas.getNomorPasanganUrut(),
                        notaDinas.getNomorUnit(),
                        notaDinas.getNomorTahun(),
                        jabatanPenerimaNotaDinas.getKdJabatan(),
                        jabatanPenerimaNotaDinas.getJabatan(),
                        pemberiNotaDinas,
                        notaDinas.getHal(),
                        notaDinas.getTanggalPembuatanMilis(),
                        notaDinas.getIsiNotaDinas(),
                        penandatangan,
                        tembusanNotaDinasList);

        return new ResponseEntity<Object>(notaDinasWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-nota-dinas/{kdNotaDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openNotaDinas(@PathVariable("kdNotaDinas") String kdNotaDinas) {
        LOGGER.info("open nota dinas");

        return new ResponseEntity<Object>(
                new CustomMessage("nota dinas opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-nota-dinas-by-penilai/{kdNotaDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openNotaDinasByPenilai(@PathVariable("kdNotaDinas") String kdNotaDinas) {
        LOGGER.info("open nota dinas by penilai");

        return new ResponseEntity<Object>(
                new CustomMessage("nota dinas opened"), HttpStatus.OK);
    }

}
