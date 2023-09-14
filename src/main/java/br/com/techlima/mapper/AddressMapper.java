package br.com.techlima.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.techlima.dto.AddressDto;
import br.com.techlima.entities.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressMapper MAPPER = Mappers.getMapper(AddressMapper.class);
	@Mapping(target = "key", source = "address.id")
	public AddressDto toAddressDto(Address address);
	@Mapping(target = "id", source = "addressDto.key")
	public Address toAddress(AddressDto addressDto);
}
