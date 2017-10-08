package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
public class SopUraianTugasJabatanListWrapper {
    private List<SopWrapper> currentSopList;
    private List<SopWrapper> notSopList;

    public SopUraianTugasJabatanListWrapper() {
    }
    public SopUraianTugasJabatanListWrapper(
            List<SopWrapper> currentSopList,
            List<SopWrapper> notSopList) {
        this.currentSopList = currentSopList;
        this.notSopList = notSopList;
    }

    public List<SopWrapper> getCurrentSopList() {
        return currentSopList;
    }

    public void setCurrentSopList(List<SopWrapper> currentSopList) {
        this.currentSopList = currentSopList;
    }

    public List<SopWrapper> getNotSopList() {
        return notSopList;
    }

    public void setNotSopList(List<SopWrapper> notSopList) {
        this.notSopList = notSopList;
    }
}
