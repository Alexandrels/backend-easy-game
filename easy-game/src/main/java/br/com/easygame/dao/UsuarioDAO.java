/**
 * 
 */
package br.com.easygame.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.easygame.conexao.ProducerEntityManager;
import br.com.easygame.entity.Usuario;

/**
 * @author mobilesys.alexandre
 * 
 */
public class UsuarioDAO {

	EntityManager entityManager;

	public UsuarioDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public UsuarioDAO() {
		
	}

	//um detalhe que esqueci, sempre que usar um EntityManager, depois de utilizar ele SEMPRE tem que
	//fechar ele, se não fechar ele fica mantendo uma conexão com o banco preza, então o correto é fazer
	//do jeito que vou fazer aqui
	public void salvar(Usuario usuario) {
		//cria um entityManager
		//é por isso que aqui não funciona pq ele tenta pegar um outro entityManager que só funciona no servidor
		entityManager = ProducerEntityManager.getEntityManager();//aqui é o problema daquela classe teste
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);//usa o entityManager pra fazer a operação no banco
		//fecha o entityManager pra não prender conexão
		//commit
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}
	public void editar(Usuario usuario){
		entityManager = ProducerEntityManager.getEntityManager();//aqui é o problema daquela classe teste
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);//usa o entityManager pra fazer a operação no banco
		//fecha o entityManager pra não prender conexão
		//commit
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}

	public boolean autenticar(String login, String senha) {
		try {
			//cria um entityManager
			this.entityManager = ProducerEntityManager.getEntityManager();
			StringBuilder builder = new StringBuilder("SELECT u FROM Usuario u ")
					.append(" WHERE u.login = :login ")
					.append(" AND u.senha = :senha ");
			//usa o entityManager
			entityManager.createQuery(builder.toString(), Usuario.class)
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			//fecha o entityManager
			if (this.entityManager != null) {
				this.entityManager.close();
			}
		}
	}
	//aqui um exemplo de como listar todos os usuarios
	public List<Usuario> listar() {
		try {
			//cria um entityManager
			this.entityManager = ProducerEntityManager.getEntityManager();
			StringBuilder builder = new StringBuilder("SELECT u FROM Usuario u ");
			//usa o entityManager
			return entityManager.createQuery(builder.toString(), Usuario.class)
					.getResultList();
		} catch (Exception e) {
			return new ArrayList<Usuario>();
		} finally {
			//fecha o entityManager
			if (this.entityManager != null) {
				this.entityManager.close();
			}
		}
	}
}
