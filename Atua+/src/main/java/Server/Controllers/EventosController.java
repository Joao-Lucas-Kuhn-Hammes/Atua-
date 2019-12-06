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
import Server.Models.Patrocinador;
import Server.Models.Tag;
import Server.Repository.EventosRepository;

@CrossOrigin
@RestController
@RequestMapping("/eventos")
public class EventosController {
	@Autowired
	private EventosRepository eventos;

	@GetMapping("{titulo}")
	public Eventos getEvento(@PathVariable(name = "titulo") String titulo) {
		return eventos.findByTitulo(titulo);
	}

	@GetMapping
	public ResponseEntity<List<Eventos>> listarEventos() {
		List<Eventos> evs = eventos.findAll();
		for(Eventos e : evs) {
			if(e.getInstituto()!=null)
				e.getInstituto().setEventos(null);
		}
		return ResponseEntity.ok(evs);
	}

	@GetMapping("/titulo")
	public List<Eventos> listarEventos(@RequestParam(name = "titulo") String titulo) {
		return eventos.findAllByTitulo(titulo);
	}
	@GetMapping("/{id}")
	public Eventos getEventos(@RequestParam(name = "id") int id) {
		return eventos.findById(id);
	}

	@PostMapping
	public ResponseEntity<Eventos> adicionar(@RequestBody Eventos novo) {
		return ResponseEntity.ok(eventos.save(novo));
	}
	
	@PostMapping("/patrocinador/{id}")
	public ResponseEntity<Eventos> adicionarPatrocinador(@RequestParam(name = "id") int id, @RequestBody Patrocinador sla){
		Eventos aux = eventos.findById(id);
		aux.setPatrocinador(sla);
		aux = eventos.save(aux);
		return ResponseEntity.ok(aux);
	}
	
	@PostMapping("/tag/{id}")
	public ResponseEntity<Eventos> adicionarTag(@RequestParam(name = "id") int id, @RequestBody Tag sla){
		Eventos aux = eventos.findById(id);
		aux.setTag(sla);
		aux = eventos.save(aux);
		return ResponseEntity.ok(aux);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		if (eventos.existsById(id)) {
			eventos.deleteById(id);
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
