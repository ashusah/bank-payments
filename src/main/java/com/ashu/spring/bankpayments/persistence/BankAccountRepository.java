package com.ashu.spring.bankpayments.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ashu.spring.bankpayments.entity.BankAccount;

public interface BankAccountRepository extends BaseRepository<BankAccount> {
	Optional<BankAccount> findById(Integer id);
	
	@SuppressWarnings("unchecked")
	BankAccount save(BankAccount entity);
	
	List<BankAccount> findAll();
	
	boolean existsById(Integer id);
}
