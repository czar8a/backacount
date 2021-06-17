package coop.home.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.bankaccount.repository.entity.Documenttypes;



public interface IDocumenttypesRepository extends JpaRepository<Documenttypes, String> {

}
