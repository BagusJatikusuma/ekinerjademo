package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UnitKerjaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.wrapper.output.TaKegiatanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 27/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TestServiceController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestServiceController.class);

    private AkunPegawaiService akunPegawaiService;
    private JenisUrtugService jenisUrtugService;
    private SopService sopService;
    private UraianTugasService uraianTugasService;
    private UraianTugasJabatanService uraianTugasJabatanService;

    @Autowired
    private AkunPegawaiDao akunPegawaiDao;
    @Autowired
    private TaKegiatanDao taKegiatanDao;
    @Autowired
    private UnitKerjaKegiatanDao unitKerjaKegiatanDao;

    @Autowired
    public TestServiceController(
            AkunPegawaiService akunPegawaiService,
            JenisUrtugService jenisUrtugService,
            SopService sopService,
            UraianTugasService uraianTugasService,
            UraianTugasJabatanService uraianTugasJabatanService) {
        this.akunPegawaiService = akunPegawaiService;
        this.jenisUrtugService = jenisUrtugService;
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
//        List<JenisUrtugUrtug> jenisUrtugUrtugs = jenisUrtugUrtugService.getJenisUrtugUrtug();
//        for (JenisUrtugUrtug jenisUrtugUrtug : jenisUrtugUrtugs) {
//            LOGGER.info(
//                    jenisUrtugUrtug.getJenisUrtug().getJenisUrtug()+" : "+jenisUrtugUrtug.getUraianTugasJabatan().getUraianTugas().getDeskripsi()
//            );
//        }
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

//    @RequestMapping(value = "/kegiatan-simda")
//    ResponseEntity<?> kegiatanSimda() {
//        LOGGER.info("get kegiatan simda");
//
//        List<TaKegiatan> taKegiatan = taKegiatanDao.findAllByQuery();
//
//        for (int i = 0; i < 30 ; i++) {
//            LOGGER.info(i+1+" : "+
//                    taKegiatan.get(i).getTaKegiatanId().getKdKegiatan().toString() +
//                    " : "+
//                    taKegiatan.get(i).getKetKegiatan()+
//                    " : "+
//                    taKegiatan.get(i).getPaguAnggaran());
//            LOGGER.info(i+1+" : "+
//                    taKegiatan.get(i).getTaProgram().getTaProgramId().getIdProg()+
//                    " : "
//                    + taKegiatan.get(i).getTaProgram().getKetProgram());
//            LOGGER.info(i+1+" : "+
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getTaSubUnitId().getKdSub()+
//                    " : " +
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getNmPimpinan() +
//                    " : "+
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getRefSubUnit().getNmSubUnit());
//            LOGGER.info(i+1+" : "+
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getRefSubUnit().getRefUnit().getRefUnitId().getKdUnit()+
//                    " : " +
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getRefSubUnit().getRefUnit().getNmUnit());
//            LOGGER.info(i+1+" : "+
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getRefSubUnit().getRefUnit().getRefBidang().getRefBidangId().getKdBIdang()+
//                    " : " +
//                    taKegiatan.get(i).getTaProgram().getTaSubUnit().getRefSubUnit().getRefUnit().getRefBidang().getNmBidang());
//        }
//
////        LOGGER.info(
////                taKegiatan.get(0)
////                        .getTaProgram()
////                        .getTaSubUnit()
////                        .getRefSubUnit()
////                        .getRefUnit()
////                        .getRefBidang()
////                        .getRefUrusan()
////                        .getNmUrusan());
//
//        return null;
//    }

    @RequestMapping(value = "/kegiatan-simda-unit-kerja/{kdUnitKerja}", method = RequestMethod.GET)
    @Transactional("simdaTransactionManager")
    ResponseEntity<?> kegiatanSimdaUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get kegiatan simda");

        UnitKerjaKegiatan unitKerjaKegiatan = unitKerjaKegiatanDao.findByKdUnitKerja(kdUnitKerja);

        LOGGER.info(
                "kdUrusan : "+unitKerjaKegiatan.getKdUrusan()+
                ", kdBidang : "+unitKerjaKegiatan.getKdBidang()+
                ", kdUnit : "+unitKerjaKegiatan.getKdUnit());

        List<TaKegiatanWrapper> taKegiatanWrapperList
                = new ArrayList<>();
        List<TaKegiatan> taKegiatanList
                = taKegiatanDao.findAllByKdUnitKerja(
                        unitKerjaKegiatan.getKdUrusan(),
                        unitKerjaKegiatan.getKdBidang(),
                        unitKerjaKegiatan.getKdUnit());

        for (TaKegiatan taKegiatan : taKegiatanList) {
            if (!taKegiatan.getKetKegiatan().equals("Non Kegiatan")) {
                taKegiatanWrapperList
                        .add(new TaKegiatanWrapper(
                                taKegiatan.getTaKegiatanId().getKdUrusan(),
                                taKegiatan.getTaKegiatanId().getKdBIdang(),
                                taKegiatan.getTaKegiatanId().getKdUnit(),
                                taKegiatan.getTaKegiatanId().getKdSub(),
                                taKegiatan.getTaKegiatanId().getTahun(),
                                taKegiatan.getTaKegiatanId().getKdProg(),
                                taKegiatan.getTaKegiatanId().getIdProg(),
                                taKegiatan.getTaKegiatanId().getKdKegiatan(),
                                taKegiatan.getKetKegiatan(),
                                taKegiatan.getLokasi(),
                                taKegiatan.getKelompokSasaran(),
                                taKegiatan.getStatusKegiatan(),
                                taKegiatan.getPaguAnggaran(),
                                taKegiatan.getWaktuPelaksanaan(),
                                taKegiatan.getKdSumber()));
            }

        }

        return new ResponseEntity<Object>(taKegiatanWrapperList, HttpStatus.OK);
    }

}
