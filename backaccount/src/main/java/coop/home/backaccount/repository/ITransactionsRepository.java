package coop.home.backaccount.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backaccount.repository.entity.Transactions;
import coop.home.backaccount.repository.entity.TransactionsPK;


public interface ITransactionsRepository extends JpaRepository<Transactions, TransactionsPK> {
	
	
	public List<Transactions> findByTransactionsPK_accountidfinancialcompanyAndTransactionsPK_idacount(BigInteger accountidfinancialcompany,String idacount);

}
