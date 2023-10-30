package br.com.gms.AppPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gms.AppPessoas.model.Contato;
import br.com.gms.AppPessoas.repository.ContatoRepository;
import br.com.gms.AppPessoas.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface{
	
	private ContatoRepository contatoRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}
	

	@Override
	public Contato save(Contato contato){
		return contatoRepository.save(contato);
		
	}

	@Override
	public Optional<Contato> getById(Long id) {
		return contatoRepository.findById(id);
	}

	@Override
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}

	@Override
	public Contato update(Contato contato) {
		Optional<Contato> upContato = contatoRepository.findById(contato.getId());
		
		        //atualizar
				if(upContato.isPresent()) {
					Contato newContato = upContato.get();
					newContato.setTipocontato(contato.getTipocontato());
					newContato.setContato(contato.getContato());
					newContato.setPessoa(contato.getPessoa());
					return contatoRepository.save(newContato);
				}
		return contato;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
