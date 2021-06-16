package br.com.fiap.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.repository.PessoaRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = PessoaRepository.class)
public class Application implements CommandLineRunner {

	@Autowired
	public PessoaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		
		repository.save(new Pessoa("Silvia", 27));
		repository.save(new Pessoa("Silvia", 22));
		
		Endereco residencial = new Endereco("Rua Jose Caetano", "SP");
		Endereco comercial = new Endereco("Avenida Silva Santos", "SP");
		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(residencial);
		enderecos.add(comercial);
		
		repository.save(new Pessoa("Paulo Silva", 45, enderecos));
		repository.save(new Pessoa("Pamela Brito", 60, enderecos));
		repository.save(new Pessoa("Julia Goncalves", 30, enderecos));
		
		Endereco rj = new Endereco("Rua João Caxias", "RJ");
		List<Endereco> ends = new ArrayList<>();
		ends.add(rj);
		
		repository.save(new Pessoa("Patricio Souza", 31, ends));
		
		// find all
		System.out.println("Documents found with findAll():");
		System.out.println("-------------------------------");
		for (Pessoa pessoa : repository.findAll()) {
			System.out.println(pessoa);
		}
		System.out.println("\n");
		
		// find all and sort by nome
		System.out.println("Documents found with findAll(Sort.by(\"nome\").ascending())):");
		System.out.println("-------------------------------");
		for (Pessoa pessoa : repository.findAll(Sort.by("nome").ascending())) {
			System.out.println(pessoa);
		}
		System.out.println("\n");
		
		// find by nome
		System.out.println("Documents found with findByNome('Silvia'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByNome("Silvia"));
		System.out.println("\n");
		
		// find by nome using like
		System.out.println("Documents found with findByNomeLike('Pa'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByNomeLike("Pa"));
		System.out.println("\n");
		
		// find by idade
		System.out.println("Documents found with findByIdade(30):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByIdade(30));
		System.out.println("\n");
		
		// find by idade between
		System.out.println("Documents found with findByIdadeBetween(25,35):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByIdadeBetween(25,35));
		System.out.println("\n");
		
		// find by regexp nome
		System.out.println("Documents found with findByRegexpNome(\"^Silv\"):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByRegexpNome("^Silv"));
		System.out.println("\n");
		
		// find by regexp nome and order by idade
		System.out.println("Documents found with findByRegexpNomeOrderByIdade('^Silv'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByRegexpNomeOrderByIdade("^Silv"));
		System.out.println("\n");

		// count by name
		System.out.println("Documents found with countByName(\"Silvia\"):");
		System.out.println("--------------------------------");
		System.out.println(repository.countByName("Silvia"));
		System.out.println("\n");
		
		// find by cidade
		System.out.println("Documents found with findByCidade(\"RJ\"):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByCidade("RJ"));
		System.out.println("\n");
	}
}
