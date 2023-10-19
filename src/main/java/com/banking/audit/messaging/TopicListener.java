package com.banking.audit.messaging;

import java.time.LocalDateTime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.banking.audit.model.AuditDto;
import com.banking.audit.model.TransactionDto;
import com.banking.audit.service.AuditService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

	@Value("${consumer.config.success.topic.name}")
	private String successTopicName;

	@Value("${consumer.config.fail.topic.name}")
	private String failedTopicName;

	@Autowired
	AuditService auditService;

	@KafkaListener(topics = "${consumer.config.success.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeSuccessTransaction(ConsumerRecord<String, TransactionDto> payload) {
		log.info("Topic : {}", successTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Transaction : {}", payload.value());

		TransactionDto completedTransaction = payload.value();
		
		AuditDto audit = new AuditDto();
		audit.setEventType(successTopicName);
		audit.setTransactionReference(completedTransaction.getId());
		audit.setEventTime(LocalDateTime.now());
		audit.setEventLog("Transaction successful");
		
		auditService.createAudit(audit);
	}

	@KafkaListener(topics = "${consumer.config.fail.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeFailedTransaction(ConsumerRecord<String, TransactionDto> payload) {
		log.info("Topic : {}", failedTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Transaction : {}", payload.value());

		TransactionDto failedTransaction = payload.value();
		
		AuditDto audit = new AuditDto();
		audit.setEventType(failedTopicName);
		audit.setTransactionReference(failedTransaction.getId());
		audit.setEventTime(LocalDateTime.now());
		audit.setEventLog("Transaction failed for the amount:  "+ failedTransaction.getAmount());
		
		auditService.createAudit(audit);
	}

}