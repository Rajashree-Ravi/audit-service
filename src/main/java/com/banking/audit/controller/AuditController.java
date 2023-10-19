package com.banking.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.audit.exception.BankingException;
import com.banking.audit.model.AuditDto;
import com.banking.audit.service.AuditService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(produces = "application/json", value = "Operations pertaining to manage audit in banking application")
@RequestMapping("/api/audit")
public class AuditController {

	@Autowired
	AuditService auditService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve specific audit with the specified audit id", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved audit with the audit id"),
			@ApiResponse(code = 404, message = "Audit with specified audit id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<AuditDto> getAuditById(@PathVariable("id") long id) {

		AuditDto audit = auditService.getAuditById(id);
		if (audit != null)
			return new ResponseEntity<>(audit, HttpStatus.OK);
		else
			throw new BankingException("audit-not-found", String.format("Audit with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping("/logActivity")
	@ApiOperation(value = "Create a new audit", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created a audit"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<AuditDto> createAudit(@RequestBody AuditDto audit) {
		return new ResponseEntity<>(auditService.createAudit(audit), HttpStatus.CREATED);
	}
}
