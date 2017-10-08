package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 08/10/17.
 */
public class SopWrapper {
    private String kdSop;
    private String sop;

    public SopWrapper() {
    }
    public SopWrapper(String kdSop, String sop) {
        this.kdSop = kdSop;
        this.sop = sop;
    }

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

    public String getSop() {
        return sop;
    }

    public void setSop(String sop) {
        this.sop = sop;
    }
}
