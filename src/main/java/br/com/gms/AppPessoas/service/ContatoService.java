package br.com.gms.AppPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gms.AppPessoas.model.Contato;
import br.com.gms.AppPessoas.model.Pessoa;
import br.com.gms.AppPessoas.repository.ContatoRepository;
import br.com.gms.AppPessoas.repository.PessoaRepository;
import br.com.gms.AppPessoas.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface{
	
	private ContatoRepository contatoRepository;
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
		this.contatoRepository = contatoRepository;
		this.pessoaRepository = pessoaRepository;
	}
	

	@Override
	public Contato save(Long pessoa_id, Contato contato){
	    Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoa_id);
	    if (pessoaOptional.isPresent()) {
	        Pessoa pessoa = pessoaOptional.get();
	        contato.setPessoa(pessoa);
	        return contatoRepository.save(contato);
	    } else {
	        throw new RuntimeException("Pessoa com ID " + pessoa_id + " não encontrada");
	    }
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
					newContato.setId(contato.getId());
					newContato.setTipocontato(contato.getTipocontato());
					newContato.setContato(contato.getContato());
					newContato.setPessoa(contato.getPessoa());
					return contatoRepository.save(newContato);
				}
		return contato;
	}

	@Override
	public void delete(Long id) {
		contatoRepository.deleteById(id);
		
	}


	@Override
	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}

}
