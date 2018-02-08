package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.StatusPenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.simdarepository.TaProgramDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.wrapper.input.PenanggungJawabKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.PenanggungJawabKegiatanWrapper;
import com.pemda.ekinerjademo.wrapper.output.PenanggungJawabWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class PenanggungJawabKegiatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PenanggungJawabKegiatanController.class);

    @Autowired private PenanggungJawabKegiatanService penanggungJawabKegiatanService;
    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private StatusPenanggungJawabKegiatanService statusPenanggungJawabKegiatanService;
    @Autowired private UnitKerjaKegiatanService unitKerjaKegiatanService;
    @Autowired private TaKegiatanService taKegiatanService;
    @Autowired private TaProgramDao taProgramDao;

    @RequestMapping(value = "/create-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createPenanggungJawabKegiatan(@RequestBody PenanggungJawabKegiatanInputWrapper inputWrapper) {
        LOGGER.info("create penanggung jawab kegiatan");

        PenanggungJawabKegiatanId id
                = new PenanggungJawabKegiatanId(
                        inputWrapper.getKdUrusan(),
                        inputWrapper.getKdBidang(),
                        inputWrapper.getKdUnit(),
                        inputWrapper.getKdSub(),
                        inputWrapper.getTahun(),
                        inputWrapper.getKdProg(),
                        inputWrapper.getIdProg(),
                        inputWrapper.getKdKeg(),
                        inputWrapper.getNipPegawai(),
                        inputWrapper.getKdStatusPenanggungJawab());

        PenanggungJawabKegiatan penanggungJawabKegiatan
                = new PenanggungJawabKegiatan();
        penanggungJawabKegiatan.setPenanggungJawabKegiatanId(id);
        penanggungJawabKegiatan.setStatusApproval(0);

        penanggungJawabKegiatanService.create(penanggungJawabKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("penanggung jawab berhasil dipasang"), HttpStatus.OK);
    }


    @RequestMapping(value = "/get-penanggung-jawab-kegiatan/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getPenanggungJawabKegiatan(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get penanggung jawab kegiatan "+kdUnitKerja);

        List<PenanggungJawabWrapper> penanggungJawabKegiatanWrapperList
                = new ArrayList<>();

        List<QutPegawai> pegawaiUnitKerja
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<StatusPenanggungJawabKegiatan> statusPenanggungJawabKegiatanList
                = statusPenanggungJawabKegiatanService.getAll();

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);
        List<TaKegiatan> taKegiatanList
                = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
        List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
                = penanggungJawabKegiatanService.getByUnitKerja(
                        unitKerjaKegiatan.getKdUrusan(),
                        unitKerjaKegiatan.getKdBidang(),
                        unitKerjaKegiatan.getKdUnit());

        CustomPegawaiCredential penanggungJawab;
        String statusPenanggungJawab = "";
        String keteranganKegiatan = "";
        for (PenanggungJawabKegiatan penanggungJawabKegiatan
                : penanggungJawabKegiatanList) {

            penanggungJawab = null;

            for (QutPegawai qutPegawai
                    : pegawaiUnitKerja) {
                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getNipPegawai()
                        .equals(qutPegawai.getNip())) {
                    penanggungJawab
                            = new CustomPegawaiCredential(
                                    qutPegawai.getNip(),
                                    qutPegawai.getNama(),
                                    qutPegawai.getGol(),
                                    qutPegawai.getPangkat(),
                                    qutPegawai.getKdJabatan(),
                                    qutPegawai.getJabatan(),
                                    qutPegawai.getKdUnitKerja(),
                                    qutPegawai.getUnitKerja(),
                                    qutPegawai.getGlrDpn(),
                                    qutPegawai.getGlrBlk());
                    break;
                }
            }

            for (StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan
                    : statusPenanggungJawabKegiatanList) {
                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab()
                        .equals(statusPenanggungJawabKegiatan.getKdStatus())) {
                    statusPenanggungJawab = statusPenanggungJawabKegiatan.getStatus();
                    break;
                }
            }

            for (TaKegiatan taKegiatan
                    : taKegiatanList) {
                if (taKegiatan.getTaKegiatanId().getKdUrusan().equals(penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUrusan())
                        && taKegiatan.getTaKegiatanId().getKdBIdang().equals(penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdBidang())
                        && taKegiatan.getTaKegiatanId().getKdUnit().equals(penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUnit())) {
                    keteranganKegiatan = taKegiatan.getKetKegiatan();
                    break;
                }
            }

            penanggungJawabKegiatanWrapperList
                    .add(new PenanggungJawabWrapper(
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUrusan(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdBidang(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUnit(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdSub(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getTahun(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdProg(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getIdProg(),
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdKeg(),
                            keteranganKegiatan,
                            penanggungJawab,
                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab(),
                            statusPenanggungJawab,
                            penanggungJawabKegiatan.getStatusApproval()
                    ));
        }

        return new ResponseEntity<Object>(penanggungJawabKegiatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> deletePenanggungJawabKegiatan(@RequestBody PenanggungJawabKegiatanInputWrapper inputWrapper) {
        LOGGER.info("delete penanggung jawab kegiatan");

        penanggungJawabKegiatanService
                .delete(new PenanggungJawabKegiatanId(
                            inputWrapper.getKdUrusan(),
                            inputWrapper.getKdBidang(),
                            inputWrapper.getKdUnit(),
                            inputWrapper.getKdSub(),
                            inputWrapper.getTahun(),
                            inputWrapper.getKdProg(),
                            inputWrapper.getIdProg(),
                            inputWrapper.getKdKeg(),
                            inputWrapper.getNipPegawai(),
                            inputWrapper.getKdStatusPenanggungJawab()));

        return new ResponseEntity<Object>(new CustomMessage("penanggung jawab berhasil dihapus"), HttpStatus.OK);
    }

}
