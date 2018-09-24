/**
 * 
 */
package com.olam.score.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.limit.domain.MasterLimitAlertLevel;


/**
 * @author umme.hani01
 *
 */
@Repository
public interface MasterLimitAlertLevelRepository extends JpaRepository<MasterLimitAlertLevel, Integer>{

}
