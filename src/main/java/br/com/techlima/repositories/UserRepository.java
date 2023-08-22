package br.com.techlima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlima.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
