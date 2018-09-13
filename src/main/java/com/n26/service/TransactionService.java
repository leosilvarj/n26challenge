package com.n26.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.model.Transaction;
import com.n26.repository.Repository;

@Service
public class TransactionService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4768480662871996774L;

	@Autowired
	Repository transactionRepository;
	
	
	public boolean addTransaction(Transaction transaction) {
		return transactionRepository.addTransaction(transaction);
	}


	public void removeAllTransaction() {
		transactionRepository.removeAllTransaction();
	}
}
