package br.com.fiap.helper;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Participante;

public class EventoHelper {
	private EntityManager em;

	public EventoHelper(EntityManager em) {
		this.em = em;
	}

	public String salvar(Evento evento) {
		try {
			em.getTransaction().begin();
			em.persist(evento);
			em.getTransaction().commit();
			return "Evento incluído com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String adicionarParticipante(int idEvento, Participante participante) {
		try {
			Evento evento = em.find(Evento.class, idEvento);
			participante.setEvento(evento);
			evento.getParticipantes().add(participante);
			em.getTransaction().begin();
			em.persist(evento);
			em.getTransaction().commit();
			return "Evento atualizado com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public List<Evento> listarEventos() {
		TypedQuery<Evento> query = em.createQuery("Select e from Evento e", Evento.class);
		return query.getResultList();
	}

	public List<Participante> listarParticipantes(int idEvento) {
		TypedQuery<Participante> query = em.createQuery("Select p from Participante p Where p.evento.id = :idevento",
				Participante.class);
		query.setParameter("idevento", idEvento);
		return query.getResultList();
	}
}