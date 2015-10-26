/**
 * 
 */
package br.com.easygame.servico;

import java.io.StringReader;

import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.easygame.conexao.ProducerEntityManager;
import br.com.easygame.dao.UsuarioDAO;

/**
 * @author Alexandre
 *
 */
@Named
@Path(value = "login")
public class LoginService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String validarLogin(String json) throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = ProducerEntityManager.getEntityManager();
			entityManager.getTransaction().begin();
			UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			JsonObject jsonObject = jsonReader.readObject();
			String login = jsonObject.getString("login");
			String senha = jsonObject.getString("senha");
			if (login != null && senha != null) {
				boolean autenticar = usuarioDAO.autenticar(login, senha);
				return Json.createObjectBuilder().add("retorno", autenticar).build().toString();
			}

		} catch (Exception e) {
			e.getCause();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return Json.createObjectBuilder().add("erro", "Não conseguiu autenticar no banco").build().toString();
	}

}
