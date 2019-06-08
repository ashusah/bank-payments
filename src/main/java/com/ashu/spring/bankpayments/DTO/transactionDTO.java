package com.ashu.spring.bankpayments.DTO;

import java.math.BigDecimal;

public class transactionDTO {
	private String sourceName;
	private String destName;
	private BigDecimal transferAmount;
	
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getDestName() {
		return destName;
	}
	public void setDestName(String destName) {
		this.destName = destName;
	}
	public BigDecimal getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}
	@Override
	public String toString() {
		return "transactionDTO [sourceName=" + sourceName + ", destName=" + destName + ", transferAmount="
				+ transferAmount + "]";
	}
	
	
}
