package coop.home.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.bankaccount.repository.entity.Financialcompanies;

public interface IFinancialcompaniesRepository extends JpaRepository<Financialcompanies, Long> {

}
