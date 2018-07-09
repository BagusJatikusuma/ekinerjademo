package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.Template;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
public interface TemplateService {
    Template get(String templateId);
    List<Template> getAll();
    void save(Template template);
    void update(Template template);
    void delete(String templateId);
}
