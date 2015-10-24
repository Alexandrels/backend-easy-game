/**
 * 
 */
package br.com.easygame.enuns;

/**
 * @author alexandre
 *
 */
public enum TipoUsuario {
	
	USUARIO_ADMINISTRADOR("Administrador Time"),
	USUARIO_COMUM("Usuario Comum"),
	USUARIO_QUADRA("Usuario Comum"),
	USUARIO_VENDEDOR("Usuario Comum");
	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}
	private final String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
}
