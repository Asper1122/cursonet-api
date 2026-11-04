package es.github.asper1122.cursonet_api.repository;

import es.github.asper1122.cursonet_api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CursoRepository extends JpaRepository<Curso, UUID> {

    boolean existsByNombre(String nombre);
}
