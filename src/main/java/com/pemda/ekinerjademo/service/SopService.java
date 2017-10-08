package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.Sop;

import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
public interface SopService {
    List<Sop> getSop();
    Sop get(String kdSop);
    void save(Sop sop);
    void update(Sop sop);
    void delete(String kdSop);
}
