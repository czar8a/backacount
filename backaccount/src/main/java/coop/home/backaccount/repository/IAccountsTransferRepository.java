package coop.home.backaccount.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backaccount.repository.entity.Accounttransfers;
import coop.home.backaccount.repository.entity.AccounttransfersPK;


public interface IAccountsTransferRepository extends JpaRepository<Accounttransfers, AccounttransfersPK> {
	
	Page<Accounttransfers> findAll(Specification<Accounttransfers> especificacion, Pageable rango);

}
