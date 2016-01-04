package br.com.easygame.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import br.com.easygame.entity.Evento;;
@Table(name = "local")
@Entity
public class Local implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "posicao")
	private String posicao;
	@Column(name = "endereco")
	private String endereco;
	@Column(name = "nome_proprietario")
	private String nomeProprietario;
	@ManyToMany(mappedBy = "locais")
	private List<Evento> eventos;

	public Local() {
	}
	

	public Local(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getNomeProprietario() {
		return nomeProprietario;
	}


	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}





	

}
