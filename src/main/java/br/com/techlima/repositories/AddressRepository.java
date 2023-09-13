package br.com.techlima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlima.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
