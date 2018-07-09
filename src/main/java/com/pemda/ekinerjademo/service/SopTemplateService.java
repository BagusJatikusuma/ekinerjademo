package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplate;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplateId;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
public interface SopTemplateService {
    List<SopTemplate> getSopTemplateBySop(String kdSop);
    void save(SopTemplate sopTemplate);
    void delete(SopTemplateId sopTemplateId);
}
