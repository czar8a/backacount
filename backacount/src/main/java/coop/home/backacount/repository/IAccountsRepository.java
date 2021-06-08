package coop.home.backacount.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backacount.repository.entity.Accounts;
import coop.home.backacount.repository.entity.AccountsPK;
import coop.home.backacount.repository.entity.Accounttransfers;


public interface IAccountsRepository extends JpaRepository<Accounts, AccountsPK> {

	public List<Accounts> findByAccountsPK_usersidfinancialcompanyAndFinancialusers_FinancialusersPK_loginuser(BigInteger idfinancialcompany, String loginuser);
	
	public Accounts findByAccountsPK_usersidfinancialcompanyAndAccountsPK_idacount(BigInteger idfinancialcompany, String idacount);
	
	
	Page<Accounts> findAll(Specification<Accounts> especificacion, Pageable rango);
}
