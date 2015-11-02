/**
 * 
 */
package br.com.easygame.teste.service;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easygame.dao.EquipeDAO;
import br.com.easygame.dao.JogadorDAO;
import br.com.easygame.entity.Jogador;
import br.com.easygame.entity.Usuario;
import br.com.easygame.enuns.SimNao;
import br.com.easygame.enuns.TipoPosicao;
import br.com.easygame.enuns.TipoUsuario;
import br.com.easygame.servico.UsuarioService;

/**
 * @author alexandre
 *
 */
public class UsuarioServiceTeste {

	private EntityManager entityManager;
	private EquipeDAO equipeDAO;
	private JogadorDAO jogadorDAO;
	private int cont = 0;

	@Before
	public void antes() {
		entityManager = Persistence.createEntityManagerFactory("easy-game-local").createEntityManager();
		entityManager.getTransaction().begin();

		equipeDAO = new EquipeDAO(entityManager);
		jogadorDAO = new JogadorDAO(entityManager);
	}

	@After
	public void depois() {
		entityManager.getTransaction().commit();
		// entityManager.getTransaction().rollback();
		entityManager.close();
	}

	@Test
	public void editarJogador() {
		Jogador jogador = new Jogador(1l);
		System.out.println("Jogador " + jogador.toString());

	}

	@Test
	public void cadastrarUsuarioTecnico() throws Exception {
		UsuarioService service = new UsuarioService();
		Usuario usuario = new Usuario();
		usuario.setNome("Murici");
		usuario.setApelido("Murici");
		usuario.setFacebook(SimNao.NAO);
		usuario.setLogin("pepe");
		usuario.setTipoPosicao(TipoPosicao.EXTRA_CAMPO);
		usuario.setSenha("1");
		usuario.salvarTipoUsuario(Arrays.asList(TipoUsuario.TECNICO));

		Response response = service.cadastrarUsuario(usuario.toJSON());

		System.out.println("Response: " + response);

	}

}
