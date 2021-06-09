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


public interface FinancialUsersService {


	public List<FinancialUsersDTO> getFinancialUsers(BigInteger idfinancialcompany) ;
}
