package coop.home.backacount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.home.backacount.repository.entity.Documenttypes;



public interface IDocumenttypesRepository extends JpaRepository<Documenttypes, String> {

}
