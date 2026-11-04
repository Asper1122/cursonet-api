package es.github.asper1122.cursonet_api.mapper;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    Curso toEntity(CreateCursoDto dto);

    CursoDetailsResponseDto toDetailsResponseDto(Curso curso);
}
