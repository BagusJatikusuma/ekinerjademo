package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bayu on 22/12/17.
 */
public class TelaahanStaffHistoryWrapper {
    private String kdLaporan;
    private String createdDate;
    private Integer statusBaca;

    public TelaahanStaffHistoryWrapper() {
    }

    public TelaahanStaffHistoryWrapper(String kdLaporan, String createdDate, Integer statusBaca) {
        this.kdLaporan = kdLaporan;
        this.createdDate = createdDate;
        this.statusBaca = statusBaca;
    }

    public String getKdLaporan() {
        return kdLaporan;
    }

    public void setKdLaporan(String kdLaporan) {
        this.kdLaporan = kdLaporan;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
