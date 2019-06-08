package com.ashu.spring.bankpayments.transferService;

import java.math.BigDecimal;

import com.ashu.spring.bankpayments.entity.BankAccount;

public interface ItransferService {
	public void doTransfer(BankAccount fromAccount,BankAccount toAccount,BigDecimal amount);

}
