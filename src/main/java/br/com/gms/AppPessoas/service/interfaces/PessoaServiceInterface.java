package br.com.gms.AppPessoas.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.gms.AppPessoas.model.Pessoa;

public interface PessoaServiceInterface {
	Pessoa save(Pessoa pessoa);
	Optional<Pessoa> getById(Long Id);
	List<Pessoa> getAll();
	Pessoa update(Pessoa pessoa);
	void delete(Long id);

}
