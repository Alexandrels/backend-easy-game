package br.com.easygame.teste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import br.com.easygame.enuns.TipoUsuario;

public class Teste {

	/**
	 * @param args
	 * @throws PortalException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		EntityManager entityManager = Persistence.createEntityManagerFactory("easy-game-local").createEntityManager();
//		entityManager.getTransaction().begin();


		// Jogador jogador = new Jogador();
		// jogador.setNome("Alexandre");
		// jogador.setPosicao("Meio");
		// jogador.setEndereco("Rua Princesa Isabel, 5");
		// jogador.setTelefone("4199221257");
		// entityManager.persist(jogador);

//		String strObjeto = Json.createObjectBuilder().add("array",
//				Json.createObjectBuilder().add("objeto", "conteudo").build().toString()).build().toString();
//		System.out.println(strObjeto);
//		JsonReader jsonReader = Json.createReader(new StringReader(strObjeto));
//		JsonObject jsonObject = jsonReader.readObject();
//		JsonValue jsonValue = jsonObject.get("array");
//		System.out.println(jsonValue);
		List<TipoUsuario> tipos = new ArrayList<>(Arrays.asList(TipoUsuario.values()));
		salvarTipoUsuario(tipos);
		
		tipos(salvarTipoUsuario(tipos));
		
		
		

//		entityManager.getTransaction().commit();
//		entityManager.close();
	}
	public static String salvarTipoUsuario(List<TipoUsuario> tiposUsuario) {
		StringBuilder builder = new StringBuilder();
		for (TipoUsuario tipoUsuario : tiposUsuario) {
			builder.append(tipoUsuario.ordinal()).append(";");
		}
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	public static List<TipoUsuario> tipos(String tipos){
		List<TipoUsuario> tiposUsuarios = new ArrayList<>();
		String[] tipo = tipos.split(";");
		for (String string : tipo) {
			tiposUsuarios.add(TipoUsuario.values()[Integer.valueOf(string)]);
		}
		for (TipoUsuario tipoUsuario : tiposUsuarios) {
			System.out.println(tipoUsuario.getDescricao());
		}
		return tiposUsuarios;
		
	}
}
