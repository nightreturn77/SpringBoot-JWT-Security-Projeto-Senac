package test.login.victor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.login.victor.entities.Account;
import test.login.victor.entities.Membros;
import test.login.victor.repositories.MembrosRepository;

@Service
public class MembrosService {
	
	@Autowired
	MembrosRepository repo; 

	
	public List<Membros> findAll(){ 
		return repo.findAll(); 
	}

	
	public Membros findById(Long id) { 
		Optional<Membros> obj = repo.findById(id);
		return obj.get();
	}
	
	
	public Membros insert(Membros obj) { 
		return repo.save(obj);
	}
	
	public void delete(Long id) { 
		repo.deleteById(id);
	}
	
	
	
	public Membros update(Long id, Membros obj) { 
		Membros entity = repo.getOne(id);
		//System.out.println(entity.getNome());
		//System.out.println(obj.getNome());
		updateData(entity, obj);
		return repo.save(entity);
	}
	
	public void updateData(Membros entity, Membros obj) { 
		System.out.println("Entidade: "+ entity.getNome());
		System.out.println("Obj: "+ obj.getNome());
		entity.setNome(obj.getNome());
		entity.setNascimento(obj.getNascimento());
		entity.setPin(obj.getPin());
		entity.setSexo(obj.getSexo());
		entity.setAccount(obj.getAccount());
		
	}
	
}
