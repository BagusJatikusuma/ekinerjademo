package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.LembarDisposisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@Repository
public interface LembarDisposisiDao extends JpaRepository<LembarDisposisi, String> {
    @Query("select l from LembarDisposisi l where l.path LIKE concat(?1,'%') ")
    List<LembarDisposisi> findLembarDisposisiTree(String parentPath);
}
