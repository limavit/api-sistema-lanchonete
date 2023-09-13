package br.com.techlima.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.techlima.dto.AddressDto;
import br.com.techlima.entities.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressMapper MAPPER = Mappers.getMapper(AddressMapper.class);
	public AddressDto toAddressDto(Address address);
	public Address toAddress(AddressDto addressDto);
}
