package coop.home.backacount.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backacount.repository.entity.Financialusers;
import coop.home.backacount.repository.entity.FinancialusersPK;




public interface IFinancialusersRepository extends JpaRepository<Financialusers, FinancialusersPK> {
	
	public Financialusers findByFinancialusersPK_Loginuser(String loginuser);
	
	public List<Financialusers> findByFinancialusersPK_idfinancialcompany(BigInteger idfinancialcompany);
	
	public Financialusers findByFinancialusersPK_idfinancialcompanyAndFinancialusersPK_Loginuser(BigInteger idfinancialcompany, String loginuser);

}
