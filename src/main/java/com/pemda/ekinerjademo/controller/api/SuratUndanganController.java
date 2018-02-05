package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratUndanganService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.SuratUndanganInputWrapper;
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
public class SuratUndanganController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratUndanganController.class);

    @Autowired private SuratUndanganService suratUndanganService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private TkdJabatanService tkdJabatanService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-surat-undangan", method = RequestMethod.POST)
    ResponseEntity<?> createSuratUndangan(
            @RequestBody SuratUndanganInputWrapper inputWrapper) {
        LOGGER.info("create surat undangan");

        String kdSuratUndangan = String.valueOf(new Date().getTime());

        SuratUndangan suratUndangan = new SuratUndangan();

        List<TembusanSuratUndangan> tembusanSuratUndanganList
                = new ArrayList<>();
        //build tembusan list
        for (String jabatanTembusan : inputWrapper.getKdTembusanList()) {
            TembusanSuratUndanganId id
                    = new TembusanSuratUndanganId(kdSuratUndangan, jabatanTembusan);

            TembusanSuratUndangan tembusanSuratUndangan = new TembusanSuratUndangan();
            tembusanSuratUndangan.setTembusanSuratUndanganId(id);
            tembusanSuratUndangan.setStatusBaca(0);
            tembusanSuratUndangan.setStatusDiterima(0);

            tembusanSuratUndanganList
                    .add(tembusanSuratUndangan);
        }
        //build surat undangan
        suratUndangan.setKdSuratUndangan(kdSuratUndangan);
        suratUndangan.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratUndangan.setNomorUrut(0);
        suratUndangan.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratUndangan.setNomorUnit(inputWrapper.getNomorUnit());
        suratUndangan.setNomorTahun(Year.now().getValue());

        suratUndangan.setKdJabatanPenerimaSuratUndangan(inputWrapper.getKdJabatanPenerimaSuratUndangan());
        suratUndangan.setTanggalPembuatanSurat(new Date().getTime());
        suratUndangan.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());

        suratUndangan.setSifat(inputWrapper.getSifat());
        suratUndangan.setLampiran(inputWrapper.getLampiran());
        suratUndangan.setHal(inputWrapper.getHal());
        suratUndangan.setNipPenerimaSuratUndangan(inputWrapper.getNipPenerimaSuratUndangan());

        suratUndangan.setBagianPembukaSuratUndangan(inputWrapper.getBagianPembukaSuratUndangan());
        suratUndangan.setBagianIsiHariSuratUndangan(inputWrapper.getBagianIsiHariSuratUndangan());
        suratUndangan.setBagianIsiTanggalSuratUndangan(inputWrapper.getBagianIsiTanggalSuratUndangan());
        suratUndangan.setBagianIsiTempatSuratUndangan(inputWrapper.getBagianIsiTempatSuratUndangan());
        suratUndangan.setBagianIsiWaktuSuratUndangan(inputWrapper.getBagianIsiWaktuSuratUndangan());
        suratUndangan.setBagianIsiAcaraSuratUndangan(inputWrapper.getBagianIsiAcaraSuratUndangan());
        suratUndangan.setBagianPenutupSuratUndangan(inputWrapper.getBagianPenutupSuratUndangan());
        suratUndangan.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratUndangan.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());

        suratUndangan.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratUndangan.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        suratUndangan.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
        suratUndangan.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        if (inputWrapper.getKdSuratUndanganBawahan() == null) {
            suratUndangan.setPathPenilaian(kdSuratUndangan);
        } else {
            SuratUndangan suratUndanganBawahan
                    = suratUndanganService.getByKdSuratUndangan(inputWrapper.getKdSuratUndanganBawahan());
            suratUndangan.setPathPenilaian(suratUndanganBawahan.getPathPenilaian()+"."+kdSuratUndangan);

            suratUndanganBawahan.setStatusPenilaian(2);
            suratUndanganService.create(suratUndanganBawahan);
        }

        suratUndangan.setNipPenilai("");
        suratUndangan.setStatusPenilaian(0);
        suratUndangan.setAlasanPenolakan("");
        suratUndangan.setStatusBaca(0);

        //save surat undangan
        suratUndanganService.create(suratUndangan);
        //save tembusan surat undangan
        for (TembusanSuratUndangan tembusanSuratUndangan : tembusanSuratUndanganList) {
            suratUndanganService.tembusanSuratUndangan(tembusanSuratUndangan);
        }
        // save surat undangan pejabat atau non pejabat
        if (inputWrapper.isSuratPejabat()) {
            SuratUndanganPejabat suratUndanganPejabat
                    = new SuratUndanganPejabat();
            suratUndanganPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            suratUndanganPejabat.setKdSuratUndangan(kdSuratUndangan);

            suratUndanganService.createSuratUndanganPejabat(suratUndanganPejabat);
        } else {
            SuratUndanganNonPejabat suratUndanganNonPejabat
                    = new SuratUndanganNonPejabat();
            suratUndanganNonPejabat.setKdSuratUndangan(kdSuratUndangan);
            suratUndanganNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());

            suratUndanganService.createSuratUndanganNonPejabat(suratUndanganNonPejabat);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat undangan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-surat-undangan-history/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratUndanganHistory(
            @PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get daftar surat undanga history");

        List<SuratUndangan> suratUndanganList
                = suratUndanganService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> suratUndanganHistoryWrappers
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        for (SuratUndangan suratUndangan : suratUndanganList) {
            if (suratUndangan.getSuratUndanganPejabat() != null) {
                isSuratPejabat = true;
            } else {
                isSuratPejabat = false;
            }

            suratUndanganHistoryWrappers
                    .add(new SuratPerintahHistoryWrapper(
                            suratUndangan.getKdSuratUndangan(),
                            df.format(new Date(suratUndangan.getTanggalPembuatanSurat())),
                            isSuratPejabat,
                            suratUndangan.getStatusBaca(),
                            "suratUndangan",
                            13,
                            suratUndangan.getTanggalPembuatanSurat(),
                            suratUndangan.getStatusPenilaian()));

        }

        return new ResponseEntity<Object>(suratUndanganHistoryWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-surat-undangan-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratUndanganTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat undangan target");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<SuratUndangan> suratUndanganList
                = suratUndanganService.getByNipPenerima(pegawaiTarget.getNip());
        List<TembusanSuratUndangan> tembusanSuratUndanganList
                = suratUndanganService.getTembusanSuratUndangan(pegawaiTarget.getKdJabatan());

        List<SuratPerintahTargetWrapper> targetSuratUndanganList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        for (SuratUndangan suratUndangan : suratUndanganList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratUndangan.getNipPenandatangan())) {

                    if (suratUndangan.getSuratUndanganPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    targetSuratUndanganList
                            .add(new SuratPerintahTargetWrapper(
                                    suratUndangan.getKdSuratUndangan(),
                                    df.format(new Date(suratUndangan.getTanggalPembuatanSurat())),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan()));
                }

            }

        }

        for (TembusanSuratUndangan tembusanSuratUndangan : tembusanSuratUndanganList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(tembusanSuratUndangan.getSuratUndangan().getNipPenandatangan())) {
                    if (tembusanSuratUndangan.getSuratUndangan().getSuratUndanganPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    targetSuratUndanganList
                            .add(new SuratPerintahTargetWrapper(
                                    tembusanSuratUndangan.getSuratUndangan().getKdSuratUndangan(),
                                    df.format(new Date(tembusanSuratUndangan.getSuratUndangan().getTanggalPembuatanSurat())),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan()));

                }

            }
        }

        return new ResponseEntity<Object>(targetSuratUndanganList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-undangan-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratUndanganTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat undangan target unread");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<SuratUndangan> suratUndanganList
                = suratUndanganService.getByNipPenerima(pegawaiTarget.getNip());
        List<TembusanSuratUndangan> tembusanSuratUndanganList
                = suratUndanganService.getTembusanSuratUndangan(pegawaiTarget.getKdJabatan());

        List<SuratPerintahTargetWrapper> targetSuratUndanganList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        for (SuratUndangan suratUndangan : suratUndanganList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratUndangan.getNipPenandatangan())) {

                    if (suratUndangan.getSuratUndanganPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    if (suratUndangan.getStatusBaca() == 0) {
                        targetSuratUndanganList
                                .add(new SuratPerintahTargetWrapper(
                                        suratUndangan.getKdSuratUndangan(),
                                        df.format(new Date(suratUndangan.getTanggalPembuatanSurat())),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan()));
                    }
                }

            }

        }

        for (TembusanSuratUndangan tembusanSuratUndangan : tembusanSuratUndanganList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(tembusanSuratUndangan.getSuratUndangan().getNipPenandatangan())) {
                    if (tembusanSuratUndangan.getSuratUndangan().getSuratUndanganPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    if (tembusanSuratUndangan.getStatusBaca() == 0) {
                        targetSuratUndanganList
                                .add(new SuratPerintahTargetWrapper(
                                        tembusanSuratUndangan.getSuratUndangan().getKdSuratUndangan(),
                                        df.format(new Date(tembusanSuratUndangan.getSuratUndangan().getTanggalPembuatanSurat())),
                                        isSuratPejabat,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan()));
                    }

                }

            }
        }

        return new ResponseEntity<Object>(targetSuratUndanganList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-undangan-by-kd-surat/{kdSuratUndangan}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratUndanganByKdSuratUndangan(@PathVariable("kdSuratUndangan") String kdSuratUndangan) {
        LOGGER.info("get surat undangan kd pengumuman");

        SuratUndangan suratUndangan
                = suratUndanganService.getByKdSuratUndangan(kdSuratUndangan);

        List<JabatanWrapper> tembusanSuratUndanganList
                = new ArrayList<>();
        CustomPegawaiCredential
                penerima = null,
                penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        //get penerima
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(suratUndangan.getNipPenerimaSuratUndangan())) {
                penerima = qutPegawai;
                break;
            }
        }
        //get penandatangan
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(suratUndangan.getNipPenandatangan())) {
                penandatangan = qutPegawai;
                break;
            }
        }

        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();
        for (TembusanSuratUndangan target
                : suratUndangan.getTembusanSuratUndanganList()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(target.getTembusanSuratUndanganId().getKdJabatan())) {
                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    tembusanSuratUndanganList.add(jabatanWrapper);

                    break;

                }

            }

        }

        boolean isSuratPejabat = false;

        if (suratUndangan.getSuratUndanganPejabat() != null)
            isSuratPejabat = true;

        SuratUndanganWrapper suratUndanganWrapper = new SuratUndanganWrapper();

        suratUndanganWrapper.setKdSuratUndangan(suratUndangan.getKdSuratUndangan());

        suratUndanganWrapper.setNomorUrusan(suratUndangan.getNomorUrusan());
        suratUndanganWrapper.setNomorUrut(suratUndangan.getNomorUrut());
        suratUndanganWrapper.setNomorPasanganUrut(suratUndangan.getNomorPasanganUrut());
        suratUndanganWrapper.setNomorUnit(suratUndangan.getNomorUnit());
        suratUndanganWrapper.setNomorTahun(suratUndangan.getNomorTahun());

        suratUndanganWrapper.setKdJabatanPenerimaSuratUndangan(penerima.getKdJabatan());
        suratUndanganWrapper.setJabatanPenerimaSuratUndangan(penerima.getJabatan());
        suratUndanganWrapper.setTanggalPembuatanSurat(suratUndangan.getTanggalPembuatanSurat());
        suratUndanganWrapper.setKotaPembuatanSurat(suratUndangan.getKotaPembuatanSurat());
        suratUndanganWrapper.setSifat(suratUndangan.getSifat());
        suratUndanganWrapper.setHal(suratUndangan.getHal());
        suratUndanganWrapper.setLampiran(suratUndangan.getLampiran());

        suratUndanganWrapper.setNipPenerimaSuratUndangan(penerima.getNip());
        suratUndanganWrapper.setNamaPenerimaSuratUndangan(penerima.getNama());
        suratUndanganWrapper
                .setUnitKerjaPenerimaSuratUndangan(tkdUnkDao.findOne(penerima.getKdUnitKerja()).getUnitKerja());
        suratUndanganWrapper.setGelarDepanPenerimaSuratUndangan(penerima.getGlrDpn());
        suratUndanganWrapper.setGelarBelakangPenerimaSuratUndangan(penerima.getGlrBlk());
        suratUndanganWrapper.setPangkatPenerimaSuratUndangan(penerima.getPangkat());
        suratUndanganWrapper.setGolonganPenerimaSuratUndangan(penerima.getGol());

        suratUndanganWrapper.setBagianPembukaSuratUndangan(suratUndangan.getBagianPembukaSuratUndangan());
        suratUndanganWrapper.setBagianIsiHariSuratUndangan(suratUndangan.getBagianIsiHariSuratUndangan());
        suratUndanganWrapper.setBagianIsiTanggalSuratUndangan(suratUndangan.getBagianIsiTanggalSuratUndangan());
        suratUndanganWrapper.setBagianIsiWaktuSuratUndangan(suratUndangan.getBagianIsiWaktuSuratUndangan());
        suratUndanganWrapper.setBagianIsiTempatSuratUndangan(suratUndangan.getBagianIsiTempatSuratUndangan());
        suratUndanganWrapper.setBagianIsiAcaraSuratUndangan(suratUndangan.getBagianIsiAcaraSuratUndangan());
        suratUndanganWrapper.setBagianPenutupSuratUndangan(suratUndangan.getBagianPenutupSuratUndangan());

        suratUndanganWrapper.setNipPenandatangan(penandatangan.getNip());
        suratUndanganWrapper.setNamaPenandatangan(penandatangan.getNama());
        suratUndanganWrapper.setUnitKerjaPenandatangan(tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja());
        suratUndanganWrapper.setJabatanPenandatangan(penandatangan.getJabatan());
        suratUndanganWrapper.setGelarDepanPenandatangan(penandatangan.getGlrDpn());
        suratUndanganWrapper.setGelarBelakangPenandatangan(penandatangan.getGlrBlk());
        suratUndanganWrapper.setPangkatPenandatangan(penandatangan.getPangkat());
        suratUndanganWrapper.setGolonganPenandatangan(penandatangan.getGol());

        suratUndanganWrapper.setTembusanSuratUndanganList(tembusanSuratUndanganList);
        suratUndanganWrapper.setSuratPejabat(isSuratPejabat);

        return new ResponseEntity<Object>(suratUndanganWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-undangan/{kdSuratUndangan}/{nipTarget}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratUndangan(
            @PathVariable("kdSuratUndangan") String kdSuratUndangan,
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("open surat undangan");

        suratUndanganService.openSuratUndangan(kdSuratUndangan);

        return new ResponseEntity<Object>(new CustomMessage("surat undangan opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-undangan-penilai/{kdSuratUndangan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratUndanganPenilai(@PathVariable("kdSuratUndangan") String kdSuratUndangan) {
        LOGGER.info("open surat undangan penilai");

        suratUndanganService.openSuratUndanganPenilai(kdSuratUndangan);

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

}
