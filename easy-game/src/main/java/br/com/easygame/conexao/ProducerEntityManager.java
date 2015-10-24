/**
 *
 */
package br.com.easygame.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author mobilesys.alexandre
 *
 */
public class ProducerEntityManager {

	/**
	 * EntitymanagerFactory
	 */
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager getEntityManager(){
		if(entityManagerFactory == null){
			//ele vai pegar o outro entityManager que não funciona pra teste local, só funciona no servidor
			entityManagerFactory = Persistence.createEntityManagerFactory("easy-game-local");
		}
		return entityManagerFactory.createEntityManager();
	}

}
