package com.n26.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Statistic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2117687884416559583L;

	private BigDecimal sum;

	private BigDecimal avg;

	private BigDecimal max;

	private BigDecimal min;

	private long count;


	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
