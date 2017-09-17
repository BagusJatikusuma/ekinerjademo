package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.exception.AuthenticationCredentialsNotFoundExcecption;
import com.pemda.ekinerjademo.util.exception.BadCredentialsException;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * this method used for receive pegawai authentication request
     * @param akunPegawai in JSON Format
     * @return RincianEKinerja JSON Format
     */
    @CrossOrigin(allowCredentials = "false") //just for testing. remove when frontend app is ready
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @Transactional
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

        QutPegawai qutPegawai =
                qutPegawaiService.getQutPegawai(akunPegawaiAuthenticated.getNipPegawai());

        if (qutPegawai == null) {
            return new ResponseEntity<Object>(
                    new CustomMessage("pegawai not found in database kepegawaian"),
                    HttpStatus.UNAUTHORIZED);
        }

        PegawaiCredential pegawaiCredential =
                new PegawaiCredential(
                        akunPegawaiAuthenticated.getNipPegawai(),
                        qutPegawai.getNama(),
                        akunPegawaiAuthenticated.getRole(),
                        "IniTokenDUmmy",
                        qutPegawai.getJabatan(),
                        qutPegawai.getUnitKerja(),
                        qutPegawai.getKdUnitKerja(),
                        qutPegawai.getKdJabatan());

        return new ResponseEntity<Object>(pegawaiCredential, HttpStatus.OK);

    }

}
