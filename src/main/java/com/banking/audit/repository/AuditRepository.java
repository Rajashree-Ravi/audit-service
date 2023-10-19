package com.banking.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.audit.entity.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {

}
