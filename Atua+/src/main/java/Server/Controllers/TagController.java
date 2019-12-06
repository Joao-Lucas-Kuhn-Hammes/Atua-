package Server.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Server.Models.Eventos;
import Server.Models.Tag;
import Server.Repository.TagRepository;
@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired
	private TagRepository tag;

	@GetMapping("{titulo}")
	public Tag getTag(@PathVariable(name = "nome") String nome) {
		return tag.findByNome(nome);
	}

	@GetMapping
	public ResponseEntity<List<Tag>> listarTags() {
		return ResponseEntity.ok(tag.findAll());
	}

	@GetMapping("/nome")
	public List<Tag> listarInstitutos(@RequestParam(name = "nome") String nome) {
		return tag.findAllByNome(nome);
	}

	@PostMapping
	public ResponseEntity<Tag> adicionar(@RequestBody Tag novo, @RequestBody Eventos nov) {
		novo.setEventos(nov);
		return ResponseEntity.ok(tag.save(novo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		if (tag.existsById(id)) {
			tag.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
/*
	@PutMapping("/{id}")
	public ResponseEntity<Eventos> atualizar(@PathVariable Integer id, @RequestBody Eventos novo) {
		if (eventos.existsById(id)) {
			Eventos old = eventos.getOne(id);

			old.setAno(novo.get);
			old.setTitulo(novo.getTitulo());
			old.setGenero(novo.getGenero());

			old = filmes.save(old);

			return ResponseEntity.ok(old);
		}
		return ResponseEntity.notFound().build();
	}
	*/
}