package br.com.easygame.teste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.easygame.entity.Jogador;
import br.com.easygame.entity.Usuario;

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

		String strObjeto = Json.createObjectBuilder().add("array",
				Json.createObjectBuilder().add("objeto", "conteudo").build().toString()).build().toString();
		System.out.println(strObjeto);
		JsonReader jsonReader = Json.createReader(new StringReader(strObjeto));
		JsonObject jsonObject = jsonReader.readObject();
		JsonValue jsonValue = jsonObject.get("array");
		System.out.println(jsonValue);
		
		
		

//		entityManager.getTransaction().commit();
//		entityManager.close();
	}

	// public static void testeArquivo() throws FileNotFoundException,
	// IOException {
	// StringBuffer carga = new StringBuffer("Geraldo da Silva Vieira\n");
	// for (int i = 0; i < 1000; i++) {
	// carga.append("Geraldo da Silva Vieira\n");
	// }
	//
	// File file = new File("C:\\Users\\Geraldo\\Downloads\\teste.txt.gz");
	//
	// byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));
	// IOUtils.write(bytes, new FileOutputStream(new File(file.getParentFile(),
	// "ttt.txt.gz")));
	//
	// file = new File(file.getParentFile(), "ttt.txt.gz");
	//
	// Scanner scanner = new Scanner(new DataInputStream(new GZIPInputStream(new
	// FileInputStream(file))), "UTF-8");
	// while (scanner.hasNext()) {
	// String linha = scanner.nextLine();
	// System.out.println(linha);
	// }
	// scanner.close();
	// }
	//
	// public static String compress(String str) throws IOException {
	// if (str == null || str.length() == 0) {
	// return str;
	// }
	// System.out.println("String length : " + str.length());
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// GZIPOutputStream gzip = new GZIPOutputStream(out);
	// gzip.write(str.getBytes());
	// gzip.close();
	// String outStr = out.toString("ISO-8859-1");
	// System.out.println("Output String lenght : " + outStr.length());
	// return outStr;
	// }
	//
	// public static String decompress(String str) throws IOException {
	// if (str == null || str.length() == 0) {
	// return str;
	// }
	// System.out.println("Input String length : " + str.length());
	// GZIPInputStream gis = new GZIPInputStream(new
	// ByteArrayInputStream(str.getBytes("ISO-8859-1")));
	// BufferedReader bf = new BufferedReader(new InputStreamReader(gis,
	// "ISO-8859-1"));
	// String outStr = "";
	// String line;
	// while ((line = bf.readLine()) != null) {
	// outStr += line;
	// }
	// System.out.println("Output String lenght : " + outStr.length());
	// return outStr;
	// }
}
