package Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Server.Models.Patrocinador;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {

	Patrocinador findByNome(String nome);

	List<Patrocinador> findAllByNome(String nome);

	Patrocinador findByEmail(String email);
	


}
