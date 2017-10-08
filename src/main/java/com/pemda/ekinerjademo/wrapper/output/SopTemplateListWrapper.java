package com.pemda.ekinerjademo.wrapper.output;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
public class SopTemplateListWrapper {
    List<TemplateWrapper> templateList;
    List<TemplateWrapper> notTemplateList;

    public SopTemplateListWrapper() {
    }
    public SopTemplateListWrapper(
            List<TemplateWrapper> templateList,
            List<TemplateWrapper> notTemplateList) {
        this.templateList = templateList;
        this.notTemplateList = notTemplateList;
    }

    public List<TemplateWrapper> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateWrapper> templateList) {
        this.templateList = templateList;
    }

    public List<TemplateWrapper> getNotTemplateList() {
        return notTemplateList;
    }

    public void setNotTemplateList(List<TemplateWrapper> notTemplateList) {
        this.notTemplateList = notTemplateList;
    }
}
