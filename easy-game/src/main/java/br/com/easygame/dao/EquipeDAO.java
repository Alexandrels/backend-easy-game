/**
 * 
 */
package br.com.easygame.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.easygame.conexao.ProducerEntityManager;
import br.com.easygame.entity.Equipe;

/**
 * @author mobilesys.alexandre
 * 
 */
public class EquipeDAO {

	EntityManager entityManager;

	public EquipeDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EquipeDAO() {

	}

	// um detalhe que esqueci, sempre que usar um EntityManager, depois de
	// utilizar ele SEMPRE tem que
	// fechar ele, se não fechar ele fica mantendo uma conexão com o banco
	// preza, então o correto é fazer
	// do jeito que vou fazer aqui
	public void salvar(Equipe equipe) {
		// cria um entityManager
		// é por isso que aqui não funciona pq ele tenta pegar um outro
		// entityManager que só funciona no servidor
		entityManager = ProducerEntityManager.getEntityManager();// aqui é o
																	// problema
																	// daquela
																	// classe
																	// teste
		entityManager.getTransaction().begin();
		entityManager.persist(equipe);// usa o entityManager pra fazer a
										// operação no banco
		// fecha o entityManager pra não prender conexão
		// commit
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}

	public void editar(Equipe equipe) {
		entityManager = ProducerEntityManager.getEntityManager();// aqui é o
																	// problema
																	// daquela
																	// classe
																	// teste
		entityManager.getTransaction().begin();
		entityManager.merge(equipe);// usa o entityManager pra fazer a operação
									// no banco
		// fecha o entityManager pra não prender conexão
		// commit
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}

	// aqui um exemplo de como listar todos os usuarios
	public List<Equipe> listar() {
		try {
			// cria um entityManager
			this.entityManager = ProducerEntityManager.getEntityManager();
			StringBuilder builder = new StringBuilder("SELECT u FROM Equipe u ");
			// usa o entityManager
			return entityManager.createQuery(builder.toString(), Equipe.class).getResultList();
		} catch (Exception e) {
			return new ArrayList<Equipe>();
		} finally {
			// fecha o entityManager
			if (this.entityManager != null) {
				this.entityManager.close();
			}
		}
	}

	public Equipe pesquisarPorId(Long id) {
		return entityManager.find(Equipe.class, id);
	}
}
