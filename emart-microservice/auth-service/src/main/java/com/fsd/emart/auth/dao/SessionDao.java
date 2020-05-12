package com.fsd.emart.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.auth.entity.SessionInfo;

@Repository
public interface SessionDao extends JpaRepository<SessionInfo, String>, JpaSpecificationExecutor<SessionInfo> {

	Optional<SessionInfo> findByIdAndSessionKey(String id, String sessionKey);
	
}
