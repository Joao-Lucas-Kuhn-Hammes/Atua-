package Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Server.Models.Tag;


public interface TagRepository extends JpaRepository<Tag, Integer> {

	Tag findByNome(String nome);

	List<Tag> findAllByNome(String nome);

}

