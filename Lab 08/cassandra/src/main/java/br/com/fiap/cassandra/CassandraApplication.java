package br.com.fiap.cassandra;

import java.util.UUID;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraApplication {

  public static void main(String[] args) {

    Cluster cluster = Cluster.builder().addContactPoints("localhost").withoutMetrics().build();
    Session session = cluster.connect("test");

    CassandraOperations template = new CassandraTemplate(session);

    Pessoa joao = template.insert(new Pessoa(UUID.randomUUID().toString(), "Joao Silva", 42));
    
    System.out.println("\nQuantidade de itens na table: " + template.count(Pessoa.class));
    
    System.out.println(template.exists(Query.query(Criteria.where("nome").is("Joao Silva")).withAllowFiltering(), Pessoa.class));

    System.out.println(template.selectOne(Query.query(Criteria.where("id").is(joao.getId())), Pessoa.class));
    
    session.close();
    cluster.close();
  }
}