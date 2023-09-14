package br.com.techlima.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.techlima.controllers.AddressController;
import br.com.techlima.controllers.UserController;
import br.com.techlima.dto.AddressDto;
import br.com.techlima.entities.Address;
import br.com.techlima.exceptions.ResourceNotFoundException;
import br.com.techlima.mapper.AddressMapper;
import br.com.techlima.repositories.AddressRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	AddressMapper addressMapper;
	private Logger logger = Logger.getLogger(Address.class.getName());

	public List<AddressDto> findAll() {
		logger.info("Listando todos os endereços.");
		List<Address> lista = addressRepository.findAll();
		var addressDto =  lista.stream().map((address) -> AddressMapper.MAPPER.toAddressDto(address)).collect(Collectors.toList());
		addressDto.stream().forEach(l -> l.add(linkTo(methodOn(AddressController.class).getAddress(l.getKey())).withSelfRel()));
		return addressDto;
	}
	
	public AddressDto findById(Long id) {
		logger.info("Retornando Address de Id: " + id);
		var addresDto = addressMapper.toAddressDto(addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem endereços para listar")));
		addresDto.add(linkTo(methodOn(AddressController.class).getAddress(addresDto.getKey())).withSelfRel());
		return addresDto;
	}
	public AddressDto createAddress(AddressDto addressDto) {
		logger.info("Criando endereco");
		Address address = addressMapper.toAddress(addressDto);
		var dto = addressMapper.toAddressDto(addressRepository.save(address));
		dto.add(linkTo(methodOn(AddressController.class).getAddress(dto.getKey())).withSelfRel());
		return dto;
	}
	public AddressDto updateAddress(AddressDto addressDto) {
		var obj = addressRepository.findById(addressDto.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
		obj.setStreet(addressDto.getStreet());
		obj.setNumber(addressDto.getNumber());
		obj.setCity(addressDto.getCity());
		obj.setUf(addressDto.getUf());
		obj.setCep(addressDto.getCep());
		var dto = addressMapper.toAddressDto(obj);
		logger.info("Endereço atualizado com sucesso");
		dto.add(linkTo(methodOn(AddressController.class).getAddress(dto.getKey())).withSelfRel());
		return dto;
	}
	public void deleteAddress(Long id) {
		var entity = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
		addressRepository.delete(entity);
		logger.info("Endereço excluído com sucesso");
	}

}
