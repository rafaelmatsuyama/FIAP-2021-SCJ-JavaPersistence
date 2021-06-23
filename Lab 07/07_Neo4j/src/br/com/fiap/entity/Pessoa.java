package br.com.fiap.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Pessoa {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;

	@SuppressWarnings("unused")
	private Pessoa() {
	};

	public Pessoa(String nome) {
		this.nome = nome;
	}

	@Relationship(type = "COLEGA", direction = Relationship.UNDIRECTED)
	public Set<Pessoa> colegas;

	public void worksWith(Pessoa pessoa) {
		if (colegas == null) {
			colegas = new HashSet<Pessoa>();
		}
		colegas.add(pessoa);
	}

	public String toString() {

		return "Colegas de " + this.nome + " => " + Optional.ofNullable(this.colegas).orElse(Collections.emptySet())
				.stream().map(Pessoa::getNome).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}