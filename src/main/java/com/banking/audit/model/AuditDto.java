package com.banking.audit.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a audit in banking application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {

	@ApiModelProperty(notes = "Unique identifier of the Audit.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Type of Event.", example = "TransactionCompletedEvent", required = true)
	@NotBlank
	private String eventType;

	@ApiModelProperty(notes = "Log of the event.", required = true)
	@NotBlank
	private String eventLog;

	@ApiModelProperty(notes = "Unique identifier of the Transaction.", example = "1", required = true)
	@NotNull
	private Long transactionReference;

	@ApiModelProperty(notes = "Event time.", required = true)
	@NotNull
	private LocalDateTime eventTime;
}
