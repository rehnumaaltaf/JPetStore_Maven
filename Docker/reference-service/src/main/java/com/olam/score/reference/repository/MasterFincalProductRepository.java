package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterFincalProduct;

@Repository
public interface MasterFincalProductRepository extends JpaRepository<MasterFincalProduct, Integer>{
	@Query("select mfp from MasterFincalProduct mfp where mfp.fkFinCalMappingtId.pkFinCalMappingId=:fkFinCalMapId")
	public List<MasterFincalProduct> getFinCalProdList(@Param("fkFinCalMapId") Integer fkFinCalMapId);
	
	@Query("delete from MasterFincalProduct mfp where mfp.fkFinCalMappingtId.pkFinCalMappingId=:fkFinCalMapId")
	@Modifying
	public void deleteByFinCalMapId(@Param("fkFinCalMapId") Integer fkFinCalMapId);
}
