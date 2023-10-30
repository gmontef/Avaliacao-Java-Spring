package br.com.gms.AppPessoas.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.gms.AppPessoas.model.Contato;

public interface ContatoServiceInterface {

	Contato save(Long pessoa_id, Contato contato);
	Optional<Contato> getById(Long id);
	List<Contato> getAll();
	Contato update(Contato contato);
	void delete(Long id);
	Contato save (Contato contato);
}
