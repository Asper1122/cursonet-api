package es.github.asper1122.cursonet_api.service.impl;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoResponseDto;
import es.github.asper1122.cursonet_api.error.AlreadyExistsException;
import es.github.asper1122.cursonet_api.error.NotFoundException;
import es.github.asper1122.cursonet_api.mapper.CursoMapper;
import es.github.asper1122.cursonet_api.model.Curso;
import es.github.asper1122.cursonet_api.repository.CursoRepository;
import es.github.asper1122.cursonet_api.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static es.github.asper1122.cursonet_api.utils.Utils.CURSO_ALREADY_EXISTS_MESSAGE;
import static es.github.asper1122.cursonet_api.utils.Utils.CURSO_NOT_FOUND_MESSAGE;

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

    @Override
    public Page<CursoResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return cursoRepository.findAll(pageable)
                .map(CursoMapper.INSTANCE::toResponseDto);
    }

    @Override
    public CursoDetailsResponseDto findById(UUID id) {
        return cursoRepository.findById(id)
                .map(CursoMapper.INSTANCE::toDetailsResponseDto)
                .orElseThrow(() -> new NotFoundException(CURSO_NOT_FOUND_MESSAGE + id));
    }

    @Override
    @Transactional
    public CursoDetailsResponseDto update(UUID id, CreateCursoDto createCursoDto) {
        return cursoRepository.findById(id)
                .map(existing -> {
                    if(!createCursoDto.nombre().equals(existing.getNombre()) &&
                            cursoRepository.existsByNombre(createCursoDto.nombre())) {
                        throw new AlreadyExistsException(CURSO_ALREADY_EXISTS_MESSAGE + createCursoDto.nombre());
                    }

                    Curso updated = CursoMapper.INSTANCE.toEntity(createCursoDto);
                    updated.setId(existing.getId());
                    return cursoRepository.save(updated);
                })
                .map(CursoMapper.INSTANCE::toDetailsResponseDto)
                .orElseThrow(() -> new NotFoundException(CURSO_NOT_FOUND_MESSAGE + id));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        cursoRepository.deleteById(id);
    }

    private void validateCurso(CreateCursoDto createCursoDto) {
        if(cursoRepository.existsByNombre(createCursoDto.nombre())) {
            throw new AlreadyExistsException(CURSO_ALREADY_EXISTS_MESSAGE + createCursoDto.nombre());
        }
    }
}
