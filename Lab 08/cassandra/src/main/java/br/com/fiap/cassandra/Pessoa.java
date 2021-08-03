package br.com.fiap.cassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Pessoa {

  @PrimaryKey
  private final String id;

  private final String nome;
  private final int idade;

  public Pessoa(String id, String nome, int idade) {
    this.id = id;
    this.nome = nome;
    this.idade = idade;
  }

  public String getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public int getIdade() {
    return idade;
  }

  @Override
  public String toString() {
    return String.format("{ @type = %1$s, id = %2$s, nome = %3$s, idade = %4$d }",
      getClass().getName(), getId(), getNome(), getIdade());
  }
}