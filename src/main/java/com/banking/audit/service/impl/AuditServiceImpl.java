package com.banking.audit.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.audit.entity.Audit;
import com.banking.audit.model.AuditDto;
import com.banking.audit.repository.AuditRepository;
import com.banking.audit.service.impl.AuditServiceImpl;
import com.banking.audit.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);

	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AuditDto getAuditById(long id) {
		Optional<Audit> audit = auditRepository.findById(id);
		return (audit.isPresent() ? mapper.map(audit.get(), AuditDto.class) : null);
	}

	@Override
	public AuditDto createAudit(AuditDto auditDto) {
		Audit audit = mapper.map(auditDto, Audit.class);
		return mapper.map(auditRepository.save(audit), AuditDto.class);
	}

}
