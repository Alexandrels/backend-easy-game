/**
 * 
 */
package br.com.easygame.enuns;

/**
 * @author alexandre
 *
 */
public enum TipoPosicao {
	
	ATACANTE("Atacante"),
	GOLEIRO("Goleiro"),
	MEIO_CAMPO("Meio Campo"),
	LATERAL("Lateral"),
	VOLANTE("Volante"),
	ZAGUEIRO("Zagueiro")
	;
	
	private TipoPosicao(String descricao) {
		this.descricao = descricao;
	}
	private final String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
}
