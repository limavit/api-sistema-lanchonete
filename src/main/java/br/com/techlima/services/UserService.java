package br.com.techlima.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techlima.dto.UserDto;
import br.com.techlima.entities.User;
import br.com.techlima.exceptions.ResourceNotFoundException;
import br.com.techlima.mapper.UserMapper;
import br.com.techlima.repositories.AddressRepository;
import br.com.techlima.repositories.UserRepository;
import br.com.techlima.utils.Validations;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserMapper userMapper;
	private Logger logger = Logger.getLogger(UserService.class.getName());

	Validations valida = new Validations();

	public List<UserDto> findAll() {
		logger.info("Listando todos os usuários");
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> UserMapper.MAPPER.toUserDto(user)).collect(Collectors.toList());

	}

	public UserDto findByid(Long id) {
		logger.info("Listando usuário de id: " + id);
		return userMapper.toUserDto(userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem registros para listar com este ID")));
	}

	public UserDto createPerson(UserDto userDto) {
		

		if (validaAddress(userDto.getIdAddress())) {
			logger.info("Não foi possivel criar usuário por falta de FK t_address");
			new ResourceNotFoundException("id do Endereço do Usuário não encontrado na tabela de endereços");
		}
		logger.info("Criando usuário");
		User user = userMapper.toUser(userDto);
		return userMapper.toUserDto(userRepository.save(user));
	}

	public UserDto updateUser(UserDto userDto) {
		var obj = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		obj.setFirstName(userDto.getFirstName());
		obj.setLastName(userDto.getLastName());
		obj.setIdAddress(userDto.getIdAddress());
		obj.setDocument(userDto.getDocument());
		var dto = userMapper.toUserDto(userRepository.save(obj));
		logger.info("Usuário atualizado com sucesso");
		return dto;

	}

	public void deleteUser(Long id) {
		var entity = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem registros para listar com este ID"));
		userRepository.delete(entity);
		logger.info("Usuário excluído com sucesso");
	}

	private boolean validaAddress(Long idAddress) {
		return addressRepository.findById(idAddress).isEmpty();
	}

}
