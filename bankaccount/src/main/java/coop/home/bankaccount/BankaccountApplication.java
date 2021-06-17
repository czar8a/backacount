package coop.home.bankaccount;

import java.math.BigInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import coop.home.bankaccount.repository.IDocumenttypesRepository;
import coop.home.bankaccount.repository.IFinancialcompaniesRepository;
import coop.home.bankaccount.repository.IFinancialusersRepository;
import coop.home.bankaccount.repository.entity.Documenttypes;
import coop.home.bankaccount.repository.entity.Financialcompanies;
import coop.home.bankaccount.repository.entity.Financialusers;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BankaccountApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext configurableApplicationContext =
		SpringApplication.run(BankaccountApplication.class, args);
		
		IFinancialcompaniesRepository financialcompaniesRepository = 
				configurableApplicationContext.getBean(IFinancialcompaniesRepository.class);

//		TODO: clean code advise: mark for deletion (obsolete or testing comments).
//		Financialcompanies financialcompanies = new Financialcompanies(BigDecimal.valueOf(1),"citiBank");
//		

		for(Financialcompanies f: financialcompaniesRepository.findAll())
			log.info(f.getName());
		
		IDocumenttypesRepository documenttypesRepository = 
				configurableApplicationContext.getBean(IDocumenttypesRepository.class);
		
		for(Documenttypes f: documenttypesRepository.findAll())
			log.info(f.getTypename());
		
		
		IFinancialusersRepository usersRepository = 
				configurableApplicationContext.getBean(IFinancialusersRepository.class);
		
		for(Financialusers f: usersRepository.findAll())
			log.info(f.getFinancialusersPK().getLoginuser());
		
		
		log.info(usersRepository.findByFinancialusersPK_Loginuser("czar8a").getPass());
		
		log.info(usersRepository.findByFinancialusersPK_idfinancialcompanyAndFinancialusersPK_Loginuser(BigInteger.valueOf(1),"czar8a").getIddocument());
	}

}
