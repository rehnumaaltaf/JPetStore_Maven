package com.olam.score.authorizationconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.authorizationconfig.domain.AuthAppUser;

@Repository
public interface TraderRepository extends JpaRepository<AuthAppUser, Integer> {

}
