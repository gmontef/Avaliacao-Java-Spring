package br.com.gms.AppPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gms.AppPessoas.model.Pessoa;
import br.com.gms.AppPessoas.repository.PessoaRepository;
import br.com.gms.AppPessoas.service.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface{
	
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> getById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		Optional<Pessoa> upPessoa = pessoaRepository.findById(pessoa.getId());
		
		//atualizar
		if(upPessoa.isPresent()) {
			Pessoa newPessoa = upPessoa.get();
			newPessoa.setNome(pessoa.getNome());
			newPessoa.setEndereco(pessoa.getEndereco());
			newPessoa.setCep(pessoa.getCep());
			newPessoa.setCidade(pessoa.getCidade());
			newPessoa.setUf(pessoa.getUf());
			return pessoaRepository.save(newPessoa);
		
		}	
		return pessoa;
	}

	@Override
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
		
	}

}
