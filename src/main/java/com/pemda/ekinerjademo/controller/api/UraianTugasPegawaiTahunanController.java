package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiTahunanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bagus on 14/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasPegawaiTahunanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasPegawaiTahunanController.class);

    @Autowired private UraianTugasPegawaiTahunanService urtugPegawaiTahunanService;
    @Autowired private QutPegawaiCloneService qutPegawaiService;
    @Autowired private UraianTugasJabatanJenisUrtugService uraianTugasJabatanJenisUrtugService;
    @Autowired private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;
    @Autowired private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/get-uraian-tugas-pegawai-tahunan-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasPegawaiTahunanByNip(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get uraian tugas pegawai tahunan by nip");

        List<UraianTugasPegawaiTahunan> urtugPegawaiTahunanList
                = urtugPegawaiTahunanService.getByNipPegawai(nipPegawai);
        List<UraianTugasPegawaiTahunanWrapper> urtugPegawaiTahunanWrapperList
                = new ArrayList<>();

        for (UraianTugasPegawaiTahunan urtugPegawaiTahunan : urtugPegawaiTahunanList) {
            if (urtugPegawaiTahunan.getStatusApproval() == 1) {
                urtugPegawaiTahunanWrapperList
                        .add(new UraianTugasPegawaiTahunanWrapper(
                                urtugPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                                urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
                                urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
                                urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
                                urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai(),
                                urtugPegawaiTahunan.getKuantitas(),
                                urtugPegawaiTahunan.getSatuanKuantitas(),
                                urtugPegawaiTahunan.getKualitas(),
                                urtugPegawaiTahunan.getWaktu(),
                                urtugPegawaiTahunan.getBiaya(),
                                urtugPegawaiTahunan.getAlasan(),
                                urtugPegawaiTahunan.getStatusApproval()
                        ));
            }
        }

        return new ResponseEntity<Object>(urtugPegawaiTahunanWrapperList, HttpStatus.OK);

    }

    @RequestMapping(value = "/create-daftar-uraian-tugas-pegawai-tahunan", method = RequestMethod.POST)
    ResponseEntity<?> createUraianTugasPegawaiTahunanList(
            @RequestBody List<UraianTugasPegawaiTahunanInputWrapper> urtugPegawaiList) {
        LOGGER.info("create daftar uraian tugas pegawai tahunan");

        urtugPegawaiTahunanService.createUraianTugasPegawaiTahunanList(urtugPegawaiList);

        return new ResponseEntity<Object>(
                new CustomMessage("uraian tugas pegawai tahunan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-uraian-tugas-pegawai-tahunan", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUraianTugasPegawaiTahunan(@RequestBody UraianTugasPegawaiTahunanInputWrapper uraianTugasPegawaiTahunanInputWrapper) {
        LOGGER.info("delete uraian tugas pegawai tahunan");

        urtugPegawaiTahunanService
                .deleteUraianTugasPegawaiTahunan(
                        new UraianTugasPegawaiTahunanId(
                                uraianTugasPegawaiTahunanInputWrapper.getKdUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getKdJabatan(),
                                uraianTugasPegawaiTahunanInputWrapper.getKdJenisUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getTahunUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getNipPegawai()
                        ));

        return new ResponseEntity<Object>(
                new CustomMessage("uraian tugas pegawai tahunan created"), HttpStatus.OK);
    }

    //insert urtug tahunan pegawai dan change status approval urtug pegawai non-DPA
    @RequestMapping(value = "/approval-urtug-tahunan-non-dpa-pegawai", method = RequestMethod.PUT)
    ResponseEntity<?> approvalUrtugTahunanNonDpaPegawai(
            @RequestBody List<UraianTugasPegawaiTahunanInputWrapper> uraianTugasPegawaiTahunanInputWrapperList ){
        LOGGER.info("approval uraian tugas tahunan non DPA");

        if (uraianTugasPegawaiTahunanInputWrapperList.isEmpty())
            return new ResponseEntity<Object>(new CustomMessage("Uraian tugas yang dipilih telah disetujui"), HttpStatus.OK);

        //get current uraian tugas non dpa pegawai in database
        List<UraianTugasPegawaiTahunan> uraianTugasPegawaiTahunanList
                = urtugPegawaiTahunanService.getByNipPegawai(
                        uraianTugasPegawaiTahunanInputWrapperList
                                .get(0)
                                .getNipPegawai());

        for (UraianTugasPegawaiTahunanInputWrapper urtugPegawaiAtasan : uraianTugasPegawaiTahunanInputWrapperList){
            boolean found = false;
            for (UraianTugasPegawaiTahunan urtugPegawai : uraianTugasPegawaiTahunanList){
                if (urtugPegawaiAtasan.getKdUrtug()
                        .equals(urtugPegawai.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
                    urtugPegawaiTahunanService.approveUrtug(urtugPegawaiAtasan);

                    found = true;
                    break;
                }
            }

            if (!found) {
                if (urtugPegawaiAtasan.getStatusApproval() == 1) {
                    urtugPegawaiTahunanService.createUraianTugasPegawaiTahunan(
                            urtugPegawaiAtasan,
                            1);
                }

            }

        }
        return new ResponseEntity<Object>(new CustomMessage("Uraian tugas yang dipilih telah disetujui"), HttpStatus.OK);
    }

    //Pengambilan ajuan data uraian tugas dari semua pegawai berdasarkan Unit Kerja
    @RequestMapping(value = "/get-uraian-tugas-tahunan-by-unit-kerja/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasTahunanByUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get uraian tugas tahunan by unit kerja");

        List<UraianTugasPegawaiTahunan> uraianTugasPegawaiTahunanList = urtugPegawaiTahunanService.getByUnitKerja(kdUnitKerja);
        List<PegawaiCredential> pegawaiCredentialList = new ArrayList<>();
        List<AjuanUraianTugasNonDpaPegawaiWrapper> ajuanUraianTugasNonDpaPegawaiWrapperList = new ArrayList<>();
        List<QutPegawai> qutPegawaiList = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList
                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByUnitKerja(kdUnitKerja);

        //looping pegawai yang ada pada uraian tugas tahunan
        for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList){
            Boolean constraint = false;

            for (PegawaiCredential pegawaiCredential : pegawaiCredentialList) {
                if (pegawaiCredential.getNipPegawai()
                        .equals(uraianTugasPegawaiTahunan
                                .getUraianTugasPegawaiTahunanId()
                                .getNipPegawai())){
                    constraint = true;
                    break;
                }
            }

            if (!constraint){
                pegawaiCredentialList.add(new PegawaiCredential(
                        uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai()
                        ,null,null,null,null,null,null));
            }
        }

        //insert data pegawai credential kedalam ajuan uraian tugas non dpa list
        for (PegawaiCredential pegawaiCredential : pegawaiCredentialList){
            for (QutPegawai qutPegawai : qutPegawaiList){
                if (qutPegawai.getNip().equals(pegawaiCredential.getNipPegawai())){
                    AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper = new AjuanUraianTugasNonDpaPegawaiWrapper();
                    ajuanUraianTugasNonDpaPegawaiWrapper.setNipPegawai(qutPegawai.getNip());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setNamaPegawai(qutPegawai.getNama());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setKdJabatan(qutPegawai.getKdJabatan());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setJabatan(qutPegawai.getJabatan());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setEselon(qutPegawai.getEselon());

                    ajuanUraianTugasNonDpaPegawaiWrapperList.add(ajuanUraianTugasNonDpaPegawaiWrapper);
                }
            }
        }

        //insert urtug ke setiap elemen tugas
        for (AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper : ajuanUraianTugasNonDpaPegawaiWrapperList){
            List<UraianTugasPegawaiTahunanWrapper> uraianTugasWrapperList = new ArrayList<>();
            List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilihWrapperList = new ArrayList<>();

            for (UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug : uraianTugasJabatanJenisUrtugList) {
                if (ajuanUraianTugasNonDpaPegawaiWrapper.getKdJabatan()
                        .equals(uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan())) {
                    LOGGER.info("urtug jenis :"+uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug());

                    boolean found = false;
                    for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList) {
                        if (ajuanUraianTugasNonDpaPegawaiWrapper.getNipPegawai()
                                .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai())) {
                            LOGGER.info(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug());

                            if (uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
                                    .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
//                                found = true;// revisi
                                if (uraianTugasPegawaiTahunan.getStatusApproval() == 0) {
                                    found = true;
                                    uraianTugasWrapperList.add(
                                            new UraianTugasPegawaiTahunanWrapper(
                                                    uraianTugasPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai(),
                                                    uraianTugasPegawaiTahunan.getKuantitas(),
                                                    uraianTugasPegawaiTahunan.getSatuanKuantitas(),
                                                    uraianTugasPegawaiTahunan.getKualitas(),
                                                    uraianTugasPegawaiTahunan.getWaktu(),
                                                    uraianTugasPegawaiTahunan.getBiaya(),
                                                    uraianTugasPegawaiTahunan.getAlasan(),
                                                    uraianTugasPegawaiTahunan.getStatusApproval()
                                            ));
                                }
                                break;
                            }

                        }
                    }

                    if (!found) {
                        uraianTugasTidakDipilihWrapperList.add(
                                new UraianTugasPegawaiTahunanWrapper(
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                                        "",
                                        uraianTugasJabatanJenisUrtug.getKuantitas(),
                                        uraianTugasJabatanJenisUrtug.getSatuanKuantitas(),
                                        uraianTugasJabatanJenisUrtug.getKualitas(),
                                        uraianTugasJabatanJenisUrtug.getWaktu(),
                                        uraianTugasJabatanJenisUrtug.getBiaya(),
                                        "",
                                        0
                                ));
                    }

                }
            }

            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasDiajukan(uraianTugasWrapperList);
            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasTidakDipilih(uraianTugasTidakDipilihWrapperList);
        }

        return new ResponseEntity<Object>(ajuanUraianTugasNonDpaPegawaiWrapperList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-uraian-tugas-tahunan-by-penilai/{kdUnitKerja}/{nipPenilai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasTahunanByPenilai(
            @PathVariable("kdUnitKerja") String kdUnitKerja,
            @PathVariable("nipPenilai") String nipPenilai) {
        LOGGER.info("get uraian tugas tahunan by unit kerja");

        List<UraianTugasPegawaiTahunan> uraianTugasPegawaiTahunanList
                = urtugPegawaiTahunanService.getByUnitKerja(kdUnitKerja);
        List<PegawaiCredential> pegawaiCredentialList
                = new ArrayList<>();
        List<AjuanUraianTugasNonDpaPegawaiWrapper> ajuanUraianTugasNonDpaPegawaiWrapperList
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<PejabatPenilaiDinilai> nipPegawaiBawahanPenilaiList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);
        List<QutPegawai> pegawaiBawahanPenilaiList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList
                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByUnitKerja(kdUnitKerja);
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();


        //filter pegawai bawahan penilai
        for (PejabatPenilaiDinilai pejabatPenilaiDinilai :
                nipPegawaiBawahanPenilaiList) {
            LOGGER.info(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());

            for (QutPegawai qutPegawai
                    : qutPegawaiList) {
                if (pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai()
                        .equals(qutPegawai.getKdJabatan())) {
                    pegawaiBawahanPenilaiList
                            .add(qutPegawai);
                }
            }
        }

        //looping pegawai yang ada pada uraian tugas tahunan
        for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList){
            Boolean constraint = false;

            for (PegawaiCredential pegawaiCredential : pegawaiCredentialList) {
                if (pegawaiCredential.getNipPegawai()
                        .equals(uraianTugasPegawaiTahunan
                                .getUraianTugasPegawaiTahunanId()
                                .getNipPegawai())){
                    constraint = true;
                    break;
                }
            }

            if (!constraint){
                pegawaiCredentialList.add(new PegawaiCredential(
                        uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai()
                        ,null,null,null,null,null,null));
            }
        }

        //insert data pegawai credential kedalam ajuan uraian tugas non dpa list
        for (PegawaiCredential pegawaiCredential : pegawaiCredentialList){
            for (QutPegawai qutPegawai : pegawaiBawahanPenilaiList){
                if (qutPegawai.getNip().equals(pegawaiCredential.getNipPegawai())){
                    AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper = new AjuanUraianTugasNonDpaPegawaiWrapper();
                    ajuanUraianTugasNonDpaPegawaiWrapper.setNipPegawai(qutPegawai.getNip());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setNamaPegawai(qutPegawai.getNama());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setKdJabatan(qutPegawai.getKdJabatan());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setJabatan(qutPegawai.getJabatan());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setEselon(qutPegawai.getEselon());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setKdUnitKerja(qutPegawai.getKdUnitKerja());

                    ajuanUraianTugasNonDpaPegawaiWrapperList.add(ajuanUraianTugasNonDpaPegawaiWrapper);
                }
            }
        }

        for (AjuanUraianTugasNonDpaPegawaiWrapper qutPegawaiWrapper
                : ajuanUraianTugasNonDpaPegawaiWrapperList) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        //insert urtug ke setiap elemen tugas
        boolean penanganan;
        for (AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper : ajuanUraianTugasNonDpaPegawaiWrapperList){
            penanganan = false;

            List<UraianTugasPegawaiTahunanWrapper> uraianTugasWrapperList = new ArrayList<>();
            List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilihWrapperList = new ArrayList<>();

            for (UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug : uraianTugasJabatanJenisUrtugList) {
                if (ajuanUraianTugasNonDpaPegawaiWrapper.getKdJabatan()
                        .equals(uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan())) {
                    LOGGER.info("urtug jenis :"+uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug());

                    boolean found = false;
                    for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList) {
                        if (ajuanUraianTugasNonDpaPegawaiWrapper.getNipPegawai()
                                .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai())) {
                            LOGGER.info(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug());

                            if (uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
                                    .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
//                                found = true; //revisi
                                if (uraianTugasPegawaiTahunan.getStatusApproval() == 0) {
                                    found = true;
                                    penanganan = false;

                                    uraianTugasWrapperList.add(
                                            new UraianTugasPegawaiTahunanWrapper(
                                                    uraianTugasPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai(),
                                                    uraianTugasPegawaiTahunan.getKuantitas(),
                                                    uraianTugasPegawaiTahunan.getSatuanKuantitas(),
                                                    uraianTugasPegawaiTahunan.getKualitas(),
                                                    uraianTugasPegawaiTahunan.getWaktu(),
                                                    uraianTugasPegawaiTahunan.getBiaya(),
                                                    uraianTugasPegawaiTahunan.getAlasan(),
                                                    uraianTugasPegawaiTahunan.getStatusApproval()
                                            ));
                                } //baris else merupakan revisi
                                else if (uraianTugasPegawaiTahunan.getStatusApproval() == 2
                                        || uraianTugasPegawaiTahunan.getStatusApproval() == 1) {
                                    found = true;
                                }

                                break;
                            }

                        }
                    }

                    if (!found) {
                        uraianTugasTidakDipilihWrapperList.add(
                                new UraianTugasPegawaiTahunanWrapper(
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                                        "",
                                        uraianTugasJabatanJenisUrtug.getKuantitas(),
                                        uraianTugasJabatanJenisUrtug.getSatuanKuantitas(),
                                        uraianTugasJabatanJenisUrtug.getKualitas(),
                                        uraianTugasJabatanJenisUrtug.getWaktu(),
                                        uraianTugasJabatanJenisUrtug.getBiaya(),
                                        "",
                                        0
                                ));
                    }

                }
            }

            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasDiajukan(uraianTugasWrapperList);
            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasTidakDipilih(uraianTugasTidakDipilihWrapperList);
            if (!uraianTugasWrapperList.isEmpty()) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(false);
            } else {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(true);
            }

        }

        return new ResponseEntity<Object>(ajuanUraianTugasNonDpaPegawaiWrapperList, HttpStatus.OK);

    }

    /**
     *
     * service yang digunakan untuk mengambil kontrak kerja yang diajukan oleh bawahan
     *
     * digunakan oleh pegawai penilai untuk melihat kontrak ajuan bawahan
     *
     * @param nipPenilai
     * @return
     */
    @RequestMapping(value = "/get-kontrak-ajuan-bawahan-notif/{kdUnitKerja}/{nipPenilai}", method = RequestMethod.GET)
    ResponseEntity<?> getKontrakAjuanBawahanNotif(
            @PathVariable("kdUnitKerja") String kdUnitKerja,
            @PathVariable("nipPenilai") String nipPenilai) {
        LOGGER.info("get kontrak ajuan bawahan");

        List<UraianTugasPegawaiTahunan> uraianTugasPegawaiTahunanList
                = urtugPegawaiTahunanService.getByUnitKerja(kdUnitKerja);
        List<PegawaiCredential> pegawaiCredentialList
                = new ArrayList<>();
        List<AjuanUraianTugasNonDpaPegawaiWrapper> ajuanUraianTugasNonDpaPegawaiWrapperList
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<PejabatPenilaiDinilai> nipPegawaiBawahanPenilaiList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);
        List<QutPegawai> pegawaiBawahanPenilaiList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList
                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByUnitKerja(kdUnitKerja);
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();


        //filter pegawai bawahan penilai
        for (PejabatPenilaiDinilai pejabatPenilaiDinilai :
                nipPegawaiBawahanPenilaiList) {
            LOGGER.info(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());

            for (QutPegawai qutPegawai
                    : qutPegawaiList) {
                if (pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai()
                        .equals(qutPegawai.getKdJabatan())) {
                    pegawaiBawahanPenilaiList
                            .add(qutPegawai);
                }
            }
        }

        //looping pegawai yang ada pada uraian tugas tahunan
        for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList){
            Boolean constraint = false;

            for (PegawaiCredential pegawaiCredential : pegawaiCredentialList) {
                if (pegawaiCredential.getNipPegawai()
                        .equals(uraianTugasPegawaiTahunan
                                .getUraianTugasPegawaiTahunanId()
                                .getNipPegawai())){
                    constraint = true;
                    break;
                }
            }

            if (!constraint){
                pegawaiCredentialList.add(new PegawaiCredential(
                        uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai()
                        ,null,null,null,null,null,null));
            }
        }

        //insert data pegawai credential kedalam ajuan uraian tugas non dpa list
        for (PegawaiCredential pegawaiCredential : pegawaiCredentialList){
            for (QutPegawai qutPegawai : pegawaiBawahanPenilaiList){
                if (qutPegawai.getNip().equals(pegawaiCredential.getNipPegawai())){
                    AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper = new AjuanUraianTugasNonDpaPegawaiWrapper();
                    ajuanUraianTugasNonDpaPegawaiWrapper.setNipPegawai(qutPegawai.getNip());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setNamaPegawai(qutPegawai.getNama());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setKdJabatan(qutPegawai.getKdJabatan());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setJabatan(qutPegawai.getJabatan());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setEselon(qutPegawai.getEselon());
                    ajuanUraianTugasNonDpaPegawaiWrapper.setKdUnitKerja(qutPegawai.getKdUnitKerja());

                    ajuanUraianTugasNonDpaPegawaiWrapperList.add(ajuanUraianTugasNonDpaPegawaiWrapper);
                }
            }
        }

        for (AjuanUraianTugasNonDpaPegawaiWrapper qutPegawaiWrapper
                : ajuanUraianTugasNonDpaPegawaiWrapperList) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        //insert urtug ke setiap elemen tugas
        boolean penanganan;
        for (AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper : ajuanUraianTugasNonDpaPegawaiWrapperList){
            penanganan = true;

            List<UraianTugasPegawaiTahunanWrapper> uraianTugasWrapperList = new ArrayList<>();
            List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilihWrapperList = new ArrayList<>();

            for (UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug : uraianTugasJabatanJenisUrtugList) {
                if (ajuanUraianTugasNonDpaPegawaiWrapper.getKdJabatan()
                        .equals(uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan())) {
                    LOGGER.info("urtug jenis :"+uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug());

                    boolean found = false;
                    for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList) {
                        if (ajuanUraianTugasNonDpaPegawaiWrapper.getNipPegawai()
                                .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai())) {
                            LOGGER.info(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug());

                            if (uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
                                    .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
//                                found = true; //revisi
                                if (uraianTugasPegawaiTahunan.getStatusApproval() == 0) {
                                    found = true;
                                    penanganan = false;

                                    uraianTugasWrapperList.add(
                                            new UraianTugasPegawaiTahunanWrapper(
                                                    uraianTugasPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
                                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai(),
                                                    uraianTugasPegawaiTahunan.getKuantitas(),
                                                    uraianTugasPegawaiTahunan.getSatuanKuantitas(),
                                                    uraianTugasPegawaiTahunan.getKualitas(),
                                                    uraianTugasPegawaiTahunan.getWaktu(),
                                                    uraianTugasPegawaiTahunan.getBiaya(),
                                                    uraianTugasPegawaiTahunan.getAlasan(),
                                                    uraianTugasPegawaiTahunan.getStatusApproval()
                                            ));
                                } //baris else merupakan revisi
                                else if (uraianTugasPegawaiTahunan.getStatusApproval() == 2
                                        || uraianTugasPegawaiTahunan.getStatusApproval() == 1) {
                                    found = true;
                                }

                                break;
                            }

                        }
                    }

                    if (!found) {
                        uraianTugasTidakDipilihWrapperList.add(
                                new UraianTugasPegawaiTahunanWrapper(
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                                        uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                                        "",
                                        uraianTugasJabatanJenisUrtug.getKuantitas(),
                                        uraianTugasJabatanJenisUrtug.getSatuanKuantitas(),
                                        uraianTugasJabatanJenisUrtug.getKualitas(),
                                        uraianTugasJabatanJenisUrtug.getWaktu(),
                                        uraianTugasJabatanJenisUrtug.getBiaya(),
                                        "",
                                        0
                                ));
                    }

                }
            }


            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasDiajukan(uraianTugasWrapperList);
            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasTidakDipilih(uraianTugasTidakDipilihWrapperList);
            if (!uraianTugasWrapperList.isEmpty()) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(false);
            } else {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(true);
            }
        }

        //hapus ajuan kontrak kerja pegawai yang sudah ditangani
        List<AjuanUraianTugasNonDpaPegawaiWrapper> toRemove = new ArrayList<>();
        for(AjuanUraianTugasNonDpaPegawaiWrapper obj: ajuanUraianTugasNonDpaPegawaiWrapperList){
            if(obj.isStatusPenangangan()){
                toRemove.add(obj);
            }
        }
        ajuanUraianTugasNonDpaPegawaiWrapperList.removeAll(toRemove);

        return new ResponseEntity<>(ajuanUraianTugasNonDpaPegawaiWrapperList, HttpStatus.OK);
    }

}
