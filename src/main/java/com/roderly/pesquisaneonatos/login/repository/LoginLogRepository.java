package com.roderly.pesquisaneonatos.login.repository;


import com.roderly.pesquisaneonatos.login.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
}