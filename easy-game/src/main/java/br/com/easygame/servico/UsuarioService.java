/**
 * 
 */
package br.com.easygame.servico;

import java.io.StringReader;
import java.util.List;

import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.easygame.conexao.ProducerEntityManager;
import br.com.easygame.dao.UsuarioDAO;
import br.com.easygame.entity.Usuario;

/**
 * @author Alexandre
 *
 */
@Named
@Path(value = "usuario")
public class UsuarioService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String cadastrarJogador(String json) throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = ProducerEntityManager.getEntityManager();
			entityManager.getTransaction().begin();
			UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			JsonObject jsonObject = jsonReader.readObject();
			String login = jsonObject.getString("login");
			String senha = jsonObject.getString("senha");
			String latitude = jsonObject.getString("latitude");
			String longitude = jsonObject.getString("longitude");
			if (login != null && senha != null) {
				Usuario usuario = new Usuario();
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setLatitude(Double.parseDouble(latitude));
				usuario.setLongitude(Double.parseDouble(longitude));
				usuarioDAO.salvar(usuario);
				entityManager.getTransaction().commit();
				return Json.createObjectBuilder().add("sucesso", usuario.toString()).build().toString();
			}

		} catch (Exception e) {
			e.getCause();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return Json.createObjectBuilder().add("erro", "Não salvou o usuario").build().toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarUsuarios() {
		EntityManager entityManager = null;
		try {
			entityManager = ProducerEntityManager.getEntityManager();
			entityManager.getTransaction().begin();
			// aqui um exemplo de como retornar todos os usuarios com JSON
			UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);
			List<Usuario> usuarios = usuarioDAO.listar();
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (Usuario usuario : usuarios) {
				arrayBuilder.add(Json.createObjectBuilder().add("id", usuario.getId().toString())
						.add("login", usuario.getLogin()).add("senha", usuario.getSenha())
						.add("latitude", usuario.getLatitude()).add("longitude", usuario.getLongitude()));

			}
			return arrayBuilder.build().toString();

		} catch (Exception e) {
			e.getCause();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return "não listou";
	}

}
