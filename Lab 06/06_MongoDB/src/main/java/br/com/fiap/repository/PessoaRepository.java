package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.fiap.entity.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
	
//	MAPEAMENTO DE FUNÇÕES QUE O MONGO REPOSITORY JÁ TEM COMO PADRÃO
//	List saveAll(List pessoas);
//
//	List findAll();
//
//	List findAll(Sort sort);
//
//	Optional findById(String id);
//
//	Pessoa insert(Pessoa pessoa);
//
//	List insert(List pessoas);
//
//	List findAll(Example example);
//
//  List findAll(Example example, Sort sort);
//
//	void delete(Pessoa pessoa);
//
//	void deleteById(String id);
//
//	void deleteAll(List pessoas);
	
	

	public List<Pessoa> findByNome(String nome);

	public List<Pessoa> findByNomeLike(String nome);

	@Query("{ 'idade' : ?0 }")
	public List<Pessoa> findByIdade(int idade);

	@Query("{ 'idade' : { $gt: ?0, $lt: ?1 } }")
	public List<Pessoa> findByIdadeBetween(int min, int max);

	@Query("{ 'nome' : { $regex: ?0 } }")
	public List<Pessoa> findByRegexpNome(String regexp);

	@Query(value = "{ 'nome': { $regex: ?0 } }", sort = "{ 'idade': 1 }") // 1: crescente, -1: descrescente
	public List<Pessoa> findByRegexpNomeOrderByIdade(String nome);

	@Query(value = "{ 'nome': ?0 }", count = true)
	public int countByName(String nome);
	
	@Query(value = "{ }", count = true)
	public int countAll(String nome);
	
	@Query("{ 'endereco.cidade' : '?0' }")
	public List<Pessoa> findByCidade(String cidade);
	
}
