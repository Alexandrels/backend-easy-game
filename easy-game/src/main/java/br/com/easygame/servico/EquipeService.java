/**
 * 
 */
package br.com.easygame.servico;

import java.util.List;

import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.easygame.conexao.ProducerEntityManager;
import br.com.easygame.dao.EquipeDAO;
import br.com.easygame.dao.UsuarioDAO;
import br.com.easygame.entity.Equipe;
import br.com.easygame.entity.Usuario;

/**
 * @author Alexandre
 *
 */
@Named
@Path(value = "equipe")
public class EquipeService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String cadastrarEquipe(String json) throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = ProducerEntityManager.getEntityManager();
			entityManager.getTransaction().begin();
			EquipeDAO equipeDAO = new EquipeDAO(entityManager);
			Equipe equipeJson = Equipe.toEquipe(json);
			equipeDAO.salvar(equipeJson);

			return Json.createObjectBuilder().add("equipe", "salvo com sucesso").build().toString();
		} catch (Exception e) {
			e.getCause();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return Json.createObjectBuilder().add("erro", "Não salvou o equipe").build().toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarEquipes() {
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
