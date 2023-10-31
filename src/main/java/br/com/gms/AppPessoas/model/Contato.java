package br.com.gms.AppPessoas.model;

import java.util.Objects;

import br.com.gms.AppPessoas.enums.TipoContato;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	private Pessoa pessoa;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoContato tipocontato;
	
	@Column(nullable = false)
	private Long contato;
	
	public Contato() {}

	public Contato(Long id, Pessoa pessoa, TipoContato tipocontato, Long contato) {
		this.id = id;
		this.pessoa = pessoa;
		this.tipocontato = tipocontato;
		this.contato = contato;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoContato getTipocontato() {
		return tipocontato;
	}

	public void setTipocontato(TipoContato tipocontato) {
		this.tipocontato = tipocontato;
	}

	public Long getContato() {
		return contato;
	}

	public void setContato(Long contato) {
		this.contato = contato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
        Contato other = (Contato) obj;

        return id == other.id;	
    }
}

