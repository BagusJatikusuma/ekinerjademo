package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Repository
public interface UraianTugasDao extends JpaRepository<UraianTugas, Long> {
    List<UraianTugas> findAllByOrderByKdUrtugDesc();
    UraianTugas findByKdUrtug(String kdUrtug);
    void deleteByKdUrtug(String kdUrtug);
}
