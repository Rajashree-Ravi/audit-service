package com.banking.audit.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit")
public class Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String eventType;

	@NotBlank
	private String eventLog;

	@NotNull
	private Long transactionReference;

	@NotNull
	private LocalDateTime eventTime;

	public Audit updateWith(Audit audit) {
		return new Audit(this.id, audit.eventType, audit.eventLog, audit.transactionReference, audit.eventTime);
	}
}
