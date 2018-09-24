package com.olam.score.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterLanguage;

@Repository
public interface LanguageRefRepository extends JpaRepository<MasterLanguage, Integer>{

}
