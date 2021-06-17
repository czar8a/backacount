package coop.home.bankaccount.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.home.bankaccount.dto.FinancialCompanyDTO;
import coop.home.bankaccount.repository.IFinancialcompaniesRepository;
import coop.home.bankaccount.repository.entity.Financialcompanies;
import lombok.extern.slf4j.Slf4j;

public interface FinancialCompanyService {


	public List<FinancialCompanyDTO> getFinancialCompanies() ;
}
