/**
 * 
 */
package br.com.easygame.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.easygame.enuns.SimNao;
import br.com.easygame.enuns.TipoPosicao;
import br.com.easygame.enuns.TipoUsuario;

/**
 * @author mobilesys.alexandre
 * 
 */
@Table(name = "usuario")
@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "login")
	private String login;
	@Column(name = "senha")
	private String senha;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "tipo")
	private String tipoUsuario;
	@Column(name = "facebook")
	private SimNao facebook = SimNao.NAO;
	@Column(name = "apelido")
	private String apelido;
	@Column(name = "posicao")
	private TipoPosicao posicao;

	public void tipoUsuarioParaString(List<TipoUsuario> tiposUsuario) {
		StringBuilder builder = new StringBuilder();
		for (TipoUsuario tipoUsuario : tiposUsuario) {
			builder.append(tipoUsuario.ordinal()).append(";");
		}
		this.tipoUsuario = builder.toString();
	}

	public List<TipoUsuario> stringParaTipoUsuario() {
		List<TipoUsuario> tiposUsuarios = new ArrayList<>();
		String[] tipo = getTipoUsuario().split(";");
		for (String string : tipo) {
			tiposUsuarios.add(TipoUsuario.values()[Integer.valueOf(string)]);
		}
		return tiposUsuarios;

	}

	public List<TipoUsuario> tiposDoUsuario() {

		return null;

	}

	public SimNao getFacebook() {
		return facebook;
	}

	public void setFacebook(SimNao facebook) {
		this.facebook = facebook;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public TipoPosicao getPosicao() {
		return posicao;
	}

	public void setPosicao(TipoPosicao posicao) {
		this.posicao = posicao;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

	public JsonObject toJSON() {
		JsonObjectBuilder builder = Json.createObjectBuilder().add("id", getId()).add("apelido", getApelido())
				.add("login", getLogin()).add("senha", getSenha()).add("latitude", getLatitude())
				.add("longitude", getLongitude()).add("facebook", getFacebook().ordinal());
		JsonArrayBuilder jogadoresJson = Json.createArrayBuilder();
		for (TipoUsuario tipoUsuario : stringParaTipoUsuario()) {
			jogadoresJson.add(Json.createObjectBuilder().add("id", tipoUsuario.ordinal()));

		}
		builder.add("posicao", jogadoresJson);
		return builder.build();
	}

	public Usuario toUsuario(JsonObject jsonObject) {
		Usuario usuario = new Usuario();
		usuario.setId(Long.valueOf(jsonObject.get("id").toString()));
		usuario.setApelido(jsonObject.getString("apelido"));
		usuario.setFacebook(jsonObject.getInt("facebook") == 1 ? SimNao.SIM : SimNao.NAO);
		usuario.setLatitude(Double.valueOf(jsonObject.getString("latitude")));
		usuario.setLongitude(Double.valueOf(jsonObject.getString("longitude")));
		usuario.setLogin(jsonObject.getString("login"));
		usuario.setSenha(jsonObject.getString("senha"));
		if(!jsonObject.getString("posicao").isEmpty()){
		}
		usuario.setPosicao(posicao);
		return null;

	}

}
