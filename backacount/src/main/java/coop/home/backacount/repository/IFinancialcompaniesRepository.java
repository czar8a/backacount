package coop.home.backacount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backacount.repository.entity.Financialcompanies;

public interface IFinancialcompaniesRepository extends JpaRepository<Financialcompanies, Long> {

}
