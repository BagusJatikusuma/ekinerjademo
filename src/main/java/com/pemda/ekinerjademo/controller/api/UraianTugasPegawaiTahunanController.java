package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunanId;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiTahunanService;
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
    @Autowired private QutPegawaiService qutPegawaiService;

    @RequestMapping(value = "/get-uraian-tugas-pegawai-tahunan-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasPegawaiTahunanByNip(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get uraian tugas pegawai tahunan by nip");

        List<UraianTugasPegawaiTahunan> urtugPegawaiTahunanList
                = urtugPegawaiTahunanService.getByNipPegawai(nipPegawai);
        List<UraianTugasPegawaiTahunanWrapper> urtugPegawaiTahunanWrapperList
                = new ArrayList<>();

        for (UraianTugasPegawaiTahunan urtugPegawaiTahunan : urtugPegawaiTahunanList) {
            urtugPegawaiTahunanWrapperList
                    .add(new UraianTugasPegawaiTahunanWrapper(
                            urtugPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai()
                    ));
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

    //inset urtug tahunan pegawai dan change status approval urtug pegawai non-DPA
    @RequestMapping(value = "/approval-urtug-tahunan-non-dpa-pegawai", method = RequestMethod.PUT)
    ResponseEntity<?> approvalUrtugTahunanNonDpaPegawai(
            @RequestBody List<UraianTugasPegawaiTahunanInputWrapper> uraianTugasPegawaiTahunanInputWrapperList ){
        LOGGER.info("approval uraian tugas tahunan non DPA");

        List<UraianTugasPegawaiTahunan> uraianTugasPegawaiTahunanList
                = urtugPegawaiTahunanService.getByNipPegawai(
                        uraianTugasPegawaiTahunanInputWrapperList
                                .get(0)
                                .getNipPegawai());

        for (UraianTugasPegawaiTahunanInputWrapper urtugPegawaiAtasan : uraianTugasPegawaiTahunanInputWrapperList){
            boolean found = false;
            for (UraianTugasPegawaiTahunan urtugPegawai : uraianTugasPegawaiTahunanList){
                if (urtugPegawaiAtasan.getKdUrtug().equals(urtugPegawai.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
                    urtugPegawaiTahunanService.approveUrtug(new UraianTugasPegawaiTahunanId(
                            urtugPegawaiAtasan.getKdUrtug(),
                            urtugPegawaiAtasan.getKdJabatan(),
                            urtugPegawaiAtasan.getKdJenisUrtug(),
                            urtugPegawaiAtasan.getTahunUrtug(),
                            urtugPegawaiAtasan.getNipPegawai()));

                    found = true;
                    break;
                }
            }

            if (!found) {
                urtugPegawaiTahunanService.createUraianTugasPegawaiTahunan(
                        urtugPegawaiAtasan,
                        true);
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

                    ajuanUraianTugasNonDpaPegawaiWrapperList.add(ajuanUraianTugasNonDpaPegawaiWrapper);
                }
            }
        }

        //insert urtug ke setiap elemen tugas
        for (AjuanUraianTugasNonDpaPegawaiWrapper ajuanUraianTugasNonDpaPegawaiWrapper : ajuanUraianTugasNonDpaPegawaiWrapperList){
            List<UraianTugasWrapper> uraianTugasWrapperList = new ArrayList<>();

            for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : uraianTugasPegawaiTahunanList){
                if (ajuanUraianTugasNonDpaPegawaiWrapper.getNipPegawai().equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai())) {
                    uraianTugasWrapperList.add(
                            new UraianTugasWrapper(
                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                                    uraianTugasPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi()));
                }
            }

            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasDiajukan(uraianTugasWrapperList);
        }

        return new ResponseEntity<Object>(ajuanUraianTugasNonDpaPegawaiWrapperList, HttpStatus.OK);

    }
}
