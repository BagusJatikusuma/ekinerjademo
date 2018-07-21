package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulanan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.service.PejabatPenilaiDinilaiService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.UraianTugasJabatanJenisUrtugService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiBulananService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiBulananInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasPegawaiBulananController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasPegawaiBulananController.class);

    @Autowired
    private UraianTugasPegawaiBulananService urtugBulananService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired private UraianTugasJabatanJenisUrtugService uraianTugasJabatanJenisUrtugService;
    @Autowired private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;

    //    @Autowired private TkdUnkDao tkdUnkDao;
    @Autowired private TkdUnkCloneDao tkdUnkDao;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/create-daftar-urtug-pegawai-bulanan", method = RequestMethod.POST)
    public ResponseEntity<?> create(
            @RequestBody List<UraianTugasPegawaiBulananInputWrapper> inputWrapperList) {
        LOGGER.info("create urtug pegawai bulanan");

        urtugBulananService.create(inputWrapperList, 0);

        return new ResponseEntity<>(new CustomMessage("ajuan berhasil dibuat"), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    public ResponseEntity<?> getAjuanKontrakBulananPegawaiBawahan() {
        return null;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/get-uraian-tugas-pegawai-tahunan-by-nip/{nipPegawai}", method = RequestMethod.GET)
    public ResponseEntity<?> getUraianTugasPegawaiBulananByNip(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get uraian tugas pegawai bulanan by nip "+nipPegawai);



        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    public ResponseEntity<?> getUraianTugasBulananByPenilai() {
        return null;
    }

    /**
     *
     * @return
     */
    public ResponseEntity<?> getUraianTugasBulananByUnitKerja() {
        return null;
    }

}
