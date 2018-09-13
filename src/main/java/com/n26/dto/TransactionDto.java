package com.n26.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6590059908942459790L;

	private BigDecimal amount;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private OffsetDateTime timestamp;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
