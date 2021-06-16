package br.com.fiap.entity;

import org.springframework.data.annotation.Id;

public class Endereco {
	
	@Id
	private String id;
	private String rua;
	private String cidade;
	
	@Override
	public String toString() {
		return rua + " - cidade: " + cidade;
	}
	
	public Endereco() {
		super();
	}
	public Endereco(String rua, String cidade) {
		super();
		this.rua = rua;
		this.cidade = cidade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
