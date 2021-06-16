package coop.home.backaccount.repository.specification;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import coop.home.backaccount.repository.entity.Accounts;
import coop.home.backaccount.repository.entity.Accounttransfers;

public class CustomSpecification {

	
	private CustomSpecification() {
	}
	
	
	public static Specification<Accounttransfers> filterByUserAccounts(BigInteger idfinancialcompany, String loginuser) {
		return new Specification<Accounttransfers>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Accounttransfers> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<Accounts,Accounttransfers> transferSourceAcountJoin = root.join("accounts");
				Join<Accounts,Accounttransfers> transferReceptionAcountJoin = root.join("accounts1");
				
				Predicate sourceAcountIdfinancialcompany = cb.equal(transferSourceAcountJoin.get("accountsPK").get("usersidfinancialcompany"), idfinancialcompany);
				Predicate sourceAcountLoginuser = cb.equal(transferSourceAcountJoin.get("financialusers").get("financialusersPK").get("loginuser"), loginuser);
				Predicate sourceAcountPredicate = cb.and(sourceAcountIdfinancialcompany , sourceAcountLoginuser);
				
				Predicate receptionAcountIdfinancialcompany = cb.equal(transferReceptionAcountJoin.get("accountsPK").get("usersidfinancialcompany"), idfinancialcompany);
				Predicate receptionAcountLoginuser = cb.equal(transferReceptionAcountJoin.get("financialusers").get("financialusersPK").get("loginuser"), loginuser);
				Predicate receptionAcountPredicate = cb.and(receptionAcountIdfinancialcompany , receptionAcountLoginuser);
				
				return cb.or(sourceAcountPredicate , receptionAcountPredicate);
			}
		};
	}

}
