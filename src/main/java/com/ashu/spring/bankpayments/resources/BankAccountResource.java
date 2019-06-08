package com.ashu.spring.bankpayments.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ashu.spring.bankpayments.DTO.transactionDTO;
import com.ashu.spring.bankpayments.entity.BankAccount;
import com.ashu.spring.bankpayments.exception.AccountNotFoundException;
import com.ashu.spring.bankpayments.exception.UnsupportedInputException;
import com.ashu.spring.bankpayments.persistence.BankAccountRepository;
import com.ashu.spring.bankpayments.transferServiceImpl.TransferServiceImpl;

@RestController
public class BankAccountResource {
	private static final Logger log = LoggerFactory.getLogger(BankAccountResource.class);
	@Autowired
	private  BankAccountRepository accountRepo;
	
	@Autowired
	private TransferServiceImpl transferService;

	@GetMapping("/accounts")
	public ResponseEntity<List<BankAccount>> returnAccounts(){
		log.info("findAll service is started");
		List<BankAccount> accounts=accountRepo.findAll();
		log.info("findAll service is stopped");
		if(accounts.isEmpty())
			throw new AccountNotFoundException();
		return ResponseEntity.status(HttpStatus.OK).body(accounts);

	}
	@GetMapping("/account/{id}")
	public ResponseEntity<Optional<BankAccount>> returnAccount(@PathVariable int id){
		log.info("findById service is started");
		Optional<BankAccount> account=accountRepo.findById(id);
		log.info("findById service is stopped");
		if(account.equals(null))
			throw new AccountNotFoundException();
		return ResponseEntity.status(HttpStatus.OK).body(account);

	}


	@GetMapping("/account/name/{name}")
	public ResponseEntity<BankAccount> returnAccount(@PathVariable String name){
		log.info("findById service is started");
		BankAccount account=accountRepo.findByName(name);
		log.info("findById service is stopped");
		if(account.equals(null))
			throw new AccountNotFoundException();
		return ResponseEntity.status(HttpStatus.OK).body(account);

	}


	@PostMapping("/account")
	public ResponseEntity<BankAccount> createUser(@RequestBody BankAccount account) {
		BankAccount savedAccount=accountRepo.save(account);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(account.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

		@PutMapping("/account/transfer")
		public synchronized ResponseEntity<List<BankAccount>> doTransfer(@RequestBody transactionDTO transaction) {
			BankAccount fromAccount=accountRepo.findByName(transaction.getSourceName());
			BankAccount toAccount=accountRepo.findByName(transaction.getDestName());
			if((fromAccount.getBalance().compareTo(transaction.getTransferAmount())<0))
				throw new UnsupportedInputException("Balance Insufficient");
			fromAccount.setBalance(fromAccount.getBalance().subtract(transaction.getTransferAmount()));
			toAccount.setBalance(toAccount.getBalance().add(transaction.getTransferAmount()));
			accountRepo.save(fromAccount);
			accountRepo.save(toAccount);
			
			
			List<BankAccount> accounts=accountRepo.findAll();
			log.info("findAll service is stopped");
			if(accounts.isEmpty())
				throw new AccountNotFoundException();
			return ResponseEntity.status(HttpStatus.OK).body(accounts);

			
			

		}


	}
