package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.TemplateLain;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananId;

import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
public interface TemplateLainService {
    void create(TemplateLain templateLain);
    void approve(String kdTemplateLain);

    TemplateLain getTemplateLain(String kdTemplateLain);
    List<TemplateLain> getByPembuat(String nipPembuat);
    List<TemplateLain> getBySekretarisApproval(String kdUnitkerja);
    List<TemplateLain> getByUrtugNonDpa(UraianTugasPegawaiBulananId id);
}
