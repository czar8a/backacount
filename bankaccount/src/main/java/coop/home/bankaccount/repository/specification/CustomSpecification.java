package coop.home.bankaccount.repository.specification;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import coop.home.bankaccount.repository.entity.Accounts;
import coop.home.bankaccount.repository.entity.Accounttransfers;

public class CustomSpecification {

	
	private CustomSpecification() {
	}
	
	
	public static Specification<Accounttransfers> filterByUserAccounts(BigInteger idfinancialcompany, String loginuser) {
		return new Specification<Accounttransfers>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Accounttransfers> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<Accounts,Accounttransfers> transferSourceAccountJoin = root.join("accounts");
				Join<Accounts,Accounttransfers> transferReceptionAccountJoin = root.join("accounts1");
				
				Predicate sourceAccountIdfinancialcompany = cb.equal(transferSourceAccountJoin.get("accountsPK").get("usersidfinancialcompany"), idfinancialcompany);
				Predicate sourceAccountLoginuser = cb.equal(transferSourceAccountJoin.get("financialusers").get("financialusersPK").get("loginuser"), loginuser);
				Predicate sourceAccountPredicate = cb.and(sourceAccountIdfinancialcompany , sourceAccountLoginuser);
				
				Predicate receptionAccountIdfinancialcompany = cb.equal(transferReceptionAccountJoin.get("accountsPK").get("usersidfinancialcompany"), idfinancialcompany);
				Predicate receptionAccountLoginuser = cb.equal(transferReceptionAccountJoin.get("financialusers").get("financialusersPK").get("loginuser"), loginuser);
				Predicate receptionAccountPredicate = cb.and(receptionAccountIdfinancialcompany , receptionAccountLoginuser);
				
				return cb.or(sourceAccountPredicate , receptionAccountPredicate);
			}
		};
	}

}
