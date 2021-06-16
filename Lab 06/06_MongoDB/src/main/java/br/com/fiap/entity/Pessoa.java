package br.com.fiap.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pessoa {
	
	@Id
    private String id;
    private String nome;
    private int idade;
    private List<Endereco> endereco;
 
    public Pessoa() {}
    
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public Pessoa(String nome, int idade, List<Endereco> endereco) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
	}

	@Override
    public String toString() {
    	return "\nNome: " + nome + " - idade: " + idade + "\nEndereco: " + endereco;
    }

	public List<Endereco> getEndereco() {
		return endereco;
	}
	
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setEmail(int idade) {
		this.idade = idade;
	}

}
