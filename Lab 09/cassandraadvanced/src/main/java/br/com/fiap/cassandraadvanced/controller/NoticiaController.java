package br.com.fiap.cassandraadvanced.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;

import br.com.fiap.cassandraadvanced.repository.NoticiaRepository;
import br.com.fiap.cassandraadvanced.model.Noticia;

@RestController
@RequestMapping("/api")
public class NoticiaController {
	@Autowired
	NoticiaRepository noticiaRepository;

	@GetMapping("/noticias")
	public ResponseEntity<List<Noticia>> getAllNoticias(@RequestParam(required = false) String titulo) {
		try {
			List<Noticia> noticias = new ArrayList<Noticia>();

			if (titulo == null)
				noticiaRepository.findAll().forEach(noticias::add);
			else
				noticiaRepository.findByTitulo(titulo).forEach(noticias::add);

			if (noticias.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(noticias, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/noticias/{id}")
	public ResponseEntity<Noticia> getNoticiaById(@PathVariable("id") UUID id) {
		Optional<Noticia> noticiaData = noticiaRepository.findById(id);

		if (noticiaData.isPresent()) {
			return new ResponseEntity<>(noticiaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/noticias")
	public ResponseEntity<Noticia> createNoticia(@RequestBody Noticia noticia) {
		try {
			Noticia _noticia = noticiaRepository
					.save(new Noticia(UUIDs.timeBased(), noticia.getTitulo(), noticia.getDescricao()));
			return new ResponseEntity<>(_noticia, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/noticias/{id}")
	public ResponseEntity<Noticia> updateTutorial(@PathVariable("id") UUID id, @RequestBody Noticia noticia) {
		Optional<Noticia> noticiaData = noticiaRepository.findById(id);

		if (noticiaData.isPresent()) {
			Noticia _noticia = noticiaData.get();
			_noticia.setTitulo(noticia.getTitulo());
			_noticia.setDescricao(noticia.getDescricao());
			return new ResponseEntity<>(noticiaRepository.save(_noticia), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/noticias/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") UUID id) {
		try {
			noticiaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/noticias")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			noticiaRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
