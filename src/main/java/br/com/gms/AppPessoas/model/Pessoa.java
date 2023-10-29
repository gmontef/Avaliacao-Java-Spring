package br.com.gms.AppPessoas.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
	     
	    @Column(nullable = false)
	    private String nome;
	    @Column
		private String endereco;
	    @Column
		private Long cep;
	    @Column
		private String cidade;
	    @Column
		private String uf;

		public Pessoa() {}
		public Pessoa(int id, String nome, String endereco, Long cep, String cidade, String uf){
			this.id = id;
			this.nome = nome;
			this.endereco = endereco;
			this.cep = cep;
			this.cidade = cidade;
			this.uf = uf;	
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public Long getCep() {
			return cep;
		}
		public void setCep(Long cep) {
			this.cep = cep;
		}
		public String getCidade() {
			return cidade;
		}
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}
		public String getUf() {
			return uf;
		}
		public void setUf(String uf) {
			this.uf = uf;
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
			Pessoa other = (Pessoa) obj;
			return id == other.id;
		}
		
		
			
}
