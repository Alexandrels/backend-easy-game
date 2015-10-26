package br.com.easygame.entity;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipe")
public class Equipe implements Serializable {

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "equipe_has_jogador", joinColumns = {
			@JoinColumn(referencedColumnName = "id_equipe") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "id_jogador") })
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	public Equipe() {
		// TODO Auto-generated constructor stub
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

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public void adicionarJogador(Jogador jogador) {
		jogadores.add(jogador);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipe other = (Equipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("id= ").append(id).append(" nome= ").append(nome)
				.append(" jogadores=[ ");
		for (Jogador jogador : jogadores) {
			builder.append(jogador.getId()).append(",");
		}
		builder.append("]");

		return builder.toString();
	}

	public static Equipe toEquipe(String json) {
		Equipe equipe = new Equipe();

		JsonReader jsonReader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = jsonReader.readObject();  
		if (!jsonObject.containsKey("nome")) {
			throw new IllegalArgumentException("Atributo 'nome' é obrigatório");
		}
		String nome = jsonObject.getString("nome");

		equipe.setNome(nome);
		equipe.setJogadores(new ArrayList<Jogador>());
		if (!jsonObject.getJsonArray("jogadores").isEmpty()) {
			for (JsonValue jogadorJson : jsonObject.getJsonArray("jogadores")) {
				JsonReader reader = Json.createReader(new StringReader(jogadorJson.toString()));
				JsonObject objeto = reader.readObject();
				Jogador jogador = new Jogador(Long.valueOf(objeto.getString("id")));
				equipe.adicionarJogador(jogador);
			}
		}
		return equipe;
	}

}
