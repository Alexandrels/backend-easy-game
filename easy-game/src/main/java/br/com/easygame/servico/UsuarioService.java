/**
 * 
 */
package br.com.easygame.servico;

import java.io.StringReader;
import java.util.List;

import javax.inject.Inject;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

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

	@Inject
	private UsuarioDAO usuarioDAO;

	/**
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarUsuario(JsonObject json) throws Exception {
		Response response = null;
		try {
			// JsonReader jsonReader = Json.createReader(new
			// StringReader(json));
			// JsonObject jsonObject = jsonReader.readObject();
			String login = json.getString("login");
			String senha = json.getString("senha");
			String latitude = json.getString("latitude");
			String longitude = json.getString("longitude");
			Integer facebook = Integer.valueOf(json.getString("facebook"));
			Integer tipo = Integer.valueOf(json.getString("tipoUsuario"));
			String apelido = json.getString("apelido");
			String posicao = json.getString("posição");
			if (login != null && senha != null) {
				Usuario usuario = new Usuario();
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setLatitude(Double.parseDouble(latitude));
				usuario.setLongitude(Double.parseDouble(longitude));
				usuarioDAO.salvar(usuario);

				response = Response.status(Response.Status.CREATED).entity("jogador salvo com sucesso!")
						.location(UriBuilder.fromUri("dsds").build(usuario.getId())).build();
				// localhost:8080/easy-game/equipe/19
			}

		} catch (Exception e) {
			e.getCause();
		}
		return response;
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
			List<Usuario> usuarios = usuarioDAO.listarTodos();
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
