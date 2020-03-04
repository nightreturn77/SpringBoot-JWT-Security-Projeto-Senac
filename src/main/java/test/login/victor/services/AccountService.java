package test.login.victor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import test.login.victor.dto.AccountNewDTO;
import test.login.victor.entities.Account;
import test.login.victor.entities.enums.Perfil;
import test.login.victor.repositories.AccountRepository;
import test.login.victor.resources.exceptions.AuthorizationException;
import test.login.victor.security.UserSS;

@Service
public class AccountService {
	
	
	@Autowired
	private AccountRepository ar; 
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Account> findAll(){ 
		return ar.findAll();
	}
	
	
	public Account findById(Integer id) { 
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Account> obj = ar.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Account.class.getName()));
	}
	
	
	public Account insert(Account obj) { 
		return ar.save(obj);
	}
	
	public Account fromDTO(AccountNewDTO objDto) {
		Account ac = new Account(null, objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
	
		return ac;
	}
	

}
