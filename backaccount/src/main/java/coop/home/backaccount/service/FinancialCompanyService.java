package coop.home.backaccount.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.home.backaccount.dto.FinancialCompanyDTO;
import coop.home.backaccount.repository.IFinancialcompaniesRepository;
import coop.home.backaccount.repository.entity.Financialcompanies;
import lombok.extern.slf4j.Slf4j;

public interface FinancialCompanyService {


	public List<FinancialCompanyDTO> getFinancialCompanies() ;
}
