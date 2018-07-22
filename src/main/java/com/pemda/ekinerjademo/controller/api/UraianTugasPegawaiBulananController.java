package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtug;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.service.PejabatPenilaiDinilaiService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.UraianTugasJabatanJenisUrtugService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiBulananService;
import com.pemda.ekinerjademo.util.exception.EkinerjaObjConverter;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiBulananInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     *
     * service yang digunakan untuk membuat daftar ajuan kontrak bulanan pegawai
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
     * service yang digunakan untuk mengapprove ajuan kontrak bawahan
     *
     * @return
     */
    @RequestMapping(value = "/approval-urtug-bulanan-non-dpa-pegawai", method = RequestMethod.PUT)
    public ResponseEntity<?> approveAjuanKontrakBulananBawahan(
            @RequestBody List<UraianTugasPegawaiBulananInputWrapper> uraianTugasPegawaiBulananInputWrappers) {
        LOGGER.info("approve ajuan kontrak bulanan bawahan");

        if (uraianTugasPegawaiBulananInputWrappers.isEmpty())
            return new ResponseEntity<Object>(new CustomMessage("Uraian tugas yang dipilih telah disetujui"), HttpStatus.OK);

        //get current uraian tugas non dpa pegawai in database
        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = urtugBulananService.getByNip(
                    uraianTugasPegawaiBulananInputWrappers
                        .get(0)
                        .getNipPegawai());

        for (UraianTugasPegawaiBulananInputWrapper urtugPegawaiAtasan : uraianTugasPegawaiBulananInputWrappers){
            boolean found = false;
            for (UraianTugasPegawaiBulanan urtugPegawai : uraianTugasPegawaiBulanans){
                if (urtugPegawaiAtasan.getKdUrtug()
                        .equals(urtugPegawai.getUraianTugasPegawaiBulananId().getKdUrtug())) {
                    urtugBulananService.approveAjuanKontrakBulanan(urtugPegawaiAtasan);

                    found = true;
                    break;
                }
            }
            //jika misalkan asalnya ditolak bawahan tapi ditambahkan oleh atasan
            if (!found) {
                if (urtugPegawaiAtasan.getStatusApproval() == 1) {
                    urtugBulananService.create(
                            urtugPegawaiAtasan,
                            1);
                }
            }
        }

        return new ResponseEntity<>(new CustomMessage("Uraian tugas yang dipilih telah disetujui"), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/get-uraian-tugas-pegawai-bulanan-by-nip/{nipPegawai}/{bulanUrtug}", method = RequestMethod.GET)
    public ResponseEntity<?> getUraianTugasPegawaiBulananByNip(
            @PathVariable("nipPegawai") String nipPegawai,
            @PathVariable("bulanUrtug") Integer bulanUrtug) {
        LOGGER.info("get uraian tugas pegawai bulanan by nip "+nipPegawai);

        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = urtugBulananService.getByNip(nipPegawai, bulanUrtug);
        List<UraianTugasPegawaiBulananWrapper> uraianTugasPegawaiBulananWrapperList
                = new ArrayList<>();

        for (UraianTugasPegawaiBulanan urtug
                : uraianTugasPegawaiBulanans) {
            if (urtug.getStatusApproval() == 1) {
                uraianTugasPegawaiBulananWrapperList
                        .add(new UraianTugasPegawaiBulananWrapper(
                                urtug.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                urtug.getUraianTugasPegawaiBulananId().getKdUrtug(),
                                urtug.getUraianTugasPegawaiBulananId().getKdJabatan(),
                                urtug.getUraianTugasPegawaiBulananId().getKdJenisUrtug(),
                                urtug.getUraianTugasPegawaiBulananId().getTahunUrtug(),
                                bulanUrtug,
                                urtug.getUraianTugasPegawaiBulananId().getNipPegawai(),
                                urtug.getTargetKuantitas(),
                                urtug.getTargetSatuanKuantitas(),
                                urtug.getTargetKualitas(),
                                urtug.getWaktu(),
                                Long.valueOf(urtug.getTargetKuantitas() * urtug.getWaktu()),
                                urtug.getBiaya(),
                                "",
                                urtug.getStatusApproval(),
                                urtug.getRealisasiKuantitas(),
                                urtug.getRealisasiKualitas(),
                                urtug.getRealisasiWaktu()));
            }

        }

        return new ResponseEntity<>(uraianTugasPegawaiBulananWrapperList, HttpStatus.OK);
    }

    /**
     *
     * service yang dibuat untuk melihat ajuan uraian tugas bulanan bawahan oleh penilai
     *
     * @return
     */
    @RequestMapping(value = "/get-uraian-tugas-bulanan-by-penilai/{kdUnitKerja}/{nipPenilai}/{bulanPengajuan}", method = RequestMethod.GET)
    public ResponseEntity<?> getUraianTugasBulananByPenilai(@PathVariable("kdUnitKerja") String kdUnitKerja,
                                                            @PathVariable("nipPenilai") String nipPenilai,
                                                            @PathVariable("bulanPengajuan") Integer bulanPengajuan) {
        LOGGER.info("get ajuan uraian tugas bulanan by penilai");

        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = urtugBulananService.getByUnitKerja(kdUnitKerja, bulanPengajuan);
        List<PegawaiCredential> pegawaiCredentialList
                = new ArrayList<>();
        List<AjuanUraianTugasPegawaiBulananWrapper> ajuanUraianTugasPegawaiBulananWrappers
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<PejabatPenilaiDinilai> nipPegawaiBawahanPenilaiList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);
        List<QutPegawai> pegawaiBawahanPenilaiList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList
                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByUnitKerja(kdUnitKerja);
//        List<TkdUnk> tkdUnkList
//                = tkdUnkDao.findAll(); //test clone
        List<TkdUnk> tkdUnkList
                = EkinerjaObjConverter.convertFromUnkClonetoOriginal(tkdUnkDao.findAll());

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

        for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulanans) {
            Boolean constraint = false;

            for (PegawaiCredential pegawaiCredential : pegawaiCredentialList) {
                if (pegawaiCredential.getNipPegawai()
                        .equals(uraianTugasPegawaiBulanan
                                .getUraianTugasPegawaiBulananId()
                                .getNipPegawai())){
                    constraint = true;
                    break;
                }
            }

            if (!constraint){
                pegawaiCredentialList.add(
                        new PegawaiCredential(
                            uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null));
            }

        }

        for (QutPegawai qutPegawai : pegawaiBawahanPenilaiList){
            AjuanUraianTugasPegawaiBulananWrapper ajuanUraianTugasNonDpaPegawaiWrapper = new AjuanUraianTugasPegawaiBulananWrapper();
            ajuanUraianTugasNonDpaPegawaiWrapper.setNipPegawai(qutPegawai.getNip());
            ajuanUraianTugasNonDpaPegawaiWrapper.setNamaPegawai(qutPegawai.getNama());
            ajuanUraianTugasNonDpaPegawaiWrapper.setKdJabatan(qutPegawai.getKdJabatan());
            ajuanUraianTugasNonDpaPegawaiWrapper.setJabatan(qutPegawai.getJabatan());
            ajuanUraianTugasNonDpaPegawaiWrapper.setEselon(qutPegawai.getEselon());
            ajuanUraianTugasNonDpaPegawaiWrapper.setKdUnitKerja(qutPegawai.getKdUnitKerja());

            ajuanUraianTugasPegawaiBulananWrappers.add(ajuanUraianTugasNonDpaPegawaiWrapper);
        }

        for (AjuanUraianTugasPegawaiBulananWrapper qutPegawaiWrapper
                : ajuanUraianTugasPegawaiBulananWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        //insert urtug ke setiap elemen tugas
        int penanganan;
        for (AjuanUraianTugasPegawaiBulananWrapper ajuanUraianTugasNonDpaPegawaiWrapper
                : ajuanUraianTugasPegawaiBulananWrappers){
            penanganan = 0;

            List<UraianTugasPegawaiTahunanWrapper> uraianTugasWrapperList = new ArrayList<>();
            List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilihWrapperList = new ArrayList<>();

            for (UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug : uraianTugasJabatanJenisUrtugList) {
                if (ajuanUraianTugasNonDpaPegawaiWrapper.getKdJabatan()
                        .equals(uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan())) {

                    boolean found = false;
                    for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulanans) {
                        if (ajuanUraianTugasNonDpaPegawaiWrapper.getNipPegawai()
                                .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai())) {

                            if (uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
                                    .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug())) {
                                if (uraianTugasPegawaiBulanan.getStatusApproval() == 0) {
                                    found = true;
                                    uraianTugasWrapperList.add(
                                            new UraianTugasPegawaiTahunanWrapper(
                                                    uraianTugasPegawaiBulanan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai(),
                                                    uraianTugasPegawaiBulanan.getTargetKuantitas(),
                                                    uraianTugasPegawaiBulanan.getTargetSatuanKuantitas(),
                                                    uraianTugasPegawaiBulanan.getTargetKualitas(),
                                                    uraianTugasPegawaiBulanan.getWaktu(),
                                                    uraianTugasPegawaiBulanan.getBiaya(),
                                                    "",
                                                    uraianTugasPegawaiBulanan.getStatusApproval()
                                            ));
                                }
                                else if (uraianTugasPegawaiBulanan.getStatusApproval() == 2
                                        || uraianTugasPegawaiBulanan.getStatusApproval() == 1) {
                                    found = true;
                                    penanganan = 1;

                                }
                                //ambil uraian tugas yang tidak dipilih oleh bawahan
                                else if (uraianTugasPegawaiBulanan.getStatusApproval() == 3) {
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

                                break;

                            }

                        }


                    }

                }
            }

            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasDiajukan(uraianTugasWrapperList);
            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasTidakDipilih(uraianTugasTidakDipilihWrapperList);

            // jika pegawai bawahan belum sama sekali mengirimkan ajuan kontrak kerja
            if (uraianTugasWrapperList.isEmpty() && penanganan == 0) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(0);
            }
            //jika pegawai bawahan baru pertama kali mengirimkan ajuan kontrak kerja
            else if (!uraianTugasWrapperList.isEmpty() && penanganan == 0) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(1);
            }
            //jika ajuan kontrak kerja bawahan sudah ditangani
            else if (uraianTugasWrapperList.isEmpty() && penanganan == 1) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(2);
            }
            //jika pegawai bawahan mengajukan lagi kontrak kerja yang lain
            else if (!uraianTugasWrapperList.isEmpty() && penanganan == 1) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(3);
            }

        }

        return new ResponseEntity<>(ajuanUraianTugasPegawaiBulananWrappers, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    public ResponseEntity<?> getUraianTugasBulananByUnitKerja() {
        return null;
    }

}
