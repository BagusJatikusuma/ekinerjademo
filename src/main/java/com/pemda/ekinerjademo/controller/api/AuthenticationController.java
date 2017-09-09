package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerja;
import com.pemda.ekinerjademo.service.AuthenticationProvider;
import com.pemda.ekinerjademo.service.EKinerjaService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.util.exception.AuthenticationCredentialsNotFoundExcecption;
import com.pemda.ekinerjademo.util.exception.BadCredentialsException;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@RestController
@RequestMapping(value = "/api")
//@CrossOrigin(origins = {"http://192.168.1.219:3000", "http://localhost:3000"}, maxAge = 500000, allowCredentials = "false")
public class AuthenticationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Resource(name = "BasicAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private QutPegawaiService qutPegawaiService;
    @Autowired
    private EKinerjaService eKinerjaService;


    /**
     * this method used for receive pegawai authentication request
     * @param akunPegawai in JSON Format
     * @return RincianEKinerja JSON Format
     */
//    @CrossOrigin(allowCredentials = "false") //just for testing. remove when frontend app is ready
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    ResponseEntity<?> validateAkunPegawai(@RequestBody AkunPegawai akunPegawai) {
        LOGGER.info("receive "+akunPegawai.getNipPegawai()+" authetication request");

        AkunPegawai akunPegawaiAuthenticated = null;

        try {
            akunPegawaiAuthenticated = authenticationProvider.authenticate(akunPegawai);
        } catch (AuthenticationCredentialsNotFoundExcecption authenticationCredentialsNotFoundExcecption) {
            return new ResponseEntity<Object>(
                    new CustomMessage(authenticationCredentialsNotFoundExcecption.getMessage()),
                    HttpStatus.UNAUTHORIZED);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<Object>(
                    new CustomMessage(e.getMessage()),
                    HttpStatus.UNAUTHORIZED);

        }

//        HashMap<String, String> map = new HashMap<>();
//        map.put("nip_pegawai", akunPegawaiAuthenticated.getNipPegawai());
//        map.put("password", akunPegawaiAuthenticated.getPassword());
//
//        String uraianTugasDeskripsi =
//                akunPegawaiAuthenticated.getRincianEKinerjaList().get(0).getUraianTugas().getDeskripsi();

        //set this logic in difference layer
        String nipPegawai = akunPegawaiAuthenticated.getNipPegawai();
        QutPegawai qutPegawai = qutPegawaiService.getQutPegawai(nipPegawai);
        String namaPegawai = qutPegawai.getNama();
        JabatanWrapper jabatan =
                new JabatanWrapper(qutPegawai.getKdJabatan(),qutPegawai.getJabatan());
        UnitOrganisasiWrapper unitOrganisasi =
                new UnitOrganisasiWrapper("dummyId", "dummyOrganisasi");
        UnitKerjaWrapper unitKerja =
                new UnitKerjaWrapper(qutPegawai.getKdUnitKerja(), qutPegawai.getUnitKerja());
        List<RincianEKinerja> rincianEKinerjaList =
                eKinerjaService.getRincianEKinerjaByNip(nipPegawai);
        List<RincianEKinerjaWrapper> rincianEKinerjaWrapperList = new ArrayList<>();

        for (RincianEKinerja rincianEKinerja : rincianEKinerjaList) {
            UraianTugasWrapper uraianTugasWrapper =
                    new UraianTugasWrapper(
                            rincianEKinerja.getUraianTugas().getKdUrtug(),
                            rincianEKinerja.getUraianTugas().getDeskripsi());
            rincianEKinerjaWrapperList
                    .add(new RincianEKinerjaWrapper(
                            uraianTugasWrapper,
                            rincianEKinerja.getSatuan(),
                            rincianEKinerja.getVolumeKerja(),
                            rincianEKinerja.getNormaWaktu(),
                            rincianEKinerja.getBebanKerja(),
                            rincianEKinerja.getPeralatan(),
                            rincianEKinerja.getKeterangan()));
        }

        UraianTugasEKinerjaWrapper uraianTugasEKinerjaWrapper =
                new UraianTugasEKinerjaWrapper(
                        nipPegawai,
                        namaPegawai,
                        jabatan,
                        unitOrganisasi,
                        unitKerja,
                        rincianEKinerjaWrapperList);

        return new ResponseEntity<Object>(uraianTugasEKinerjaWrapper, HttpStatus.OK);

    }

}
