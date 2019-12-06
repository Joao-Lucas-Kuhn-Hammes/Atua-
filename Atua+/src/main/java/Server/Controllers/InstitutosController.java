package Server.Controllers;


	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Server.Models.Eventos;
import Server.Models.Institutos;
import Server.Models.Patrocinador;
import Server.Repository.InstitutosRepository;

@CrossOrigin
	@RestController
	@RequestMapping("/institutos")
	public class InstitutosController {
		@Autowired
		private InstitutosRepository ist;

		@GetMapping("{titulo}")
		public Institutos getInstitutos(@PathVariable(name = "titulo") String titulo) {
			System.out.println(ist.findByTitulo(titulo));
			return ist.findByTitulo(titulo);
		}
		
		@GetMapping
		public ResponseEntity<List<Institutos>> listarInstitutos() {
			return ResponseEntity.ok(ist.findAll());
		}

		@GetMapping("/titulo")
		public List<Institutos> listarInstitutos(@RequestParam(name = "titulo") String titulo) {
			return ist.findAllByTitulo(titulo);
		}

		@GetMapping("/{email}/{senha}")
		public Institutos login(@PathVariable(name = "email") String email, @PathVariable(name = "senha") String senha){
			Institutos i;
			try {
				i = ist.findByEmail(email);
				if(i.getSenha().equals(senha)) {
					return i;
				}else {
					return new Institutos();
				}
			}catch(NullPointerException e) {
				return new Institutos();
			}
			
			
		}
		
		@PostMapping
		public ResponseEntity<Institutos> adicionar(@RequestBody Institutos novo) {
			return ResponseEntity.ok(ist.save(novo));
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deletar(@PathVariable Integer id) {
			if (ist.existsById(id)) {
				ist.deleteById(id);
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



