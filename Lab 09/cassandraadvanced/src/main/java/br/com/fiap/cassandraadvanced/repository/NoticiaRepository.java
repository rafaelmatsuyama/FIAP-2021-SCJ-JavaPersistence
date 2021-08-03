package br.com.fiap.cassandraadvanced.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import br.com.fiap.cassandraadvanced.model.Noticia;

public interface NoticiaRepository extends CassandraRepository<Noticia, UUID> {
	  @AllowFiltering
	  List<Noticia> findByTitulo(String titulo);
}
