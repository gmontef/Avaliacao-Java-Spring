package br.com.gms.AppPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gms.AppPessoas.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
