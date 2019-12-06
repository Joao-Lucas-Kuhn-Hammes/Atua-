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

import Server.Models.Patrocinador;
import Server.Repository.PatrocinadorRepository;
import ch.qos.logback.classic.net.SyslogAppender;
@CrossOrigin
@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorController {
	@Autowired
	private PatrocinadorRepository pat;

	@GetMapping("/{nome}")
	public Patrocinador getPatrocinador(@PathVariable(name = "nome") String nome) {
		return pat.findByNome(nome);
	}

	@GetMapping
	public ResponseEntity<List<Patrocinador>> listarPatrocinadores() {
		return ResponseEntity.ok(pat.findAll());
	}
	
	@GetMapping("/{email}/{senha}")
	public Patrocinador login(@PathVariable(name = "email") String email, @PathVariable(name = "senha") String senha){
		Patrocinador p;
		try {
			p = pat.findByEmail(email);
			if(p.getSenha().equals(senha)) {
				return p;
			}else {
				return new Patrocinador();
			}
		}catch(NullPointerException e) {
			return new Patrocinador();
		}
		
		
	}
	@GetMapping("/nome")
	public List<Patrocinador> listarPatrocinadores(@RequestParam(name = "nome") String nome) {
		return pat.findAllByNome(nome);
	}

	@PostMapping
	public ResponseEntity<Patrocinador> adicionar(@RequestBody Patrocinador novo) {
		return ResponseEntity.ok(pat.save(novo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		if (pat.existsById(id)) {
			pat.deleteById(id);
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
