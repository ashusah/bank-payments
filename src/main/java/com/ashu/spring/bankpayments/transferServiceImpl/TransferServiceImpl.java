package com.ashu.spring.bankpayments.transferServiceImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ashu.spring.bankpayments.entity.BankAccount;
import com.ashu.spring.bankpayments.exception.UnsupportedInputException;
import com.ashu.spring.bankpayments.transferService.ItransferService;

@Component
public class TransferServiceImpl implements ItransferService {

	@Override
	public synchronized void doTransfer( BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {
		// TODO Auto-generated method stub
		if((fromAccount.getBalance().compareTo(amount)<0))
			throw new UnsupportedInputException("Balance Insufficient");
		fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
		
	}

}
