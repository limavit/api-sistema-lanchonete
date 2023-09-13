package br.com.techlima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techlima.dto.AddressDto;
import br.com.techlima.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService addreService = new AddressService();
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AddressDto> findAll(){
		return addreService.findAll();
	}
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AddressDto getAddress(@PathVariable(value = "id") Long id) {
		return addreService.findById(id);
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AddressDto createAddress(@RequestBody AddressDto addressDto) {
		return addreService.createAddress(addressDto);
	}
	@PutMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AddressDto updateAddress(AddressDto addressDto) {
		return addreService.updateAddress(addressDto);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id){
		addreService.deleteAddress(id);
		return ResponseEntity.noContent().build();
	}

}
