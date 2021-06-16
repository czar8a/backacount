package coop.home.backaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backaccount.repository.entity.Documenttypes;



public interface IDocumenttypesRepository extends JpaRepository<Documenttypes, String> {

}
