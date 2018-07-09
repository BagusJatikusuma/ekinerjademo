package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.util.exception.AuthenticationCredentialsNotFoundExcecption;
import com.pemda.ekinerjademo.util.exception.BadCredentialsException;

/**
 * Created by bagus on 07/09/17.
 */
public interface AuthenticationProvider {
    AkunPegawai authenticate(AkunPegawai akunPegawai)
            throws AuthenticationCredentialsNotFoundExcecption, BadCredentialsException;
}
