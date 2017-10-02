package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bagus on 27/09/17.
 */
@RestController
@RequestMapping(value = "/api")
public class TestServiceController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestServiceController.class);

    private AkunPegawaiService akunPegawaiService;
    private JenisUrtugService jenisUrtugService;
    private JenisUrtugUrtugService jenisUrtugUrtugService;
    private SopJenisUrtugUrtugService sopJenisUrtugUrtugService;
    private SopService sopService;
    private UraianTugasService uraianTugasService;
    private UraianTugasJabatanService uraianTugasJabatanService;

    @Autowired
    private AkunPegawaiDao akunPegawaiDao;
    @Autowired
    private TaKegiatanDao taKegiatanDao;

    @Autowired
    public TestServiceController(
            AkunPegawaiService akunPegawaiService,
            JenisUrtugService jenisUrtugService,
            JenisUrtugUrtugService jenisUrtugUrtugService,
            SopJenisUrtugUrtugService sopJenisUrtugUrtugService,
            SopService sopService,
            UraianTugasService uraianTugasService,
            UraianTugasJabatanService uraianTugasJabatanService) {
        this.akunPegawaiService = akunPegawaiService;
        this.jenisUrtugService = jenisUrtugService;
        this.jenisUrtugUrtugService = jenisUrtugUrtugService;
        this.sopJenisUrtugUrtugService = sopJenisUrtugUrtugService;
        this.sopService = sopService;
        this.uraianTugasService = uraianTugasService;
        this.uraianTugasJabatanService = uraianTugasJabatanService;
    }

    @RequestMapping(value = "/log-service", method = RequestMethod.GET)
    @Transactional("ekinerjaTransactionManager")
    ResponseEntity<?> logService() {
        LOGGER.info("get log service request");

//        List<AkunPegawai> akunPegawaiList = akunPegawaiService.getAkunPegawaiList();
//
//        akunPegawaiList.forEach(
//                akunPegawai -> LOGGER.info(akunPegawai.getNipPegawai()+" : "+akunPegawai.getRole().getRole())
//        );
//        List<JenisUrtug> jenisUrtugs = jenisUrtugService.getJenisUrtug();
        List<JenisUrtugUrtug> jenisUrtugUrtugs = jenisUrtugUrtugService.getJenisUrtugUrtug();
        for (JenisUrtugUrtug jenisUrtugUrtug : jenisUrtugUrtugs) {
            LOGGER.info(
                    jenisUrtugUrtug.getJenisUrtug().getJenisUrtug()+" : "+jenisUrtugUrtug.getUraianTugasJabatan().getUraianTugas().getDeskripsi()
            );
        }
//        List<SopJenisUrtugUrtug> sopJenisUrtugUrtugs = sopJenisUrtugUrtugService.getSopJenisUrtugUrtug();
//        List<Sop> sops = sopService.getSop();
//        List<UraianTugas> uraianTugasList = uraianTugasService.getAllUraianTugas();
//        List<UraianTugasJabatan> uraianTugasJabatans = uraianTugasJabatanService.getUraianTugasJabatan();


        return null;
    }

    @RequestMapping(value = "/save-akun-pegawai", method = RequestMethod.GET)
    @Transactional("ekinerjaTransactionManager")
    ResponseEntity<?> saveAkunPegawai() {
        LOGGER.info("update akun pegawai");

        Role role = new Role();
        role.setId("AD001");

        AkunPegawai akunPegawai = new AkunPegawai();
        akunPegawai.setRole(role);
        akunPegawai.setNipPegawai("11151463");
        akunPegawai.setPassword("bekasiPemda");

        akunPegawaiDao.save(akunPegawai);

        return null;
    }

    @RequestMapping(value = "/kegiatan-simda")
    @Transactional("simdaTransactionManager")
    ResponseEntity<?> kegiatanSimda() {
        LOGGER.info("get kegiatan simda");

        List<TaKegiatan> taKegiatan = taKegiatanDao.findAllByQuery();

        LOGGER.info(
                taKegiatan.get(0)
                        .getTaProgram()
                        .getTaSubUnit()
                        .getRefSubUnit()
                        .getRefUnit()
                        .getRefBidang()
                        .getRefUrusan()
                        .getNmUrusan());

        return null;
    }

}
