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

@Service
@Slf4j
public class FinancialCompanyService {

	@Autowired
	private IFinancialcompaniesRepository financialcompaniesRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<FinancialCompanyDTO> getFinancialCompanies() {
		
		List<FinancialCompanyDTO> financialCompanies = new ArrayList<>();
		for(Financialcompanies f : financialcompaniesRepository.findAll()) {
			if(f!=null)
				financialCompanies.add(modelMapper.map(f, FinancialCompanyDTO.class));
		}
		return financialCompanies;
	}
}
