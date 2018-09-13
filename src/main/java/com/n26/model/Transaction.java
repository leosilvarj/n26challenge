package com.n26.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Transaction implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5021904663500296654L;

	private BigDecimal amount;

	private Long timestamp;

	public Transaction() {};

	public Transaction(BigDecimal amount, Long timestamp) {
	    this.setAmount(amount);
	    this.setTimestamp(timestamp);
	}

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
