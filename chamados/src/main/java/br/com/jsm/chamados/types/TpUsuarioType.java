package br.com.jsm.chamados.types;

/**
 * @author luiz.lima
 * @since 12/11/2019
 *
 */
public enum TpUsuarioType {
	SELECIONE(0), ADMIN(1), USUARIO(2);
	
	private final int value;
	
	TpUsuarioType(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
