package es.github.asper1122.cursonet_api.service.impl;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.error.AlreadyExistsException;
import es.github.asper1122.cursonet_api.mapper.CursoMapper;
import es.github.asper1122.cursonet_api.model.Curso;
import es.github.asper1122.cursonet_api.repository.CursoRepository;
import es.github.asper1122.cursonet_api.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    @Transactional
    public CursoDetailsResponseDto create(CreateCursoDto createCursoDto) {

        validateCurso(createCursoDto);

        Curso cursoSaved = cursoRepository.save(CursoMapper.INSTANCE.toEntity(createCursoDto));

        return CursoMapper.INSTANCE.toDetailsResponseDto(cursoSaved);
    }

    private void validateCurso(CreateCursoDto createCursoDto) {
        if(cursoRepository.existsByNombre(createCursoDto.nombre())) {
            throw new AlreadyExistsException("Ya existe el curso con nombre: " + createCursoDto.nombre());
        }
    }
}
