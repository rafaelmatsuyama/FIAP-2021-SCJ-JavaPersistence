package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Funcionario;

public class Helper {

	private EntityManager em;

	public Helper(EntityManager em) {
		this.em = em;
	}

	public void salvar(Funcionario funcionario) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}

	// JPQL: Usando Query
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFuncionarios() {
		Query query = em.createQuery("select f from Funcionario f");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Funcionario buscarFuncionario(String numMatricula) {
		Query query = em.createQuery("select f from Funcionario f where matricula = :matricula");
		query.setParameter("matricula", numMatricula);
		Funcionario f = (Funcionario) query.getSingleResult();
		return f;
	}

	// JPQL: usando NamedQuery
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodos() {
		Query query = em.createNamedQuery("Funcionario.findAll");
		return query.getResultList();
	}
}
