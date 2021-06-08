package coop.home.backacount.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import coop.home.backacount.dto.financialusers.FinancialUsersDTO;
import coop.home.backacount.repository.IFinancialusersRepository;
import coop.home.backacount.repository.entity.Financialusers;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FinancialUsersService {

	@Autowired
	private IFinancialusersRepository financialusersRepository;
	
	@Autowired
	private MessageSource mensajes;

	public List<FinancialUsersDTO> getFinancialUsers(BigInteger idfinancialcompany) {
		
		List<FinancialUsersDTO> financialUsers = new ArrayList<>();
		for(Financialusers u : financialusersRepository.findByFinancialusersPK_idfinancialcompany(idfinancialcompany)) {
			if(u!=null) {
				financialUsers.add(new FinancialUsersDTO(u.getIddocument(),
						u.getIddoctype(),
						u.getFinancialcompanies().getIdfinancialcompany(),
						u.getFinancialusersPK().getLoginuser(),
						u.getFinancialusername(),u.getFinancialuserlastname()));
			}
		}
		return financialUsers;
	}
}
