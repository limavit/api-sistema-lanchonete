package br.com.techlima.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return lista.stream().map((address) -> AddressMapper.MAPPER.toAddressDto(address)).collect(Collectors.toList());
	}
	
	public AddressDto findById(Long id) {
		logger.info("Retornando Address de Id: " + id);
		return addressMapper.toAddressDto(addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem endereços para listar")));
	}
	public AddressDto createAddress(AddressDto addressDto) {
		logger.info("Criando usuaário");
		Address address = addressMapper.toAddress(addressDto);
		return addressMapper.toAddressDto(addressRepository.save(address));
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
		return dto;
	}
	public void deleteAddress(Long id) {
		var entity = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
		addressRepository.delete(entity);
		logger.info("Endereço excluído com sucesso");
	}

}
