package coop.home.backacount.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.home.backacount.dto.FinancialCompanyDTO;
import coop.home.backacount.repository.IFinancialcompaniesRepository;
import coop.home.backacount.repository.entity.Financialcompanies;
import lombok.extern.slf4j.Slf4j;

public interface FinancialCompanyService {


	public List<FinancialCompanyDTO> getFinancialCompanies() ;
}
