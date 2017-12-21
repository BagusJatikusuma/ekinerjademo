package com.pemda.ekinerjademo.repository.bismarepository;

import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 21/12/17.
 */
@Repository
public interface TkdUnkDao extends JpaRepository<TkdUnk, String> {
}
