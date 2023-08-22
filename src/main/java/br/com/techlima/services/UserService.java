package br.com.techlima.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techlima.entities.User;
import br.com.techlima.exceptions.ResourceNotFoundException;
import br.com.techlima.repositories.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	public List<User> findAll(){
		logger.info("Listando todos os usuários");
		return userRepository.findAll();
		
	}
	public User findByid(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sem registros para listar com este ID"));
	}
	public User createPerson(User user) {
		logger.info("Criando usuário");
		return userRepository.save(user);
	}
	public User updateUser(User user) {
		
		var obj = userRepository.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado"));
		obj.setFirstName(user.getFirstName());
		obj.setLastName(user.getLastName());
		obj.setIdAddress(user.getIdAddress());
		obj.setDocument(user.getDocument());
		logger.info("Usuário atualizado com sucesso");
		return userRepository.save(obj);
				
	}
	public void deleteUser(Long id) {
		var entity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sem registros para listar com este ID"));
		userRepository.delete(entity);
		logger.info("Usuário excluído com sucesso");
	}

}
