package Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Server.Models.Institutos;

public interface InstitutosRepository extends JpaRepository<Institutos, Integer> {

	Institutos findByTitulo(String titulo);

	List<Institutos> findAllByTitulo(String titulo);
	
	Institutos findByEmail(String email);

}


