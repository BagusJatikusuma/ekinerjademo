package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaProgram;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdJabatanCloneDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UnitKerjaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaProgramDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.service.impl.TkdJabatanServiceImpl;
import com.pemda.ekinerjademo.util.CSVUtil;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.TaKegiatanWrapper;
import com.pemda.ekinerjademo.wrapper.output.TaProgramWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private TaProgramDao taProgramDao;
    @Autowired
    private UnitKerjaKegiatanDao unitKerjaKegiatanDao;
    @Autowired
    private TkdUnkCloneDao tkdUnkCloneDao;
    @Autowired
    private CSVUtil csvUtil;
    @Autowired
    @Qualifier("TkdJabatanCloneService")
    private TkdJabatanService tkdJabatanCloneDao;
    @Autowired
    private QutPegawaiCloneService qutPegawaiCloneService;

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
                                taKegiatan.getKdSumber(),
                                taKegiatan.getTaIndikatorList().get(0).getTolakUkur(),
                                taKegiatan.getTaIndikatorList().get(0).getTargetAngka(),
                                taKegiatan.getTaIndikatorList().get(0).getTargetUraian()));
            }

        }

        return new ResponseEntity<Object>(taKegiatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-kegiatan-by-program-simda", method = RequestMethod.POST)
    @Transactional("simdaTransactionManager")
    ResponseEntity<?> getKegiatanByProgram() {
        return null;
    }

    @RequestMapping(value = "/program-simda-unit-kerja/{kdUnitKerja}", method = RequestMethod.GET)
    @Transactional("simdaTransactionManager")
    ResponseEntity<?> getProgramSimdaUnitKerja(
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get kegiatan simda");

        UnitKerjaKegiatan unitKerjaKegiatan = unitKerjaKegiatanDao.findByKdUnitKerja(kdUnitKerja);

        LOGGER.info(
                "kdUrusan : "+unitKerjaKegiatan.getKdUrusan()+
                        ", kdBidang : "+unitKerjaKegiatan.getKdBidang()+
                        ", kdUnit : "+unitKerjaKegiatan.getKdUnit());

        List<TaProgramWrapper> taProgramWrappers
                = new ArrayList<>();
        List<TaProgram> taProgramList
                = taProgramDao.findAllByKdUnitKerja(
                        unitKerjaKegiatan.getKdUrusan(),
                        unitKerjaKegiatan.getKdBidang(),
                        unitKerjaKegiatan.getKdUnit());

        for (TaProgram taProgram : taProgramList) {
            if (!taProgram.getKetProgram().equals("Non Program")) {
                taProgramWrappers
                        .add(new TaProgramWrapper(
                                taProgram.getTaProgramId().getKdUrusan(),
                                taProgram.getTaProgramId().getKdBIdang(),
                                taProgram.getTaProgramId().getKdUnit(),
                                taProgram.getTaProgramId().getKdSub(),
                                taProgram.getTaProgramId().getTahun(),
                                taProgram.getTaProgramId().getKdProg(),
                                taProgram.getTaProgramId().getIdProg(),
                                taProgram.getKetProgram(),
                                taProgram.getTolakUkur(),
                                taProgram.getTargetAngka(),
                                taProgram.getTargetUraian(),
                                taProgram.getKdUrusan1(),
                                taProgram.getKdBidang1()
                        ));
            }
        }

        return new ResponseEntity<Object>(taProgramWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/clone-jabatan-csv", method = RequestMethod.GET)
    ResponseEntity<?> cloneTkdJabatanFromCSV() {
        LOGGER.info("clone jabatan from csv");

        String jabatanCSVPath = "/home/pemkab/project/temp_simpeg_importer/jabatan.csv";
        List<TkdUnkClone> tkdUnkClones = tkdUnkCloneDao.findAll();

        /** **/
        String csvFile = jabatanCSVPath;
        String line = "";
        String cvsSplitBy = ";";

        LOGGER.info("path of file "+csvFile);

        List<TkdJabatanClone> tkdJabatanClones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                TkdJabatanClone objJatan = new TkdJabatanClone();
                LOGGER.error("size of line "+country.length + "with example first element "+country[0]);

                for (TkdUnkClone objUnk : tkdUnkClones) {
                    if (country.length > 3) {
                        if (objUnk.getUnitKerja().equals(country[3])) {
                            objJatan.setKdJabatan(country[0]);
                            objJatan.setJabatan(country[1]);
                            objJatan.setEselon(country[2]);
                            objJatan.setUnitKerja(country[3]);
                            objJatan.setKdUnitKerja(objUnk);

                            tkdJabatanClones.add(objJatan);

                            break;
                        }
                    }
                    else {
                        objJatan.setKdJabatan(country[0]);
                        objJatan.setJabatan(country[1]);
                        objJatan.setEselon(country[2]);

                        tkdJabatanClones.add(objJatan);
                    }
                }

//                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /** **/

        for (TkdJabatanClone obj : tkdJabatanClones) {
            tkdJabatanCloneDao.createClone(obj);
        }

        return new ResponseEntity<>(new CustomMessage("cloning jabatan from csv succes"), HttpStatus.OK);
    }

    @RequestMapping(value = "/clone-jabatan-csv/{kdJabatanAwal}", method = RequestMethod.GET)
    ResponseEntity<?> cloneTkdJabatanFromCSVPerjabatan(@PathVariable("kdJabatanAwal") String kdJabatanAwal) {
        LOGGER.info("clone jabatan from csv");

        String jabatanCSVPath = "/home/pemkab/project/temp_simpeg_importer/jabatan.csv";
        List<TkdUnkClone> tkdUnkClones = tkdUnkCloneDao.findAll();

        /** **/
        String csvFile = jabatanCSVPath;
        String line = "";
        String cvsSplitBy = ";";

        LOGGER.info("path of file "+csvFile);

        List<TkdJabatanClone> tkdJabatanClones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
//                // use comma as separator
//                LOGGER.info("line is "+line);
                String[] country = line.split(cvsSplitBy);
                TkdJabatanClone objJatan = new TkdJabatanClone();

                if (country[0].startsWith(kdJabatanAwal)) {
                    LOGGER.error("size of line " + country.length + "with example first element " + country[0]);

                    for (TkdUnkClone objUnk : tkdUnkClones) {
                        if (country.length > 3) {
                            if (objUnk.getUnitKerja().equals(country[3])) {
                                objJatan.setKdJabatan(country[0]);
                                objJatan.setJabatan(country[1]);
                                objJatan.setEselon(country[2]);
                                objJatan.setUnitKerja(country[3]);
                                objJatan.setKdUnitKerja(objUnk);

                                tkdJabatanClones.add(objJatan);

                                break;
                            }
                        } else {
                            objJatan.setKdJabatan(country[0]);
                            objJatan.setJabatan(country[1]);
                            objJatan.setEselon(country[2]);

                            tkdJabatanClones.add(objJatan);
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /** **/

        for (TkdJabatanClone obj : tkdJabatanClones) {
            tkdJabatanCloneDao.createClone(obj);
        }

        return new ResponseEntity<>(new CustomMessage("cloning jabatan from csv succes"), HttpStatus.OK);
    }

    @RequestMapping(value = "/synchron", method = RequestMethod.GET)
    ResponseEntity<?> synchron() {
            LOGGER.info("synchron");

            List<QutPegawaiClone> qutPegawaiClones = qutPegawaiCloneService.getQutPegawaiClone();
            List<TkdJabatanClone> tkdJabatanClones = tkdJabatanCloneDao.getAllClone();

            for (QutPegawaiClone objPeg : qutPegawaiClones) {
                for (TkdJabatanClone objJab : tkdJabatanClones) {
                    if (objPeg.getKdJabatan().equals(objJab.getKdJabatan())) {
                        if (objJab.getKdUnitKerja() == null)
                            LOGGER.error("jabatan "+objJab.getKdJabatan()+" tidak sinkron dengan unit kerja");
                        else
                            objPeg.setKdUnitKerja(objJab.getKdUnitKerja().getKdUnK());

                        break;
                    }
                }
            }

            qutPegawaiCloneService.saveQutPegawaiList(qutPegawaiClones);

            return new ResponseEntity<>(new CustomMessage("sinkronisasi kdunitkerja pegawai selesai"), HttpStatus.OK);
    }

}
