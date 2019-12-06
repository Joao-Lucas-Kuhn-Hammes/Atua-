package Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Server.Models.Eventos;

public interface EventosRepository extends JpaRepository<Eventos, Integer> {

	Eventos findByTitulo(String titulo);
	
	Eventos findById(int Id);

	List<Eventos> findAllByTitulo(String titulo);


}
