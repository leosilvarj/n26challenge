package com.n26.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.dto.TransactionDto;
import com.n26.exception.DateConvertException;
import com.n26.model.Transaction;
import com.n26.service.TransactionService;

@RestController
public class TransactionController implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2986065492198674295L;

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTransaction(final @Valid @RequestBody TransactionDto transactionDto) {
		Transaction transaction = converteToBo(transactionDto);
		if (transaction == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		if (this.transactionService.addTransaction(transaction)) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/transactions")
	public ResponseEntity<String> deleteTransaction() {
		transactionService.removeAllTransaction();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	private Transaction converteToBo(TransactionDto transaction) {
		Transaction t = new Transaction();
		try {
			t.setAmount(transaction.getAmount());

			t.setTimestamp(transaction.getTimestamp().toInstant().toEpochMilli());
			if (t.getTimestamp().compareTo(System.currentTimeMillis()) > 0) {
			    return null;
			}
		} catch (Exception e) {
			throw new DateConvertException("Date time format error.");
		}

		return t;
	}
}
