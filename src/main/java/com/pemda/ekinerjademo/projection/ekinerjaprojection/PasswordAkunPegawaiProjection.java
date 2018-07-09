package com.pemda.ekinerjademo.projection.ekinerjaprojection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by bagus on 25/09/17.
 */
public interface PasswordAkunPegawaiProjection {
    @Value("#{target.password}")
    String getPassword();
}
