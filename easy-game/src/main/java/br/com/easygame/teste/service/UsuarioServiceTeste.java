/**
 * 
 */
package br.com.easygame.teste.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easygame.dao.EquipeDAO;
import br.com.easygame.dao.JogadorDAO;
import br.com.easygame.entity.Jogador;

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
		System.out.println("Jogador "+jogador.toString());

	}
	
	@Test
	public void salvarUsuarioTecnico() {
		
		
	}

}
