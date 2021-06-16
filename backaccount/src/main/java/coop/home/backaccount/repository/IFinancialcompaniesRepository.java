package coop.home.backaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backaccount.repository.entity.Financialcompanies;

public interface IFinancialcompaniesRepository extends JpaRepository<Financialcompanies, Long> {

}
