package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import com.pemda.ekinerjademo.service.AuthenticationProvider;
import com.pemda.ekinerjademo.util.exception.AuthenticationCredentialsNotFoundExcecption;
import com.pemda.ekinerjademo.util.exception.BadCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by bagus on 07/09/17.
 */
@Service("BasicAuthenticationProvider")
@Transactional
public class BasicAuthenticationProvider implements AuthenticationProvider {
    public static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticationProvider.class);

    @Autowired
    private AkunPegawaiService akunPegawaiService;

    @Override
    public AkunPegawai authenticate(AkunPegawai akunPegawai) throws
            AuthenticationCredentialsNotFoundExcecption, BadCredentialsException {
        LOGGER.info("authenticating "+akunPegawai.getNipPegawai());

        AkunPegawai akunPegawaiAuthenticated = null;

        if (!akunPegawai.getNipPegawai().isEmpty() && !akunPegawai.getPassword().isEmpty()) {
            akunPegawaiAuthenticated =
                    akunPegawaiService.getAkunPegawai(
                            akunPegawai.getNipPegawai()
                    );

            if (akunPegawaiAuthenticated != null) {
                if (!akunPegawaiAuthenticated.getPassword().
                        equals(akunPegawai.getPassword())) {
//                    throw new BadCredentialsException("wrong password");
                    throw new BadCredentialsException("nip atau password salah");
                }

            }
            else
//                throw new BadCredentialsException("user not found");
                throw new BadCredentialsException("nip atau password salah");

        }
        else throw new AuthenticationCredentialsNotFoundExcecption("username or password is empty");

        return akunPegawaiAuthenticated;
    }
}
