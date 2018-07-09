package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 08/10/17.
 */
public class SopTemplateWrapper {
    private String kdSop;
    private String kdTemplate;

    public SopTemplateWrapper() {
    }
    public SopTemplateWrapper(String kdSop, String kdTemplate) {
        this.kdSop = kdSop;
        this.kdTemplate = kdTemplate;
    }

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

    public String getKdTemplate() {
        return kdTemplate;
    }

    public void setKdTemplate(String kdTemplate) {
        this.kdTemplate = kdTemplate;
    }
}
