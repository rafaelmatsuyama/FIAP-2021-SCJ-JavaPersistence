package br.com.fiap.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import br.com.fiap.entity.Pessoa;
import br.com.fiap.repository.PessoaRepository;

@SpringBootApplication
@EntityScan("br.com.fiap.entity.Pessoa")
@EnableNeo4jRepositories(basePackageClasses = PessoaRepository.class)
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner demo(final PessoaRepository pessoaRepository) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {

				pessoaRepository.deleteAll();

				Pessoa p1 = new Pessoa("Rafael");
				Pessoa p2 = new Pessoa("Zangief");
				Pessoa p3 = new Pessoa("Blanka");

				List<Pessoa> time = Arrays.asList(p1, p2, p3);

				time.stream().forEach(new Consumer<Pessoa>() {
					public void accept(Pessoa pessoa) {
						System.out.println("\t" + pessoa.toString());
					}
				});

				pessoaRepository.save(p1);
				pessoaRepository.save(p2);
				pessoaRepository.save(p3);
				

				p1 = pessoaRepository.findByNome(p1.getNome());
				p1.worksWith(p2);
				p1.worksWith(p3);
				pessoaRepository.save(p1);

				p2 = pessoaRepository.findByNome(p2.getNome());
				p2.worksWith(p3);
				// Não é necessário ligar p1 com p2, pois já sabemos disso na relação anterior.
				pessoaRepository.save(p2);

				time.stream().forEach(new Consumer<Pessoa>() {
					public void accept(Pessoa pessoa) {
						System.out.println(
								"\t" + pessoaRepository.findByNome(pessoa.getNome()).toString());
					}
				});
			}
		};
	}

}