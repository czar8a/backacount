package coop.home.backacount.repository.specification;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import coop.home.backacount.repository.entity.Accounts;
import coop.home.backacount.repository.entity.Accounttransfers;

public class CustomSpecification {

	
	private CustomSpecification() {
	}
	
	
	public static Specification<Accounttransfers> filterByUserAccounts(BigInteger idfinancialcompany, String loginuser) {
		return new Specification<Accounttransfers>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Accounttransfers> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<Accounts,Accounttransfers> transferJoin = root.join("accounts");
				
				return cb.and(cb.equal(transferJoin.get("accountsPK").get("usersidfinancialcompany"), idfinancialcompany),
						cb.equal(transferJoin.get("financialusers").get("financialusersPK").get("loginuser"), loginuser));
			}
		};
	}

}
