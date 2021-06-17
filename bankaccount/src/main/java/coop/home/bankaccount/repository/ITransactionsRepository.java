package coop.home.bankaccount.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.bankaccount.repository.entity.Transactions;
import coop.home.bankaccount.repository.entity.TransactionsPK;


public interface ITransactionsRepository extends JpaRepository<Transactions, TransactionsPK> {
	
	
	public List<Transactions> findByTransactionsPK_accountidfinancialcompanyAndTransactionsPK_idaccount(BigInteger accountidfinancialcompany,String idaccount);

}
