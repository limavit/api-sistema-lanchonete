package br.com.techlima.utils;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.techlima.repositories.AddressRepository;
import br.com.techlima.repositories.UserRepository;

public class Validations {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	
	public boolean validaAddress(Long id) {		
		return addressRepository.findById(id).isEmpty();		
	}

}
