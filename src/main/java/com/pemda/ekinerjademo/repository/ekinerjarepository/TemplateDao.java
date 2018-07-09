package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 08/10/17.
 */
@Repository
public interface TemplateDao extends JpaRepository<Template, String>{
    Template findByKdTemplate(String kdTemplate);
    void deleteByKdTemplate(String kdTemplate);
}
