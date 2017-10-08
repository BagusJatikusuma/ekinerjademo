package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplate;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@Repository
public interface SopTemplateDao extends JpaRepository<SopTemplate, String> {
    List<SopTemplate> findBySopTemplateId_KdSop(String kdSop);
    SopTemplate findBySopTemplateId(SopTemplateId sopTemplateId);
    void deleteBySopTemplateId(SopTemplateId sopTemplateId);
}
