package com.banking.audit.service;

import com.banking.audit.model.AuditDto;

public interface AuditService {

	AuditDto getAuditById(long id);

	AuditDto createAudit(AuditDto auditDto);
}
