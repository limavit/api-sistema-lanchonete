package br.com.techlima.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.techlima.dto.UserDto;
import br.com.techlima.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
	@Mapping(target = "key", source = "user.id")
	public UserDto toUserDto(User user) ;
	@Mapping(target = "id", source = "userDto.key")
	public User toUser(UserDto userDto);

}
