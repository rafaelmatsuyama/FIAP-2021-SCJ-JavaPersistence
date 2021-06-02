package br.com.fiap.aplicacao;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Participante;
import br.com.fiap.helper.EventoHelper;

public class AppEventos {
	public static void main(String[] args) {
		//incluirEvento();
		//listarEventos();
		listarParticipantes(1);
	}

	private static void incluirEvento() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		EventoHelper helper = new EventoHelper(em);
		Evento evento = new Evento();
		evento.setDescricao("Novo curso disponivel");
		evento.setResponsavel("Juvenal Santos");
		evento.setData(new Date());
		Participante p1 = new Participante();
		p1.setNome("Jose Antonio");
		p1.setEmail("jantonio@fiap.com.br");
		p1.setEvento(evento);
		Participante p2 = new Participante();
		p2.setNome("Camila");
		p2.setEmail("camila@fiap.com.br");
		p2.setEvento(evento);
		Participante p3 = new Participante();
		p3.setNome("Bonifacio");
		p3.setEmail("bonifacio@fiap.com.br");
		p3.setEvento(evento);
		evento.getParticipantes().add(p1);
		evento.getParticipantes().add(p2);
		evento.getParticipantes().add(p3);
		System.out.println(helper.salvar(evento));
	}

	private static void listarEventos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		EventoHelper helper = new EventoHelper(em);
		for (Evento evento : helper.listarEventos()) {
			System.out.println("Id: " + evento.getId());
			System.out.println("Descrição: " + evento.getDescricao());
			System.out.println("Responsável: " + evento.getResponsavel());
			System.out.println("-------------------------------------");
		}
	}

	private static void listarParticipantes(int idEvento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		EventoHelper helper = new EventoHelper(em);
		for (Participante participante : helper.listarParticipantes(idEvento)) {
			System.out.println("Id: " + participante.getId());
			System.out.println("Nome: " + participante.getNome());
			System.out.println("Email: " + participante.getEmail());
			System.out.println("-------------------------------------");
		}
	}
}